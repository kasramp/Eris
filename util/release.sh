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
      git fetch origin develop && git merge origin/master &&
      git push https://$USERNAME:$TOKEN@github.com/kasramp/Eris.git HEAD:develop
    fi
fi