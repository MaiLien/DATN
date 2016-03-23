angular.module('appDATN.auth')
    .controller('AuthCtrl', function ($scope, $state, AuthService) {

        $scope.error = false;

        $scope.login = function(user){
            $scope.error = false;
            AuthService.login(user)
                .success(function(data){
                    if(data.headers.resultCode == 0){
                        AuthService.setAuth();
                        $state.go('student.list');
                    }
                    else{
                        $scope.error = true;
                    }
                })
                .error(function(error){
                    //$scope.error = true;
                    console.log("Login error")
                    $state.go('error');
                })
        }

        $scope.logout = function () {
            $scope.error = false;
            AuthService.logout()
                .success(function(){
                    AuthService.initAuth();
                    $state.go('login')
                })
                .error(function () {
                    //$scope.error = true;
                    console.log("Logout error")
                    $state.go('error');
                })
        }

        $scope.getAuth = function () {
            return AuthService.getAuth();
        }

    });