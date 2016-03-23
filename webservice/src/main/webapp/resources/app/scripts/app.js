'use strict';

angular.module('appDATN.common', ['ui.router'])
angular.module('appDATN.auth', ['ui.router'])
angular.module('appDATN.student', ['appDATN.auth', 'ui.router'])

var app = angular.module('appDATN', [
  'appDATN.common',
  'appDATN.auth',
  'appDATN.student',
  'ui.router'
]);

/* Go to login if not authenticated */
app.run(function ($rootScope, $state) {
  $rootScope.$on('$stateChangeError', function (event, toState, toParams, fromState, fromParams, error) {
    if (error === 'Have_to_login') {
      $state.go('login');
    }
  });

  $rootScope.$on('$stateChangeError', function (event, toState, toParams, fromState, fromParams, error) {
    if (error === 'Logged') {
      $state.go('home');
    }
  });

  $rootScope.$on('$stateChangeError', function (event, toState, toParams, fromState, fromParams, error) {
    if (error === 'Error') {
      $state.go('error');
    }
  });
});

/* Set a default state */
app.config(function ($urlRouterProvider) {
  $urlRouterProvider.when('', '/login');
  //$urlRouterProvider.otherwise('/login');
});


