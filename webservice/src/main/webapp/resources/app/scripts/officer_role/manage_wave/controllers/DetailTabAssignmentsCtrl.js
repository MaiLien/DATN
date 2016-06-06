angular.module('appDATN.officer_wave')
    .controller('DetailTabAssignmentsCtrl', function ($scope, $stateParams, ProjectWaveService) {

        $scope.displayOptions = [{value: 0, text: 'Hiện thị theo danh sách sinh viên'}, {value: 1, text: 'Hiện thị theo danh sách giảng viên'}];
        $scope.selectedDisplayOption = $scope.displayOptions[0].value;

        $scope.setCurrentProjectWaveId($stateParams.projectWaveId);

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


        load = function(){
            $scope.getAssignmentsDispalyedInStudents();
        };

        load();

    });
