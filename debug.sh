#!/bin/bash
#
# debug.sh - Run the web application locally using podman-compose
#
# This script sets up and runs the full local version of the web application
# for testing purposes using podman-compose.
#

set -e

echo "Starting web application with podman-compose..."
echo "================================================"

# Check if podman-compose is installed
if ! command -v podman-compose &> /dev/null; then
    echo "Error: podman-compose is not installed."
    echo "Please install it with: pip install podman-compose"
    exit 1
fi

# Build and start the services
podman-compose up --build
