#!/bin/bash

init_ssl_cert() {
  BIZBRAINZ_CUSTOM_DOMAIN="$1"

  local rsa_key_size=4096
  local data_path="/bizBrainz-stacks/data/certificate"

  mkdir -p "$data_path/www"

  echo "Re-generating nginx config template with domain"
  bash "/opt/bizBrainz/templates/nginx-app-http.conf.template.sh" "$BIZBRAINZ_CUSTOM_DOMAIN" >"/etc/nginx/conf.d/nginx_app.conf.template"

  echo "Generating nginx configuration"
  cat /etc/nginx/conf.d/nginx_app.conf.template | envsubst "$(printf '$%s,' $(env | grep -Eo '^BIZBRAINZ_[A-Z0-9_]+'))" | sed -e 's|\${\(BIZBRAINZ_[A-Z0-9_]*\)}||g' >/etc/nginx/sites-available/default

  echo "Start Nginx to verify certificate"
  nginx

  local live_path="/etc/letsencrypt/live/$BIZBRAINZ_CUSTOM_DOMAIN"
  local ssl_path="/bizBrainz-stacks/ssl"
  if [[ -e "$ssl_path/fullchain.pem" ]] && [[ -e "$ssl_path/privkey.pem" ]]; then
    echo "Existing custom certificate"
    echo "Stop Nginx"
    nginx -s stop
    return
  fi

  if [[ -e "$live_path" ]]; then
    echo "Existing certificate for domain $BIZBRAINZ_CUSTOM_DOMAIN"
    echo "Stop Nginx"
    nginx -s stop
    return
  fi

  echo "Creating certificate for '$BIZBRAINZ_CUSTOM_DOMAIN'"

  echo "Requesting Let's Encrypt certificate for '$BIZBRAINZ_CUSTOM_DOMAIN'..."
  echo "Generating OpenSSL key for '$BIZBRAINZ_CUSTOM_DOMAIN'..."

  mkdir -p "$live_path" && openssl req -x509 -nodes -newkey rsa:2048 -days 1 \
    -keyout "$live_path/privkey.pem" \
    -out "$live_path/fullchain.pem" \
    -subj "/CN=localhost"

  echo "Removing key now that validation is done for $BIZBRAINZ_CUSTOM_DOMAIN..."
  rm -Rfv /etc/letsencrypt/live/$BIZBRAINZ_CUSTOM_DOMAIN /etc/letsencrypt/archive/$BIZBRAINZ_CUSTOM_DOMAIN /etc/letsencrypt/renewal/$BIZBRAINZ_CUSTOM_DOMAIN.conf

  echo "Generating certification for domain $BIZBRAINZ_CUSTOM_DOMAIN"
  mkdir -p "$data_path/certbot"
  certbot certonly --webroot --webroot-path="$data_path/certbot" \
    --register-unsafely-without-email \
    --domains $BIZBRAINZ_CUSTOM_DOMAIN \
    --rsa-key-size $rsa_key_size \
    --agree-tos \
    --force-renewal

  if (($? != 0)); then
    echo "Stop Nginx due to provisioning fail"
    nginx -s stop
    return 1
  fi

  echo "Stop Nginx"
  nginx -s stop
}
