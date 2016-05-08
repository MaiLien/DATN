angular.module('appDATN.officer_wave')
    .factory("ProjectWaveService", function ($http) {

        addWave = function (wave) {
            return $http.post('API/addProjectWave', wave);
        };

        getProjectWave = function(projectWaveId){
            return $http.get('API/getProjectWave', {params : {id: projectWaveId}});
        };

        getStudents = function(projectWaveId){
            return $http.get('API/getStudentsOfProjectWave', {params : {id: projectWaveId}});
        };

        getTeachers = function(projectWaveId){
            return $http.get('API/getTeachersOfProjectWave', {params : {id: projectWaveId}});
        };

        getProjectWavesByPage = function(currentPage, pageSize, searchInput){
            return $http.get('API/getProjectWavesByPage', {params : {pageIndex: currentPage, sizeOfPage:pageSize, searchInput:searchInput}});
        };

        deleteProjectWave = function (id) {
            return $http.delete('API/deleteProjectWave', {params : {projectWaveId:id}})
        };

        addStudent = function(studentId){
            return $http.post('API/addStudentForProjectWave', studentId);
        };

        addTeacher = function(addTeacherForWaveRequest){
            console.log("called addTeacher service : " + addTeacherForWaveRequest);
            return $http.post('API/addTeacherForProjectWave', addTeacherForWaveRequest);
        };

        return {
            addWave : addWave,
            getProjectWave : getProjectWave,
            getStudents : getStudents,
            getTeachers: getTeachers,
            getProjectWavesByPage: getProjectWavesByPage,
            deleteProjectWave: deleteProjectWave,
            addStudent: addStudent,
            addTeacher: addTeacher
        };

    });