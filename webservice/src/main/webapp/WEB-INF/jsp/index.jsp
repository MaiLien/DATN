<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="appDATN">
<head>
    <title>home</title>

    <!-- Import css -->
    <link href="resources/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet" />
    <link href="resources/bower_components/font-awesome/css/font-awesome.css" rel="stylesheet">

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

</head>
<body>
<div ng-include="'/resources/app/views/partials/_header.html'"></div>
<div class="row">
    <div class="col-xs-3">
        <div ng-include="'/resources/app/views/partials/_sidebar.html'"></div>
    </div>
    <div class="col-xs-9">
        <div ng-view> </div>
    </div>
</div>
<div ng-include="'/resources/app/views/partials/_footer.html'"></div>
</body>
</html>
