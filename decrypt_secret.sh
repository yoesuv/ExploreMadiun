#!/bin/sh

# Decrypt the file
mkdir $HOME/secrets
# --batch to prevent interactive command
# --yes to assume "yes" for questions
gpg --quiet --batch --yes --decrypt --passphrase="API_KEY_PASSPHRASE" \
--output $HOME/secrets/apiKey.properties apiKey.properties.gpg