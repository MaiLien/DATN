angular.module('appDATN.wave')
    .controller('DetailProjectWaveCtrl', function ($scope, ProjectWaveService, projectWave) {
        $scope.projectWave = projectWave;
    });
