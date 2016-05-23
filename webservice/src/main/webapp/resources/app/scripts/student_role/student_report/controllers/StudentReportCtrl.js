angular.module('appDATN.student_wave')
    .controller('StudentReportCtrl', function ($scope, $state, $mdMedia, $mdDialog, StudentReportService, user) {

        $scope.studentId = user.id;

        $scope.viewing_report = {
            reportDetails: [
                {
                    'startTimeAndEndTime':'01/01/2016 - 12/01/2016',
                    'workContent':'Chọn đề tài và phân tích thiết kế hệ thống',
                    'note':''
                }
            ]
        };

        refresh = function(){
            $scope.errMessage = null;
            $scope.successMessage = null;
            $scope.isTimeRegisters = false;

            $scope.joinedWaves = null;
            $scope.joiningWaves = null;
            $scope.joinedTeachers = null;
            $scope.teachersWhoStudentRegister = null;
        };

        $scope.getWavesStudentJoined = function () {
            StudentReportService.getWavesStudentJoined($scope.studentId)
                .success(function (data) {
                    var resultCode = data.headers.resultCode;

                    if(resultCode == 1054)
                        $state.go('login');

                    else if(resultCode == 0){
                        $scope.joinedWaves = data.body.projectWavesJoined;
                        $scope.joiningWaves = data.body.projectWavesJoining;
                        if($scope.joiningWaves.length == 1){
                            var joiningProject = $scope.joiningWaves[0];
                            $scope.getStudentProjectInfoOfWaveResponse($scope.studentId, joiningProject.id);
                        }
                    }

                    else{
                        $state.go('error');
                    }
                })
                .error(function (error) {
                    $state.go('error');
                });
        };

        $scope.getStudentProjectInfoOfWaveResponse = function(studentId, projectWaveId){
            StudentReportService.getStudentProjectInfoOfWaveResponse(studentId, projectWaveId)
                .success(function (data) {
                    if(data.headers.resultCode == 1054){
                        $state.go('login');
                    }
                    else{
                        console.log("aa");
                        $scope.student = data.body.student;
                        $scope.projectWave = data.body.projectWave;
                        $scope.projectInfo = data.body.projectInforResponse;
                        $scope.reports = $scope.projectInfo.reports;
                    }
                })
                .error(function (error) {
                    $state.go('error');
                })
        };

        $scope.setViewingReport = function (report) {
            $scope.viewing_report = report;
        };

        $scope.addItemOfReportDetails = function (reportDetail) {
            if(reportDetail != null && reportDetail.startTimeAndEndTime && reportDetail.workContent){
                $scope.viewing_report.reportDetails.push(reportDetail);
                $scope.reportDetail = null;
            }
            $scope.reportDetailsDirty = true;
        };

        $scope.deleteItemOfReportDetails = function(item){
            if(item != null){
                var index = $scope.viewing_report.reportDetails.indexOf(item);
                $scope.viewing_report.reportDetails.splice(index, 1);
            }
            $scope.reportDetailsDirty = true;
        };

        function load() {
            $scope.getWavesStudentJoined();
        }

        load();

    });