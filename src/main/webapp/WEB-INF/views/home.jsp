<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus" ng-app="myApp"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">

        <title>Lyra - User Dashboard</title>

        <meta name="description" content="Lyra - User Dashboard">
        <meta name="author" content="pixelcave">
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

        <!-- Page JS Plugins CSS go here -->

        <!-- Bootstrap and OneUI CSS framework -->
        <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
        <link rel="stylesheet" id="css-main" href="resources/assets/css/oneui.min.css">
        <link rel="stylesheet" id="css-theme" href="resources/assets/css/themes/flat.min.css"> 


        <!-- You can include a specific file from css/themes/ folder to alter the default color theme of the template. eg: -->
        <!-- <link rel="stylesheet" id="css-theme" href="assets/css/themes/flat.min.css"> -->
        <!-- END Stylesheets -->
    </head>
    <body ng-controller="MainCtrl">
        <!-- Page Container -->
        <!--
            Available Classes:

            'enable-cookies'             Remembers active color theme between pages (when set through color theme list)

            'sidebar-l'                  Left Sidebar and right Side Overlay
            'sidebar-r'                  Right Sidebar and left Side Overlay
            'sidebar-mini'               Mini hoverable Sidebar (> 991px)
            'sidebar-o'                  Visible Sidebar by default (> 991px)
            'sidebar-o-xs'               Visible Sidebar by default (< 992px)

            'side-overlay-hover'         Hoverable Side Overlay (> 991px)
            'side-overlay-o'             Visible Side Overlay by default (> 991px)

            'side-scroll'                Enables custom scrolling on Sidebar and Side Overlay instead of native scrolling (> 991px)

            'header-navbar-fixed'        Enables fixed header
        -->
        <div id="page-container" class="sidebar-l sidebar-o side-scroll header-navbar-fixed">

            <!-- Sidebar -->
            <nav id="sidebar">
                <!-- Sidebar Scroll Container -->
                <div id="sidebar-scroll">
                    <!-- Sidebar Content -->
                    <!-- Adding .sidebar-mini-hide to an element will hide it when the sidebar is in mini mode -->
                    <div class="sidebar-content">
                        <!-- Side Header -->
                        <div class="side-header side-content bg-white-op">
                            <!-- Layout API, functionality initialized in App() -> uiLayoutApi() -->
                            <button class="btn btn-link text-gray pull-right hidden-md hidden-lg" type="button" data-toggle="layout" data-action="sidebar_close">
                                <i class="fa fa-times"></i>
                            </button>
                            <!-- Themes functionality initialized in App() -> uiHandleTheme() -->
                            <div class="btn-group pull-right">

                            </div>
                            <a class="h5 text-white" href="#/">
                               <span class="h4 font-w600 sidebar-mini-hide text-primary">L</span><span class="h4 font-w600 sidebar-mini-hide">yra</span>
                            </a>
                        </div>
                        <!-- END Side Header -->

                        <!-- Side Content -->
                        <div class="side-content">
                            <ul class="nav-main">
                                <li>
                                    <a class="active" ui-sref="home"><i class="si si-speedometer"></i><span class="sidebar-mini-hide">Dashboard</span></a>
                                </li>
                                <li class="nav-main-heading"><span class="sidebar-mini-hide">Menu</span></li>

                                <li ng-if = "accessTask == true">
                                    <a ui-sref="tareaView"><i class="si si-note"></i>Tareas</a>
                                </li>
                                <li ng-if = "accessUser == true">
                                    <a ui-sref="userView"><i class="si si-users"></i>Usuarios</a>
                                </li>
                                <li ng-if = "accessStudent == true">
                                    <a ui-sref="alumnoView"><i class="si si-graduation"></i>Estudiantes</a>
                                </li>
                                <li ng-if = "accessMateria == true">
                                    <a ui-sref="materiaView"><i class="si si-book-open"></i>Materias</a>
                                </li>
                                <li ng-if = "accessGrado == true">
                                    <a ui-sref="gradoView"><i class="si si-equalizer"></i>Grados</a>
                                </li>
                                <li ng-if = "accessSeccion == true">    
                                    <a ui-sref="seccionView"><i class="si si-map"></i>Secciones</a>
                                </li> 
                                <li ng-if = "accessCategory == true">
                                    <a ui-sref="categoriaView"><i class="si si-list"></i>Categorias</a>
                                </li>
                                 <li ng-if = "accessBulkLoad == true">
                                    <a ui-sref="excelView"><i class="si si-rocket"></i>Subir Excel</a>
                                </li>                             
                            </ul>
                        </div>
                        <!-- END Side Content -->
                    </div>
                    <!-- Sidebar Content -->
                </div>
                <!-- END Sidebar Scroll Container -->
            </nav>
            <!-- END Sidebar -->

            <!-- Header -->
            <header id="header-navbar" class="content-mini content-mini-full">
                <!-- Header Navigation Right -->
                <ul class="nav-header pull-right">
                    <li>
                        <div class="btn-group">
                            <button class="btn btn-default btn-image dropdown-toggle" data-toggle="dropdown" ng-click="enableNotificaciones()" type="button">
                                <img ng-src="{{user.logoInstitucion == '' && 'resources/assets/img/imagenes/big-default_user-logo.png' || user.logoInstitucion == null && 'resources/assets/img/imagenes/big-default_user-logo.png' || user.logoInstitucion}}" alt="Imagen no encontrada" onError="this.src='resources/assets/img/imagenes/No-image-found.jpg '">
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li class="dropdown-header">Perfil</li>
                                <li>
                                    <a tabindex="-1" ui-sref="tareaView">
                                        <i class="si si-envelope-open pull-right"></i>
                                        <span class="badge badge-primary pull-right" id="noti"></span>Notificaciones
                                    </a>
                                </li>
                                <li>
                                    <a tabindex="-1" ui-sref="perfilView">
                                        <i class="si si-user pull-right"></i> Perfil de usuario
                                    </a>
                                </li>
                                <li>
                                    <a tabindex="-1" ui-sref="institucionView"><i class="si si-settings pull-right"></i>Instituci&oacuten</a>
                                </li>
                                <li class="divider"></li>
                                <li class="dropdown-header">Acciones</li>
                                <li>
                                    <a tabindex="-1" href="#">
                                        <i class="si si-lock pull-right"></i>Bloquear cuenta
                                    </a>
                                </li>
                                <li ng-click="logoff()">
                                    <a tabindex="-1">
                                        <i class="si si-logout pull-right"></i>Cerrar sesi&oacuten
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
                <!-- END Header Navigation Right -->
            </header>
            <!-- END Header -->

            <!-- Main Container -->
            <main id="main-container">
                <!-- Page Header -->
                <div class="content bg-image overflow-hidden" style="background-image: url('resources/assets/img/photos/photo27@2x.jpg');">
                    <div class="push-50-t push-15">
                        <h1 class="h2 text-white animated zoomIn">Dashboard</h1>

                        <h2 class="h5 text-white-op animated zoomIn">Saludos {{title}} {{user.firstName}}!</h2>
                    </div>
                </div>
                <!-- END Page Header -->

                <!-- Page Content -->
                <div class="content" ui-view>
                    


                </div>
                <!-- END Page Content -->
            </main>
            <!-- END Main Container -->

            <!-- Footer -->
            <footer id="page-footer" class="content-mini content-mini-full font-s12 bg-gray-lighter clearfix">
                <div class="pull-left">
                    <a class="font-w600" href="#" target="_blank">Iron Throne</a> &copy; <span class="js-year-copy"></span>
                </div>
            </footer>
            <!-- END Footer -->
        </div>
        <!-- END Page Container -->

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
        <script src="resources/bower_components/angular-bootstrap/ui-bootstrap.min.js"></script>
        <script src="resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
        <script src="resources/bower_components/angular-route/angular-route.js"></script>
        <script src="resources/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
        <script src="resources/non_bower_components/angular-file-upload-shim.min.js"></script>
        <script src="resources/non_bower_components/angular-file-upload.min.js"></script>
        <script src="resources/assets/js/initUI.js"></script>

        <!-- Page JS Plugins + Page JS Code -->
        <script src="resources/app.js"></script>
