'use strict';

angular.module('appDATN.student_wave')
    .config(function ($stateProvider) {
        $stateProvider
            .state('student_role.wave_list', {
                parent: 'student_role',
                url: '/wave/list',
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
                        controller:'StudentWaveListCtrl',
                        templateUrl: '/resources/app/scripts/student_role/student_wave/views/list_wave_student_join.html'
                    }
                }
            })
    });