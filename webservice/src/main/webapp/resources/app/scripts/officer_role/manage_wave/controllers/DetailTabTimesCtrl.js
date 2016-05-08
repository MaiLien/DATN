angular.module('appDATN.officer_wave')
    .controller('DetailTabTimesCtrl', function ($scope, $stateParams, ProjectWaveService, projectWaveTimes) {

        $scope.waveId = $stateParams.projectWaveId;

        $scope.projectWaveTimes = projectWaveTimes;

        $scope.setCurrentProjectWaveId($scope.waveId);

    });
