#!/bin/sh

# This script is baked into the bizBrainz-editor Dockerfile and is used to boot Nginx when the Docker container starts
# Refer: /app/client/Dockerfile
set -o errexit
set -o xtrace

if [ -z "$BIZBRAINZ_SERVER_PROXY_PASS" ]; then
  export BIZBRAINZ_SERVER_PROXY_PASS='http://localhost:8080'
  echo "No explicit value for BIZBRAINZ_SERVER_PROXY_PASS, using '$BIZBRAINZ_SERVER_PROXY_PASS'."
fi

cp /nginx-root.conf.template /etc/nginx/nginx.conf

if [ -f /nginx.conf.template ]; then
  # This is to support installations where the docker-compose.yml file would mount a template confi at this location.
  app_template=/nginx.conf.template
elif [ -z "$BIZBRAINZ_SSL_CERT_PATH" ]; then
  if [ -z "$BIZBRAINZ_DOMAIN" ]; then
    export BIZBRAINZ_DOMAIN=_
  fi
  app_template=/nginx-app-http.conf.template
else
  if [ -z "$BIZBRAINZ_DOMAIN" ]; then
    echo "BIZBRAINZ_DOMAIN is required when SSL is enabled." >&2
    exit 2
  fi
  app_template=/nginx-app-https.conf.template
fi

cat "$app_template" \
  | envsubst "$(printf '$%s,' $(env | grep -Eo '^BIZBRAINZ_[A-Z0-9_]+'))" \
  | sed -e 's|\${\(BIZBRAINZ_[A-Z0-9_]*\)}||g' \
  | tee /etc/nginx/conf.d/default.conf

exec nginx -g 'daemon off;'
