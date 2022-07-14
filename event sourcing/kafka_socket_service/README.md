# Kafka in Socket Service

## Requirements
 - NodeJS - https://nodejs.org/en/download/, version **8+** 

## Purpose

The purpose of the app is to read the ISB Kafka feed and forward filtered data to socket clients.

## Libraries used

- *kafkajs*, a Kafka Node client module (https://kafka.js.org)
- *winston*, a Node logging module (https://www.npmjs.com/package/winston)
- *Socket.io* (server: https://www.npmjs.com/package/socket.io; client: https://www.npmjs.com/package/socket.io-client)
- *config*, a configuration files manager (https://www.npmjs.com/package/config)
- *rxjs*, reactive extensions (https://www.npmjs.com/package/rxjs)
- *lodash*, a JS utility collection (https://www.npmjs.com/package/lodash)

## Building the app

- Install the required packages:
```shell script
$ npm i
```
- Run the tests:
```shell script
$ npm run test
$ npm run test-coverage
```
- Run the build script (this will also run the tests):
```shell script
$ npm run build
```
Note: the build and tests will be executed against the *qa environment* set of configuration, as a default.
Alternatively, you can run the builds of specific environments: 
```shell script
$ npm run build-qa
$ npm run build-stage
$ npm run build-prod
```

## Configuration
### Config files
By default, the *config* Node module will attempt to use the *./config* folder in the application root. Optionally, to 
override this, you must set the **NODE_CONFIG_DIR** environment variable with the path to the config folder of your choice.

The build of the application is bundled with a set of example configuration folders, specific to environments. These are 
located in the **./config-examples/** folder and they are as following:

- *config-dev*: used for development
- *config-qa*: general instance of KSS for QA environment, with no specific target
- *config-qa-nj*: KSS QA instance, targeted for Network Jackpot product
- *config-stage-lux*: instance of KSS for stage Lux environment
- *config-stage-asia*: KSS stage Asia instance
- *config-prod-lux*: instance of KSS for Lux prod environment
- *config-prod-asia*: KSS Asia prod instance

In the deployment task, please copy one of these folders in the root of the application, with the name **config**

_Example (assuming you are in the application folder root):_
```shell script
$ cp -R ./config-examples/config-qa-nj ./config
```

### App environment
The app supports **qa**, **stage** and **prod** environments, which need to be set in the **ENV** variable, to be used by the 
config and testing modules. Also, each environment will also use the proper configuration files per environment.

Each app environment will connect to the appropriate Kafka instance and provide a socket server host, ergo, a QA build 
will connect to Kafka QA and provide the following hosts for socket server:

- QA:
  - https://kss-qa.isoftbet.com:3012
  - https://kss-qa-nj.isoftbet.com:3012
- stage:
  - https://kss-stage.isoftbet.com:3013
  - https://kss-nj-lux-stage.isoftbet.com:3013
- production:
  - https://kss.isoftbet.com:3014
  - https://kss-nj-lux.isoftbet.com:3014

The Kafka configurations used can be found [here](https://confluence.isoftbet.com:8088/display/TEAM/Kafka+Deployment+Plan).

### Deployment
The project repository has four branches:
- *release/devel*, used for project development
- *release/qa*
- *release/stage*
- *release/prod*

In order to deploy, you simply need to merge your changes from dev to qa branch and the QA build will start then deploy.
Same goes for qa to stage and stage to prod.

**WARNING:** DUE TO THE CONNECTION AND SYNCING TIME OF KAFKA, YOUR FILTERS WILL START LATE (GENERALLY IN ABOUT A MINUTE
AFTER DEPLOYMENT)

## License

Copyright (c) 2019 iSoftBet, All Rights Reserved.

## Notice

You may not use, distribute or modify this document without the written permission of its copyright owner.

