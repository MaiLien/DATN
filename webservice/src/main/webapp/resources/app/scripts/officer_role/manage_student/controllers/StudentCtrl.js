angular.module('appDATN.officer_student')
    .controller('StudentCtrl', function ($scope, $state, StudentService, AuthService, $stateParams) {

        $scope.status;
        $scope.students;
        $scope.student;
        $scope.studentCurrent;

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
            StudentService.deleteStudent(student)
                .success(function(data){
                    $scope.student = data.body;
                })
                .error(function(error){
                    $scope.status = 'Unable to load customer data: ' + error.message;
                })
        }

        $scope.setStudentCurrent = function (student) {
            $scope.studentCurrent = student;
        }

        $scope.detailStudent = function () {
            $scope.student = JSON.parse($stateParams.studentObj);
        }

    });