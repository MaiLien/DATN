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

        $scope.addStudent = function(student){
            StudentService.addStudent(student)
                .success(function(data){
                    $scope.student = data.body;
                })
                .error(function(error){
                    $scope.status = 'Unable to load customer data: ' + error.message;
                })
        }

        $scope.updateStudent = function(student){
            StudentService.updateStudent(student)
                .success(function(data){
                    $scope.student = data.body;
                })
                .error(function(error){
                    $scope.status = 'Unable to load customer data: ' + error.message;
                })
        }

        $scope.deleteStudent = function(student){
            //student.id = '09a70730-6067-4c09-afa4-08db8a68d537';
            StudentService.deleteStudent(student)
                .success(function(data){
                    $scope.student = data.body;
                })
                .error(function(error){
                    $scope.status = 'Unable to load customer data: ' + error.message;
                })
        }

    });