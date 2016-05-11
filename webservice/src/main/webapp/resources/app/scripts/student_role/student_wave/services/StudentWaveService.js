angular.module('appDATN.student_wave')
    .factory("StudentWaveService", function ($http) {

        getWavesStudentJoined = function (studentId) {
            return $http.get('API/getWavesStudentJoined', {params : {studentId: studentId}})
        };

        getListTeacherOfWave = function (waveId) {
            return $http.get('API/getTeachersOfProjectWave', {params : {id: waveId}})
        };

        getTeachersWhoStudentRegistered = function(studentId, waveId){
            return $http.get('API/getTeachersWhoDirectingStudentInProjectWave', {params : {studentId: studentId, waveId: waveId}});
        };

        return {
            getWavesStudentJoined: getWavesStudentJoined,
            getListTeacherOfWave: getListTeacherOfWave,
            getTeachersWhoStudentRegistered: getTeachersWhoStudentRegistered
        };

    });