angular.module('appDATN.wave')
    .factory("ProjectWaveService", function ($http) {

        addWave = function (wave) {
            return $http.post('API/addProjectWave', wave);
        };

        getProjectWave = function(projectWaveId){
            console.log("called getProjectWave service : " + projectWaveId)
            return $http.get('API/getProjectWave', {params : {id: projectWaveId}});
        };

        getStudents = function(id){
            return $http.get('API/getStudentsOfProjectWave', {params : {id: id}});
        };

        getProjectWavesByPage = function(currentPage, pageSize, searchInput){
            return $http.get('API/getProjectWavesByPage', {params : {pageIndex: currentPage, sizeOfPage:pageSize, searchInput:searchInput}});
        };

        deleteProjectWave = function (id) {
            return $http.delete('API/deleteProjectWave', {params : {projectWaveId:id}})
        };

        return {
            addWave : addWave,
            getProjectWave : getProjectWave,
            getStudents : getStudents,
            getProjectWavesByPage: getProjectWavesByPage,
            deleteProjectWave: deleteProjectWave
        };

    });