angular.module('appDATN.auth')
    .factory("AuthService", function ($http, $state) {

        login = function (user) {
            return $http.post(
                '/login',
                $.param(user),
                { headers: {'content-type': 'application/x-www-form-urlencoded'}});
        };

        getSession = function (){
            return $http.get('/session')
        };

        logout = function () {
            return $http.get('/logout')
        };

        return{
            login:login,
            getSession:getSession,
            logout:logout
        };

    });