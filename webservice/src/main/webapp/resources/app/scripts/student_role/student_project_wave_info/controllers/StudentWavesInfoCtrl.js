angular.module('appDATN.student_wave')
    .controller('StudentWavesInfoCtrl', function ($scope, $state, StudentWaveInfoService, user) {

        $scope.joinedWaves;
        $scope.joiningWaves;
        $scope.studentId = user.id;

        $scope.getWavesStudentJoined = function () {
            StudentWaveInfoService.getWavesStudentJoined($scope.studentId)
                .success(function (data) {
                    if (data.headers.resultCode == 0) {
                        $scope.joinedWaves = data.body.projectWavesJoined;
                        $scope.joiningWaves = data.body.projectWavesJoining;
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
