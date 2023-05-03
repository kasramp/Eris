#!/bin/bash
set -ev

if [[ "$LAST_COMMIT" != *"maven-release-plugin"* ]]; then
    git config --global user.email $EMAIL
    git config --global user.name $USERNAME
    git symbolic-ref HEAD refs/heads/$(git branch --show-current)
    git symbolic-ref HEAD
    if [ $(git branch --show-current) == "develop" ]; then
      mvn -B clean release:clean release:prepare release:perform -Dusername=$USERNAME -Dpassword=$TOKEN -DtagNameFormat=@{artifactId}-beta-@{version} -Darguments="-Dmaven.deploy.skip=true"
    else
      mvn -B clean release:clean release:prepare release:perform -Dusername=$USERNAME -Dpassword=$TOKEN -Darguments="-Dmaven.deploy.skip=true" &&
      TAGGED_VERSION=$(git for-each-ref --sort=-creatordate --format='%(refname:short)' refs/tags --count 10 | grep -oP '^eris-(\d+).*' | head -1 | cut -d "-" -f2) &&
      IMAGE_NAME=kasramp/eris &&
      mvn versions:set -DnewVersion="$TAGGED_VERSION" &&
      mvn spring-boot:build-image -Dspring-boot.build-image.imageName="$IMAGE_NAME":"$TAGGED_VERSION" &&
      docker tag "$IMAGE_NAME":"$TAGGED_VERSION" "$IMAGE_NAME":latest &&
      docker login -u "$DOCKER_USERNAME" --password-stdin <<< "$DOCKER_PASSWORD" &&
      docker push "$IMAGE_NAME" --all-tags &&
      git checkout pom.xml &&
      git fetch origin develop && git merge origin/master &&
      git push https://$USERNAME:$TOKEN@github.com/kasramp/Eris.git HEAD:develop
    fi
fi