'use strict';

/**
 * @ngdoc overview
 * @name appApp
 * @description
 * # appApp
 *
 * Main module of the application.
 */
angular
  .module('appDATN', ['appDATN.controller', 'ngRoute'])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: '/resources/app/views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: '/resources/app/views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .otherwise({
        redirectTo: '/'
      });
  });

angular.module('appDATN.controller', ['appDATN.service']);

angular.module('appDATN.service', []);
