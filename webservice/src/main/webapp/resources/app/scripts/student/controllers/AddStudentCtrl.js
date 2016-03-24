angular.module('appDATN.student')
    .controller('AddStudentCtrl', function ($scope, $state, StudentService) {

        $scope.addStudent = function(student){
            if($scope.addStudentForm.$valid){
                StudentService.addStudent(student)
                    .success(function(data){
                        $scope.student = data.body;
                    })
                    .error(function(error){
                        $scope.status = 'Unable to load customer data: ' + error.message;
                    });
                $scope.addStudentForm.$setPristine();
            }else{
                $scope.setDirty();
            }
        }

        $scope.setDirty = function(){
            if($scope.addStudentForm.$invalid){
                Object.keys($scope.addStudentForm.$error).forEach(function (key) {
                    $scope.addStudentForm.$error[key].forEach(function (control) {
                        control.$setDirty();
                    });
                });
                return;
            }
        }

    });