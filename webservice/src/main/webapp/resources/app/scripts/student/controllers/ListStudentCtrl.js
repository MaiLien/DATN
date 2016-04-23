angular.module('appDATN.student')
    .controller('ListStudentCtrl', function ($scope, $state, $log, $mdDialog, $mdMedia, StudentService) {

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

        $scope.searchInput = "";

        $scope.getStudents = function (currentPage, pageSize) {
            StudentService.getStudents(currentPage, pageSize, $scope.searchInput)
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

        $scope.DoPagingAct = function(text, page, pageSize) {
            $scope.getStudents(page-1, pageSize);
        };

        //$scope.showConfirm = function(ev) {
        //    var confirm = $mdDialog.confirm()
        //        .title('Would you like to delete your debt?')
        //        .textContent('All of the banks have agreed to forgive you your debts.')
        //        .ariaLabel('Lucky day')
        //        .targetEvent(ev)
        //        .ok('Please do it!')
        //        .cancel('Sounds like a scam');
        //    $mdDialog.show(confirm).then(function() {
        //        $scope.status = 'You decided to get rid of your debt.';
        //    }, function() {
        //        $scope.status = 'You decided to keep your debt.';
        //    });
        //};

        $scope.deleteStudent = function(ev, student) {
            var confirm = $mdDialog.confirm()
                .title('Xóa sinh viên ' + student.name + '?')
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Xóa')
                .cancel('Hủy');
            $mdDialog.show(confirm).then(function() {
                StudentService.deleteStudent(student)
                    .success(function(data){
                        $scope.getStudents($scope.currentPage-1, $scope.pageSize, $scope.searchInput);
                    })
            }, function() {

            });
        };

        $scope.lockStudent = function(ev, student) {
            console.log("Khóa tài khoản sinh viên ");
            var confirm = $mdDialog.confirm()
                .title('Khóa tài khoản sinh viên ' + student.name + '?')
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Khóa')
                .cancel('Hủy');
            $mdDialog.show(confirm).then(function() {
                StudentService.lockStudent(student)
                    .success(function(data){
                        $scope.getStudents($scope.currentPage-1, $scope.pageSize, $scope.searchInput);
                    })
            }, function() {

            });
        };

        $scope.unlockStudent = function(ev, student) {
            var confirm = $mdDialog.confirm()
                .title('Mở khóa tài khoản sinh viên ' + student.name + '?')
                .ariaLabel('Lucky day')
                .targetEvent(ev)
                .ok('Mở khóa')
                .cancel('Hủy');
            $mdDialog.show(confirm).then(function() {
                StudentService.unlockStudent(student)
                    .success(function(data){
                        $scope.getStudents($scope.currentPage-1, $scope.pageSize, $scope.searchInput);
                    })
            }, function() {

            });
        };

        $scope.editStudent = function (ev, student) {
            var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
            $mdDialog.show({
                controller: ListStudentCtrl,
                templateUrl: 'edit_student.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                fullscreen: useFullScreen
            })
                .then(function(answer) {
                    $scope.status = 'You said the information was "';
                }, function() {
                    $scope.status = 'You cancelled the dialog.';
                });
            $scope.$watch(function() {
                return $mdMedia('xs') || $mdMedia('sm');
            }, function(wantsFullScreen) {
                $scope.customFullscreen = (wantsFullScreen === true);
            });
        };

        $scope.searchStudent = function (searchInput) {
            if(!(searchInput == null || searchInput == "")){
                $scope.currentPage = 0;
                $scope.getStudents($scope.currentPage, $scope.pageSize, searchInput);
            }
        };

        function load() {
            $scope.getStudents($scope.currentPage, $scope.pageSize);
        }

        load();

    });