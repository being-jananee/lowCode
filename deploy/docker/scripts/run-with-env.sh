#!/bin/bash

ENV_PATH="/bizBrainz-stacks/configuration/docker.env"
PRE_DEFINED_ENV_PATH="/opt/bizBrainz/templates/pre-define.env"
echo 'Load environment configuration'
set -o allexport
. "$ENV_PATH"
. "$PRE_DEFINED_ENV_PATH"
set +o allexport

if [[ -z "${BIZBRAINZ_MAIL_ENABLED}" ]]; then
  unset BIZBRAINZ_MAIL_ENABLED # If this field is empty is might cause application crash
fi

if [[ -z "${BIZBRAINZ_OAUTH2_GITHUB_CLIENT_ID}" ]] || [[ -z "${BIZBRAINZ_OAUTH2_GITHUB_CLIENT_SECRET}" ]]; then
  unset BIZBRAINZ_OAUTH2_GITHUB_CLIENT_ID # If this field is empty is might cause application crash
  unset BIZBRAINZ_OAUTH2_GITHUB_CLIENT_SECRET
fi

if [[ -z "${BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_ID}" ]] || [[ -z "${BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_SECRET}" ]]; then
  unset BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_ID # If this field is empty is might cause application crash
  unset BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_SECRET
fi

if [[ -z "${BIZBRAINZ_GOOGLE_MAPS_API_KEY}" ]]; then
  unset BIZBRAINZ_GOOGLE_MAPS_API_KEY
fi

if [[ -z "${BIZBRAINZ_RECAPTCHA_SITE_KEY}" ]] || [[ -z "${BIZBRAINZ_RECAPTCHA_SECRET_KEY}" ]] || [[ -z "${BIZBRAINZ_RECAPTCHA_ENABLED}" ]]; then
  unset BIZBRAINZ_RECAPTCHA_SITE_KEY # If this field is empty is might cause application crash
  unset BIZBRAINZ_RECAPTCHA_SECRET_KEY
  unset BIZBRAINZ_RECAPTCHA_ENABLED
fi

if [[ -z "${BIZBRAINZ_GIT_ROOT:-}" ]]; then
  export BIZBRAINZ_GIT_ROOT=/bizBrainz-stacks/git-storage
fi
mkdir -pv "$BIZBRAINZ_GIT_ROOT"

exec "$@"
