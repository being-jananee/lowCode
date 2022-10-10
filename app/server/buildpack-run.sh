# Create the dist directory
mkdir -p dist/plugins
mkdir -p plugins

# Copy the server jar
cp ./bizBrainz-server/target/server-1.0-SNAPSHOT.jar dist/
cp ./bizBrainz-server/target/server-1.0-SNAPSHOT.jar ./

# Copy all the plugins
cp ./bizBrainz-plugins/*/target/*.jar dist/plugins/
cp ./bizBrainz-plugins/*/target/*.jar plugins/
