angular.module('appDATN.wave')
    .factory("ProjectWaveService", function ($http) {

        addWave = function (wave) {
            return $http.post('API/addProjectWave', wave);
        };

        getProjectWave = function(projectWaveId){
            console.log("called getProjectWave service : " + projectWaveId);
            return $http.get('API/getProjectWave', {params : {id: projectWaveId}});
        };

        getStudents = function(projectWaveId){
            console.log("called getStudents service : " + projectWaveId);
            return $http.get('API/getStudentsOfProjectWave', {params : {id: projectWaveId}});
        };

        getProjectWavesByPage = function(currentPage, pageSize, searchInput){
            return $http.get('API/getProjectWavesByPage', {params : {pageIndex: currentPage, sizeOfPage:pageSize, searchInput:searchInput}});
        };

        deleteProjectWave = function (id) {
            return $http.delete('API/deleteProjectWave', {params : {projectWaveId:id}})
        };

        addStudent = function(studentId){
            console.log("called addStudent service : " + studentId);
            return $http.post('API/addStudentForProjectWave', studentId);
        };

        return {
            addWave : addWave,
            getProjectWave : getProjectWave,
            getStudents : getStudents,
            getProjectWavesByPage: getProjectWavesByPage,
            deleteProjectWave: deleteProjectWave,
            addStudent: addStudent
        };

    });