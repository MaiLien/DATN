angular.module('appDATN.wave')
    .controller('ListProjectWaveCtrl', function ($scope, $state, ProjectWaveService) {

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

        $scope.DoPagingAct = function(text, page, pageSize) {
            $scope.getProjectWaves(page-1, pageSize);
        };


        function load() {
            $scope.getProjectWaves($scope.currentPage, $scope.pageSize);
        }

        load();

    });