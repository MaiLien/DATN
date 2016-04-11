angular.module('appDATN.student')
    .factory("StudentService", function ($http) {

        getStudents = function(pageIndex, sizeOfPage, searchInput){
            return $http.get('API/students', {params : {pageIndex: pageIndex, sizeOfPage:sizeOfPage, searchInput:searchInput}});
        }

        getStudent = function(studentId){
            return $http.get('API/student', {params : {id: studentId}})
        }

        addStudent = function(student){
            return $http.post('API/student', student)
        }

        addStudentFromFile = function(excelFile){
            return $http.post('importStudentFromFile', excelFile)
        }

        updateStudent = function(student){
            return $http.put('API/student', student)
        }

        deleteStudent = function(student){
            return $http.delete('API/student', {params : student})
        }

        lockStudent = function(student){
            return $http.put('API/lockStudent', student)
        }

        unlockStudent = function(student){
            return $http.put('API/unlockStudent', student)
        }

        return {
            getStudents: getStudents,
            getStudent: getStudent,
            addStudent: addStudent,
            addStudentFromFile: addStudentFromFile,
            updateStudent: updateStudent,
            deleteStudent: deleteStudent,
            lockStudent: lockStudent,
            unlockStudent: unlockStudent
        };

    });