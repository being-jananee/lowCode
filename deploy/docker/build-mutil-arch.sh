#!/bin/bash
docker buildx build --platform linux/arm64,linux/amd64 --push -t bizBrainz/bizBrainz-ce  .
