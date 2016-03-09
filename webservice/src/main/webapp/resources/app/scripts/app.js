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
        templateUrl: '/resources/app/views/login.html',
        controller: 'AuthCtrl',
        controllerAs: 'auth'
      })
      .when('/login', {
        templateUrl: '/resources/app/views/login.html',
        controller: 'AuthCtrl',
        controllerAs: 'auth'
      })
      .when('/listStudent', {
        templateUrl: '/resources/app/views/list_student.html',
        controller: 'StudentCtrl',
        controllerAs: 'student'
      })
      .when('/getStudent', {
        templateUrl: '/resources/app/views/get_student.html',
        controller: 'StudentCtrl',
        controllerAs: 'student'
      })
      .when('/addStudent', {
        templateUrl: '/resources/app/views/add_student.html',
        controller: 'StudentCtrl',
        controllerAs: 'student'
      })
      .when('/updateStudent', {
        templateUrl: '/resources/app/views/update_student.html',
        controller: 'StudentCtrl',
        controllerAs: 'student'
      })
      .when('/deleteStudent', {
        templateUrl: '/resources/app/views/delete_student.html',
        controller: 'StudentCtrl',
        controllerAs: 'student'
      })
      .otherwise({
        redirectTo: '/'
      });
  });

angular.module('appDATN.controller', ['appDATN.service']);

angular.module('appDATN.service', ['appDATN.service']);
