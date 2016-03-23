'use strict';

angular.module('appDATN.auth').
    config(function ($stateProvider) {
        $stateProvider
            .state('login', {
                url: '/login',
                parent: 'anonymous',
                resolve: {
                    user: function ($q, $state, AuthService) {
                        var deferred = $q.defer();
                        AuthService.getSession()
                            .success(function(result){
                                if(result.body != null){
                                    deferred.reject('Logged')
                                }else{
                                    deferred.resolve();
                                }
                            })
                            .error(function(error){
                                deferred.reject('Error')
                            })
                        return deferred.promise;
                    }
                },
                views: {
                    //menu_view: {
                    //    controller: 'AuthCtrl',
                    //    templateUrl: '/resources/app/scripts/authenticate/views/_header.html'
                    //},
                    content_view: {
                        controller: 'AuthCtrl',
                        templateUrl: '/resources/app/scripts/authenticate/views/login.html'
                    }
                }
            });
    });