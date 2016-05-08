angular.module('appDATN.teacher')
    .factory("TeacherInfoService", function ($http) {

        getTeacherInfo = function () {
            return $http.get('/getTeacherInfo');
        };

        return {
            getTeacherInfo: getTeacherInfo
        };

    });