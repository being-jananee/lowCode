#!/bin/bash

set -o nounset

user_encryption_password="$1"
user_encryption_salt="$2"

cat <<EOF
BIZBRAINZ_ENCRYPTION_PASSWORD=$user_encryption_password
BIZBRAINZ_ENCRYPTION_SALT=$user_encryption_salt
EOF
