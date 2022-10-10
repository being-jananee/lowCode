#!/bin/bash

set -o nounset

mongo_database="$1"
encoded_mongo_root_user="$2"
encoded_mongo_root_password="$3"
mongo_host="$4"
disable_telemetry="$5"

cat << EOF
# Read our documentation on how to configure these features
# https://docs.bizBrainz.com/v/v1.2.1/setup/docker#enabling-services-for-self-hosting

# ***** Email **********
BIZBRAINZ_MAIL_ENABLED=false
# BIZBRAINZ_MAIL_FROM=YOUR_VERIFIED_EMAIL_ID
# BIZBRAINZ_REPLY_TO=YOUR_VERIFIED_EMAIL_ID
# BIZBRAINZ_MAIL_HOST=
# BIZBRAINZ_MAIL_PORT=
# ***** Set to true if providing a TLS port ******
# BIZBRAINZ_MAIL_SMTP_TLS_ENABLED=
# BIZBRAINZ_MAIL_USERNAME=
# BIZBRAINZ_MAIL_PASSWORD=
# BIZBRAINZ_MAIL_SMTP_AUTH=true
# ******************************

# ******** Google OAuth ********
# BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_ID=
# BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_SECRET=
# ******************************

# ********* Github OAUth **********
# BIZBRAINZ_OAUTH2_GITHUB_CLIENT_ID=
# BIZBRAINZ_OAUTH2_GITHUB_CLIENT_SECRET=
# *********************************

# ******** Form Login/Signup ********
# BIZBRAINZ_FORM_LOGIN_DISABLED=
# BIZBRAINZ_SIGNUP_DISABLED=
# ***********************************

# ******** Google Maps ***********
# BIZBRAINZ_GOOGLE_MAPS_API_KEY=
# ********************************

# ******** Database *************
BIZBRAINZ_REDIS_URL=redis://redis:6379
BIZBRAINZ_MONGODB_URI=mongodb://$encoded_mongo_root_user:$encoded_mongo_root_password@$mongo_host/$mongo_database?retryWrites=true&authSource=admin
MONGO_INITDB_DATABASE=$mongo_database
MONGO_INITDB_ROOT_USERNAME=$encoded_mongo_root_user
MONGO_INITDB_ROOT_PASSWORD=$encoded_mongo_root_password
# *******************************

# *** EE Specific Config ********
# BIZBRAINZ_MARKETPLACE_URL=
# BIZBRAINZ_RAPID_API_KEY_VALUE=
# BIZBRAINZ_ROLLBAR_ACCESS_TOKEN=
# BIZBRAINZ_ROLLBAR_ENV=
# BIZBRAINZ_SEGMENT_KEY=
# *******************************

# ******** ANALYTICS *************
BIZBRAINZ_DISABLE_TELEMETRY=$disable_telemetry
# *******************************

# ****** MAX PAYLOAD SIZE *******
# BIZBRAINZ_CODEC_SIZE=
# *******************************

# ***** Google Recaptcha Config ******
# BIZBRAINZ_RECAPTCHA_SITE_KEY=
# BIZBRAINZ_RECAPTCHA_SECRET_KEY=
# BIZBRAINZ_RECAPTCHA_ENABLED=
# ************************************

# ******** INTERCOM *************
# BIZBRAINZ_DISABLE_INTERCOM=
# *******************************

# BIZBRAINZ_PLUGIN_MAX_RESPONSE_SIZE_MB=5

BIZBRAINZ_DISABLE_IFRAME_WIDGET_SANDBOX=false

EOF
