#!/bin/bash

set -o nounset

# In the config file, there's three types of variables, all represented with the syntax `$name`. The ones that are not
# escaped with a backslash are rendered within this script. Among the ones that are escaped with a backslash, the ones
# starting with `BIZBRAINZ_` will be rendered at boot-up time by bizBrainz-editor docker container. The rest (like $scheme
# and $host) are for nginx to work out.

NGINX_SSL_CMNT="$1"
custom_domain="$2"

cat <<EOF
map \$http_x_forwarded_proto \$origin_scheme {
  default \$http_x_forwarded_proto;
  '' \$scheme;
}

server {
    listen 80;
$NGINX_SSL_CMNT    server_name $custom_domain ;
    client_max_body_size 100m;

    gzip on;

    root /var/www/bizBrainz;
    index index.html index.htm;

    location /.well-known/acme-challenge/ {
        root /var/www/certbot;
    }

    proxy_set_header X-Forwarded-Proto \$origin_scheme;
    proxy_set_header X-Forwarded-Host \$host;

    location / {
        try_files \$uri /index.html =404;

        sub_filter __BIZBRAINZ_SENTRY_DSN__ '\${BIZBRAINZ_SENTRY_DSN}';
        sub_filter __BIZBRAINZ_SMART_LOOK_ID__ '\${BIZBRAINZ_SMART_LOOK_ID}';
        sub_filter __BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_ID__ '\${BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_ID}';
        sub_filter __BIZBRAINZ_OAUTH2_GITHUB_CLIENT_ID__ '\${BIZBRAINZ_OAUTH2_GITHUB_CLIENT_ID}';
        sub_filter __BIZBRAINZ_MARKETPLACE_ENABLED__ '\${BIZBRAINZ_MARKETPLACE_ENABLED}';
        sub_filter __BIZBRAINZ_SEGMENT_KEY__ '\${BIZBRAINZ_SEGMENT_KEY}';
        sub_filter __BIZBRAINZ_ALGOLIA_API_ID__ '\${BIZBRAINZ_ALGOLIA_API_ID}';
        sub_filter __BIZBRAINZ_ALGOLIA_SEARCH_INDEX_NAME__ '\${BIZBRAINZ_ALGOLIA_SEARCH_INDEX_NAME}';
        sub_filter __BIZBRAINZ_ALGOLIA_API_KEY__ '\${BIZBRAINZ_ALGOLIA_API_KEY}';
        sub_filter __BIZBRAINZ_CLIENT_LOG_LEVEL__ '\${BIZBRAINZ_CLIENT_LOG_LEVEL}';
        sub_filter __BIZBRAINZ_GOOGLE_MAPS_API_KEY__ '\${BIZBRAINZ_GOOGLE_MAPS_API_KEY}';
        sub_filter __BIZBRAINZ_TNC_PP__ '\${BIZBRAINZ_TNC_PP}';
        sub_filter __BIZBRAINZ_VERSION_ID__ '\${BIZBRAINZ_VERSION_ID}';
        sub_filter __BIZBRAINZ_VERSION_RELEASE_DATE__ '\${BIZBRAINZ_VERSION_RELEASE_DATE}';
        sub_filter __BIZBRAINZ_INTERCOM_APP_ID__ '\${BIZBRAINZ_INTERCOM_APP_ID}';
        sub_filter __BIZBRAINZ_MAIL_ENABLED__ '\${BIZBRAINZ_MAIL_ENABLED}';
        sub_filter __BIZBRAINZ_DISABLE_TELEMETRY__ '\${BIZBRAINZ_DISABLE_TELEMETRY}';
        sub_filter __BIZBRAINZ_RECAPTCHA_SITE_KEY__ '\${BIZBRAINZ_RECAPTCHA_SITE_KEY}';
        sub_filter __BIZBRAINZ_RECAPTCHA_SECRET_KEY__ '\${BIZBRAINZ_RECAPTCHA_SECRET_KEY}';
        sub_filter __BIZBRAINZ_RECAPTCHA_ENABLED__ '\${BIZBRAINZ_RECAPTCHA_ENABLED}';
        sub_filter __BIZBRAINZ_DISABLE_INTERCOM__ '\${BIZBRAINZ_DISABLE_INTERCOM}';
        sub_filter __BIZBRAINZ_FORM_LOGIN_DISABLED__ '\${BIZBRAINZ_FORM_LOGIN_DISABLED}';
        sub_filter __BIZBRAINZ_SIGNUP_DISABLED__ '\${BIZBRAINZ_SIGNUP_DISABLED}';
    }


    location /api {
        proxy_pass http://bizBrainz-internal-server:8080;
    }

    location /oauth2 {
        proxy_pass http://bizBrainz-internal-server:8080;
    }

    location /login {
        proxy_pass http://bizBrainz-internal-server:8080;
    }
}

