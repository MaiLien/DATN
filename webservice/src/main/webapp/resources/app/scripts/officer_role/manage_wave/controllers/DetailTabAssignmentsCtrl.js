angular.module('appDATN.officer_wave')
    .controller('DetailTabAssignmentsCtrl', function ($scope, $stateParams, ProjectWaveService, projectWaveInfo) {

        $scope.displayOptions = [{value: 0, text: 'Hiện thị theo danh sách sinh viên'}, {value: 1, text: 'Hiện thị theo danh sách giảng viên'}];
        $scope.selectedDisplayOption = $scope.displayOptions[0].value;

        $scope.setCurrentProjectWaveId($stateParams.projectWaveId);
        $scope.projectWaveInfo = projectWaveInfo;

        $scope.getAssignmentsDispalyedInTeachers = function(){
            ProjectWaveService.getAssignmentsDispalyedInTeachers($stateParams.projectWaveId)
                .success(function(data){

                })
                .error(function(error){

                })
        };

        $scope.getAssignmentsDispalyedInStudents = function(){
            ProjectWaveService.getAssignmentsDispalyedInStudents($stateParams.projectWaveId)
                .success(function(data){
                    var resultCode = data.headers.resultCode;
                    if(resultCode == 1054) {
                        $state.go('login');
                    }
                    if (resultCode == 0) {
                        $scope.assignmentsDispalyedInStudents = data.body;
                    }
                })
                .error(function(error){
                    $state.go('error');
                })
        };

        $scope.getTeachersToChangeAssignment = function(student){
            ProjectWaveService.getTeachersToChangeAssignment(student.id, $stateParams.projectWaveId)
                .success(function(data){
                    var resultCode = data.headers.resultCode;
                    if(resultCode == 1054) {
                        $state.go('login');
                    }
                    if (resultCode == 0) {
                        $scope.changingAssignment = data.body;
                    }
                })
                .error(function (error) {
                    $state.go('error');
                })
        };

        $scope.changeAssignment = function(){
            var request = createChangeAssignmentRequest();
            ProjectWaveService.changeAssignment(request)
                .success(function(data){
                    var resultCode = data.headers.resultCode;
                    if(resultCode == 1054) {
                        $state.go('login');
                    }
                    if (resultCode == 0) {
                        $scope.getAssignmentsDispalyedInStudents();
                    }
                })
                .error(function (error) {

                })
        };

        createChangeAssignmentRequest = function(){
            var teachers = [];
            var teacherOptions = $scope.changingAssignment.teacherOptions;
            for(i=0; i < teacherOptions.length; i++){
                if(teacherOptions[i].checked)
                    teachers.push(teacherOptions[i].id);
            }

            return{
                studentId: $scope.changingAssignment.student.id,
                projectWaveId: $stateParams.projectWaveId,
                teachers: teachers
            }
        };

        $scope.refreshDisplay = function(){
            if($scope.selectedDisplayOption == 0){
                $scope.getAssignmentsDispalyedInStudents();
            }
            else {
                //$scope.getAssignmentsDispalyedInTeachers();
                console.log('getAssignmentsDispalyedInTeachers');
            }
        };

        load = function(){
            $scope.getAssignmentsDispalyedInStudents();
        };

        load();

    });
