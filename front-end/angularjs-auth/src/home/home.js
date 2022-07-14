'use strict';
var angular = require('angular');

require('../css/home.css')

function homeCtrl($scope, $sessionStorage, $state, $filter, LoginSvc) {
  $scope.title = 'ui';
  $scope.navBar = require('../includes/navbar.html')
  $scope.links = $state.get()
    .filter(x => x.name.startsWith('home.'))
    .map(x => {
      return {
        title: x.url.slice(1),
        link: $state.href(x.name)
      }
    });
  $scope.tableData = [
    ['#', 'Header', 'Header', 'Header', 'Header' ],
    ["1001","Lorem","ipsum","dolor","sit"],
    ["1002","amet","consectetur","adipiscing","elit"],
    ["1003","Integer","nec","odio","Praesent"],
    ["1003","libero","Sed","cursus","ante"],
    ["1004","dapibus","diam","Sed","nisi"],
    ["1005","Nulla","quis","sem","at"],
    ["1006","nibh","elementum","imperdiet","Duis"],
    ["1007","sagittis","ipsum","Praesent","mauris"],
    ["1008","Fusce","nec","tellus","sed"],
    ["1009","augue","semper","porta","Mauris"],
    ["1010","massa","Vestibulum","lacinia","arcu"],
    ["1011","eget","nulla","Class","aptent"],
    ["1012","taciti","sociosqu","ad","litora"],
    ["1013","torquent","per","conubia","nostra"]
  ]

  $scope.signout = signout;

  function signout(){
    LoginSvc.logout()
    $state.go('login')
  }

}

var stateConfig = {
  name: 'home',
  url: '/home',
  templateUrl: require('./home.html'),
  controller: 'homeCtrl'
};

homeCtrl.$inject = [
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
  .controller('homeCtrl', homeCtrl)
  .config([ '$stateProvider', routeConfig ])

module.exports = stateConfig;
