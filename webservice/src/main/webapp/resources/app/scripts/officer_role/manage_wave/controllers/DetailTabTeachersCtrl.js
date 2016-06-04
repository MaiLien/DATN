angular.module('appDATN.officer_wave')
    .controller('DetailTabTeachersCtrl', function ($scope, $stateParams, ProjectWaveService, projectWaveTeachers) {

        $scope.projectWaveId = $stateParams.projectWaveId;

        $scope.getTeachersToAddForProjectWave = function(){
            ProjectWaveService.getTeachersToAddForProjectWave($stateParams.projectWaveId)
                .success(function(data){
                    $scope.teachersToAddForWave = data.body;
                });
        };

        $scope.getTeachersOfWave = function(){
            ProjectWaveService.getTeachers($scope.projectWaveId)
                .success(function(data){
                    $scope.teachersOfProjectWave = data.body;
                });
        };

        $scope.addTeachersForWave = function(){
            var request = createAddTeacherForWaveRequest();
            if(request.teachers.length > 0){
                ProjectWaveService.addTeachersForWave(request)
                    .success(function(data){
                        $scope.getTeachersOfWave();
                    })
            }
        };

        $scope.deleteTeacherFromWave = function(teacher){
            ProjectWaveService.deleteTeacherFromWave(teacher.id, $scope.projectWaveId)
                .success(function(data){
                    $scope.getTeachersOfWave();
                })
        };

        createAddTeacherForWaveRequest = function(){
            var out = [];
            for(i=0; i<$scope.teachersToAddForWave.length; i++){
                if($scope.teachersToAddForWave[i].checked)
                    out.push({teacherId: $scope.teachersToAddForWave[i].id, maxGuide: $scope.teachersToAddForWave[i].maxGuide});
            }
            return {
                projectWaveId: $scope.projectWaveId,
                teachers: out
            }
        };

        load = function(){
            $scope.getTeachersOfWave();
        };

        load();

    });
