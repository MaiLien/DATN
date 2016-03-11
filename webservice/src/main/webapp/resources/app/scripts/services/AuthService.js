angular.module('appDATN.service')
    .factory("AuthService", function ($http) {

        login = function (user) {
            return $http.post(
                '/login',
                $.param(user),
                { headers: {'content-type': 'application/x-www-form-urlencoded'}});
        }

        isStudent = function () {
            return true;
        }

        isTeacher = function () {
            return true;
        }

        isOfficer = function () {
            return true;
        }

        return{
            login:login
        };

    });