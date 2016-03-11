angular.module('appDATN.controller')
    .controller('AuthCtrl', function ($scope,AuthService) {

        $scope.isAuthenticated = false;

        $scope.login = function(user){
            AuthService.login(user)
                .success(function(data){
                    if(data.headers.resultCode == 0)
                        $scope.isAuthenticated = true;
                })
                .error(function(error){
                    console.log(error);
                })
        }

        //$scope.isAuthenticated = function(username, password){
        //    AuthService.isAuthenticated();
        //}
        //
        //
        //$scope.isStudent = AuthService.isStudent();
        //$scope.isTeacher = false;
        //$scope.isOfficer = false;
    });