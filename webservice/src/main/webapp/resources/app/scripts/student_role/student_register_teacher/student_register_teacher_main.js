'use strict';

angular.module('appDATN.student_wave')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student_role.student_register_teacher_for_wave', {
                parent: 'student_role.waves_student',
                url: '/wave/registerTeacher',
                views: {
                    content_view: {
                        controller:'StudentRegisterTeacherForWave',
                        templateUrl: '/resources/app/scripts/student_role/student_register_teacher/views/student_register_teacher_for_wave.html'
                    }
                }
            })
    });