'use strict';
var angular = require('angular');

require('../css/login.css')

function loginCtrl($scope, $sessionStorage, $state, $filter, LoginSvc) {
  $scope.progress = false;
  $scope.title = 'ui'
  $scope.copyrightYear = new Date().getFullYear();
  LoginSvc.logout();
  $scope.submitForm = function(isValid){
    if ( isValid ) {
      $scope.progress = true;
      LoginSvc.login($scope.username, $scope.password)
        .then(res => {
          $scope.progress = false;
          if ( res.authenticated ) {
            $state.go('home');
          } else {
            $scope.message = res.message;
          }
        })
        .catch(err => {
          $scope.progress = false;
          $scope.message = err;
        })
    }
  }
}

var stateConfig = {
  name: 'login',
  url: '/login',
  templateUrl: require('./login.html'),
  controller: 'loginCtrl'
};

loginCtrl.$inject = [
  '$scope',
  '$sessionStorage',
  '$state',
  '$filter',
  'LoginSvc'
]

function routeConfig($stateProvider) {
  $stateProvider.state(stateConfig)
}

angular.module('consumer')
  .controller('loginCtrl', loginCtrl)
  .config([ '$stateProvider', routeConfig ])

module.exports = stateConfig;
