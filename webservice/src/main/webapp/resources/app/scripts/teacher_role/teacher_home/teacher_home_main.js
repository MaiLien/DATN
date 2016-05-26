'use strict';

angular.module('appDATN.teacher')
    .config(function ($stateProvider) {
        $stateProvider
            .state('teacher_role.home', {
                parent: 'teacher_role.teacher_session',
                url: '/home',
                views: {
                    content_view: {
                        controller:'TeacherInfoCtrl',
                        templateUrl: '/resources/app/scripts/teacher_role/teacher_home/views/teacher_home.html'
                    }
                }
            })
    });