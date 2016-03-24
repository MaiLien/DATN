'use strict';

var app = angular.module('appDATN.student', [
    'ui.router'
]);

angular.module('appDATN.student')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student', {
                abstract: true,
                url: '/student',
                parent: 'logged',
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
                        controller: 'StudentCtrl',
                        templateUrl: '/resources/app/scripts/student/views/list_student.html'
                    }
                }
            })
            .state('student.add', {
                url: '/add',
                views: {
                    content_view: {
                        //controller: 'StudentCtrl',
                        templateUrl: '/resources/app/scripts/student/views/add_student.html'
                    }
                }
            })
            .state('student.detail', {
                url: '/detail/:studentObj',
                views: {
                    content_view: {
                        controller: 'StudentCtrl',
                        templateUrl: '/resources/app/scripts/student/views/detail_student.html'
                    }
                }
            });
    });