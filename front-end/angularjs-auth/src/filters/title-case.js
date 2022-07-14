'use strict'

const angular = require('angular')

function capitalize(s){
  return s[0].toUpperCase() + s.slice(1).toLowerCase();
}

function titleCase(){
  return (input) => input.replace(/([a-z])([A-Z])/g, '$1 $2').split(' ').map(capitalize).join(' ')
}


angular.module('consumer')
  .filter('titlecase', titleCase)

module.exports = {
  name: 'titlecase',
  filter: titleCase
}
