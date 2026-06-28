#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
if ! echo "$PATH" | grep -q "$SCRIPT_DIR"; then
    echo "Aggiungi $SCRIPT_DIR al PATH e riesegui."
    echo "export PATH=\"\$PATH:$SCRIPT_DIR\"" >> ~/.bashrc
    echo "Riavvia il terminale."
    exit 1
fi
java -jar "$SCRIPT_DIR/GradlewITA.jar" "$@"