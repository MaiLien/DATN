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
            .state('logged', {
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
                            });
                        return deferred.promise;
                    }
                },
                views: {
                    footer_view: {
                        templateUrl: '/resources/app/scripts/common/views/_footer.html'
                    },
                    css_imported: {
                        templateUrl: '/resources/app/scripts/common/views/_css_imported.html'
                    },
                    js_imported: {
                        templateUrl: '/resources/app/scripts/common/views/_js_imported.html'
                    },
                    menu_header_view: {
                        template: '<div ui-view="menu_header_view"></div>'
                    },
                    sidebar_view: {
                        template: '<div ui-view="sidebar_view"></div>'
                    },
                    content_view: {
                        template: '<div ui-view="content_view"></div>'
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