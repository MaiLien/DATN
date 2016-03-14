angular.module('appDATN.controller')
    .controller('AuthCtrl', function ($scope,$route ,AuthService) {

        $scope.status = true;

        $scope.login = function(user){
            AuthService.login(user)
                .success(function(data){
                    if(data.headers.resultCode == 0){
                        AuthService.setAuth();
                    }
                })
                .error(function(error){
                    $scope.status = false;
                    console.log("Login error")
                })
        }

        $scope.logout = function () {
            AuthService.logout()
                .success(function(){
                    AuthService.initAuth();
                })
                .error(function () {
                    $scope.status = false;
                    console.log("Logout error")
                })
        }

        $scope.getAuth = function () {
            return AuthService.getAuth();
        }

    });