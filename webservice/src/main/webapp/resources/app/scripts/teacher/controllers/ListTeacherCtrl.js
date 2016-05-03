angular.module('appDATN.teacher')
    .controller('ListTeacherCtrl', function ($scope, $state, $log, $mdDialog, $mdMedia, TeacherService) {

        $scope.teachers;
        $scope.currentPage = 0;
        $scope.pageSize = 10;
        $scope.total = 0;
        $scope.adjacent = 2;
        $scope.dots = '...';
        $scope.hideIfEmpty = true;
        $scope.showPrevNext = true;
        $scope.showFirstLast = true;

        $scope.searchInput = "";

        $scope.getTeachers = function (currentPage, pageSize) {
            TeacherService.getTeachers(currentPage, pageSize, $scope.searchInput)
                .success(function (data) {
                    if (data.headers.resultCode == 0) {
                        $scope.teachers = data.body.content;
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
            $scope.getTeachers(page-1, pageSize);
        };

        $scope.deleteTeacher = function(ev, teacher) {
            var confirm = $mdDialog.confirm()
                .title('Xóa giảng viên ' + teacher.name + '?')
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Xóa')
                .cancel('Hủy');
            $mdDialog.show(confirm).then(function() {
                TeacherService.deleteTeacher(teacher)
                    .success(function(data){
                        $scope.getTeachers($scope.currentPage-1, $scope.pageSize, $scope.searchInput);
                    })
            }, function() {

            });
        };

        function load() {
            $scope.getTeachers($scope.currentPage, $scope.pageSize);
        }

        load();

    });