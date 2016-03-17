'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'ui.router',
  'myApp.userView',
  'myApp.alumnoView',
  'myApp.materiaView',
  'myApp.categoriaView',
  'myApp.userView'
])

.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        
    .state('userView', {
    	url: '/users_config',
      	templateUrl: 'resources/userView/userView.html',
		controller: 'userViewCtrl'
		// params: {
  // 		user: { "userId":0, "firstName":null, "lastName":null}
		// }
    })

    .state('alumnoView', {
      url: '/estudiante_config',
        templateUrl: 'resources/alumnoView/alumnoView.html',
    controller: 'alumnoViewCtrl'

    })

     .state('materiaView', {
      url: '/materia_config',
      templateUrl: 'resources/materiaView/materiaView.html',
      controller: 'materiaViewCtrl'
    // params: {
  //    user: { "userId":0, "firstName":null, "lastName":null}
    // }
    })  

     .state('categoriaView', {
      url: '/categoria_config',
      templateUrl: 'resources/categoriaView/categoriaView.html',
      controller: 'categoriaViewCtrl'
    })  

});


// .config(['$routeProvider','$provide','$httpProvider', function($routeProvider,$provide,$httpProvider, $stateProvider, $urlRouterProvider) {
// 	$routeProvider.otherwise({redirectTo: '/home'});
  
// 	$provide.factory('responseHttpInterceptor', function($q) {
// 		  return {
// 		    response: function(response) {
// 		      // do something on success
// 		      return response;
// 		    },
// 		    responseError: function(response) {
// 		      // do something on error
// 		    	if(response.status === 401){
// 					window.location.href = "/lyra/#/login";

// 				}
// 		      return $q.reject(response);
// 		    }
// 		  };
// 		});
	
// 	$httpProvider.interceptors.push('responseHttpInterceptor');
	
// 	//RESPONSE INTERCEPTOR FOR ALL THE JQUERY CALLS: EX:THE JQGRID
// 	$.ajaxSetup({
// 	    beforeSend: function() {
// 	    },
// 	    complete: function(response) {
// 	    	if(response.status === 401){
// 	    		window.location.href = "/lyra/#/login";
// 			}
// 	    }
// 	});

//  $urlRouterProvider;

// 	$stateProvider
        
//     .state('userView', {
//     	url: '/users',
//       	templateUrl: 'userView/userView.html',
// 		controller: 'userViewCtrl'
//     })
  
// }]);

