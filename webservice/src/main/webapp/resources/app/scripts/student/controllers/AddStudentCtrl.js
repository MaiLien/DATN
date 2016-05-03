angular.module('appDATN.student')
    .controller('AddStudentCtrl', function ($scope, $state, StudentService) {

        $scope.errMessage = null;
        $scope.successMessage = false;

        $scope.initMessage = function(){
            $scope.errMessage = null;
            $scope.successMessage = false;
        };

        $scope.addStudent = function(student){
            $scope.initMessage();
            if($scope.addStudentForm.$valid){
                StudentService.addStudent(student)
                    .success(function(data){
                        if(data.headers.resultCode == 1060)
                            $scope.errMessage = "Mã sinh viên đã tồn tại";
                        else if(data.headers.resultCode == 500)
                            $scope.errMessage = "Lỗi hệ thống";
                        else{
                            $scope.successMessage = true;
                            $scope.student = null;
                            $scope.addStudentForm.$setPristine();
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
            if($scope.addStudentForm.$invalid){
                Object.keys($scope.addStudentForm.$error).forEach(function (key) {
                    $scope.addStudentForm.$error[key].forEach(function (control) {
                        control.$setDirty();
                    });
                });
            }
        }

        $scope.cancel = function(){
            $scope.initMessage();
            $scope.student = null;
            $scope.addStudentForm.$setPristine();
        }

    });