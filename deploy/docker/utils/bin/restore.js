const fsPromises = require('fs/promises');
const path = require('path');
const os = require('os');
const readlineSync = require('readline-sync');

const shell = require('shelljs');

const utils = require('./utils');
const Constants = require('./constants');

async function getBackupFileName() {

  const backupFiles = await utils.listLocalBackupFiles();
  console.log("\n" + backupFiles.length + " Bizbrainz backup file(s) found: [Sorted in ascending/chronological order]");
  if (backupFiles.length == 0) {
    return;
  }
  console.log('----------------------------------------------------------------');
  console.log('Index\t|\tBizbrainz Backup Archive File');
  console.log('----------------------------------------------------------------');
  for (var i = 0; i < backupFiles.length; i++) {
    if (i === backupFiles.length - 1)
      console.log(i + '\t|\t' + backupFiles[i] + ' <--Most recent backup');
    else
      console.log(i + '\t|\t' + backupFiles[i]);
  }
  console.log('----------------------------------------------------------------');

  var backupFileIndex = parseInt(readlineSync.question('Please enter the backup file index: '), 10);
  if (!isNaN(backupFileIndex) && Number.isInteger(backupFileIndex) && (backupFileIndex >= 0) && (backupFileIndex < backupFiles.length)) {
    return backupFiles[parseInt(backupFileIndex, 10)];
  }
  else {
    console.log('Invalid input, please try the command again with a valid option');
    return;
  }

}

async function extractArchive(backupFilePath, restoreRootPath) {
  console.log('Extracting the Bizbrainz backup archive at ' + backupFilePath);
  await utils.execCommand(['tar', '-C', restoreRootPath, '-xf', backupFilePath]);
  console.log('Extracting the backup archive completed');
}

async function restoreDatabase(restoreContentsPath) {
  console.log('Restoring database  ....');
  await utils.execCommand(['mongorestore', `--uri=${process.env.BIZBRAINZ_MONGODB_URI}`, '--drop', `--archive=${restoreContentsPath}/mongodb-data.gz`, '--gzip']);
  console.log('Restoring database completed');
}

async function restoreDockerEnvFile(restoreContentsPath, backupName) {
  console.log('Restoring docker environment file');
  const dockerEnvFile = '/bizBrainz-stacks/configuration/docker.env';
  var encryptionPwd = process.env.BIZBRAINZ_ENCRYPTION_PASSWORD;
  var encryptionSalt = process.env.BIZBRAINZ_ENCRYPTION_SALT;
  await utils.execCommand(['mv', dockerEnvFile, dockerEnvFile + '.' + backupName]);
  await utils.execCommand(['cp', restoreContentsPath + '/docker.env', dockerEnvFile]);

  if (encryptionPwd && encryptionSalt) {
    const input = readlineSync.question('Existing encryption env values of the previous instance were found.\n\
    Press Enter to continue with existing encryption values\n\
    Or Type "n"/"No" to provide encryption key & password for the new restore instance.\n');
    const answer = input && input.toLocaleUpperCase();
    if (answer === 'N' || answer === 'NO') {
      encryptionPwd = readlineSync.question('Enter the BIZBRAINZ_ENCRYPTION_PASSWORD: ', {
        hideEchoBack: true
      });
      encryptionSalt = readlineSync.question('Enter the BIZBRAINZ_ENCRYPTION_SALT: ', {
        hideEchoBack: true
      });
    }
    else {
      console.log('Restoring docker environment file with existing encryption password & salt');
    }
  }
  else {
    encryptionPwd = readlineSync.question('Enter the BIZBRAINZ_ENCRYPTION_PASSWORD: ', {
      hideEchoBack: true
    });
    encryptionSalt = readlineSync.question('Enter the BIZBRAINZ_ENCRYPTION_SALT: ', {
      hideEchoBack: true
    });
  }

  await fsPromises.appendFile(dockerEnvFile, '\nBIZBRAINZ_ENCRYPTION_PASSWORD=' + encryptionPwd +
    '\nBIZBRAINZ_ENCRYPTION_SALT=' + encryptionSalt);

  console.log('Restoring docker environment file completed');
}

