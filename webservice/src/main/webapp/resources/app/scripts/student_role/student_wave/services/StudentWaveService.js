angular.module('appDATN.student_wave')
    .factory("StudentWaveService", function ($http) {

        getWavesStudentJoined = function (studentId) {
            return $http.get('API/getWavesStudentJoined', {params : {studentId: studentId}})
        };

        return {
            getWavesStudentJoined: getWavesStudentJoined
        };

    });