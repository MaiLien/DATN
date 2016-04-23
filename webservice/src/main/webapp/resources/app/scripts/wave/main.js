angular.module('appDATN.wave')
    .config(function ($stateProvider) {
        $stateProvider
            .state('wave', {
                abstract: true,
                url: '/wave',
                parent: 'logged',
                views: {
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    }
                }
            })
            .state('wave.add', {
                url: '/add',
                views: {
                    content_view: {
                        controller: 'AddProjectWaveCtrl',
                        templateUrl: '/resources/app/scripts/wave/views/add_project_wave.html'
                    }
                }
            })
            .state('wave.list', {
                url: '/list',
                views: {
                    content_view: {
                        controller: 'ListProjectWaveCtrl',
                        templateUrl: '/resources/app/scripts/wave/views/list_project_wave.html'
                    }
                }
            })
            .state('wave.detail', {
                url: '/detail',
                resolve:{
                    projectWave: function ($q, $state, $stateParams, ProjectWaveService) {
                        var deferred = $q.defer();
                        ProjectWaveService.getProjectWave($stateParams.projectWaveId)
                            .success(function (data) {
                                if(data.headers.resultCode == 0) {
                                    deferred.resolve(data.body);
                                }else{
                                    deferred.reject('Error');
                                }
                            })
                            .error(function (error) {
                                deferred.reject('Error');
                            });
                        return deferred.promise;
                    }
                    //,students: function($q, $state, $stateParams, ProjectWaveService){
                    //    var deferred = $q.defer();
                    //    ProjectWaveService.getStudents($stateParams.projectWaveId)
                    //        .success(function (data) {
                    //            if(data.headers.resultCode == 0) {
                    //                deferred.resolve(data.body);
                    //            }else{
                    //                deferred.reject('Error');
                    //            }
                    //        })
                    //        .error(function (error) {
                    //            deferred.reject('Error');
                    //        });
                    //    return deferred.promise;
                    //}
                },
                views: {
                    content_view: {
                        controller: 'DetailProjectWaveCtrl',
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave.html'
                    }
                }
            })
            .state('wave.detail.times', {
                url: '/detail/tabs/times/:projectWaveId',
                views: {
                    detail_project_wave_tab_view: {
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave_tab_times.html'
                    }
                }
            })
            .state('wave.detail.students', {
                url: '/detail/tabs/students',
                views: {
                    detail_project_wave_tab_view: {
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave_tab_students.html'
                    }
                }
            })
            .state('wave.detail.teachers', {
                url: '/detail/tabs/teachers',
                views: {
                    detail_project_wave_tab_view: {
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave_tab_teachers.html'
                    }
                }
            })
            .state('wave.detail.assignments', {
                url: '/detail/tabs/assignments',
                views: {
                    detail_project_wave_tab_view: {
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave_tab_assignments.html'
                    }
                }
            })
    });

