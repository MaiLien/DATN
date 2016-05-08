'use strict';

angular.module('appDATN.officer_student')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student', {
                abstract: true,
                url: '/student',
                parent: 'officer',
                views: {
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    }
                }
            })
            .state('student.list', {
                url: '/list',
                views: {
                    content_view: {
                        controller: 'ListStudentCtrl',
                        templateUrl: '/resources/app/scripts/officer_role/manage_student/views/list_student.html'
                    }
                }
            })
            .state('student.add', {
                url: '/add',
                views: {
                    content_view: {
                        controller: 'AddStudentCtrl',
                        templateUrl: '/resources/app/scripts/officer_role/manage_student/views/add_student.html'
                    }
                }
            })
            .state('student.addFromFile', {
                url: '/addFromFile',
                views: {
                    content_view: {
                        controller: 'AddStudentFromFileCtrl',
                        templateUrl: '/resources/app/scripts/officer_role/manage_student/views/add_student_from_file.html'
                    }
                }
            })
            .state('student.detail', {
                url: '/detail/:studentObj',
                views: {
                    content_view: {
                        controller: 'StudentCtrl',
                        templateUrl: '/resources/app/scripts/officer_role/manage_student/views/detail_student.html'
                    }
                }
            });
    });