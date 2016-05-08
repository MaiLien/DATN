'use strict';

angular.module('appDATN.teacher')
    .config(function ($stateProvider) {
        $stateProvider
            .state('teacher_role', {
                abstract: true,
                url: '/teacher',
                parent: 'logged',
                views: {
                    sidebar_view: {
                        templateUrl: '/resources/app/scripts/teacher_role/teacher_home/views/_sidebar.html'
                    },
                    menu_header_view: {
                        controller:'TeacherInfoCtrl',
                        templateUrl: '/resources/app/scripts/teacher_role/teacher_home/views/_header.html'
                    },
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    }
                }
            })
    });