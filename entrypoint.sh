#!/bin/bash
set -e

run() {
    cd /src
    mvn install
    mvn compile
}
case "$1" in
"run")
    echo "Run"
    run
    ;;
*)
    echo "Custom command : $@"
    exec "$@"
    ;;
esac