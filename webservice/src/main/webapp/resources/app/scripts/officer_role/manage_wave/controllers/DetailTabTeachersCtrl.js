angular.module('appDATN.officer_wave')
    .controller('DetailTabTeachersCtrl', function ($scope, $stateParams, ProjectWaveService, projectWaveTeachers) {

        $scope.init = function(){
            $scope.infoMessage = null;
            $scope.errMessage = null;
            $scope.successMessage = null;
            $scope.teacherTemp = null;
        };

        $scope.resetScopeVar = function(){
            $scope.teacherUsername = null;
            $scope.numberOfStudent = null;
        };

        $scope.teachersOfWave = projectWaveTeachers;

        $scope.init();

        $scope.setCurrentProjectWaveId($stateParams.projectWaveId);

        $scope.addTeacherForProjectWave = function(){
            $scope.init();
            $scope.setDirty();
            if($scope.addTeacherForWaveForm.$valid){
                var request = $scope.createAddTeacherRequest();
                ProjectWaveService.addTeacher(request)
                    .success(function(data){

                        if(data.headers.resultCode == 1062)
                            $scope.infoMessage = "Giảng viên đã tham gia đợt Đồ án";

                        else if(data.headers.resultCode == 1061)
                            $scope.errMessage = "Không tồn tại đợt đồ án";

                        else if(data.headers.resultCode == 1500)
                            $scope.errMessage = "Không tồn tại giảng viên này";

                        else if(data.headers.resultCode == 1063)
                            $scope.infoMessage = "Giảng viên đã tham gia đợt đồ án";

                        else if(data.headers.resultCode == 500)
                            $scope.errMessage = "Lỗi hệ thống";

                        else  if(data.headers.resultCode == 0){
                            $scope.teacherTemp = data.body;
                            $scope.successMessage = "Thêm thành công giảng viên vào đợt Đồ án";
                            $scope.teachersOfWave.push($scope.teacherTemp);
                            $scope.resetScopeVar();
                            $scope.addTeacherForWaveForm.$setPristine();

                        }
                        else{
                            $scope.errMessage = "Lỗi hệ thống";
                        }
                    })
                    .error(function(error){

                    })
            }
        };

        $scope.createAddTeacherRequest = function(){
            return {
                teacherUsername: $scope.teacherUsername,
                numberOfStudent: $scope.numberOfStudent,
                projectWaveId: $scope.getCurrentProjectWaveId()
            };
        };

        $scope.setDirty = function(){
            if($scope.addTeacherForWaveForm.$invalid){
                Object.keys($scope.addTeacherForWaveForm.$error).forEach(function (key) {
                    $scope.addTeacherForWaveForm.$error[key].forEach(function (control) {
                        control.$setDirty();
                    });
                });
            }
        };

        $scope.cancel = function(){
            $scope.init();
            $scope.resetScopeVar();
            $scope.addTeacherForWaveForm.$setPristine();
        }

    });
