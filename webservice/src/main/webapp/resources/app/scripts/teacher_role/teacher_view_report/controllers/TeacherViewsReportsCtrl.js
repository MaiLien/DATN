angular.module('appDATN.student')
    .controller('TeacherViewsReportsCtrl', function ($scope, $state, TeacherViewsReportsService) {

        $scope.teacher;

        $scope.reportStatusOption = [{ name: "Đang chờ duyệt", value: 0 }, { name: "Đã duyệt", value: 1 }, { name: "Tất cả", value: 2 }, { name: 'Đã phản hồi ý kiến', value: 3 }];
        $scope.reportStatusSelected = $scope.reportStatusOption[0];

        $scope.projectWavesOption = [
            {name: "Đợt 1, học kì 2, năm học 2015-2016", value: 0 },
            {name: "Đợt 2, học kì 2, năm học 2015-2016", value: 1 }
        ];
        $scope.ProjectWaveSelected = $scope.projectWavesOption[0];

        $scope.reports = [
            {maSv: '102110142',
             tenSv: 'Nguyễn Văn A',
             phanTram: 65,
             deTai: 'Xây dựng website hỗ trợ quá trình quản lý làm đồ án tốt nghiệp tại khoa Công nghệ thông tin - ĐHBK - ĐHĐN',
             tienDo:[{thoiGian: '20/04/2016 - 25/04/2016', noiDung: 'Chọn đề tài'},
                     {thoiGian: '26/04/2016 - 03/05/2016', noiDung: 'Phân tích, thiết kế hệ thống'},
                     {thoiGian: '07/05/2016 - 25/05/2016', noiDung: 'Tìm hiểu về Spring Framework'}],
             yKienSv: 'Không',
             yKienGv: '',
             baoCaoDot: 1,
             tinhTrang: { name: "Đang chờ duyệt", value: 0 },
             dotDATN: 0
            },
            {maSv: '102110143',
            tenSv: 'Nguyễn Văn B',
            phanTram: 50,
            deTai: 'Xây dựng website thi trắc nghiệm Tin học Đại cương',
            tienDo:[{thoiGian: '20/04/2016 - 25/04/2016', noiDung: 'Chọn đề tài'},
                {thoiGian: '26/04/2016 - 03/05/2016', noiDung: 'Tìm hiểu mô hình MVC'},
                {thoiGian: '07/05/2016 - 25/05/2016', noiDung: 'Phân tích, thiết kế hệ thống, thiết kế giao diện'}],
            yKienSv: 'Không',
            yKienGv: '',
            baoCaoDot: 1,
            tinhTrang: { name: "Đã duyệt", value: 1 },
            dotDATN: 1
            }
        ];

        $scope.setViewingReport = function(report){
            $scope.viewingReport = report;
        };

        $scope.changeStatusToReacted = function(){
            $scope.viewingReport.tinhTrang =  { name: 'Đã phản hồi ý kiến', value: 3 }
        };

        $scope.changeStatusToDone = function(){
            $scope.viewingReport.tinhTrang =  { name: 'Đã duyệt', value: 1 }
        };

        load = function(){

        };

        load();

    });