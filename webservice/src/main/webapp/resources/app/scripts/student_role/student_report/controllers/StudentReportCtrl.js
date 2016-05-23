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
            $scope.viewing_report = angular.copy(report);
            $scope.reportDetail = null;
            $scope.reportDetailsDirty = false;

        };

        $scope.addItemOfReportDetails = function (reportDetail) {
            if(reportDetail != null && reportDetail.startTimeAndEndTime && reportDetail.workContent){
                if($scope.viewing_report.reportDetails == null){
                    $scope.viewing_report.reportDetails = [];
                }
                $scope.viewing_report.reportDetails.push(reportDetail);
                $scope.reportDetail = null;
                $scope.reportDetailsDirty = false;
            }else{
                $scope.reportDetailsDirty = true;
            }
        };

        $scope.deleteItemOfReportDetails = function(item){
            $scope.reportDetailsDirty = false;
            if(item != null){
                var index = $scope.viewing_report.reportDetails.indexOf(item);
                $scope.viewing_report.reportDetails.splice(index, 1);
            }
            if($scope.viewing_report.reportDetails.length == 0){
                $scope.reportDetailsDirty = true;
            }
        };

        $scope.saveReportWithStatusIsDone = function(report){
            var request = createReportRequest(report, 1);
            $scope.saveStudentReport(request);
        };

        $scope.saveReportWithStatusIsDraft = function(report){
            var request = createReportRequest(report, 0);
            $scope.saveStudentReport(request);
        };

        createReportRequest = function(report, status){
            console.log(report);
            return {
                id: report.id,
                studentId: $scope.studentId,
                reportId: report.reportOfWaveId,
                status: status,
                percentFinish: report.percentFinish,
                studentOpinion: report.studentOpinion,
                teacherOpinion: report.teacherOpinion,
                reportDetails: report.reportDetails
            }
        };

        $scope.saveStudentReport = function(request){
            StudentReportService.saveStudentReport(request)
                .success(function(data){
                    var resultCode = data.headers.resultCode;
                    if(resultCode == 1054)
                        $state.go('login');
                    else if(resultCode == 0)
                        $scope.getWavesStudentJoined();
                })
                .error(function(error){
                    $state.go('error');
                })
        };

        function load() {
            $scope.getWavesStudentJoined();
        }

        load();

    });