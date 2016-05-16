angular.module('appDATN.student_wave')
    .factory("StudentReportService", function ($http) {

        getStudentReportsOfWave = function (studentId, projectWaveId) {
            return $http.get('API/getStudentReportsOfWave', {params : {studentId: studentId, projectWaveId: projectWaveId}});
        };

        return {
            getStudentReportsOfWave: getStudentReportsOfWave
        };

    });