angular.module('appDATN.teacher')
    .factory("TeacherProjectWaveService", function ($http) {

        getProjectWaveTeacherJoin = function(teacherId){
            return $http.get('API/getWavesTeacherJoined', {params : {teacherId: teacherId}})
        };

        getStudentsOfProjectWaveToPropose = function (teacherId, projectWaveId) {
            return $http.get('API/getStudentsOfProjectWaveToPropose', {params : {teacherId: teacherId, projectWaveId: projectWaveId}});
        };

        proposeStudent = function(request){
            return $http.post('API/proposeStudent', request);
        };

        return {
            getProjectWaveTeacherJoin : getProjectWaveTeacherJoin,
            getStudentsOfProjectWaveToPropose : getStudentsOfProjectWaveToPropose,
            proposeStudent : proposeStudent
        };

    });