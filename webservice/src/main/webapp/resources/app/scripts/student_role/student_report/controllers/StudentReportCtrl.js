angular.module('appDATN.student_wave')
    .controller('StudentReportCtrl', function ($scope, $state, $mdMedia, $mdDialog, StudentReportService, user) {

        $scope.studentId = user.id;

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
                            $scope.getStudentReportsOfWave($scope.studentId, joiningProject.id);
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

        $scope.getStudentReportsOfWave = function(studentId, projectWaveId){
            StudentReportService.getStudentReportsOfWave(studentId, projectWaveId)
                .success(function (data) {
                    if(data.headers.resultCode == 1054){
                        $state.go('login');
                    }
                    else{
                        $scope.student = data.body.student;
                        $scope.projectWave = data.body.projectWave;
                        $scope.reports = data.body.reports;
                    }
                })
                .error(function (error) {
                    $state.go('error');
                })
        };

        $scope.setViewingReport = function (report) {
            $scope.viewing_report = report;
        };

        function load() {
            $scope.getWavesStudentJoined();
        }

        load();

    });