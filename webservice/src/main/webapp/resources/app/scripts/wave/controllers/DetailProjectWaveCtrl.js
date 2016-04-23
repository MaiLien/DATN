angular.module('appDATN.wave')
    .controller('DetailProjectWaveCtrl', function ($scope, ProjectWaveService, projectWave) {
        $scope.projectWave = projectWave;
        //$scope.students = students;

        $scope.getStudents = function () {
            ProjectWaveService.getStudents($scope.projectWave.id)
                .success(function (data) {
                    console.log(data.body);
                })
                .error(function (error) {
                    
                });
        }
    });
