'use strict';

angular.module('appDATN.common')
    .config(function ($stateProvider) {
        $stateProvider
            .state('anonymous', {
                abstract: true,
                views: {
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    },
                    css_imported: {
                        templateUrl: '/resources/app/scripts/authenticate/views/_css_imported.html'
                    },
                    js_imported: {
                        templateUrl: '/resources/app/scripts/authenticate/views/_js_imported.html'
                    }
                }
            })
            .state('abs_logged', {
                abstract: true,
                views: {
                    menu_view: {
                        templateUrl: '/resources/app/scripts/common/views/_header.html'
                    },
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
                    },
                    footer_view: {
                        templateUrl: '/resources/app/scripts/common/views/_footer.html'
                    },
                    css_imported: {
                        templateUrl: '/resources/app/scripts/common/views/_css_imported.html'
                    },
                    js_imported: {
                        templateUrl: '/resources/app/scripts/common/views/_js_imported.html'
                    },
                    sidebar_view: {
                        //controller: 'AuthCtrl',
                        templateUrl: '/resources/app/scripts/common/views/_sidebar.html'
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
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
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