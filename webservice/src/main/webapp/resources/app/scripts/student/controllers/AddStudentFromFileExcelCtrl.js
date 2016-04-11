angular.module('appDATN.student')
    .controller('AddStudentFromFileCtrl', function ($scope, $timeout, StudentService) {

        $scope.addStudentFromFile = function (student) {

        }

        $scope.uploadPic = function (file) {
            StudentService.addStudentFromFile(file)
                .then(function (response) {
                    $timeout(function () {
                        file.result = response.data;
                        $scope.smsResult = "ok";
                    });
                }, function (response) {
                    if (response.status > 0)
                        $scope.smsResult = "fail";
                        $scope.errorMsg = response.status + ': ' + response.data;
                }, function (evt) {
                    //progress
                });
        }

    });