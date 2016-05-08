angular.module('appDATN.officer_wave')
    .controller('ListProjectWaveCtrl', function ($scope, $state, $log, $mdDialog, $mdMedia, ProjectWaveService) {

        $scope.projectWaves = [];
        $scope.currentPage = 0;
        $scope.pageSize = 10;
        $scope.total = 0;
        $scope.adjacent = 2;
        $scope.dots = '...';
        $scope.hideIfEmpty = true;
        $scope.showPrevNext = true;
        $scope.showFirstLast = true;

        $scope.searchInput = "";

        $scope.getProjectWaves = function (currentPage, pageSize) {
            ProjectWaveService.getProjectWavesByPage(currentPage, pageSize, $scope.searchInput)
                .success(function (data) {
                    if (data.headers.resultCode == 0) {
                        $scope.projectWaves = data.body.content;
                        $scope.currentPage = data.body.number + 1;
                        $scope.pageSize = data.body.size;
                        $scope.total = data.body.totalElements;
                    }else{
                        $state.go('error');
                    }
                })
                .error(function (error) {
                    $state.go('error');
                });
        };

        $scope.searchProjectWave = function(searchInput){
            if(!(searchInput == null || searchInput == "")){
                $scope.currentPage = 0;
                $scope.getProjectWaves($scope.currentPage, $scope.pageSize, searchInput);
            }
        };

        $scope.deleteProjectWave = function(ev, projectWave){
            var confirm = $mdDialog.confirm()
                .title('Xóa đợt đồ án tốt nghiệp năm học ' + projectWave.schoolYear + ', học kỳ ' + projectWave.semester)
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Xóa')
                .cancel('Hủy');
            $mdDialog.show(confirm).then(function() {
                ProjectWaveService.deleteProjectWave(projectWave.id)
                    .success(function(data){
                        $scope.getProjectWaves($scope.currentPage-1, $scope.pageSize, $scope.searchInput);
                    })
            }, function() {

            })
        };

        $scope.DoPagingAct = function(text, page, pageSize) {
            $scope.getProjectWaves(page-1, pageSize);
        };


        function load() {
            $scope.getProjectWaves($scope.currentPage, $scope.pageSize);
        }

        load();

    });