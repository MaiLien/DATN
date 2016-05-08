'use strict';

angular.module('appDATN.student')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student_role', {
                abstract: true,
                url: '/student',
                parent: 'logged',
                views: {
                    sidebar_view: {
                        templateUrl: '/resources/app/scripts/student_role/student_home/views/_sidebar.html'
                    },
                    menu_header_view: {
                        controller:'StudentInfoCtrl',
                        templateUrl: '/resources/app/scripts/student_role/student_home/views/_header.html'
                    },
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    }
                }
            })
    });