angular.module('appDATN.student')
    .controller('TeacherApproveReportsCtrl', function ($scope, $state, TeacherApproveReportsService, user) {

        $scope.teacherId = user.id;

        $scope.getReportToApprove = function () {
            TeacherApproveReportsService.getReportToApprove($scope.teacherId)
                .success(function(data){
                    $scope.reports = data.body;
                })
                .error(function (error) {

                })
        };

        $scope.setViewingReport = function(report){
            $scope.viewingReport = report;
        };

        $scope.acceptReport = function(){
            if($scope.acceptReportFrom.$valid){
                var request = createApproveReportRequest(2);
                TeacherApproveReportsService.approveReport(request)
                    .success(function(data){
                        $scope.getReportToApprove();
                    })
            }
        };

        $scope.refundReport = function(){
            if($scope.refundReportForm.$valid){
                var request = createApproveReportRequest(3);
                TeacherApproveReportsService.approveReport(request)
                    .success(function(data){
                        $scope.getReportToApprove();
                    })
            }
        };

        createApproveReportRequest = function(status){
            return{
                reportId: $scope.viewingReport.id,
                teacherOpinions: $scope.viewingReport.teacherOpinions,
                status: status
            }
        };

        load = function(){
            $scope.getReportToApprove();
        };

        load();

    });