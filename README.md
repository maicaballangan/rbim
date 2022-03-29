INTRODUCTION
------------
This is the API for RBIM

REQUIREMENTS
------------
This has been tested on Windows, Mac OS X, and Linux operating systems.
The minimum requirements are as follows:

* Git >= 1.9
* JDK >= 11 
* Play Framework = 1.6.0 https://www.playframework.com/releases
* Docker
* RAM >= 4GB (8GB preferred) for Docker

REPO CONTENTS
-------------
You should now have the following non-empty files and folders:

        app/                source files
        bin/                executable files
        conf/               configuration files
        data/               flat files
          attachments/      attachments
          keys/             keystore file
          postman/          Postman fixtures
        docs/               document files
          data model/       data model
          uml/              UML diagrams
        public/             static files       
        test/               test files
        CHANGELOG.md        Changelog
        LICENSE.md          license file
        README.md           this file                      

GETTING STARTED
---------------
* Clone project `git clone -j11 https://github.com/scm/ac/rbim.git rbim` 
* Run `play dependencies` on project dir to install dependencies
* Run application `play start`
* Open app on browser `http://localhost:9009/v1`

If you are asked for a Basic HTTP Authentication, enter:
username: rbim  
password: rbim@@1

Make sure you have enough RAM!!

ENJOY :-)

DOCKER INSTALLATION FOR LOCAL DB SETUP 
----------------
* Install Docker
* Pull postgre image
``
sudo docker pull mcr.microsoft.com/mssql/server:2019-latest
``

* Run mssql docker image
``
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=popcom@123" \
-p 1433:1433 --name rbim -h rbim \
-d mcr.microsoft.com/mssql/server:2019-latest
``

IDE INSTALLATION
----------------
* Run `play idea` on project dir
* Install IntelliJ
* Choose 'Open' and open the generated `rbim.ipr` file in the project directory. 
* Make sure JDK 11 has been added. Select JDK 11 as the project JDK.

TESTING
--------------
You can run code coverage with `play autotest`

DEPENDENCY CHECK 
----------------
You can run a dependency check with `./bin/dependency-check.sh`

DEPLOYMENT
----------------
You can run a dependency check with `./bin/dependency-check.sh`



PLAY 1 FRAMEWORK DOCUMENTATION
----------------
https://www.playframework.com/documentation/1.5.x/home

PROBLEMS?
---------
Contact jamaicaballangan@gmail.com

**The RBIM Development Team**
