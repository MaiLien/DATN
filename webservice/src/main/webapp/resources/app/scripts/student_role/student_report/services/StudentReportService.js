angular.module('appDATN.student_wave')
    .factory("StudentReportService", function ($http) {

        getStudentProjectInfoOfWaveResponse = function (studentId, projectWaveId) {
            return $http.get('API/getStudentProjectInfoOfWaveResponse', {params : {studentId: studentId, projectWaveId: projectWaveId}})
        };

        getWavesStudentJoined = function (studentId) {
            return $http.get('API/getWavesStudentJoined', {params : {studentId: studentId}})
        };

        saveStudentReport = function (studentReportRequest) {
            console.log(studentReportRequest);
            return $http.post('API/saveStudentReport', studentReportRequest);
        };

        return {
            getWavesStudentJoined: getWavesStudentJoined,
            getStudentProjectInfoOfWaveResponse: getStudentProjectInfoOfWaveResponse,
            saveStudentReport: saveStudentReport
        };

    });