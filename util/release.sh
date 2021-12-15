#!/bin/bash
set -ev

if [[ "$LAST_COMMIT" != *"maven-release-plugin"* ]];then
    git config --global user.email $EMAIL
    git config --global user.name $USERNAME
#    git remote set-head origin develop
#    git show-ref --head
    git symbolic-ref HEAD refs/heads/develop
    git symbolic-ref HEAD
    mvn -B clean release:clean release:prepare release:perform -Dusername=$USERNAME -Dpassword=$TOKEN -Darguments="-Dmaven.deploy.skip=true"
fi
