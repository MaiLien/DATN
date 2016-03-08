angular.module('appDATN.controller')
    .controller('StudentCtrl', function ($scope, StudentService) {

        $scope.students;
        $scope.status;

        $scope.getStudents = function (){
            StudentService.getStudents()
                .success(function (data) {
                    console.log("seccessss")
                    console.log(data);
                    $scope.students = data.body;
                })
                .error(function (error) {
                    console.log("loiii")
                    $scope.status = 'Unable to load customer data: ' + error.message;
                });
        }

    });