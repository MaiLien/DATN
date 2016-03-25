'use strict';

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
                //url: '/list/:page/:size',
                //resolve:{
                //    students: function ($q, $state, $stateParams, StudentService) {
                //        var deferred = $q.defer();
                //        StudentService.getStudents($stateParams.page, $stateParams.size)
                //            .success(function (data) {
                //                if(data.headers.resultCode == 0) {
                //                    deferred.resolve(data.body);
                //                }else{
                //                    deferred.reject('Error');
                //                }
                //            })
                //            .error(function (error) {
                //                deferred.reject('Error');
                //            });
                //        return deferred.promise;
                //    }
                //},
                views: {
                    content_view: {
                        controller: 'ListStudentCtrl',
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