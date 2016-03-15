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
});

/* Set a default state */
app.config(function ($urlRouterProvider) {
  $urlRouterProvider.when('', '/login');
});

