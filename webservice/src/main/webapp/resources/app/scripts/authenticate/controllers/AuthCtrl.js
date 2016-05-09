angular.module('appDATN.auth')
    .controller('AuthCtrl', function ($scope, $state, AuthService) {

        $scope.error = false;

        $scope.login = function(user){
            $scope.error = false;
            AuthService.login(user)
                .success(function(data){
                    if(data.headers.resultCode == 0){
                        var typeOfUser = data.body.typeOfUser;
                        switch (typeOfUser){//TEACHER(0), STUDENT(1), OFFICER(2), ADMIN(3);
                            case 0:
                                $state.go('teacher_role.home');
                                break;
                            case 1:
                                $state.go('student_role.home');
                                break;
                            case 2:
                                $state.go('officer.home');
                                break;
                        }
                    }
                    else{
                        $scope.error = true;
                    }
                })
                .error(function(error){
                    console.log("Login error");
                    $state.go('error');
                })
        };

        $scope.logout = function () {
            $scope.error = false;
            AuthService.logout()
                .success(function(){
                    $state.go('login')
                })
                .error(function () {
                    console.log("Logout error");
                    $state.go('error');
                })
        };
    });