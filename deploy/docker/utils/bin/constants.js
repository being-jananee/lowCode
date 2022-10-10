
const BACKUP_PATH = "/bizBrainz-stacks/data/backup"

const RESTORE_PATH = "/bizBrainz-stacks/data/restore"

const DUMP_FILE_NAME = "bizBrainz-data.archive"

const BACKUP_ERROR_LOG_PATH = "/bizBrainz-stacks/logs/backup"

const LAST_ERROR_MAIL_TS = "/bizBrainz-stacks/data/backup/last-error-mail-ts"

const MIN_REQUIRED_DISK_SPACE_IN_BYTES = 2147483648 // 2GB

const DURATION_BETWEEN_BACKUP_ERROR_MAILS_IN_MILLI_SEC = 21600000 // 6 hrs

module.exports = {
    BACKUP_PATH,
    RESTORE_PATH,
    DUMP_FILE_NAME,
    LAST_ERROR_MAIL_TS,
    BACKUP_ERROR_LOG_PATH,
    MIN_REQUIRED_DISK_SPACE_IN_BYTES,
    DURATION_BETWEEN_BACKUP_ERROR_MAILS_IN_MILLI_SEC,
}
