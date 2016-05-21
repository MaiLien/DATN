'use strict';

angular.module('appDATN.student_wave')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student_role.student_report', {
                parent: 'student_role.student_session',
                url: '/wave/report',
                views: {
                    content_view: {
                        controller:'StudentReportCtrl',
                        templateUrl: '/resources/app/scripts/student_role/student_report/views/student_report.html'
                    }
                }
            })
    });