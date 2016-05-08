angular.module('appDATN.officer_teacher')
    .factory("TeacherService", function ($http, Upload) {

        getTeachers = function(pageIndex, sizeOfPage, searchInput){
            return $http.get('API/getTeachersByPage', {params : {pageIndex: pageIndex, sizeOfPage:sizeOfPage, searchInput:searchInput}});
        };

        getTeacher = function(teacherId){
            return $http.get('API/getTeacher', {params : {id: teacherId}})
        };

        addTeacher = function(teacher){
            return $http.post('API/addTeacher', teacher)
        };

        addTeacherFromFile = function(file){
            return file.upload = Upload.upload({
                url: '/API/importTeacherFromFile',
                data: {excelFile: file}
            });
        };

        updateTeacher = function(teacher){
            return $http.put('API/updateTeacher', teacher)
        };

        deleteTeacher = function(teacher){
            return $http.delete('API/deleteTeacher', {params : teacher})
        };

        return {
            getTeachers: getTeachers,
            getTeacher: getTeacher,
            addTeacher: addTeacher,
            addTeacherFromFile: addTeacherFromFile,
            updateTeacher: updateTeacher,
            deleteTeacher: deleteTeacher
        };

    });