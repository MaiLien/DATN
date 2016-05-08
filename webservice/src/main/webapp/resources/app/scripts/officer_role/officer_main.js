'use strict';

angular.module('appDATN.officer')
    .config(function ($stateProvider) {
        $stateProvider
            .state('officer', {
                abstract: true,
                url: '/officer',
                parent: 'logged',
                views: {
                    sidebar_view: {
                        templateUrl: '/resources/app/scripts/officer_role/officer_home/views/_sidebar.html'
                    },
                    menu_header_view: {
                        controller:'OfficerInfoCtrl',
                        templateUrl: '/resources/app/scripts/officer_role/officer_home/views/_header.html'
                    },
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    }
                }
            })
    });