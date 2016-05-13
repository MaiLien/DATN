angular.module('appDATN.student_wave')
    .factory("StudentRegisterTeacherService", function ($http) {

        getWavesStudentJoined = function (studentId) {
            return $http.get('API/getWavesStudentJoined', {params : {studentId: studentId}})
        };

        getTeachersOfProjectWave = function (waveId) {
            return $http.get('API/getTeachersOfProjectWave', {params : {id: waveId}})
        };

        getTeachersWhoStudentRegistered = function(studentId, waveId){
            return $http.get('API/getTeachersWhoDirectingStudentInProjectWave', {params : {studentId: studentId, waveId: waveId}});
        };

        cancelRegisterTeacher = function(request){
            return $http.post('API/cancelRegisterTeacher', request);
        };

        registerTeacher = function(request){
            return $http.post('API/registerTeacher', request);
        };

        return {
            getWavesStudentJoined: getWavesStudentJoined,
            getTeachersOfProjectWave: getTeachersOfProjectWave,
            getTeachersWhoStudentRegistered: getTeachersWhoStudentRegistered,
            cancelRegisterTeacher: cancelRegisterTeacher,
            registerTeacher: registerTeacher
        };

    });