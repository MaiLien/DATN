'use strict';

angular.module('appDATN.teacher')
    .config(function ($stateProvider) {
        $stateProvider
            .state('teacher_role', {
                abstract: true,
                url: '/teacher',
                parent: 'logged',
                views: {
                    sidebar_view: {
                        templateUrl: '/resources/app/scripts/teacher_role/teacher_home/views/_sidebar.html'
                    },
                    menu_header_view: {
                        controller:'TeacherInfoCtrl',
                        templateUrl: '/resources/app/scripts/teacher_role/teacher_home/views/_header.html'
                    },
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    }
                }
            })
            .state('teacher_role.teacher_session', {
                parent: 'teacher_role',
                abstract: true,
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