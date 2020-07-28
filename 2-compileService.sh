#!/bin/bash
# Compile Service

javac -extdirs lib service/*.java
javac -d service/ -extdirs lib service/*.java
