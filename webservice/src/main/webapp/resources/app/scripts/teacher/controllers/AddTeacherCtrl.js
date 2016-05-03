angular.module('appDATN.teacher')
    .controller('AddTeacherCtrl', function ($scope, $state, TeacherService) {

        $scope.errMessage = null;
        $scope.successMessage = false;

        $scope.initMessage = function(){
            $scope.successMessage = false;
            $scope.errMessage = null;
        };

        $scope.addTeacher = function(teacher){
            $scope.initMessage();
            if($scope.addTeacherForm.$valid){
                TeacherService.addTeacher(teacher)
                    .success(function(data){
                        if(data.headers.resultCode == 1060){
                            $scope.errMessage = "Mã giảng viên đã tồn tại";
                        }
                        else if(data.headers.resultCode == 500){
                            $scope.errMessage = "Lỗi hệ thống";
                        }
                        else{
                            $scope.teacher = null;
                            $scope.successMessage = true;
                            $scope.addTeacherForm.$setPristine();
                        }
                    })
                    .error(function(error){
                        $state.go('error');
                    });

            }else{
                $scope.setDirty();
            }
        };

        $scope.setDirty = function(){
            if($scope.addTeacherForm.$invalid){
                Object.keys($scope.addTeacherForm.$error).forEach(function (key) {
                    $scope.addTeacherForm.$error[key].forEach(function (control) {
                        control.$setDirty();
                    });
                });
            }
        }

        $scope.cancel = function(){
            $scope.initMessage();
            $scope.teacher = null;
            $scope.addTeacherForm.$setPristine();
        }

    });