angular.module('appDATN.student_wave')
    .controller('StudentWaveListCtrl', function ($scope, $state, StudentWaveService, user) {

        $scope.waves;
        $scope.studentId = user.id;

        $scope.getWavesStudentJoined = function () {
            StudentWaveService.getWavesStudentJoined($scope.studentId)
                .success(function (data) {
                    if (data.headers.resultCode == 0) {
                        $scope.waves = data.body;
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
