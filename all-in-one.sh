#!/bin/bash

echo "Riavvio Tomcat ..."
./7-shutdownTomcat.sh && ./1-startTomcat.sh
if [ $? -eq 0 ]; then
    echo "Tomcat Riavviato!"
    echo "Creo Server ..."
    ./2-compileService.sh && ./3-deployAAR.sh
    if [ $? -eq 0 ]; then
        echo "Server Creato!"
        #echo "Riavvio Tomcat ..."
        #./7-shutdownTomcat.sh && ./1-startTomcat.sh
        #if [ $? -eq 0 ]; then
        #    echo "Tomcat Riavviato!"
            echo "Creo Client ..."
            ./4-createStub.sh && ./5-compileClient.sh
            if [ $? -eq 0 ]; then
                echo "Client creato!"
                echo "Avvio App ..."
                ./6-runclient.sh
            else
                echo "Errore"
            fi
        #else
        #    echo "Errore"
        #fi
    else
        echo "Errore"
    fi
else
    echo "Errore"
fi