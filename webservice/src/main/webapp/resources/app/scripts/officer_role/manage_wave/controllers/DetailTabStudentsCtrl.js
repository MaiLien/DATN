angular.module('appDATN.officer_wave')
    .controller('DetailTabStudentsCtrl', function ($scope, $timeout, $stateParams, ProjectWaveService){

        $scope.setCurrentProjectWaveId($stateParams.projectWaveId);

        $scope.init = function(){
            $scope.infoMessage = null;
            $scope.errMessage = null;
            $scope.successMessage = null;
            $scope.studentTemp =null;
        };

        $scope.init();

        $scope.addStudentForWave = function(studentUsername){
            $scope.init();
            if(studentUsername != null && studentUsername != ""){
                var request = $scope.createAddStudentRequest(studentUsername);
                ProjectWaveService.addStudent(request)
                    .success(function(data){
                        if(data.headers.resultCode == 1062)
                            $scope.infoMessage = "Sinh viên đã tham gia đợt Đồ án";

                        else if(data.headers.resultCode == 1064)
                            $scope.errMessage ="Sinh viên không thể tham gia nhiều đợt đồ án trong cùng 1 khoảng thời gian";

                        else if(data.headers.resultCode == 1061)
                            $scope.errMessage = "Không tồn tại đợt đồ án";

                        else if(data.headers.resultCode == 1500)
                            $scope.errMessage = "Không tồn tại sinh viên này";

                        else if(data.headers.resultCode == 500)
                            $scope.errMessage = "Lỗi hệ thống";

                        else{
                            $scope.studentTemp = data.body;
                            $scope.successMessage = "Thêm thành công sinh viên vào đợt Đồ án";
                            $scope.studentUsername = null;
                            $scope.getStudentsOfProjectWave();
                        }
                    })
                    .error(function(error){

                    })
            }
        };

        $scope.createAddStudentRequest = function(studentUsername){
            return {
                studentUsername: studentUsername,
                projectWaveId: $scope.getCurrentProjectWaveId()
            };
        };

        $scope.getStudentsOfProjectWave = function(){
            ProjectWaveService.getStudents($stateParams.projectWaveId)
                .success(function (data){
                    var resultCode = data.headers.resultCode;
                    if(resultCode == 1054){
                        $state.go('login');
                    }
                    else if(resultCode == 0){
                        $scope.studentsOfWave = data.body;
                    }
                    else{
                        $state.go('error');
                    }
                })
                .error(function (error) {
                    $state.go('error');
                });
        };

        $scope.addStudentForProjectWaveFromFile = function (file) {
            ProjectWaveService.addStudentForProjectWaveFromFile(file, $stateParams.projectWaveId)
                .then(function (response){
                    $scope.successItems = response.data.body.successItems;
                    $scope.failItems = response.data.body.failItems;
                    $scope.totalItem = response.data.body.totalItem;
                    $scope.addStudentForProjectWaveFromFileMessage = "Sinh viên đã tham gia đợt Đồ án";
                    $scope.getStudentsOfProjectWave();
                }, function (response) {
                    if (response.status != 200)
                        $state.go('error');
                }, function (evt){
                    //progress
                });
        };

        $scope.setStudentToDelete = function(student){
            $scope.studentToDelete = student;
        };

        $scope.deleteStudentFromWave = function(){
            ProjectWaveService.deleteStudentFromWave($scope.studentToDelete.id, $stateParams.projectWaveId)
                .success(function(data){
                    $scope.getStudentsOfProjectWave();
                })
        };

        load = function(){
            $scope.getStudentsOfProjectWave();
        };

        load();

    });
