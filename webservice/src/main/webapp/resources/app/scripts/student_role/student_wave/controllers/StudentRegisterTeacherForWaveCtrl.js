angular.module('appDATN.student_wave')
    .controller('StudentRegisterTeacherForWave', function ($scope, $state, StudentWaveService, user) {

        $scope.errMessage;
        $scope.successMessage;

        //$scope.joinedWaves;
        //$scope.joiningWaves;
        //$scope.joinedTeachers;
        //$scope.teachersWhoStudentRegister;

        $scope.studentId = user.id;

        refresh = function(){
            $scope.errMessage = null;
            $scope.successMessage = null;

            $scope.joinedWaves = null;
            $scope.joiningWaves = null;
            $scope.joinedTeachers = null;
            $scope.teachersWhoStudentRegister = null;

            $scope.getWavesStudentJoined();
        };

        $scope.getTeachersOfProjectWave = function (projectWaveId) {
            StudentWaveService.getTeachersOfProjectWave(projectWaveId)
                .success(function(data){
                    var resultCode = data.headers.resultCode;

                    if(resultCode == 1054)
                        $state.go('login');

                    else if(resultCode == 0){
                        $scope.joinedTeachers = data.body;
                    }

                    else{
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
                    var resultCode = data.headers.resultCode;

                    if(resultCode == 1054)
                        $state.go('login');

                    else if(resultCode == 0){
                        $scope.teachersWhoStudentRegister = data.body;
                    }

                    else{
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
                    var resultCode = data.headers.resultCode;

                    if(resultCode == 1054)
                        $state.go('login');

                    else if(resultCode == 0){
                        $scope.joinedWaves = data.body.projectWavesJoined;
                        $scope.joiningWaves = data.body.projectWavesJoining;
                        if($scope.joiningWaves.length == 1){
                            $scope.getTeachersOfProjectWave($scope.joiningWaves[0].id);
                            $scope.getTeachersWhoStudentRegistered($scope.studentId, $scope.joiningWaves[0].id);
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

        $scope.cancelRegisterTeacher = function (teacherId, projectWaveId) {
            refresh();
            var request = $scope.prepareRequestRegisterTeacher(teacherId, projectWaveId);
            StudentWaveService.cancelRegisterTeacher(request)
                .success(function(data){
                    var resultCode = data.headers.resultCode;
                    if(resultCode == 0 || resultCode == 1067)
                        $scope.successMessage = "Bạn đã hủy đăng ký thành công";
                    else if(resultCode == 1054)
                        $state.go('login');
                    else if(resultCode == 1068)
                        $state.go('login');
                    else if(resultCode == 1069)
                        $scope.errMessage = "Giảng viên bạn đăng ký không tồn tại";
                    else if(resultCode == 1061)
                        $state.go('error');
                    else $scope.errMessage = "Lỗi hệ thống";
                })
                .error(function(error){
                    $scope.errMessage = "Lỗi hệ thống";
                })
        };

        $scope.registerTeacher = function (teacherId, projectWaveId) {
            refresh();
            var request = $scope.prepareRequestRegisterTeacher(teacherId, projectWaveId);
            StudentWaveService.registerTeacher(request)
                .success(function(data){
                    var resultCode = data.headers.resultCode;
                    console.log("data.headers.resultCode: " + resultCode);
                    if(resultCode == 0)
                        $scope.successMessage = "Bạn đã đăng ký thành công";
                    else if(resultCode == 1054)
                        $state.go('login');
                    else if(resultCode == 1068)
                        $state.go('login');
                    else if(resultCode == 1069)
                        $scope.errMessage = "Giảng viên bạn đăng ký không tồn tại";
                    else if(resultCode == 1061)
                        $state.go('error');
                    else if(resultCode == 1069)
                        $scope.errMessage = "Giảng viên bạn đăng ký không tham gia hướng dẫn đợt đồ án này";
                    else if(resultCode == 1070)
                        $scope.successMessage = "Bạn đã đăng ký giảng viên này";
                    else if(resultCode == 1071)
                        $scope.errMessage = "Bạn không được phép đăng ký nhiều hơn 1 giảng viên hướng dẫn";
                    else $scope.errMessage = "Lỗi hệ thống";
                })
                .error(function(error){
                    $scope.errMessage = "Lỗi hệ thống";
                })
        };

        $scope.prepareRequestRegisterTeacher = function(teacherId, projectWaveId){
            return{
                studentId: $scope.studentId,
                teacherId: teacherId,
                projectWaveId: projectWaveId
            }
        };

        function load() {
            $scope.getWavesStudentJoined();
        }

        load();

    });
