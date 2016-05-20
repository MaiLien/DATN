angular.module('appDATN.officer_teacher')
    .controller('AddTeacherFromFileCtrl', function ($scope, $timeout, $state, TeacherService) {

        $scope.successItems = [];
        $scope.failItems = [];
        $scope.errMessage = null;
        $scope.totalItem = 0;

        //paging
        $scope.successCurrentPage = 1;
        $scope.successPageItems = [];
        $scope.successTotal = 0;

        $scope.errorCurrentPage = 1;
        $scope.errorPageItems = [];
        $scope.errorTotal = 0;

        $scope.pageSize = 5;
        $scope.total = 0;
        $scope.adjacent = 2;
        $scope.dots = '...';
        $scope.hideIfEmpty = true;
        $scope.showPrevNext = true;
        $scope.showFirstLast = true;

        $scope.SuccessDoPagingAct = function(currentPage, pageSize) {
            $scope.successPageItems = [];
            var i = (currentPage - 1)* pageSize;
            for(var j = 0; j<pageSize  && i < $scope.successItems.length; j++){
                $scope.successPageItems.push($scope.successItems[i]);
                i++;
            }
        };

        $scope.ErrorDoPagingAct = function(currentPage, pageSize) {
            $scope.errorPageItems = [];
            var i = (currentPage - 1)* pageSize;
            for(var j = 0; j<pageSize && i < $scope.failItems.length; j++){
                $scope.errorPageItems.push($scope.failItems[i]);
                i++;
            }
        };

        $scope.setValuePaging = function(){
            $scope.successCurrentPage = 1;
            $scope.successTotal = $scope.successItems.length;

            $scope.errorCurrentPage = 1;
            $scope.errorTotal = $scope.failItems.length;
            $scope.ErrorDoPagingAct($scope.errorCurrentPage, $scope.pageSize);
            $scope.SuccessDoPagingAct($scope.successCurrentPage, $scope.pageSize);
        };

        $scope.addTeacherFromFile = function (file) {
            $scope.resetData();
            TeacherService.addTeacherFromFile(file)
                .then(function (response) {
                    console.log("response.data.headers.resultCode: " + response.data.headers.resultCode);
                    if(response.data.headers.resultCode == 1054){
                        $state.go('login');
                    }
                    else{
                        $scope.successItems = response.data.body.successItems;
                        $scope.failItems = response.data.body.failItems;
                        $scope.totalItem = response.data.body.totalItem;
                        $scope.setValuePaging();
                    }
                }, function (response) {
                    if (response.status != 200)
                        $scope.errMessage = "Lỗi hệ thống";
                }, function (evt) {
                    //progress
                });
        };

        $scope.resetData = function(){
            $scope.successItems = [];
            $scope.failItems = [];
            $scope.errMessage = null;
            $scope.totalItem = 0;
        }

    });