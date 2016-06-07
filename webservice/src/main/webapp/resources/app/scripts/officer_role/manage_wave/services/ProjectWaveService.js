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

        getTeachersToAddForProjectWave = function(projectWaveId){
            return $http.get('API/getTeachersToAddForProjectWave', {params : {id: projectWaveId}});
        };

        addTeachersForWave = function(request){
            return $http.post('API/addTeachersForWave', request);

        };

        deleteTeacherFromWave = function(teacherId, projectWaveId){
            return $http.delete('API/deleteTeacherFromWave', {params : {teacherId: teacherId, projectWaveId: projectWaveId}})
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
            return $http.post('API/addTeacherForProjectWave', addTeacherForWaveRequest);
        };

        getAssignmentsDispalyedInStudents = function(projectWaveId){
            return $http.get('API/getAssignmentsDispalyedInStudents', {params : {projectWaveId: projectWaveId}});
        };

        getAssignmentsDispalyedInTeachers = function(projectWaveId){
            return $http.get('API/getAssignmentsDispalyedInTeachers', {params : {projectWaveId: projectWaveId}});
        };

        getTeachersToChangeAssignment = function(studentId, projectWaveId){
            return $http.get('API/getTeachersToChangeAssignment', {params : {studentId: studentId, projectWaveId: projectWaveId}});
        };

        changeAssignment = function(request){
            return $http.post('API/changeAssignment', request);
        };

        getReportsOfWave = function(projectWaveId){
            return $http.get('API/getReportsOfWave', {params : {projectWaveId: projectWaveId}});
        };

        getReportStatistic = function(reportId){
            return $http.get('API/getReportStatistic', {params : {reportId: reportId}});
        };

        return {
            addWave : addWave,
            getProjectWave : getProjectWave,
            getStudents : getStudents,
            getTeachers: getTeachers,
            getTeachersToAddForProjectWave: getTeachersToAddForProjectWave,
            addTeachersForWave: addTeachersForWave,
            deleteTeacherFromWave: deleteTeacherFromWave,
            getProjectWavesByPage: getProjectWavesByPage,
            deleteProjectWave: deleteProjectWave,
            addStudent: addStudent,
            addTeacher: addTeacher,
            getAssignmentsDispalyedInStudents: getAssignmentsDispalyedInStudents,
            getAssignmentsDispalyedInTeachers: getAssignmentsDispalyedInTeachers,
            getTeachersToChangeAssignment: getTeachersToChangeAssignment,
            changeAssignment: changeAssignment,
            getReportsOfWave: getReportsOfWave,
            getReportStatistic: getReportStatistic
        };

    });