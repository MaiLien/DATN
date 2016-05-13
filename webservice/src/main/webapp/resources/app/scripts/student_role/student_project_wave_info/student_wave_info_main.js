'use strict';

angular.module('appDATN.student_wave')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student_role.waves_info', {
                parent: 'student_role.waves_student',
                url: '/wave/detail',
                views: {
                    content_view: {
                        controller:'StudentWavesInfoCtrl',
                        templateUrl: '/resources/app/scripts/student_role/student_project_wave_info/views/info_waves_student_join.html'
                    }
                }
            })
    });