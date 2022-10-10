#!/bin/bash
set -o errexit
# Check if Lock File exists, if not create it and set trap on exit
if { set -C; 2>/dev/null >/home/bizBrainz/.bizBrainz.lock; }; then
    trap "rm -f /home/bizBrainz/.bizBrainz.lock" EXIT
else
    exit
fi

start_docker() {
    if [ `sudo systemctl is-active docker.service` == "inactive" ];then
        echo "Starting docker"
        sudo systemctl start docker.service
    fi
}
start_docker

install_dir="{{ cloud_directory }}"
# Check if Apsmith setup, if not create run setup up script
if [ ! -f $install_dir/docker-compose.yml ]; then
    echo ""
    echo "Setting Bizbrainz"
    first_time_setup_script=$install_dir/scripts/first-time-setup.sh
    sudo chmod +x $first_time_setup_script;
    /bin/bash $first_time_setup_script
else
    echo "Booting bizBrainz"
    cd $install_dir
    docker-compose up --detach --remove-orphans
fi
