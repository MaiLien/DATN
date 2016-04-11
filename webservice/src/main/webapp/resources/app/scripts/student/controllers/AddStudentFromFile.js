angular.module('appDATN.student')
    .controller('AddStudentFromFileCtrl', function ($scope, $state, $timeout, StudentService) {

        $scope.addStudentFromFile = function(student){

        }

        $scope.uploadPic = function(file) {
            file.upload = Upload.upload({
                url: 'importStudentFromFile',
                data: {file: file},
            });

            file.upload.then(function (response) {
                $timeout(function () {
                    file.result = response.data;
                });
            }, function (response) {
                if (response.status > 0)
                    $scope.errorMsg = response.status + ': ' + response.data;
            }, function (evt) {
            });
        }

});