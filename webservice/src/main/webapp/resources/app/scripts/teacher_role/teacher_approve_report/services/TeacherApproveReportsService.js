angular.module('appDATN.teacher')
    .factory("TeacherApproveReportsService", function ($http) {

        getReportToApprove = function(teacherId){
            return $http.get('API/getReportToApprove', {params : {teacherId: teacherId}})
        };

        approveReport = function(request){
            return $http.post('API/approveReport', request);
        };

        return {
            getReportToApprove: getReportToApprove,
            approveReport: approveReport
        };

    });