async function restoreGitStorageArchive(restoreContentsPath, backupName) {
  console.log('Restoring git-storage archive');
  // TODO: Consider BIZBRAINZ_GIT_ROOT env for later iterations
  const gitRoot = '/bizBrainz-stacks/git-storage';
  await utils.execCommand(['mv', gitRoot, gitRoot + '-' + backupName]);
  await utils.execCommand(['mv', restoreContentsPath + '/git-storage', '/bizBrainz-stacks']);
  console.log('Restoring git-storage archive completed');

}

async function checkRestoreVersionCompatability(restoreContentsPath) {
  const content = await fsPromises.readFile('/opt/bizBrainz/rts/version.js', { encoding: 'utf8' });
  const currentVersion = content.match(/\bexports\.VERSION\s*=\s*["']([^"]+)["']/)[1];
  const manifest_data = await fsPromises.readFile(restoreContentsPath + '/manifest.json', { encoding: 'utf8' });
  const manifest_json = JSON.parse(manifest_data);
  const restoreVersion = manifest_json["bizBrainzVersion"];
  console.log('Current Bizbrainz Version: ' + currentVersion);
  console.log('Restore Bizbrainz Version: ' + restoreVersion);

  if (currentVersion === restoreVersion) {
    console.log('The restore instance is compatible with the current bizBrainz version');
  } else {
    console.log('**************************** WARNING ****************************');
    console.log('The Bizbrainz instance to be restored is not compatible with the current version.');
    console.log('Please update your bizBrainz image to \"index.docker.io/bizBrainz/bizBrainz-ce:' + restoreVersion +
      '\" in the \"docker-compose.yml\" file\nand run the cmd: \"docker-compose restart\" ' +
      'after the restore process is completed, to ensure the restored instance runs successfully.');
    const confirm = readlineSync.question('Press Enter to continue \nOr Type "c" to cancel the restore process.\n');
    if (confirm.toLowerCase() === 'c') {
      process.exit(0);
    }
  }
}

async function run() {
  let errorCode = 0;
  try {
    check_supervisord_status_cmd = '/usr/bin/supervisorctl >/dev/null 2>&1';
    shell.exec(check_supervisord_status_cmd, function (code) {
      if (code > 0) {
        shell.echo('application is not running, starting supervisord');
        shell.exec('/usr/bin/supervisord');
      }
    });

    const backupFileName = await getBackupFileName();
    if (backupFileName == null) {
      process.exit(errorCode);
    } else {
      const backupFilePath = path.join(Constants.BACKUP_PATH, backupFileName);
      const backupName = backupFileName.replace(/\.tar\.gz$/, "");
      const restoreRootPath = await fsPromises.mkdtemp(os.tmpdir());
      const restoreContentsPath = path.join(restoreRootPath, backupName);

      await extractArchive(backupFilePath, restoreRootPath);
      await checkRestoreVersionCompatability(restoreContentsPath);

      console.log('****************************************************************');
      console.log('Restoring Bizbrainz instance from the backup at ' + backupFilePath);
      utils.stop(['backend', 'rts']);
      await restoreDatabase(restoreContentsPath);
      await restoreDockerEnvFile(restoreContentsPath, backupName);
      await restoreGitStorageArchive(restoreContentsPath, backupName);
      console.log('Bizbrainz instance successfully restored.');
      await fsPromises.rm(restoreRootPath, { recursive: true, force: true });
    }
  } catch (err) {
    console.log(err);
    errorCode = 1;

  } finally {
    utils.start(['backend', 'rts']);
    process.exit(errorCode);

  }
}


module.exports = {
  run,
};
