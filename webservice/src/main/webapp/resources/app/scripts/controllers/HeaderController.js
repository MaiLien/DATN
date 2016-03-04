angular.module('appDATN.controller')
    .controller('HeaderController', function ($scope,AuthService) {
        $scope.authenticated = AuthService.isAuthenticated();
        $scope.isStudent = AuthService.isStudent();
        $scope.isTeacher = false;
        $scope.isOfficer = false;
    });