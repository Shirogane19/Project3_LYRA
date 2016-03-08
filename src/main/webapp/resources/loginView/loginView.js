// 'use strict';

// angular.module('myApp.loginView', ['ngRoute'])

// .config(['$routeProvider', function($routeProvider) {
//   $routeProvider.when('/login', {
//     templateUrl: 'resources/loginView/loginView.html',
//     controller: 'LoginFormController'
//   });
// }])

// .controller('LoginFormController', ['$scope','$http',function($scope,$http) {
// 	$scope.user = {email:"jcorellam@ucenfotec.ac.cr",password:"1234"};
	
// 	$scope.checkLogin = function(){
// 		$scope.authError = null;

//         // Try to login 

//     	$http.post('rest/login/checkuser/',$scope.user).success(function (loginResponse) {

//       .then(function(response) {
//     		if(loginResponse.code == 200){
//     			var usuario = {"idUser":loginResponse.idUsuario,"firstName":loginResponse.firstName,"lastName":loginResponse.lastName};
//     			var path = "/lyra/app#/view1";
//     			window.location.href = path;
//     		}else{
//     			$scope.authError = 'Email or Password not right';
//     		}
//          }, function(x) {
//                  $scope.authError = 'Server Error';
//                 });
//     	});
    	
//     };
// }]);




'use strict';

angular.module('myApp.loginView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'resources/loginView/loginView.html',
    controller: 'LoginViewCtrl'
  });
}])

