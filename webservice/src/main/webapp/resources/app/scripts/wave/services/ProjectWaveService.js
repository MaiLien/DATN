angular.module('appDATN.wave')
    .factory("ProjectWaveService", function ($http) {

        addWave = function (wave) {
            return $http.post('API/addProjectWave', wave);
        };

        getProjectWave = function(projectWaveId){
            return $http.get('API/getProjectWave', {params : {id: projectWaveId}});
        };

        return {
            addWave: addWave,
            getProjectWave : getProjectWave
        };

    });