<!--         <script src="resources/components/usuarios/usuarios.js"></script>
        <script src="resources/components/usuarios/usuario-service.js"></script> -->
        <script src="resources/bower_components/ngstorage/ngStorage.min.js"></script>
                <!-- JS Code -->
        <script src="resources/assets/js/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="resources/assets/js/pages/base_tables_datatables.js"></script>
        <script src="resources/assets/js/pages/base_forms_validation.js"></script>
        <script src="resources/assets/js/plugins/jquery-validation/jquery.validate.min.js"></script>
        <script src="resources/assets/js/plugins/jquery-validation/additional-methods.min.js"></script>
        <script src="resources/assets/js/plugins/select2/select2.full.min.js"></script>
        <script src="resources/assets/js/plugins/summernote/summernote.js"></script>
        <script src="resources/assets/js/plugins/dropzonejs/dropzone.min.js"></script>

            <!--     VIEWS -->
        <script src="resources/userView/userView.js"></script>
        <script src="resources/alumnoView/alumnoView.js"></script>
        <script src="resources/materiaView/materiaView.js"></script>
        <script src="resources/institucionView/institucionView.js"></script>
        <script src="resources/perfilView/perfilView.js"></script>
        <script src="resources/gradoView/gradoView.js"></script>
        <script src="resources/seccionView/seccionView.js"></script>
        <script src="resources/tareaView/tareaView.js"></script>
        <script src="resources/categoriaView/categoriaView.js"></script>
        <script src="resources/regMedicoView/regMedicoView.js"></script>
        <script src="resources/errorView/errorView.js"></script>
        <script src="resources/excelView/excelView.js"></script>

        <script type="text/javascript">
        OneUI.init();
        </script>

</body>
</html>
