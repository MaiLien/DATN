<!DOCTYPE html>
<html ng-app="appDATN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="/resources/bower_components/bootstrap/dist/css/bootstrap.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/resources/bower_components/font-awesome/css/font-awesome.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/resources/bower_components/Ionicons/css/ionicons.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="/resources/template/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/resources/template/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/resources/template/dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <div ui-view="menu_view"></div>
    <div ui-view="content_view"></div>
    <div ui-view="footer_view"></div>

</div><!-- ./wrapper -->



<!-- jQuery 2.1.4 -->
<script src="/resources/template/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="/resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>
<!-- FastClick -->
<script src="/resources/template/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/resources/template/dist/js/app.min.js"></script>
<!-- Sparkline -->
<script src="/resources/template/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="/resources/template/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="/resources/template/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- SlimScroll 1.3.0 -->
<script src="/resources/template/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- ChartJS 1.0.1 -->
<script src="/resources/template/plugins/chartjs/Chart.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<%--<script src="/resources/template/dist/js/pages/dashboard2.js"></script>--%>
<!-- AdminLTE for demo purposes -->
<script src="/resources/template/dist/js/demo.js"></script>

<script src="/resources/bower_components/angular/angular.js"></script>
<script src="/resources/bower_components/angular-ui-router/release/angular-ui-router.js"></script>


<script src="/resources/app/scripts/app.js"></script>

<script src="/resources/app/scripts/common/main.js"></script>

<script src="/resources/app/scripts/authenticate/main.js"></script>
<script src="/resources/app/scripts/authenticate/controllers/AuthCtrl.js"></script>
<script src="/resources/app/scripts/authenticate/services/AuthService.js"></script>

<script src="/resources/app/scripts/student/main.js"></script>
<script src="/resources/app/scripts/student/controllers/StudentCtrl.js"></script>
<script src="/resources/app/scripts/student/services/StudentService.js"></script>

</body>
</html>
