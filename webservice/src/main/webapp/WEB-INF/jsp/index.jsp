<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="appDATN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ITF</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <title>home</title>

    <!-- Import css -->
    <link href="resources/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet" />
    <link href="resources/bower_components/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="/resources/bower_components/Ionicons/css/ionicons.css" rel="stylesheet">
    <link href="/resources/app/styles/AdminLTE.css" rel="stylesheet">
    <link href="/resources/app/styles/skin-blue.css" rel="stylesheet">

    <!-- Import js -->
    <script src="resources/bower_components/jquery/dist/jquery.js"></script>
    <script src="resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>
    <script src="/resources/bower_components/angular/angular.js"></script>
    <script src="/resources/bower_components/angular-route/angular-route.js"></script>


    <script src="/resources/app/scripts/app.js"></script>
    <script src="/resources/app/scripts/controllers/about.js"></script>
    <script src="/resources/app/scripts/controllers/HeaderCtrl.js"></script>
    <script src="/resources/app/scripts/controllers/AuthCtrl.js"></script>
    <script src="/resources/app/scripts/services/AuthService.js"></script>

    <script src="/resources/app/scripts/js/AdminLTE.js"></script>

</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <div ng-include="'/resources/app/views/partials/_header.html'"></div>

    <div ng-include="'/resources/app/views/partials/_sidebar.html'"></div>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <%--<h1>--%>
                <%--Page Header--%>
                <%--<small>Optional description</small>--%>
            <%--</h1>--%>
            <%--<ol class="breadcrumb">--%>
                <%--<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>--%>
                <%--<li class="active">Here</li>--%>
            <%--</ol>--%>
        </section>

        <!-- Main content -->
        <section class="content">

            <ng-view></ng-view>

        </section>
    </div>

    <div ng-include="'/resources/app/views/partials/_footer.html'"></div>

</div>


</body>

</html>
