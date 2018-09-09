#!/bin/bash
set -ev

git config --global user.email $GITHUB_EMAIL
git config --global user.name $GITHUB_USERNAME
git remote set-head origin $TRAVIS_BRANCH
git show-ref --head
git symbolic-ref HEAD refs/heads/$TRAVIS_BRANCH
git symbolic-ref HEAD
mvn -B clean org.jacoco:jacoco-maven-plugin:prepare-agent release:clean release:prepare -Dusername=$GITHUB_USERNAME -Dpassword=$GITHUB_PASSWORD sonar:sonar
mvn -B release:perform -Dusername=$GITHUB_USERNAME -Dpassword=$GITHUB_PASSWORD