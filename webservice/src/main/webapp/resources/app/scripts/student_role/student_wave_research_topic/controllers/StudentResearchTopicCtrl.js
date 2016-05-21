angular.module('appDATN.student_wave')
    .controller('StudentResearchTopicCtrl', function ($scope, $state, $mdMedia, $mdDialog, StudentResearchTopicService, user) {

        $scope.studentId = user.id;

        function load() {
        }

        load();

    });