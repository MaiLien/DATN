angular.module('appDATN.officer_wave')
    .controller('DetailTabReportsCtrl', function ($scope, $stateParams, ProjectWaveService, projectWaveReports) {

        $scope.setCurrentProjectWaveId($stateParams.projectWaveId);

        $scope.projectWaveId = $stateParams.projectWaveId;

        $scope.reports = projectWaveReports;
        $scope.selectedReport = $scope.reports[0];

        $scope.getReportStatistic = function(){
            if($scope.selectedReport != null){
                ProjectWaveService.getReportStatistic($scope.selectedReport.id)
                    .success(function(data){
                        var resultCode = data.headers.resultCode;
                        if(resultCode == 1054){
                            $state.go('login');
                        }
                        if(resultCode == 0){
                            $scope.viewingReport = data.body.report;
                            $scope.donePercent = data.body.donePercent;
                            $scope.waitingPercent = data.body.waitingPercent;
                            $scope.notPercent = data.body.notPercent;
                            $scope.doneList = data.body.doneList;
                            $scope.waitingList = data.body.waitingList;
                            $scope.notList = data.body.notList;
                            $scope.totalReport = $scope.doneList.length + $scope.waitingList.length + $scope.notList.length;
                        }
                        else{
                            $state.go('error');
                        }
                    })
                    .error(function(error){
                        $state.go('error');
                    })
            }
        };

        load = function(){
            $scope.getReportStatistic();
        };

        load();

    });
