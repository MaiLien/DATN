angular.module('appDATN.student')
    .controller('AddStudentCtrl', function ($scope, $state, StudentService) {

        $scope.addStudent = function(student){
            if($scope.addStudentForm.$valid){
                StudentService.addStudent(student)
                    .success(function(data){
                        $scope.student = data.body;
                        $scope.addStudentForm.$setPristine();
                        $state.go('student.list');
                    })
                    .error(function(error){
                        $state.go('error');
                    });

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

        $scope.datepickerOptions = {
            format: 'yyyy-mm-dd',
            language: 'fr',
            startDate: "2012-10-01",
            endDate: "2012-10-31",
            autoclose: true,
            weekStart: 0
        }

    });