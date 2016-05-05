angular.module('appDATN.wave')
    .controller('DetailTabTimesCtrl', function ($scope, $stateParams, ProjectWaveService, projectWaveTimes) {

        $scope.waveId = $stateParams.projectWaveId;

        $scope.projectWaveTimes = projectWaveTimes;

        $scope.setCurrentProjectWaveId($scope.waveId);

    });
