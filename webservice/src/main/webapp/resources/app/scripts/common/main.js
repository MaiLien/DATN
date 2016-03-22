'use strict';

angular.module('appDATN.common')
    .config(function ($stateProvider) {
        $stateProvider
            .state('anonymous', {
                abstract: true,
                views: {
                    menu_view: {
                        template: '<div ui-view="menu_view"></div>'
                    },
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    },
                    footer_view: {
                        templateUrl: '/resources/app/scripts/common/views/_footer.html'
                    }
                }
            })
            .state('abs_logged', {
                abstract: true,
                views: {
                    menu_view: {
                        templateUrl: '/resources/app/scripts/common/views/logged.html'
                    },
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    },
                    footer_view: {
                        templateUrl: '/resources/app/scripts/common/views/_footer.html'
                    }
                }
            })
            .state('logged',{
                parent: 'abs_logged',
                abstract: true,
                resolve: {
                    user: function ($q, $state, AuthService) {
                        var deferred = $q.defer();
                        AuthService.getSession()
                            .success(function(result){
                                if(result.body == null){
                                    deferred.reject('Have_to_login')
                                }else{
                                    deferred.resolve(result.body);
                                }
                            })
                            .error(function(error){
                                deferred.reject('Error')
                            })
                        return deferred.promise;
                    }
                },
                views: {
                    menu_view: {
                        controller: 'AuthCtrl',
                        templateUrl: '/resources/app/scripts/common/views/_header.html'
                    },
                    sidebar_view: {
                        controller: 'AuthCtrl',
                        templateUrl: '/resources/app/scripts/common/views/_sidebar.html'
                    },
                    content_view: {
                        template: '<div id="wrapper"><div class="container-fluid"><div ui-view="content_view"></div></div></div>'
                    }
                }
            })
            .state('home',{
                parent: 'logged',
                views: {
                    content_view: {
                        templateUrl: '/resources/app/scripts/common/views/home.html'
                    }
                }
            })
            .state('error', {
                parent: 'anonymous',
                views: {
                    content_view: {
                        templateUrl: '/resources/app/scripts/common/views/error.html'
                    }
                }
            });
    });