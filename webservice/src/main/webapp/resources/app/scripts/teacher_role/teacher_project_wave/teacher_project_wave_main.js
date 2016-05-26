'use strict';

angular.module('appDATN.teacher')
    .config(function ($stateProvider) {
        $stateProvider
            .state('teacher_role.project_wave_list', {
                parent: 'teacher_role.teacher_session',
                url: '/project_wave_list',
                views: {
                    content_view: {
                        controller:'TeacherProjectWaveCtrl',
                        templateUrl: '/resources/app/scripts/teacher_role/teacher_project_wave/views/teacher_list_project_wave.html'
                    }
                }
            })
    });