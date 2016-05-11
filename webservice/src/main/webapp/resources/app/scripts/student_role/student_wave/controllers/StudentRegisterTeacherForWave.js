angular.module('appDATN.student_wave')
    .controller('StudentRegisterTeacherForWave', function ($scope, $state, StudentWaveService, user) {

        $scope.joinedWaves;
        $scope.joiningWaves;
        $scope.joinedTeachers;
        $scope.teachersWhoStudentRegister;
        $scope.studentId = user.id;

        $scope.getTeachersOfProjectWave = function (projectWaveId) {
            StudentWaveService.getTeachersOfProjectWave(projectWaveId)
                .success(function(data){
                    if(data.headers.resultCode == 0){
                        $scope.joinedTeachers = data.body;
                    }else{
                        $state.go('error');
                    }
                })
                .error(function (error) {
                    $state.go('error');
                })
        };

        $scope.getTeachersWhoStudentRegistered = function(studentId, waveId){
            StudentWaveService.getTeachersWhoStudentRegistered(studentId, waveId)
                .success(function(data){
                    if(data.headers.resultCode == 0){
                        $scope.teachersWhoStudentRegister = data.body;
                    }else{
                        $state.go('error');
                    }
                })
                .error(function(error){
                    $state.go('error');
                })
        };

        $scope.getWavesStudentJoined = function () {
            StudentWaveService.getWavesStudentJoined($scope.studentId)
                .success(function (data) {
                    if (data.headers.resultCode == 0) {
                        $scope.joinedWaves = data.body.projectWavesJoined;
                        $scope.joiningWaves = data.body.projectWavesJoining;
                        if($scope.joiningWaves.length == 1){
                            $scope.getTeachersOfProjectWave($scope.joiningWaves[0].id);
                            $scope.getTeachersWhoStudentRegistered($scope.studentId, $scope.joiningWaves[0].id);
                        }
                    }else{
                        $state.go('error');
                    }
                })
                .error(function (error) {
                    $state.go('error');
                });
        };

        function load() {
            $scope.getWavesStudentJoined();
        }

        load();

    });
