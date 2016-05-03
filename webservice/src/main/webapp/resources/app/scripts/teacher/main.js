'use strict';

angular.module('appDATN.teacher')
    .config(function ($stateProvider) {
        $stateProvider
            .state('teacher', {
                abstract: true,
                url: '/teacher',
                parent: 'logged',
                views: {
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    }
                }
            })
            .state('teacher.list', {
                url: '/list',
                views: {
                    content_view: {
                        controller: 'ListTeacherCtrl',
                        templateUrl: '/resources/app/scripts/teacher/views/list_teacher.html'
                    }
                }
            })
            .state('teacher.add', {
                url: '/add',
                views: {
                    content_view: {
                        controller: 'AddTeacherCtrl',
                        templateUrl: '/resources/app/scripts/teacher/views/add_teacher.html'
                    }
                }
            })
            .state('teacher.addFromFile', {
                url: '/addFromFile',
                views: {
                    content_view: {
                        controller: 'AddTeacherFromFileCtrl',
                        templateUrl: '/resources/app/scripts/teacher/views/add_teacher_from_file.html'
                    }
                }
            })
            .state('teacher.detail', {
                url: '/detail/:teacherObj',
                views: {
                    content_view: {
                        controller: 'TeacherDetailsCtrl',
                        templateUrl: '/resources/app/scripts/teacher/views/detail_teacher.html'
                    }
                }
            });
    });