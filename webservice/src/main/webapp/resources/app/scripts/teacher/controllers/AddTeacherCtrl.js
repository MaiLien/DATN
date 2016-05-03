angular.module('appDATN.teacher')
    .controller('AddTeacherCtrl', function ($scope, $state, TeacherService) {

        $scope.errMessage = null;

        $scope.addTeacher = function(teacher){
            if($scope.addTeacherForm.$valid){
                TeacherService.addTeacher(teacher)
                    .success(function(data){
                        $scope.teacher = null;
                        $scope.addTeacherForm.$setPristine();
                        if(data.headers.resultCode == 1060)
                            $scope.errMessage = "Mã giảng viên đã tồn tại.";
                        if(data.headers.resultCode == 500)
                            $scope.errMessage = "Lỗi hệ thống.";
                        //$state.go('teacher.list');
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

    });