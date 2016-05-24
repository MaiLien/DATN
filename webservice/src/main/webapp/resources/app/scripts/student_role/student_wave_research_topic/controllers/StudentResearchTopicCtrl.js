angular.module('appDATN.student_wave')
    .controller('StudentResearchTopicCtrl', function ($scope, $state, $mdMedia, $mdDialog, StudentResearchTopicService, user) {

        $scope.studentId = user.id;

        $scope.getWavesStudentJoined = function () {
            StudentResearchTopicService.getWavesStudentJoined($scope.studentId)
                .success(function (data) {
                    var resultCode = data.headers.resultCode;

                    if(resultCode == 1054)
                        $state.go('login');

                    else if(resultCode == 0){
                        $scope.joinedWaves = data.body.projectWavesJoined;
                        $scope.joiningWaves = data.body.projectWavesJoining;
                        if($scope.joiningWaves.length == 1){
                            var joiningProject = $scope.joiningWaves[0];
                            $scope.getStudentWaveResearchTopic($scope.studentId, joiningProject.id);
                        }
                    }

                    else{
                        $state.go('error');
                    }
                })
                .error(function (error) {
                    $state.go('error');
                });
        };

        $scope.getStudentWaveResearchTopic = function (studentId, projectWaveId){
            StudentResearchTopicService.getStudentWaveResearchTopic(studentId, projectWaveId)
                .success(function(data){
                    var resultCode = data.headers.resultCode;

                    if(resultCode == 1054)
                        $state.go('login');

                    else if(resultCode == 0){
                        $scope.student = data.body.student;
                        $scope.projectWave = data.body.projectWave;
                        $scope.researchTopic = data.body.researchTopic;
                    }

                    else $state.go('error');
                })
                .error(function(error){
                    $state.go('error');
                })
        };

        $scope.saveResearchTopic = function(researchTopic){
            var request = createSaveResearchTopicRequest(researchTopic);
            StudentResearchTopicService.saveResearchTopic(request)
                .success(function(data){
                    var resultCode = data.headers.resultCode;

                    if(resultCode == 1054)
                        $state.go('login');

                    else if(resultCode == 0){
                        $scope.getWavesStudentJoined();
                    }

                    else $state.go('error');
                })
                .error(function(error){
                    $state.go('error');
                })
        };

        $scope.setUpdateResearchProject = function(){
            $scope.project = angular.copy($scope.researchTopic);
        };

        createSaveResearchTopicRequest = function (researchTopic) {
            return{
                studentId: $scope.student.id,
                projectWaveId: $scope.projectWave.id,
                researchTopic: researchTopic
            }
        };

        function load() {
            $scope.getWavesStudentJoined();
        }

        load();

    });