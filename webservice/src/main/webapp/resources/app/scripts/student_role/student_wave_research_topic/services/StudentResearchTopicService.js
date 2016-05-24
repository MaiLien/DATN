angular.module('appDATN.student_wave')
    .factory("StudentResearchTopicService", function ($http) {

        getWavesStudentJoined = function (studentId) {
            return $http.get('API/getWavesStudentJoined', {params : {studentId: studentId}})
        };

        getStudentWaveResearchTopic = function(studentId, projectWaveId){
            return $http.get('API/getStudentWaveResearchTopic', {params : {studentId: studentId, projectWaveId: projectWaveId}})
        };

        saveResearchTopic = function(request){
            return $http.post('API/saveResearchTopic', request)
        };


        return {
            getWavesStudentJoined: getWavesStudentJoined,
            getStudentWaveResearchTopic: getStudentWaveResearchTopic,
            saveResearchTopic: saveResearchTopic
        };

    });