.controller('LoginViewCtrl', ['$scope','$http',function($scope,$http) {
 
  $scope.user = {};

 // $scope.user = {email:"jean@maradiaga.com",password:"12345"};

  $scope.checkLogin = function(){

    $http.post('rest/login/checkuser/',$scope.user).success(function (loginResponse) {

      if(loginResponse.code == 200){
        var usuario = {"userId":loginResponse.userId,"firstName":loginResponse.firstName,"lastName":loginResponse.lastName};
     //   console.log(user);
        var path = "/lyra/app#/home";
        window.location.href = path;

      }else{
          alert("invalido");
      }

    });

  };

  //----------------------------------------------------------------------------------------//
  //Formulario de Subsripción//

  $scope.onPoint = false;//ng-show del formulario de subscripción
  $scope.onPointUserForm = true;
  $scope.onPointNoRegister = false;
  $scope.isCreating = true;
  $scope.institucion = {};
  $scope.subscriptor = {};
  var date = new Date();

  $scope.showForm = function(){//Funcion de la visibilidad del formulario de subscripción
    $scope.onPoint = true;
    console.log('Formulario? ', $scope.onPoint);
    //console.log((date.getFullYear()) + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2));
  }

  $scope.showUserForm = function(){//Funcion de la visibilidad del 
    if($scope.onPointUserForm){
      $scope.onPointUserForm = false;
    }else{
      $scope.onPointUserForm = true;
    }
    $scope.onPointNoRegister = false;
    console.log('Formulario? ', $scope.onPointUserForm);
  }

  $scope.checkUser = function(){

    $http.post('rest/login/checkuser/',$scope.user).success(function (loginResponse) {

      console.log(loginResponse);

      if(loginResponse.code == 200){

        $http.post('rest/protected/users/getUser',loginResponse.userId).success(function(response) {

            console.log(response);

            $scope.subscriptor.idSubscriptor = response.usuario.idUsuario;
            $scope.subscriptor.apellido = response.usuario.apellido;
            $scope.subscriptor.cedula = response.usuario.cedula;
            $scope.subscriptor.correo = response.usuario.email;
            $scope.subscriptor.movil = response.usuario.movil;
            $scope.subscriptor.nombre = response.usuario.nombre;
            $scope.subscriptor.telefono = response.usuario.telefono;

            $scope.user = {};
            $scope.showUserForm();

        }); 

            
      }else{
          $scope.onPointNoRegister = true;
      }
    });

  }

  $scope.saveSubscripcion = function(){

    if($scope.isCreating){//Si esta creando setea un -1 al tipo de usuario
      $scope.subscriptor.idUsuario = -1;
      $scope.subscriptor.activeSub = true;
    }

    $scope.requestObject = {
      "pageNumber": 0,
      "pageSize": 0,
      "direction": "string",
      "sortBy": ["string"],
      "searchColumn": "string",
      "searchTerm": "string",
      "institucion":
        {
          "idInstitucion": -1,
          "hasSuscripcion": true,
          "logoInstitucion": $scope.institucion.urlLogo,
          "nombreInstitucion": $scope.institucion.nombre,
          "subscripcions": [
              {
                "idSubscripcion": -1,
                "fechaFin": "",
                "fechaInicio": (date.getFullYear()) + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2),
                "activeSub": true
              }
          ],
          "usuarios": [
              {
               "idUsuario": $scope.subscriptor.idUsuario,
                "apellido": $scope.subscriptor.idUsuario,
                "cedula": $scope.subscriptor.cedula,
                "dateOfJoin": (date.getFullYear()) + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2),
                "email": $scope.subscriptor.correo,
                "movil": $scope.subscriptor.movil,
                "nombre": $scope.subscriptor.nombre,
                "password": "",
                "telefono": $scope.subscriptor.telefono,
                "activeSub": $scope.subscriptor.activeSub


              }
          ]
        }
    }

    $http.post('rest/protected/institucion/save',$scope.requestObject).success(function(response) {
      console.log(response);
    }); 



    // $scope.requestObjectSubscripcion = {

    //     "pageNumber": 0,
    //     "pageSize": 0,
    //     "direction": "string",
    //     "sortBy": [
    //       "string"
    //     ],
    //     "searchColumn": "string",
    //     "searchTerm": "string",
    //     "subscripcion": {

    //         "idSubscripcion": -1,
    //         "fechaFin": "",
    //         "fechaInicio": (date.getFullYear()) + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2),
    //         "institucion": {

    //             "idInstitucion": -1,
    //             "hasSuscripcion": true,
    //             "logoInstitucion": $scope.institucion.urlLogo,
    //             "nombreInstitucion": $scope.institucion.nombre
    //         },
    //         "activeSub": true


    //   }
    // }

    // $scope.requestObjectUsuario = {

    //     "pageNumber": 0,
    //     "pageSize": 0,
    //     "direction": "string",
    //     "sortBy": [
    //       "string"
    //     ],
    //     "searchColumn": "string",
    //     "searchTerm": "string",
    //     "usuario": {

    //         "idUsuario": $scope.subscriptor.idUsuario,
    //         "apellido": $scope.subscriptor.idUsuario,
    //         "cedula": $scope.subscriptor.cedula,
    //         "dateOfJoin": (date.getFullYear()) + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2),
    //         "email": $scope.subscriptor.correo,
    //         "movil": $scope.subscriptor.movil,
    //         "nombre": $scope.subscriptor.nombre,
    //         "password": "",
    //         "telefono": $scope.subscriptor.telefono,
    //         "activeSub": $scope.subscriptor.activeSub

    //   }
    // }

    // console.log($scope.requestObjectUsuario);

    // $http.post('rest/protected/users/saveUser',$scope.requestObjectUsuario).success(function(response) {

    //   console.log(response);

    //   console.log($scope.requestObjectSubscripcion);

    //   $http.post('rest/protected/subscripcion/save',$scope.requestObjectSubscripcion).success(function(response2) {

    //     console.log(response2);

    //   }); 

    // }); 


  }

  $scope.initScripts = function(){//Inicialización de componente del formulario

    angular.element(document).ready(function () {
        BaseFormWizard.init();
    });

  }

  $scope.initScripts();
      
}]);


//      $http.post('http://localhost:8090/rest/login/checkuser/',$scope.user).success(function (loginResponse) {