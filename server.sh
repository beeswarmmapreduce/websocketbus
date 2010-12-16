#!/bin/bash

CP="bin"
for FILE in $(find jetty-*/lib/*.jar); do
	CP=$CP":"$FILE
done

java -classpath $CP SuperWebChatServer

