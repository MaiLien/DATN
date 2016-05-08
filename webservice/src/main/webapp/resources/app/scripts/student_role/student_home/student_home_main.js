'use strict';

angular.module('appDATN.student')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student_role.home', {
                parent: 'student_role',
                url: '/home',
                views: {
                    content_view: {
                        controller:'StudentInfoCtrl',
                        templateUrl: '/resources/app/scripts/student_role/student_home/views/student_home.html'
                    }
                }
            })
    });