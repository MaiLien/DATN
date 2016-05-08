'use strict';

angular.module('appDATN.student_wave')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student_role.wave_list', {
                parent: 'student_role',
                url: '/wave/list',
                views: {
                    content_view: {
                        controller:'StudentWaveListCtrl',
                        templateUrl: '/resources/app/scripts/student_role/student_wave/views/list_wave_student_join.html'
                    }
                }
            })
    });