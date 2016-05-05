angular.module('appDATN.wave')
    .controller('DetailTabStudentsCtrl', function ($scope, $stateParams, ProjectWaveService, projectWaveStudents) {

        $scope.init = function(){
            $scope.infoMessage = null;
            $scope.errMessage = null;
            $scope.successMessage = null;
            $scope.studentTemp =null;
        };

        $scope.studentsOfWave = projectWaveStudents;

        $scope.init();

        $scope.setCurrentProjectWaveId($stateParams.projectWaveId);

        $scope.addStudentForWave = function(studentUsername){
            $scope.init();
            if(studentUsername != null && studentUsername != ""){
                var request = $scope.createAddStudentRequest(studentUsername);
                ProjectWaveService.addStudent(request)
                    .success(function(data){
                        if(data.headers.resultCode == 1062)
                            $scope.infoMessage = "Sinh viên đã tham gia đợt Đồ án";

                        else if(data.headers.resultCode == 1061)
                            $scope.errMessage = "Không tồn tại đợt đồ án";

                        else if(data.headers.resultCode == 1500)
                            $scope.errMessage = "Không tồn tại sinh viên này";

                        else if(data.headers.resultCode == 500)
                            $scope.errMessage = "Lỗi hệ thống";

                        else{
                            $scope.studentTemp = data.body;
                            $scope.successMessage = "Thêm thành công sinh viên vào đợt Đồ án";
                            $scope.studentUsername = null;
                            $scope.studentsOfWave.push($scope.studentTemp);
                        }
                    })
                    .error(function(error){

                    })
            }
        };

        $scope.createAddStudentRequest = function(studentUsername){
            return {
                studentUsername: studentUsername,
                projectWaveId: $scope.getCurrentProjectWaveId()
            };
        }

    });
