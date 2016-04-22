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
            .state('wave.detail', {
                url: '/detail/:projectWaveId',
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
                },
                views: {
                    content_view: {
                        controller: 'DetailProjectWaveCtrl',
                        templateUrl: '/resources/app/scripts/wave/views/detail_project_wave.html'
                    }
                }
            })
    });