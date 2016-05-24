<!DOCTYPE html>
<html ng-app="appDATN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ITF Template</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="/resources/bower_components/bootstrap/dist/css/bootstrap.css">
    <!-- Font Awesome -->
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">--%>
    <link rel="stylesheet" href="/resources/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
<%--<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">--%>
    <link rel="stylesheet" href="/resources/bower_components/Ionicons/css/ionicons.min.css">

    <div ui-view="css_imported"></div>
    <%--<link rel="stylesheet" href="/resources/bower_components/angular-bootstrap-datepicker/dist/angular-bootstrap-datepicker.css" />--%>
    <link href="/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.css" rel="stylesheet">
    <link href="/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.standalone.css" rel="stylesheet">

    <link rel="stylesheet" href="/resources/template2/plugins/daterangepicker/daterangepicker-bs3.css">

    <link rel="stylesheet" href="/resources/bower_components/bootstrap-fileinput/css/fileinput.css">

    <link rel="stylesheet" href="/resources/app/styles/mystyle.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <div ui-view="menu_header_view"></div>
    <!-- Content Wrapper. Contains page content -->
    <div ui-view="sidebar_view"></div>
    <div class="content-wrapper">
        <div ui-view="content_view"></div>
    </div>
    <!-- /.content-wrapper -->
    <div ui-view="footer_view"></div>

    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.1.4 -->
<script src="/resources/bower_components/jquery/dist/jquery.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="/resources/bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.5 -->
<script src="/resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>

<script src="/resources/bower_components/raphael/raphael-min.js"></script>
<script src="/resources/template2/plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="/resources/template2/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="/resources/template2/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="/resources/template2/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="/resources/template2/plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="/resources/bower_components/moment/moment.js"></script>
<script src="/resources/template2/plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="/resources/bower_components/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<%--date range picker--%>
<script src="/resources/template2/plugins/daterangepicker/daterangepicker.js"></script>

<script src="/resources/app/js/edited_fileInput.js" charset="utf-8"></script>

<!-- CK Editor -->
<script src="/resources/template2/plugins/ckeditor/ckeditor.js"></script>

<!-- Bootstrap WYSIHTML5 -->
<script src="/resources/template2/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>

<!-- DataTables -->
<script src="/resources/template2/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/resources/template2/plugins/datatables/dataTables.bootstrap.min.js"></script>

<!-- Slimscroll -->
<script src="/resources/template2/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/resources/template2/plugins/fastclick/fastclick.min.js"></script>

<script src="/resources/template_login/js/jquery.backstretch.min.js"></script>

<div ui-view="js_imported"></div>

<script src="/resources/bower_components/angular/angular.js"></script>
<%--<script src="/resources/bower_components/angular-bootstrap-datepicker/dist/angular-bootstrap-datepicker.js" charset="utf-8"></script>--%>
<script src="/resources/bower_components/angular-ui-router/release/angular-ui-router.js"></script>
<script src="/resources/template_paging/paging.js"></script>
<script src="/resources/bower_components/angular-animate/angular-animate.js"></script>
<script src="/resources/bower_components/angular-material/angular-material.js"></script>
<script src="/resources/bower_components/angular-aria/angular-aria.js"></script>
<script src="/resources/bower_components/angular-messages/angular-messages.js"></script>
<script src="/resources/bower_components/ng-file-upload/ng-file-upload.js"></script>
<script src="/resources/bower_components/angular-sanitize/angular-sanitize.js"></script>

<script src="/resources/app/scripts/app.js"  charset="utf-8"></script>

<script src="/resources/app/scripts/common/common_main.js"  charset="utf-8"></script>
<script src="/resources/app/scripts/common/directives/NgEnter.js"  charset="utf-8"></script>
<script src="/resources/app/scripts/common/directives/ckEditor.js"  charset="utf-8"></script>
<script src="/resources/app/scripts/common/filters/SortDateTime.js"  charset="utf-8"></script>

<script src="/resources/app/scripts/authenticate/auth_main.js"  charset="utf-8"></script>
<script src="/resources/app/scripts/authenticate/controllers/AuthCtrl.js"  charset="utf-8"></script>
<script src="/resources/app/scripts/authenticate/services/AuthService.js"  charset="utf-8"></script>

<%--Officer role--%>
<script src="/resources/app/scripts/officer_role/officer_main.js"></script>

