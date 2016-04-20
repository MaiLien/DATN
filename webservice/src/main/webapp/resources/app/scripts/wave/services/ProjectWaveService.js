angular.module('appDATN.wave')
    .factory("ProjectWaveService", function ($http) {

        addWave = function (wave) {
            return $http.post('API/project-wave', wave);
        };

        return {
            addWave: addWave
        };

    });