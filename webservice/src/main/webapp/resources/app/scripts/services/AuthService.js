angular.module('appDATN.service')
    .factory("AuthService", function () {
        return {
            isAuthenticated: function () {
                return true;
            },
            isStudent: function () {
                return true;
            },
            isTeacher: function () {
                return true;
            },
            isOfficer: function () {
                return true;
            }

        }
    });