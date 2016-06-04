angular.module('appDATN.teacher')
    .factory("TeacherGuideStudentsService", function ($http) {


        getProjectWaveTeacherJoin = function(teacherId){
            return $http.get('API/getWavesTeacherJoined', {params : {teacherId: teacherId}})
        };

        getListStudentWhoTeacherGuideInWave = function(teacherId, waveId){
            return $http.get('API/getListStudentWhoTeacherGuideInWave', {params : {teacherId: teacherId, waveId: waveId}})
        };

        getStudentProjectInfoOfWaveResponse = function (studentId, projectWaveId) {
            return $http.get('API/getStudentProjectInfoOfWaveResponse', {params : {studentId: studentId, projectWaveId: projectWaveId}});
        };

        approveReport = function(request){
            return $http.post('API/approveReport', request);
        };

        return {
            getProjectWaveTeacherJoin: getProjectWaveTeacherJoin,
            getListStudentWhoTeacherGuideInWave: getListStudentWhoTeacherGuideInWave,
            getStudentProjectInfoOfWaveResponse: getStudentProjectInfoOfWaveResponse,
            approveReport: approveReport
        };

    });