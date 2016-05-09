angular.module('appDATN.student_wave')
    .controller('InfoWavesStudentJoinedCtrl', function ($scope, $state, StudentWaveService, user) {

        $scope.joinedWaves;
        $scope.joiningWaves;
        $scope.studentId = user.id;

        $scope.getWavesStudentJoined = function () {
            StudentWaveService.getWavesStudentJoined($scope.studentId)
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
