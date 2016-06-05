angular.module('appDATN.student')
    .controller('TeacherGuideStudentsCtrl', function ($scope, $state, user, TeacherGuideStudentsService) {

        $scope.teacherId = user.id;
        $scope.view_list = true;

        $scope.getProjectWaveTeacherJoin = function(){
            TeacherGuideStudentsService.getProjectWaveTeacherJoin($scope.teacherId)
                .success(function (data) {
                    var resultCode = data.headers.resultCode;
                    if(resultCode == 1054) {
                        $state.go('login');
                    }
                    if (resultCode == 0) {
                        $scope.projectWavesJoined = data.body.projectWavesJoined;
                        $scope.projectWavesJoining = data.body.projectWavesJoining;
                        if($scope.projectWavesJoining.length > 0)
                            $scope.selectedWaveOption = $scope.projectWavesJoining[0].id;
                        else if($scope.projectWavesJoined > 0)
                            $scope.selectedWaveOption = $scope.projectWavesJoined[0].id;
                        $scope.getListStudent();
                    }else{
                        $state.go('error');
                    }
                })
                .error(function (error) {
                    $state.go('error');
                })
        };

        $scope.getListStudent = function () {
            $scope.view_list = true;
            if($scope.selectedWaveOption != null){
                TeacherGuideStudentsService.getListStudentWhoTeacherGuideInWave($scope.teacherId, $scope.selectedWaveOption)
                    .success(function(data){
                        var resultCode = data.headers.resultCode;
                        if(resultCode == 1054) {
                            $state.go('login');
                        }
                        if (resultCode == 0) {
                            $scope.students = data.body;
                        }
                        else{
                            $state.go('error');
                        }
                    })
                    .error(function (error){
                        $state.go('error');
                    })
            }
        };

        $scope.view_student_details = function (studentId) {
            $scope.view_list = false;
            TeacherGuideStudentsService.getStudentProjectInfoOfWaveResponse(studentId, $scope.selectedWaveOption)
                .success(function(data){
                    console.log(data);
                    $scope.student = data.body.student;
                    $scope.projectWave = data.body.projectWave;
                    $scope.projectInfo = data.body.projectInforResponse;
                    $scope.reports = $scope.projectInfo.reports;
                })
                .error(function (error){

                })
        };

        $scope.setViewingReport = function(report){
            $scope.viewingReport = report;
        };

        $scope.acceptReport = function(){
            if($scope.approveReportForm.$valid){
                var request = createApproveReportRequest(2);
                TeacherGuideStudentsService.approveReport(request)
                    .success(function(data){
                        $scope.getListStudent();
                    })
            }
        };

        $scope.refundReport = function(){
            if($scope.approveReportForm.$valid){
                var request = createApproveReportRequest(3);
                TeacherGuideStudentsService.approveReport(request)
                    .success(function(data){
                        $scope.getListStudent();
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
            $scope.getProjectWaveTeacherJoin();
        };

        load();

    });