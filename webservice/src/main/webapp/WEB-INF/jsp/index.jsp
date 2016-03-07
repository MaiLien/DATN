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

    <header class="main-header">
        <a href="#" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>I</b>TF</span>
            <span class="logo-lg"><b>IT F</b>aculty</span>
        </a>
        <nav class="navbar navbar-static-top" role="navigation">
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account Menu -->
                    <li class="user user-menu">
                        <a href="#">
                            <img src="/resources/app/images/avatar.png" class="user-image" alt="User Image">
                            <span class="hidden-xs">Alexander Pierce</span>
                        </a>
                    </li>
                    <%-- Log out button --%>
                    <li class="user user-menu">
                        <a href="#">
                            <span class="hidden-xs">Log out</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- Left side column. contains sidebar -->
    <aside class="main-sidebar">
        <section class="sidebar">

            <!-- search form (Optional) -->
            <%--<form action="#" method="get" class="sidebar-form">--%>
                <%--<div class="input-group">--%>
                    <%--<input type="text" name="q" class="form-control" placeholder="Search...">--%>
              <%--<span class="input-group-btn">--%>
                <%--<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>--%>
              <%--</span>--%>
                <%--</div>--%>
            <%--</form>--%>
            <!-- /.search form -->

            <ul class="sidebar-menu">
                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>Hộp thư</span> <i class="fa fa-angle-left pull-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a href="#">Hộp thư đến</a></li>
                        <li><a href="#">Hộp thư đi</a></li>
                    </ul>
                </li>
                <li><a href="#"><i class="fa fa-link"></i> <span>Đợt ĐATN</span></a></li>
                <li><a href="#"><i class="fa fa-link"></i> <span>Sinh viên</span></a></li>
                <li><a href="#"><i class="fa fa-link"></i> <span>Giảng viên</span></a></li>
                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>Multilevel</span> <i class="fa fa-angle-left pull-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a href="#">Link in level 2</a></li>
                        <li><a href="#">Link in level 2</a></li>
                    </ul>
                </li>
            </ul>
        </section>
    </aside>

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

            <!-- Your Page Content Here -->

        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            Anything you want
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2016 <a href="https://www.facebook.com/pho.phaohoa">PhoPhaoHoa</a>.</strong> All rights reserved.
    </footer>

    <div class="control-sidebar-bg"></div>
</div>


</body>

</html>
