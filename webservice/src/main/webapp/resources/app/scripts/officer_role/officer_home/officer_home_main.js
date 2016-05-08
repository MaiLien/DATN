'use strict';

angular.module('appDATN.officer')
    .config(function ($stateProvider) {
        $stateProvider
            .state('officer.home', {
                parent: 'officer',
                url: '/home',
                views: {
                    content_view: {
                        controller:'OfficerInfoCtrl',
                        templateUrl: '/resources/app/scripts/officer_role/officer_home/views/officer_home.html'
                    }
                }
            })
    });