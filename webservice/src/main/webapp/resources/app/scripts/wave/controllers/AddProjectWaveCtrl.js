angular.module('appDATN.wave')
    .controller('AddProjectWaveCtrl', function ($scope, ProjectWaveService) {

        $scope.reportTime = null;
        $scope.wave = {
            reportTimes : ['14/09/2016 00:00:00 - 14/05/2017 23:59:59', '14/12/2016 00:00:00 - 14/08/2018 23:59:59', '14/12/2016 00:00:00 - 14/12/2016 23:59:59', '14/12/2017 00:00:00 - 14/08/2018 23:59:59']
        };

        $scope.addItemOfProgressReport = function(){
            if($scope.reportTime != null){
                $scope.wave.reportTimes.push($scope.reportTime);
                $scope.reportTime = null;
            }
        };

        $scope.deleteItemOfProgressReport = function(item){
            if(item != null){
                var index = $scope.wave.reportTimes.indexOf(item);
                $scope.wave.reportTimes.splice(index, 1);
            }
        };

        $scope.addWave = function(){
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