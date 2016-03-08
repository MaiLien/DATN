//angular.module('appDATN.service')
//    .factory("StudentService", function ($http) {
//        return {
//            getStudents: function () {
//                $http.get('API/students').success(function(students){
//                    return students;
//                });
//            }
//
//        }
//    });

angular.module('appDATN.service')
    .factory("StudentService", function ($http) {

        getStudents = function () {
            console.log("service called")
            return $http.get('API/students');
        }

        return {
            getStudents: getStudents
        };

    });