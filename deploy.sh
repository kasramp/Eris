#!/bin/bash
IMAGE_VERSION="${1:-latest}"
DOCKERHUB_USERNAME="${2:-$DOCKER_USERNAME}"
DOCKERHUB_PASSWORD="${3:-$DOCKER_PASSWORD}"
export OPENWEATHERMAP_API_KEY=open-weather-api-key
export SEGMENTIO_WRITE_API_KEY=segment-api-key
export ACTUATOR_USERNAME=username
export ACTUATOR_PASSWORD=password
export IMAGE_TAG="$IMAGE_VERSION"
docker login -u "$DOCKERHUB_USERNAME" --password-stdin <<< "$DOCKERHUB_PASSWORD" && \
docker stack deploy --with-registry-auth -c docker-compose-swarm.yml eris_stack