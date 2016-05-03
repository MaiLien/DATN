angular.module('appDATN.student')
    .controller('AddStudentCtrl', function ($scope, $state, StudentService) {

        $scope.errMessage = null;

        $scope.addStudent = function(student){
            if($scope.addStudentForm.$valid){
                StudentService.addStudent(student)
                    .success(function(data){
                        $scope.student = null;
                        $scope.addStudentForm.$setPristine();
                        if(data.headers.resultCode == 1060)
                            $scope.errMessage = "Mã sinh viên đã tồn tại.";
                        if(data.headers.resultCode == 500)
                            $scope.errMessage = "Lỗi hệ thống.";
                        //$state.go('student.list');
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

    });