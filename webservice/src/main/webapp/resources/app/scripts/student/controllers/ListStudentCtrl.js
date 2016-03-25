angular.module('appDATN.student')
    .controller('ListStudentCtrl', function ($scope, $state, $log, $mdDialog, StudentService) {

        //$scope.students = students.content;
        //
        ////page index
        //$scope.currentPage = students.number + 1;
        ////size of page
        //$scope.pageSize = students.size;
        ////total elements
        //$scope.total = students.totalElements;
        //$scope.adjacent = 2;
        //$scope.dots = '...';
        //$scope.hideIfEmpty = true;
        //$scope.showPrevNext = true;
        //$scope.showFirstLast = true;

        $scope.students;
        $scope.currentPage = 0;
        $scope.pageSize = 10;
        $scope.total = 0;
        $scope.adjacent = 2;
        $scope.dots = '...';
        $scope.hideIfEmpty = true;
        $scope.showPrevNext = true;
        $scope.showFirstLast = true;

        $scope.getStudents = function (currentPage, pageSize) {
            StudentService.getStudents(currentPage, pageSize)
                .success(function (data) {
                    if (data.headers.resultCode == 0) {
                        $scope.students = data.body.content;
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
        }

        $scope.DoPagingAct = function(text, page, pageSize, total) {
            $scope.getStudents(page-1, pageSize);
            //$state.go('student.list', {page: page - 1, size: pageSize});
        };

        $scope.showConfirm = function(ev) {
            // Appending dialog to document.body to cover sidenav in docs app
            var confirm = $mdDialog.confirm()
                .title('Would you like to delete your debt?')
                .textContent('All of the banks have agreed to forgive you your debts.')
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Please do it!')
                .cancel('Sounds like a scam');
            $mdDialog.show(confirm).then(function() {
                $scope.status = 'You decided to get rid of your debt.';
            }, function() {
                $scope.status = 'You decided to keep your debt.';
            });
        };

        function load() {
            $scope.getStudents($scope.currentPage, $scope.pageSize);
        }

        load();
    });