angular.module('appDATN.service')
    .factory("StudentService", function () {
        return {
            getStudents: function () {
                $http.get('API/students').success(function(students){
                    return students;
                });
            },
            addStudent: function () {
                return true;
            },
            deleteStudent: function () {
                return true;
            },
            updateStudent: function () {
                return true;
            }

        }
    });