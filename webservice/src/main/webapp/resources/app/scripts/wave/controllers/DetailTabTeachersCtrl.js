angular.module('appDATN.wave')
    .controller('DetailTabTeachersCtrl', function ($scope, $stateParams, ProjectWaveService, projectWaveTeachers) {

        $scope.init = function(){
            $scope.infoMessage = null;
            $scope.errMessage = null;
            $scope.successMessage = null;
            $scope.teacherTemp =null;
        };

        $scope.teachersOfWave = projectWaveTeachers;

        $scope.init();

        $scope.setCurrentProjectWaveId($stateParams.projectWaveId);

        $scope.addTeacherForProjectWave = function(teacherUsername){
            console.log("$scope.addTeacherForWave");
            $scope.init();
            if(teacherUsername != null && teacherUsername != ""){
                var request = $scope.createAddTeacherRequest(teacherUsername);
                ProjectWaveService.addTeacher(request)
                    .success(function(data){
                        console.log(data);
                        if(data.headers.resultCode == 1062)
                            $scope.infoMessage = "Giảng viên đã tham gia đợt Đồ án";

                        else if(data.headers.resultCode == 1061)
                            $scope.errMessage = "Không tồn tại đợt đồ án";

                        else if(data.headers.resultCode == 1500)
                            $scope.errMessage = "Không tồn tại giảng viên này";

                        else if(data.headers.resultCode == 500)
                            $scope.errMessage = "Lỗi hệ thống";

                        else{
                            $scope.teacherTemp = data.body;
                            $scope.successMessage = "Thêm thành công giảng viên vào đợt Đồ án";
                            $scope.teacherUsername = null;
                            $scope.teachersOfWave.push($scope.teacherTemp);
                        }
                    })
                    .error(function(error){

                    })
            }
        };

        $scope.createAddTeacherRequest = function(teacherUsername){
            return {
                teacherUsername: teacherUsername,
                projectWaveId: $scope.getCurrentProjectWaveId()
            };
        }

    });
