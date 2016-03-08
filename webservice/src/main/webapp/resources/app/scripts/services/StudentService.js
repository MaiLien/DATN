angular.module('appDATN.service')
    .factory("StudentService", function ($http) {

        getStudents = function(){
            return $http.get('API/students');
        }

        getStudent = function(studentId){
            return $http.get('API/student', {params : {id: studentId}})
        }

        addStudent = function(student){
            return $http.post('API/student', {params : {id: student.id}})
        }

        return {
            getStudents: getStudents,
            getStudent: getStudent,
            addStudent: addStudent
        };

    });