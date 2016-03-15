'use strict';

angular.module('appDATN.auth').
    config(function ($stateProvider) {
        $stateProvider
            .state('login', {
                url: '/login',
                parent: 'anonymous',
                views: {
                    menu_view: {
                        controller: 'AuthCtrl',
                        templateUrl: '/resources/app/scripts/authenticate/views/_header.html'
                    },
                    content_view: {
                        controller: 'AuthCtrl',
                        templateUrl: '/resources/app/scripts/authenticate/views/login.html'
                    }
                }
            });
    });