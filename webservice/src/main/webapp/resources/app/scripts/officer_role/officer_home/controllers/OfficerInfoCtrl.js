angular.module('appDATN.officer')
    .controller('OfficerInfoCtrl', function ($scope, $state, OfficerInfoService, AuthService) {

        $scope.officer;

        $scope.logout = function () {
            $scope.error = false;
            AuthService.logout()
                .success(function(){
                    $state.go('login')
                })
                .error(function () {
                    //$scope.error = true;
                    console.log("Logout error")
                    $state.go('error');
                })
        };

        load = function(){
            OfficerInfoService.getOfficerInfo()
                .success(function(data){
                    if(data.body == null)
                        $state.go('login');
                    else{
                        $scope.officer = data.body;
                    }
                })
                .error(function (error) {

                })
        };

        load();

    });