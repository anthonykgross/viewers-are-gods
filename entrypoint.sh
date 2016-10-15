#!/bin/bash
set -e

cd /src
mvn install
mvn compile

echo "Compilation terminee"