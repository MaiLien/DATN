angular.module('appDATN.wave')
    .factory("ProjectWaveService", function ($http) {

        addWave = function (wave) {
            return $http.post('API/addProjectWave', wave);
        };

        getProjectWave = function(projectWaveId){
            return $http.get('API/getProjectWave', {params : {id: projectWaveId}});
        };

        getStudents = function(id){
            return $http.get('API/getStudentsOfProjectWave', {params : {id: id}});
        };

        return {
            addWave : addWave,
            getProjectWave : getProjectWave,
            getStudents : getStudents
        };

    });