'use strict';

angular.module('appDATN.teacher')
    .config(function ($stateProvider) {
        $stateProvider
            .state('teacher_role.list_student', {
                parent: 'teacher_role.teacher_session',
                url: '/list_student',
                views: {
                    content_view: {
                        controller:'TeacherGuideStudentsCtrl',
                        templateUrl: '/resources/app/scripts/teacher_role/teacher_guide_students/views/teacher_guide_students.html'
                    }
                }
            })
    });