<script src="/resources/app/scripts/officer_role/officer_home/officer_home_main.js"></script>
<script src="/resources/app/scripts/officer_role/officer_home/controllers/OfficerInfoCtrl.js"></script>
<script src="/resources/app/scripts/officer_role/officer_home/services/OfficerInfoService.js"></script>

<script src="/resources/app/scripts/officer_role/manage_student/officer_student_main.js"  charset="utf-8"></script>
<script src="/resources/app/scripts/officer_role/manage_student/services/StudentService.js" charset="utf-8"></script>
<script src="/resources/app/scripts/officer_role/manage_student/controllers/StudentCtrl.js" charset="utf-8"></script>
<script src="/resources/app/scripts/officer_role/manage_student/controllers/AddStudentCtrl.js" charset="utf-8"></script>
<script src="/resources/app/scripts/officer_role/manage_student/controllers/ListStudentCtrl.js" charset="utf-8"></script>
<script src="/resources/app/scripts/officer_role/manage_student/controllers/EditStudentCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_student/controllers/AddStudentFromFileExcelCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>

<script src="/resources/app/scripts/officer_role/manage_teacher/officer_teacher_main.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_teacher/services/TeacherService.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_teacher/controllers/AddTeacherCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_teacher/controllers/ListTeacherCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_teacher/controllers/AddTeacherFromFileCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>

<script src="/resources/app/scripts/officer_role/manage_wave/officer_wave_main.js" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_wave/services/ProjectWaveService.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_wave/controllers/AddProjectWaveCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_wave/controllers/DetailCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_wave/controllers/DetailTabTimesCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_wave/controllers/DetailTabStudentsCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_wave/controllers/DetailTabTeachersCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_wave/controllers/DetailTabAssignmentsCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/officer_role/manage_wave/controllers/ListProjectWaveCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>



<%--Student role--%>
<script src="/resources/app/scripts/student_role/student_main.js" charset="utf-8" URIEncoding="UTF-8"></script>

<script src="/resources/app/scripts/student_role/student_home/student_home_main.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/student_role/student_home/controllers/StudentInfoCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/student_role/student_home/services/StudentInfoService.js" charset="utf-8" URIEncoding="UTF-8"></script>

<script src="/resources/app/scripts/student_role/student_wave_research_topic/student_wave_research_topic_main.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/student_role/student_wave_research_topic/services/StudentResearchTopicService.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/student_role/student_wave_research_topic/controllers/StudentResearchTopicCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>

<script src="/resources/app/scripts/student_role/student_register_teacher/student_register_teacher_main.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/student_role/student_register_teacher/services/StudentRegisterTeacherService.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/student_role/student_register_teacher/controllers/StudentRegisterTeacherForWaveCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>

<script src="/resources/app/scripts/student_role/student_project_wave_info/student_wave_info_main.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/student_role/student_project_wave_info/services/StudentWaveInfoService.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/student_role/student_project_wave_info/controllers/StudentWavesInfoCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>

<script src="/resources/app/scripts/student_role/student_report/student_report_main.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/student_role/student_report/services/StudentReportService.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/student_role/student_report/controllers/StudentReportCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>

<%--Teacher role--%>
<script src="/resources/app/scripts/teacher_role/teacher_main.js" charset="utf-8" URIEncoding="UTF-8"></script>

<script src="/resources/app/scripts/teacher_role/teacher_home/teacher_home_main.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/teacher_role/teacher_home/controllers/TeacherInfoCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/teacher_role/teacher_home/services/TeacherInfoService.js" charset="utf-8" URIEncoding="UTF-8"></script>

<script src="/resources/app/scripts/teacher_role/teacher_project_wave/teacher_project_wave_main.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/teacher_role/teacher_project_wave/services/TeacherProjectWaveService.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/teacher_role/teacher_project_wave/controllers/TeacherProjectWaveCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>

<script src="/resources/app/scripts/teacher_role/teacher_propose_student/teacher_propose_student_main.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/teacher_role/teacher_propose_student/services/TeacherProposeStudentService.js" charset="utf-8" URIEncoding="UTF-8"></script>
<script src="/resources/app/scripts/teacher_role/teacher_propose_student/controllers/TeacherProposeStudentCtrl.js" charset="utf-8" URIEncoding="UTF-8"></script>

</body>
</html>


