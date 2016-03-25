<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>

<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus" ng-app="myApp"> <!--<![endif]-->
    <head>

        <meta charset="utf-8">

        <title>Lyra- Iron Throne</title>

        <meta name="description" content="Lyra- Login">
        <meta name="author" content="Iron Throne">
        <meta name="robots" content="noindex, nofollow">
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

        <!-- Icons -->
        <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
        <link rel="shortcut icon" href="resources/assets/img/favicons/favicon.png">

        <link rel="icon" type="image/png" href="resources/assets/img/favicons/favicon-16x16.png" sizes="16x16">
        <link rel="icon" type="image/png" href="resources/assets/img/favicons/favicon-32x32.png" sizes="32x32">
        <link rel="icon" type="image/png" href="resources/assets/img/favicons/favicon-96x96.png" sizes="96x96">
        <link rel="icon" type="image/png" href="resources/assets/img/favicons/favicon-160x160.png" sizes="160x160">
        <link rel="icon" type="image/png" href="resources/assets/img/favicons/favicon-192x192.png" sizes="192x192">

        <link rel="apple-touch-icon" sizes="57x57" href="resources/assets/img/favicons/apple-touch-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="resources/assets/img/favicons/apple-touch-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="resources/assets/img/favicons/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="resources/assets/img/favicons/apple-touch-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="resources/assets/img/favicons/apple-touch-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="resources/assets/img/favicons/apple-touch-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="resources/assets/img/favicons/apple-touch-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="resources/assets/img/favicons/apple-touch-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="resources/assets/img/favicons/apple-touch-icon-180x180.png">
        <!-- END Icons -->

        <!-- Stylesheets -->
        <!-- Web fonts -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400italic,600,700%7COpen+Sans:300,400,400italic,600,700">

        <!-- Bootstrap and OneUI CSS framework -->
        <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
        <link rel="stylesheet" id="css-main" href="resources/assets/css/oneui.min.css">

        <!-- You can include a specific file from css/themes/ folder to alter the default color theme of the template. eg: -->
        <!-- <link rel="stylesheet" id="css-theme" href="assets/css/themes/flat.min.css"> -->
        <!-- END Stylesheets -->
    </head>
<body>
  <!--[if lt IE 7]>
      <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
  <![endif]-->

    <div ng-view></div>   <!-- LOGIN VIEW IS CALLED -->

  <!-- Login Footer -->
  <div class="pulldown push-30-t text-center animated fadeInUp">
    <small class="text-muted"><span class="js-year-copy"></span> &copy; Lyra</small>
  </div>
        <!-- END Login Footer -->
  

        <!-- OneUI Core JS: jQuery, Bootstrap, slimScroll, scrollLock, Appear, CountTo, Placeholder, Cookie and App.js -->
        <script src="resources/assets/js/core/jquery.min.js"></script>
        <script src="resources/assets/js/core/bootstrap.min.js"></script>
        <script src="resources/assets/js/core/jquery.slimscroll.min.js"></script>
        <script src="resources/assets/js/core/jquery.scrollLock.min.js"></script>
        <script src="resources/assets/js/core/jquery.appear.min.js"></script>
        <script src="resources/assets/js/core/jquery.countTo.min.js"></script>
        <script src="resources/assets/js/core/jquery.placeholder.min.js"></script>
        <script src="resources/assets/js/core/js.cookie.min.js"></script>
        <script src="resources/bower_components/angular/angular.js"></script>
        <script src="resources/bower_components/angular-route/angular-route.js"></script>
        <script src="resources/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>

        <script src="resources/appLogin.js"></script>
        <script src="resources/bower_components/ngstorage/ngStorage.min.js"></script>
<!--         <script src="resources/router.js"></script> -->
        <script src="resources/loginView/loginView.js"></script>
        <script src="resources/assets/js/initUI.js"></script>
        <!-- Page JS Plugins -->
        <script src="resources/assets/js/plugins/jquery-validation/jquery.validate.min.js"></script>
        <script src="resources/assets/js/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
        

        <!-- Page JS Code -->
        <script src="resources/assets/js/pages/base_pages_login.js"></script>
        <script src="resources/assets/js/pages/base_forms_wizard.js"></script>
        <script src="resources/assets/js/pages/base_forms_validation.js"></script>


    </body>
</html>
