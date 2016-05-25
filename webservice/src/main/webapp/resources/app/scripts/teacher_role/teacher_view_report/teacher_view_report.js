'use strict';

angular.module('appDATN.teacher')
    .config(function ($stateProvider) {
        $stateProvider
            .state('teacher_role.reports', {
                parent: 'teacher_role',
                url: '/reports',
                views: {
                    content_view: {
                        controller:'TeacherViewsReportsCtrl',
                        templateUrl: '/resources/app/scripts/teacher_role/teacher_view_report/views/teacher_view_reports.html'
                    }
                }
            })
    });