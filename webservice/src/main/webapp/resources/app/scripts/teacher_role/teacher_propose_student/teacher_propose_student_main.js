'use strict';

angular.module('appDATN.teacher')
    .config(function ($stateProvider) {
        $stateProvider
            .state('teacher_role.propose_student', {
                parent: 'teacher_role',
                url: '/propose_student',
                views: {
                    content_view: {
                        controller:'TeacherProposeStudentCtrl',
                        templateUrl: '/resources/app/scripts/teacher_role/teacher_propose_student/views/teacher_propose_student.html'
                    }
                }
            })
    });