angular.module('appDATN.officer_wave')
    .controller('AddProjectWaveCtrl', function ($scope, $state, ProjectWaveService) {

        $scope.reportTime = null;
        $scope.reportTimeDirty = false;
        $scope.wave = {
            reportTimes : []
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