angular.module('appDATN.officer_wave')
    .controller('DetailTabTeachersCtrl', function ($scope, $stateParams, ProjectWaveService) {

        $scope.setCurrentProjectWaveId($stateParams.projectWaveId);

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
                    var resultCode = data.headers.resultCode;
                    if(resultCode == 1054){
                        $state.go('login');
                    }
                    else if(resultCode == 0){
                        $scope.teachersOfProjectWave = data.body;
                    }
                    else{
                        $state.go('error');
                    }
                })
                .error(function(error){
                    $state.go('error');
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

        $scope.setTeacherToDelete=function(teacher){
            $scope.teacherToDelete = teacher;
        };

        $scope.deleteTeacherFromWave = function(){
            ProjectWaveService.deleteTeacherFromWave($scope.teacherToDelete.id, $scope.projectWaveId)
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
