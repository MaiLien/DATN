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
            if($scope.approveReportForm.$valid){
                var request = createApproveReportRequest(2);
                TeacherApproveReportsService.approveReport(request)
                    .success(function(data){
                        var resultCode = data.headers.resultCode;
                        if(resultCode == 1054) {
                            $state.go('login');
                        }
                        if (resultCode == 0) {
                            $scope.getReportToApprove();
                        }
                    })
            }
        };

        $scope.refundReport = function(){
            if($scope.approveReportForm.$valid){
                var request = createApproveReportRequest(3);
                TeacherApproveReportsService.approveReport(request)
                    .success(function(data){
                        var resultCode = data.headers.resultCode;
                        if(resultCode == 1054) {
                            $state.go('login');
                        }
                        if (resultCode == 0) {
                            $scope.getReportToApprove();
                        }
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

        $scope.setOptionApprove = function(option){
            $scope.isRefund = option;
        };

        load = function(){
            $scope.getReportToApprove();
        };

        load();

    });