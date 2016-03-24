angular.module('appDATN.student')
    .controller('ListStudentCtrl', function ($scope, $state, StudentService, students) {

        $scope.students = students;

            $scope.getStudents = function () {
            StudentService.getStudents()
                .success(function (data) {
                    if (data.headers.resultCode == 0) {
                        $scope.students = data.body;
                    }
                })
                .error(function (error) {
                    $state.go('error');
                });
        }
        //
        //function load() {
        //    $scope.getStudents();
        //}
        //
        //load();
    });