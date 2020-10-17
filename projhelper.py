#!/usr/bin/python3

import os
import sys
import subprocess

ON = True
OFF = False

BASE_PATH = '/home/soasec/SOAsec-Chat/'

def runScript(path):
    FNULL = open(os.devnull, 'w')
    retcode = subprocess.call(path,
                            stdout=FNULL,
                            stderr=subprocess.STDOUT)
    return retcode

def reboot_tomcat(func):
    def inner():
        val = runScript("./7-shutdownTomcat.sh")
        val &= runScript("./1-startTomcat.sh")
        if val:
            func()
    return inner

def sec(enable):
    src = ''
    dst = '/home/soasec/SOAsec-Chat/client/src/axis-repo/conf/axis2.xml'
    ret = True

    if enable:
        print('Abilito sicurezza ... ', end='')
        src = '/home/soasec/SOAsec-Chat/client/src/axis-repo/conf/axis2.xml.secon'
    else:
        print('Disabilito sicurezza ... ', end='')
        src = '/home/soasec/SOAsec-Chat/client/src/axis-repo/conf/axis2.xml.secoff'

    try:
        if os.path.exists(dst):
            os.remove(dst)
        os.symlink(src, dst)
        print('[ OK ]')
    except Exception as e:
        print('[ Err ]')
        print(e)
        ret = False
    
    return ret

@reboot_tomcat
def compileServer():
    print('Compilo il server ... ', end='')
    ret = runScript("./2-compileService.sh")
    if ret:
        print('[ OK ]')
        print('Creo AAR e lo deployo ... ', end='')
        ret &= runScript("./3-deployAAR.sh")
        if ret:
            print('[ OK ]')
        else:
            print('[ Err ]')
    else:
        print('[ Err ]')
    return ret

@reboot_tomcat
def compileClient():
    print('Creo lo Stub ... ', end='')
    ret = runScript("./4-createStub.sh")
    if ret:
        print('[ OK ]')
        print('Compilo il Client ... ', end='')
        ret &= runScript("./5-compileClient.sh")
        if ret:
            print('[ OK ]')
        else:
            print('[ Err ]')
    else:
        print('[ Err ]')
    return ret

@reboot_tomcat
def runClient():
    ret = runScript("./6-runclient.sh")
    return ret

def help():
    opts = {
        '--on' : 'abilita le feature di sicurezza di axis2',
        '--off' : 'diabilita le feature di sicurezza di axis2',
        '--server' : 'compila il server',
        '--client' : 'compila il client',
        '--run' : 'esegue il client',
        '--compile-and-run': 'compila il server, il client e esegue il client'
    }

    print('FLAG\t\tDESCRIZIONE')

    for flag, descrizione in opts.items():
        print(flag + '\t\t' + descrizione)

def main(opt):
    try:
        if opt & 0:
            if not sec(ON):
                raise Exception("il metodo sec(ON) ha fallito") 
        if opt & 1:
            if not sec(OFF):
                raise Exception("il metodo sec(OFF) ha fallito")
        if opt & 2:
            if not compileServer():
                raise Exception("il metodo compileServer() ha fallito")
        if opt & 4:
            if not compileClient():
                raise Exception("il metodo compileClient() ha fallito")
        if opt & 8:
            if not runClient():
                raise Exception("il metodo runClient() ha fallito")
    except Exception as e:
        print(e)
    return

if __name__ == "__main__":
    opts = {
        '--on' : 0,
        '--off' : 1,
        '--server' : 2,
        '--client' : 4,
        '--run' : 8,
        '--compile-and-run': 14
    }

    res = 0

    if len(sys.argv) == 1:
        help()
    else:
        for opt in sys.argv:
            if opt in opts.keys():
                res += opts[opt]
    
        main(res)