$NGINX_SSL_CMNT server {
$NGINX_SSL_CMNT    listen 443 ssl;
$NGINX_SSL_CMNT    server_name $custom_domain;
$NGINX_SSL_CMNT    client_max_body_size 100m;
$NGINX_SSL_CMNT
$NGINX_SSL_CMNT    ssl_certificate /etc/letsencrypt/live/$custom_domain/fullchain.pem;
$NGINX_SSL_CMNT    ssl_certificate_key /etc/letsencrypt/live/$custom_domain/privkey.pem;
$NGINX_SSL_CMNT
$NGINX_SSL_CMNT    include /etc/letsencrypt/options-ssl-nginx.conf;
$NGINX_SSL_CMNT    ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem;
$NGINX_SSL_CMNT
$NGINX_SSL_CMNT    proxy_set_header X-Forwarded-Proto \$origin_scheme;
$NGINX_SSL_CMNT    proxy_set_header X-Forwarded-Host \$host;
$NGINX_SSL_CMNT
$NGINX_SSL_CMNT    root /var/www/bizBrainz;
$NGINX_SSL_CMNT    index index.html index.htm;
$NGINX_SSL_CMNT
$NGINX_SSL_CMNT    location / {
$NGINX_SSL_CMNT        try_files \$uri /index.html =404;
$NGINX_SSL_CMNT
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_SENTRY_DSN__ '\${BIZBRAINZ_SENTRY_DSN}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_SMART_LOOK_ID__ '\${BIZBRAINZ_SMART_LOOK_ID}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_ID__ '\${BIZBRAINZ_OAUTH2_GOOGLE_CLIENT_ID}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_OAUTH2_GITHUB_CLIENT_ID__ '\${BIZBRAINZ_OAUTH2_GITHUB_CLIENT_ID}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_MARKETPLACE_ENABLED__ '\${BIZBRAINZ_MARKETPLACE_ENABLED}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_SEGMENT_KEY__ '\${BIZBRAINZ_SEGMENT_KEY}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_ALGOLIA_API_ID__ '\${BIZBRAINZ_ALGOLIA_API_ID}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_ALGOLIA_SEARCH_INDEX_NAME__ '\${BIZBRAINZ_ALGOLIA_SEARCH_INDEX_NAME}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_ALGOLIA_API_KEY__ '\${BIZBRAINZ_ALGOLIA_API_KEY}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_CLIENT_LOG_LEVEL__ '\${BIZBRAINZ_CLIENT_LOG_LEVEL}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_GOOGLE_MAPS_API_KEY__ '\${BIZBRAINZ_GOOGLE_MAPS_API_KEY}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_TNC_PP__ '\${BIZBRAINZ_TNC_PP}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_VERSION_ID__ '\${BIZBRAINZ_VERSION_ID}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_VERSION_RELEASE_DATE__ '\${BIZBRAINZ_VERSION_RELEASE_DATE}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_INTERCOM_APP_ID__ '\${BIZBRAINZ_INTERCOM_APP_ID}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_MAIL_ENABLED__ '\${BIZBRAINZ_MAIL_ENABLED}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_DISABLE_TELEMETRY__ '\${BIZBRAINZ_DISABLE_TELEMETRY}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_RECAPTCHA_SITE_KEY__ '\${BIZBRAINZ_RECAPTCHA_SITE_KEY}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_RECAPTCHA_SECRET_KEY__ '\${BIZBRAINZ_RECAPTCHA_SECRET_KEY}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_RECAPTCHA_ENABLED__ '\${BIZBRAINZ_RECAPTCHA_ENABLED}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_DISABLE_INTERCOM__ '\${BIZBRAINZ_DISABLE_INTERCOM}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_FORM_LOGIN_DISABLED__ '\${BIZBRAINZ_FORM_LOGIN_DISABLED}';
$NGINX_SSL_CMNT        sub_filter __BIZBRAINZ_SIGNUP_DISABLED__ '\${BIZBRAINZ_SIGNUP_DISABLED}';
$NGINX_SSL_CMNT    }
$NGINX_SSL_CMNT
$NGINX_SSL_CMNT    location /api {
$NGINX_SSL_CMNT        proxy_pass http://bizBrainz-internal-server:8080;
$NGINX_SSL_CMNT    }
$NGINX_SSL_CMNT
$NGINX_SSL_CMNT    location /oauth2 {
$NGINX_SSL_CMNT        proxy_pass http://bizBrainz-internal-server:8080;
$NGINX_SSL_CMNT    }
$NGINX_SSL_CMNT
$NGINX_SSL_CMNT    location /login {
$NGINX_SSL_CMNT        proxy_pass http://bizBrainz-internal-server:8080;
$NGINX_SSL_CMNT    }
$NGINX_SSL_CMNT
$NGINX_SSL_CMNT }
EOF
