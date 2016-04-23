angular.module('appDATN.wave')
    .controller('AddProjectWaveCtrl', function ($scope, $state, ProjectWaveService) {

        $scope.reportTime = null;
        $scope.reportTimeDirty = false;
        $scope.wave = {
            reportTimes : ['14/09/2016 00:00:00 - 14/05/2017 23:59:59', '14/12/2016 00:00:00 - 14/08/2018 23:59:59', '14/12/2016 00:00:00 - 14/12/2016 23:59:59', '14/12/2017 00:00:00 - 14/08/2018 23:59:59']
            //reportTimes : []
        };

        $scope.addItemOfProgressReport = function(){
            if($scope.reportTime != null){
                $scope.wave.reportTimes.push($scope.reportTime);
                $scope.reportTime = null;
            }
            $scope.reportTimeDirty = true;
        };

        $scope.deleteItemOfProgressReport = function(item){
            if(item != null){
                var index = $scope.wave.reportTimes.indexOf(item);
                $scope.wave.reportTimes.splice(index, 1);
            }
            $scope.reportTimeDirty = true;
        };

        $scope.addWave = function(){
            $scope.setDirty();
            if($scope.addWaveForm.$valid && $scope.wave.reportTimes.length != 0){
                ProjectWaveService.addWave($scope.wave)
                    .success(function (data) {
                        $state.go('wave.detail.times', {projectWaveId: data.body.id});
                    })
                    .error(function (error) {
                        $state.go('error');
                    });
            }
        };

        $scope.setDirty = function(){
            $scope.reportTimeDirty = true;
            if($scope.addWaveForm.$invalid){
                Object.keys($scope.addWaveForm.$error).forEach(function (key) {
                    $scope.addWaveForm.$error[key].forEach(function (control) {
                        control.$setDirty();
                    });
                });
            }
        }

    });