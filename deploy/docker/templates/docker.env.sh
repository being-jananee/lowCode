#!/bin/bash

set -o nounset
MONGO_USER="$1"
MONGO_PASSWORD="$2"
ENCRYPTION_PASSWORD="$3"
ENCRYPTION_SALT="$4"
SUPERVISOR_PASSWORD="$5"

cat <<EOF
# Sentry
BIZBRAINZ_SENTRY_DSN=

# Smart look
BIZBRAINZ_SMART_LOOK_ID=

# Google OAuth
BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_ID=
BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_SECRET=

# Github OAuth
BIZBRAINZ_OAUTH2_GITHUB_CLIENT_ID=
BIZBRAINZ_OAUTH2_GITHUB_CLIENT_SECRET=

# Form Login/Signup
BIZBRAINZ_FORM_LOGIN_DISABLED=
BIZBRAINZ_SIGNUP_DISABLED=

# Segment
BIZBRAINZ_SEGMENT_KEY=

# RapidAPI
BIZBRAINZ_RAPID_API_KEY_VALUE=
BIZBRAINZ_MARKETPLACE_ENABLED=

# Algolia Search (Docs)
BIZBRAINZ_ALGOLIA_API_ID=
BIZBRAINZ_ALGOLIA_API_KEY=
BIZBRAINZ_ALGOLIA_SEARCH_INDEX_NAME=

#Client log level (debug | error)
BIZBRAINZ_CLIENT_LOG_LEVEL=

# GOOGLE client API KEY
BIZBRAINZ_GOOGLE_MAPS_API_KEY=

# Email server
BIZBRAINZ_MAIL_ENABLED=
BIZBRAINZ_MAIL_HOST=
BIZBRAINZ_MAIL_PORT=
BIZBRAINZ_MAIL_USERNAME=
BIZBRAINZ_MAIL_PASSWORD=
BIZBRAINZ_MAIL_FROM=
BIZBRAINZ_REPLY_TO=

# Email server feature toggles
# true | false values
BIZBRAINZ_MAIL_SMTP_AUTH=
BIZBRAINZ_MAIL_SMTP_TLS_ENABLED=

# Disable all telemetry
# Note: This only takes effect in self-hosted scenarios.
# Please visit: https://docs.bizBrainz.com/telemetry to read more about anonymized data collected by Bizbrainz
BIZBRAINZ_DISABLE_TELEMETRY=false
#BIZBRAINZ_SENTRY_DSN=
#BIZBRAINZ_SENTRY_ENVIRONMENT=

# Google Recaptcha Config
BIZBRAINZ_RECAPTCHA_SITE_KEY=
BIZBRAINZ_RECAPTCHA_SECRET_KEY=
BIZBRAINZ_RECAPTCHA_ENABLED=

BIZBRAINZ_MONGODB_URI=mongodb://$MONGO_USER:$MONGO_PASSWORD@localhost:27017/bizBrainz
BIZBRAINZ_MONGODB_USER=$MONGO_USER
BIZBRAINZ_MONGODB_PASSWORD=$MONGO_PASSWORD
BIZBRAINZ_API_BASE_URL=http://localhost:8080/api/v1

BIZBRAINZ_REDIS_URL=redis://127.0.0.1:6379

BIZBRAINZ_ENCRYPTION_PASSWORD=$ENCRYPTION_PASSWORD
BIZBRAINZ_ENCRYPTION_SALT=$ENCRYPTION_SALT

BIZBRAINZ_CUSTOM_DOMAIN=

# Java command line arguments, as space-delimited string. Ex: "-Xms800M -Xmx800M"
BIZBRAINZ_JAVA_ARGS=

# BIZBRAINZ_PLUGIN_MAX_RESPONSE_SIZE_MB=5
# MAX PAYLOAD SIZE
# BIZBRAINZ_CODEC_SIZE=

BIZBRAINZ_SUPERVISOR_USER=bizBrainz
BIZBRAINZ_SUPERVISOR_PASSWORD=$SUPERVISOR_PASSWORD

# Set this to a space separated list of addresses that should be allowed to load Bizbrainz in a frame.
# Example: "https://mydomain.com https://another-trusted-domain.com" will allow embedding on those two domains.
# Default value, if commented or not set, is "'none'", which disables embedding completely.
# https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Security-Policy/frame-ancestors
BIZBRAINZ_ALLOWED_FRAME_ANCESTORS="'self' *"
EOF