'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'ngStorage',
  'ui.router',
  'ui.bootstrap',
  'ui.bootstrap.tpls',
  'angularFileUpload',
  'myApp.startView',
  'myApp.userView',
  'myApp.alumnoView',
  'myApp.materiaView',
  'myApp.institucionView',
  'myApp.perfilView',
  'myApp.gradoView',
  'myApp.seccionView',
  'myApp.tareaView',
  'myApp.categoriaView',
  'myApp.registroView',
  'myApp.errorView',
  'myApp.excelView',
  'myApp.successView',
  'myApp.seccionProfesorView',
  'myApp.alumnoEncargadoView'
  //'myApp.usuarios'
])

.constant('USER_ROLES', {
  master:    1,  
  admin:     2,
  profesor:  3,
  encargado: 4
})

.controller("MainCtrl",['$scope','$http','$localStorage','USER_ROLES','$state',function($scope,$http,$localStorage,USER_ROLES,$state) {

  $scope.title =  "";
  $scope.user =  {}
  $scope.roles = {};

  $scope.accessStart =  false;
  $scope.accessTask =  false;
  $scope.accessUser =  false;
  $scope.accessStudent = false;
  $scope.accessMateria = false;
  $scope.accessGrado = false;  
  $scope.accessSeccion =  false;
  $scope.accessCategory = false;
  $scope.accessBulkLoad = false;


  $scope.save = function(u) {
    $localStorage.user = u;
  };
 
  $scope.load = function() {
    $scope.user = $localStorage.user;
    $scope.roles = $scope.user.roles;

    //console.log($scope.user);
  };

  $scope.logoff= function() {
    $localStorage.$reset();
    $scope.loginPage();
  };

  $scope.enableNotificaciones = function(){

    //console.log("nott" , $localStorage.user.userId);
    var num = 0;


    $scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
    "string","usuario":{"idUsuario": $localStorage.user.userId}};

    $http.post('rest/protected/users/getUser',$scope.requestObject).success(function(response) {

      for (var i = 0; i < response.usuario.tareas.length; i++) {
           if(response.usuario.tareas[i].activeTa == true){
              num ++;
           }
      };

      document.getElementById("noti").textContent= num;

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 


  }

  $scope.loginPage= function() {

    var path = "/lyra/";
    window.location.href = path;
  };

  $scope.initPermissions = function() {

    if($scope.roles.indexOf(USER_ROLES.encargado) !== -1) {
        $scope.accessStart =  true;
        $scope.accessTask =  true;
        //$scope.accessStudent = true; 
        //$scope.accessSeccion =  true;
        $scope.accessStudentManagers = true;
      
    }



    if($scope.roles.indexOf(USER_ROLES.profesor) !== -1) {
        $scope.accessStart =  true;
        $scope.accessTask =  true;
        //$scope.accessStudent = true;
        //$scope.accessMateria = true;
        //$scope.accessGrado = true;  
        //$scope.accessSeccion =  true;
        $scope.accessSeccionProfesor =  true;
        $scope.title = "Prof.";
    }

    if($scope.roles.indexOf(USER_ROLES.admin) !== -1) {
        $scope.accessStart =  true;
        $scope.accessTask =  true;
        $scope.accessUser =  true;
        $scope.accessStudent = true;
        $scope.accessMateria = true;
        $scope.accessGrado = true;  
        $scope.accessSeccion =  true;
        $scope.accessCategory = true;
        $scope.title = "Admin.";
      
    }

    if($scope.roles.indexOf(USER_ROLES.master) !== -1) {
        $scope.accessStart =  true;
        $scope.accessTask =  true;
        $scope.accessUser =  true;
        $scope.accessStudent = true;
        $scope.accessMateria = true;
        $scope.accessGrado = true;  
        $scope.accessSeccion =  true;
        $scope.accessCategory = true;
        $scope.accessBulkLoad = true;
        $scope.title = "Master";
    }

  };

  $scope.load();
  $scope.initPermissions();




}])

.config(function($stateProvider, $urlRouterProvider) {

    
    $urlRouterProvider.otherwise('/inicio');
   // $urlRouterProvider.otherwise('/404');

    $stateProvider

    
    .state('startView', {
      url: '/inicio',
        templateUrl: 'resources/views/startView/startView.html',
        controller: 'StartViewCtrl',
        data: {
        requireLogin: true
       
      }

    })

    .state('userView', {
    	url: '/users_config',
      	templateUrl: 'resources/views/userView/userView.html',
		    controller: 'userViewCtrl',
        data: {
        requireLogin: true //, // this property will apply to all children of 'app' if I use inheritance. Like app.userView
       // hasRole: true
      }

    })

    .state('perfilView', {
      url: '/mi_perfil',
        templateUrl: 'resources/views/perfilView/perfilView.html',
        controller: 'perfilViewCtrl',
        data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }

    })

    .state('gradoView', {
      url: '/grado_config',
        templateUrl: 'resources/views/gradoView/gradoView.html',
        controller: 'gradoViewCtrl',
        data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }

    })

    .state('alumnoView', {
      url: '/estudiante_config',
      templateUrl: 'resources/views/alumnoView/alumnoView.html',
      controller: 'alumnoViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }

    })

    .state('registroView', {
      url: '/historial_medico',
      templateUrl: 'resources/views/regMedicoView/regMedicoView.html',
      controller: 'registroViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      },
      params: {
         alumnoInfo: null
      }

    })

    .state('institucionView', {
      url: '/institucion_config',
      templateUrl: 'resources/views/institucionView/institucionView.html',
      controller: 'institucionViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    }) 

    .state('materiaView', {
      url: '/materia_config',
      templateUrl: 'resources/views/materiaView/materiaView.html',
      controller: 'materiaViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })  

    .state('seccionView', {
      url: '/seccion_config',
      templateUrl: 'resources/views/seccionView/seccionView.html',
      controller: 'seccionViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })

    .state('tareaView', {
      url: '/tarea_config',
      templateUrl: 'resources/views/tareaView/tareaView.html',
      controller: 'tareaViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })

    .state('categoriaView', {
      url: '/categoria_config',
      templateUrl: 'resources/views/categoriaView/categoriaView.html',
      controller: 'categoriaViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })  

    .state('errorView', {
      url: '/error',
      templateUrl: 'resources/views/errorView/errorView.html',
      controller: 'errorViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    }) 

    .state('excelView', {
      url: '/excel_config',
      templateUrl: 'resources/views/excelView/excelView.html',
      controller: 'excelViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })  

    .state('successView', {
      url: '/excel_success',
      templateUrl: 'resources/views/successView/successView.html',
      controller: 'successViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })  

    .state('404', {
        url: '/404',
        templateUrl: 'resources/views/errorView/404.html',
        data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })

    .state('500', {
        url: '/500',
        templateUrl: 'resources/views/errorView/500.html',
        data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })

    .state('401', {
        url: '/401',
        templateUrl: 'resources/views/errorView/401.html',
        data: {
        requireLogin: false // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })

    .state('seccionProfesorView', {
      url: '/mi_secciones',
      templateUrl: 'resources/views/seccionProfesorView/seccionProfesorView.html',
      controller: 'seccionProfesorViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })

    .state('alumnoEncargadoView', {
      url: '/mis_muchachos',
      templateUrl: 'resources/views/alumnoEncargadoView/alumnoEncargadoView.html',
      controller: 'alumnoEncargadoViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    });
      
})

.run(function ($rootScope,$localStorage) {

  $rootScope.$on('$stateChangeStart', function (event, toState, toParams) {

   // console.log(toState);

    var requireLogin = toState.data.requireLogin;
    var hasRole = toState.data.hasRole;

    if (requireLogin && typeof $localStorage.user === 'undefined') {
      event.preventDefault();
      var path = "/lyra/";
      window.location.href = path;
    }

  });

  $rootScope.$on("$stateChangeError", function(event, toState, toParams, fromState, fromParams, error) {
    if (error) {
      $state.go('error.404');
    }

    if (error && $localStorage.user === 'undefined') {
      $state.go('error.401');
    }

  });

});

