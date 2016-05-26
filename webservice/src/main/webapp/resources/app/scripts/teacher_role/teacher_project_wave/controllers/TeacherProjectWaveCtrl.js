angular.module('appDATN.student')
    .controller('TeacherProjectWaveCtrl', function ($scope, $state, TeacherProjectWaveService, user) {

        $scope.teacherId = user.id;
        $scope.waveOptions = [{ name: "Đang hoạt động", value: 0 }, { name: "Đã kết thúc", value: 1 }, { name: "Tất cả", value: 2 }];
        $scope.selectedWaveOption = $scope.waveOptions[0];
        $scope.teacher;

        $scope.getProjectWaveTeacherJoin = function(){
            TeacherProjectWaveService.getProjectWaveTeacherJoin($scope.teacherId)
                .success(function (data) {
                    var resultCode = data.headers.resultCode;
                    if(resultCode == 1054) {
                        $state.go('login');
                    }
                    if (resultCode == 0) {
                        $scope.projectWavesJoined = data.body.projectWavesJoined;
                        $scope.projectWavesJoining = data.body.projectWavesJoining;
                    }else{
                        $state.go('error');
                    }
                })
                .error(function (error) {
                    $state.go('error');
                })
        };

        $scope.setViewingProjectWave = function(item){
            $scope.viewingProjectWave = angular.copy(item);
        };

        $scope.getStudentsOfProjectWaveToPropose = function(projectWave){ //TODO student is proposed by another teacher?
            $scope.setViewingProjectWave(projectWave);
            TeacherProjectWaveService.getStudentsOfProjectWaveToPropose($scope.teacherId, projectWave.id)
                .success(function(data){
                    var resultCode = data.headers.resultCode;
                    if(resultCode == 1054) {
                        $state.go('login');
                    }
                    if (resultCode == 0) {
                        $scope.studentsToPropose = data.body;//TODO just change => html?
                    }
                    else{
                        $state.go('error');
                    }
                })
                .error(function(error){
                    $state.go('error');
                })
        };

        $scope.proposeStudent = function(studentsToPropose){
            var studentProposed = [];
            for(i = 0; i<studentsToPropose.length; i++){
                if(studentsToPropose[i].proposed == true){
                    studentProposed.push(studentsToPropose[i].studentResponse.id);
                }
            }
            var request = createProposeStudentRequest(studentProposed);
            TeacherProjectWaveService.proposeStudent(request)
                .success(function(data){
                    //TODO
                })
                .error(function(error){

                })
        };

        createProposeStudentRequest = function(studentProposed){
            return{
                teacherId: $scope.teacherId,
                projectWaveId: $scope.viewingProjectWave.id,
                studentsProposed: studentProposed
            }
        };

        load = function(){
            $scope.getProjectWaveTeacherJoin();
        };

        load();

    });