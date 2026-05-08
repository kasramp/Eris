#!/bin/bash
set -ev

PAYLOAD=$(printf '{"docker_version":"%s", "dockerhub_username":"%s", "dockerhub_password":"%s"}' "$TAGGED_VERSION" "$DOCKER_USERNAME" "$DOCKER_PASSWORD")
SIGNATURE=$(echo -n "$PAYLOAD" | openssl sha256 -hmac "$WEBHOOK_TOKEN" | sed 's/^.* //')
RESPONSE=$(curl -X POST "https://webhook.madadipouya.com/hooks/rolling-update-eris-webhook" \
     -H "Content-Type: application/json" \
     -H "X-Docker-Version: $TAGGED_VERSION" \
     -H "X-Hub-Signature-256: sha256=$SIGNATURE" \
     -d "$PAYLOAD" \
     -s -o /dev/null -w "%{http_code}")

if [[ "$RESPONSE" -eq 200 ]]; then
    echo "Deploying ($TAGGED_VERSION) version finished successfully"
    exit 0
else
    echo "Failed to deploy ($TAGGED_VERSION) version. Got response code: $RESPONSE"
    exit 1
fi