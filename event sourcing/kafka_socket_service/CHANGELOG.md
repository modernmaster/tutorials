
# Change Log
All notable changes to this project will be documented in this file.
 
The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/).

## [1.2.1] - 2021-02-22

### Added
- unit tests code coverage
- kss_environment field to logs
- filtering by skinID for wallet_transaction
- multiple events support

### Changed
- kafkaBorkers in stage asia config
- SSL certificates
- TLS to min v1.2 
 
## [1.2.0] - 2021-01-12

### Added
- Competition Labs (POC) topic and mock data
- catching lsof errors

### Changed
- implements unlimited monitoring
- refactoring logs
- update tests for backoffice_realtime filtering
 
## [1.1.0] - 2020-06-18
 
### Added
- sentry.io monitoring
- volume test script
- writing to kafka only for the specified topic reply
- timer to always have a valid token available 
- error handling and logging for producer API 
- displaying app version via '/version' path
- this changelog (Yay!)
- example configs for NJ and updates README.md
- SSO client details and producer API for Lux stage and prod
 
### Changed
- set win trigger, from wallet_approved: true to false; adds formatted logging of socket count
- filtering for JP_BO to use initial progressive ID and levelID
- timestamp format to ISO datetime
- permanent configs, enables gap message mockup only for dev environment
- updated operator identifier for universal GAP message
- removed mocked data for universal GAP message
 
### Fixed
- small issue with accessing empty array
 
## [1.0.0] - 2020-04-22
 
### Added

- stable and tested implementation of KSS, soft release
   
### Changed
 
### Fixed

