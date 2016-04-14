'use strict';

angular.module('appDATN.common', ['ui.router']);
angular.module('appDATN.auth', []);
angular.module('appDATN.student', ['appDATN.auth', 'appDATN.common']);
angular.module('appDATN.wave', ['ngMaterial']);

var app = angular.module('appDATN', [
  'appDATN.common',
  'appDATN.auth',
  'appDATN.student',
  'appDATN.wave',
  'ui.router',
  'bw.paging',
  'ngMaterial',
  'ngFileUpload'
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
app.config(function ($urlRouterProvider, $httpProvider) {
  $urlRouterProvider.when('', '/login');
  $httpProvider.defaults.headers.post['Content-Type'] = 'application/json; charset=utf-8';
  $httpProvider.defaults.headers.common['Content-Type'] = 'application/json; charset=utf-8';
  //$urlRouterProvider.otherwise('/login');
});


