#!/bin/sh

# Add an `authSource` query param to MongoDB URI, if missing.
if [ -n "$BIZBRAINZ_MONGODB_URI" ]; then
  if ! echo "$BIZBRAINZ_MONGODB_URI" | grep -Fq "authSource="; then
    if echo "$BIZBRAINZ_MONGODB_URI" | grep -Fq '?'; then
      BIZBRAINZ_MONGODB_URI="$BIZBRAINZ_MONGODB_URI&authSource=admin"
    else
      BIZBRAINZ_MONGODB_URI="$BIZBRAINZ_MONGODB_URI?authSource=admin"
    fi
  fi
fi

# Ref -Dlog4j2.formatMsgNoLookups=true https://spring.io/blog/2021/12/10/log4j2-vulnerability-and-spring-boot
exec java -Djava.security.egd="file:/dev/./urandom" "$@" -Dlog4j2.formatMsgNoLookups=true -jar server.jar
