angular.module('appDATN.officer_wave')
    .controller('DetailTabAssignmentsCtrl', function ($scope, $stateParams, ProjectWaveService) {

        $scope.displayOptions = [{value: 0, text: 'Hiện thị theo danh sách sinh viên'}, {value: 1, text: 'Hiện thị theo danh sách giảng viên'}];
        $scope.selectedDisplayOption = $scope.displayOptions[0].value;

        $scope.setCurrentProjectWaveId($stateParams.projectWaveId);

    });
