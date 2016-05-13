angular.module('appDATN.student_wave')
    .factory("StudentWaveInfoService", function ($http) {

        getWavesStudentJoined = function (studentId) {
            return $http.get('API/getWavesStudentJoined', {params : {studentId: studentId}})
        };

        getTeachersOfProjectWave = function (waveId) {
            return $http.get('API/getTeachersOfProjectWave', {params : {id: waveId}})
        };

        return {
            getWavesStudentJoined: getWavesStudentJoined,
            getTeachersOfProjectWave: getTeachersOfProjectWave
        };

    });