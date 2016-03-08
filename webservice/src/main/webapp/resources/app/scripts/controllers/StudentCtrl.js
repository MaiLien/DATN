angular.module('appDATN.controller')
    .controller('StudentCtrl', function ($scope, StudentService) {

        $scope.status;
        $scope.students;
        $scope.student;

        $scope.getStudents = function (){
            StudentService.getStudents()
                .success(function (data) {
                    $scope.students = data.body;
                })
                .error(function (error) {
                    $scope.status = 'Unable to load customer data: ' + error.message;
                });
        }

        $scope.getStudent = function(student_id){
            StudentService.getStudent(student_id)
                .success(function(data){
                    $scope.student = data.body;
                })
                .error(function(error){
                    $scope.status = 'Unable to load customer data: ' + error.message;
                })
        }

        $scope.addStudent = function(){
            console.log('controller 0');
            $scope.student.id = '1234567';
            StudentService.addStudent($scope.student)
                .success(function(data){
                    console.log('controller 1');
                    $scope.student = data.body;
                })
                .error(function(error){
                    $scope.status = 'Unable to load customer data: ' + error.message;
                })
        }

    });