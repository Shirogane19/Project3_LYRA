'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'ngStorage',
  'ui.router',
  'ui.bootstrap',
  'ui.bootstrap.tpls',
  'myApp.userView',
  'myApp.alumnoView',
  'myApp.materiaView',
  'myApp.institucionView',
  'myApp.perfilView',
  'myApp.gradoView',
  'myApp.seccionView',
  'myApp.tareaView'
  //'myApp.usuarios'
])

.controller("MainCtrl",['$scope','$localStorage',function($scope,$localStorage) {

  $scope.user =  {}


  $scope.save = function(u) {
    $localStorage.user = u;
  };
 
  $scope.load = function() {
    $scope.user = $localStorage.user;
    console.log($scope.user);
  };

  $scope.logoff= function() {
    $localStorage.$reset();
    $scope.loginPage();
  };

  $scope.loginPage= function() {

    var path = "/lyra/";
    window.location.href = path;
  };

  $scope.load();




}])

.config(function($stateProvider, $urlRouterProvider) {

    
    $urlRouterProvider.otherwise('/home');
   // $urlRouterProvider.otherwise('/404');

    $stateProvider

        
    .state('userView', {
    	url: '/users_config',
      	templateUrl: 'resources/userView/userView.html',
		    controller: 'userViewCtrl',
        data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }

    })

    .state('perfilView', {
      url: '/mi_perfil',
        templateUrl: 'resources/perfilView/perfilView.html',
        controller: 'perfilViewCtrl',
        data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }

    })

    .state('gradoView', {
      url: '/grado_config',
        templateUrl: 'resources/gradoView/gradoView.html',
        controller: 'gradoViewCtrl',
        data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }

    })

    .state('alumnoView', {
      url: '/estudiante_config',
      templateUrl: 'resources/alumnoView/alumnoView.html',
      controller: 'alumnoViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }

    })

    .state('institucionView', {
      url: '/institucion_config',
      templateUrl: 'resources/institucionView/institucionView.html',
      controller: 'institucionViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    }) 

    .state('materiaView', {
      url: '/materia_config',
      templateUrl: 'resources/materiaView/materiaView.html',
      controller: 'materiaViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })  

    .state('seccionView', {
      url: '/seccion_config',
      templateUrl: 'resources/seccionView/seccionView.html',
      controller: 'seccionViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })

    .state('tareaView', {
      url: '/tarea_config',
      templateUrl: 'resources/tareaView/tareaView.html',
      controller: 'tareaViewCtrl',
      data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })


    .state('404', {
        url: '{path:.*}',
        templateUrl: 'resources/errorView/404.html',
        data: {
        requireLogin: true // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    })

    .state('401', {
        url: '{path:.*}',
        templateUrl: 'resources/errorView/401.html',
        data: {
        requireLogin: false // this property will apply to all children of 'app' if I use inheritance. Like app.userView
      }
    });
      
})

.run(function ($rootScope,$localStorage) {

  $rootScope.$on('$stateChangeStart', function (event, toState, toParams) {

    console.log(toState);

    var requireLogin = toState.data.requireLogin;

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

