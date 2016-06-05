'use strict';

angular.module('appDATN.teacher')
    .config(function ($stateProvider) {
        $stateProvider
            .state('teacher_role.reports', {
                parent: 'teacher_role.teacher_session',
                url: '/reports',
                views: {
                    content_view: {
                        controller:'TeacherApproveReportsCtrl',
                        templateUrl: '/resources/app/scripts/teacher_role/teacher_approve_report/views/teacher_approve_reports.html'
                    }
                }
            })
    });