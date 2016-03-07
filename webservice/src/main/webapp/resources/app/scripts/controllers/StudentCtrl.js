angular.module('appDATN.controller')
    .controller('StudentCtrl', function ($scope, StudentService) {
        //StudentService.getStudents().success(function (students){
        //    $scope.students = students;
        //});

        $scope.students = StudentService.getStudents();

        //$scope.students = function(){|
        //    $http.get('API/students').success(function(users){
        //        $scope.ssstudents = users;
        //    });
        //}
    });