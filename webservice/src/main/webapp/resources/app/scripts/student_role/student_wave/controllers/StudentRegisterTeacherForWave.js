angular.module('appDATN.student_wave')
    .controller('StudentRegisterTeacherForWave', function ($scope, $state, StudentWaveService, user) {

        $scope.studentId = user.id;

        function load() {

        }

        load();

    });
