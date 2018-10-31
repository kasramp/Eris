#!/bin/bash
set -ev

if [[ "$TRAVIS_COMMIT_DESCRIPTION" != *"maven-release-plugin"* ]];then
    git config --global user.email $GITHUB_EMAIL
    git config --global user.name $GITHUB_USERNAME
    git remote set-head origin $TRAVIS_BRANCH
    git show-ref --head
    git symbolic-ref HEAD refs/heads/$TRAVIS_BRANCH
    git symbolic-ref HEAD
    mvn -B clean release:clean release:prepare release:perform -Dusername=$GITHUB_USERNAME -Dpassword=$GITHUB_PASSWORD
fi
