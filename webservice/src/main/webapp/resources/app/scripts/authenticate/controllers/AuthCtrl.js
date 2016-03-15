angular.module('appDATN.auth')
    .controller('AuthCtrl', function ($scope, $state, AuthService) {

        $scope.status = true;

        $scope.login = function(user){
            AuthService.login(user)
                .success(function(data){
                    if(data.headers.resultCode == 0){
                        AuthService.setAuth();
                        $state.go('student.list');
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
                    $state.go('login')
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