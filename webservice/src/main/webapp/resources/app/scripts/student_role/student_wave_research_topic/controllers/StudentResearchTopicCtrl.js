angular.module('appDATN.student_wave')
    .controller('StudentResearchTopicCtrl', function ($scope, $state, $mdMedia, $mdDialog, StudentResearchTopicService, user) {

        $scope.studentId = user.id;

        $scope.project = {
            'description':'<p>sfdsdgf<strong>sdfg</strong><strong><s>dfgdf</s></strong></p>'
        };


        function load() {
        }

        load();

    });