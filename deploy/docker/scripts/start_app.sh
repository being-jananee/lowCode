#!/bin/bash

install_dir="$1"

cd "$install_dir" \
&& echo "Start application..." \
&& mv "$install_dir/stacks/data/backup/bizBrainz-data.archive" "$install_dir/stacks/data/restore" \
&& docker-compose up -d

wait_for_containers_start() {
    local timeout=$1
    while [[ $timeout -gt 0 ]]; do
        status_code="$(curl -s -o /dev/null -w "%{http_code}" http://localhost/api/v1 || true)"
        if [[ status_code -eq 401 ]]; then
            break
        else
            echo -ne "Waiting for all containers to start. This check will timeout in $timeout seconds...\r\c"
        fi
        ((timeout--))
        sleep 1
    done
}

wait_for_containers_start 180
# Force import DB to ignore confirm step
docker-compose exec -T bizBrainz bizBrainzctl import_db -f