'use strict';

angular.module('appDATN.student')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student_role', {
                abstract: true,
                url: '/student',
                parent: 'logged',
                views: {
                    sidebar_view: {
                        templateUrl: '/resources/app/scripts/student_role/student_home/views/_sidebar.html'
                    },
                    menu_header_view: {
                        controller:'StudentInfoCtrl',
                        templateUrl: '/resources/app/scripts/student_role/student_home/views/_header.html'
                    },
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    }
                }
            })
            .state('student_role.waves_student', {
                parent: 'student_role',
                abstract: true,
                url: '/wave/detail',
                resolve:{
                    user: function ($q, $state, AuthService) {
                        var deferred = $q.defer();
                        AuthService.getSession()
                            .success(function (data) {
                                if(data.headers.resultCode == 0) {
                                    if(data.body == null){
                                        $state.go('login');
                                    }else{
                                        deferred.resolve(data.body);
                                    }
                                }else{
                                    $state.go('login');
                                }
                            })
                            .error(function (error) {
                                deferred.reject('Error');
                            });
                        return deferred.promise;
                    }
                },
                views: {
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    }
                }
            })
    });