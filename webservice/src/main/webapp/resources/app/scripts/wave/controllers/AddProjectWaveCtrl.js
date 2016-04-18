angular.module('appDATN.wave')
    .controller('AddProjectWaveCtrl', function ($scope, ProjectWaveService) {

        $scope.reportTime = null;
        $scope.reportTimes = ['14/04/2016 00:00:00 - 14/04/2016 23:59:59', '14/04/2016 00:00:00 - 14/04/2016 23:59:59'];

        $scope.addItemOfProgressReport = function(){
            if($scope.reportTime != null){
                $scope.reportTimes.push($scope.reportTime);
                $scope.reportTime = null;
            }
        };

        $scope.deleteItemOfProgressRepor = function(item){
            if(item != null){
                var index = $scope.reportTimes.indexOf(item);
                $scope.reportTimes.splice(index, 1);
            }
        };

        $scope.addWave = function(student){
            if($scope.addWaveForm.$valid){

            }else{
                $scope.setDirty();
            }
        };

        $scope.setDirty = function(){
            if($scope.addWaveForm.$invalid){
                Object.keys($scope.addWaveForm.$error).forEach(function (key) {
                    $scope.addWaveForm.$error[key].forEach(function (control) {
                        control.$setDirty();
                    });
                });
            }
        }

    });