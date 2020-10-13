#!/bin/bash

function resetTomcat() {
    ./7-shutdownTomcat.sh && ./1-startTomcat.sh
}

resetTomcat &&
./2-compileService.sh && 
./3-deployAAR.sh && 
resetTomcat &&
./4-createStub.sh && 
./5-compileClient.sh &&
resetTomcat &&
./6-runclient.sh