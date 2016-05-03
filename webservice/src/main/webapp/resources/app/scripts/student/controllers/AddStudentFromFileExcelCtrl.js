angular.module('appDATN.student')
    .controller('AddStudentFromFileCtrl', function ($scope, $timeout, StudentService) {

        $scope.successItems = null;
        $scope.failItems = null;
        $scope.errMessage = null;
        $scope.totalItem = 0;

        $scope.addStudentFromFile = function (file) {
            $scope.resetData();
            StudentService.addStudentFromFile(file)
                .then(function (response) {
                    console.log(response);
                    $scope.successItems = response.data.body.successItems;
                    $scope.failItems = response.data.body.failItems;
                    $scope.totalItem = response.data.body.totalItem;
                }, function (response) {
                    if (response.status != 200)
                        $scope.errMessage = "Lỗi hệ thống";
                        //$scope.errorMsg = response.status + ': ' + response.data;
                }, function (evt) {
                    //progress
                });
        }

        $scope.resetData = function(){
            $scope.successItems = null;
            $scope.failItems = null;
            $scope.errMessage = null;
            $scope.totalItem = 0;
        }

    });