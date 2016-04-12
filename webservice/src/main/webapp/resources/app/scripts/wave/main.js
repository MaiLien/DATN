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
    });