angular.module('appDATN.student')
    .controller('StudentInfoCtrl', function ($scope, $state, StudentInfoService, AuthService) {

        $scope.student;

        $scope.logout = function () {
            $scope.error = false;
            AuthService.logout()
                .success(function(){
                    AuthService.initAuth();
                    $state.go('login')
                })
                .error(function () {
                    $state.go('error');
                })
        };

        load = function(){
            StudentInfoService.getStudentInfo()
                .success(function(data){
                    if(data.body == null)
                        $state.go('login');
                    else{
                        $scope.student = data.body;
                    }
                })
                .error(function (error) {

                })
        };

        load();

    });