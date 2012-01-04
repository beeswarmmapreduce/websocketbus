#!/bin/sh

VERSION=8.1.0.RC2

wget http://download.eclipse.org/jetty/$VERSION/dist/jetty-distribution-$VERSION.tar.gz && \
tar xf jetty-distribution-$VERSION.tar.gz && \
rm jetty-distribution-$VERSION.tar.gz

