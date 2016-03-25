angular.module('appDATN.student')
    .controller('ListStudentCtrl', function ($scope, $state, $log, StudentService, students) {

        $scope.students = students.content;

        //page index
        $scope.currentPage = students.number + 1;
        //size of page
        $scope.pageSize = students.size;
        //total elements
        $scope.total = students.totalElements;
        $scope.adjacent = 2;
        $scope.dots = '...';
        $scope.hideIfEmpty = true;
        $scope.showPrevNext = true;
        $scope.showFirstLast = true;


        $scope.getStudents = function () {
            StudentService.getStudents(currentPage-1, pageSize)
                .success(function (data) {
                    if (data.headers.resultCode == 0) {
                        $scope.students = data.body;
                    }
                })
                .error(function (error) {
                    $state.go('error');
                });
        }

        $scope.DoPagingAct = function(text, page, pageSize, total) {
            $state.go('student.list', {page: page - 1, size: pageSize});
        };

        //
        //function load() {
        //    $scope.getStudents();
        //}
        //
        //load();
    });