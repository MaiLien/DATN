angular.module('appDATN.student')
    .factory("StudentInfoService", function ($http) {

        getStudentInfo = function () {
            return $http.get('/getStudentInfo');
        };

        return {
            getStudentInfo: getStudentInfo
        };

    });