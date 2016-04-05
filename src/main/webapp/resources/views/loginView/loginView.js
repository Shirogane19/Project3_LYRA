'use strict';

angular.module('myApp.loginView', ['ngRoute','ngStorage'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'resources/views/loginView/loginView.html',
    controller: 'LoginViewCtrl'
  });
}])

.directive('pwCheck', [function () {// Validación, reviza que las contraseñas sean iguales
    return {
      require: 'ngModel',
      link: function (scope, elem, attrs, ctrl) {
        var firstPassword = '#' + attrs.pwCheck;
        elem.add(firstPassword).on('keyup', function () {
          scope.$apply(function () {
            var v = elem.val()===$(firstPassword).val();
            ctrl.$setValidity('pwmatch', v);
          });
        });
      }
    }
  }])

.controller('LoginViewCtrl', ['$scope','$http','$window','$localStorage',function($scope,$http,$window,$localStorage) {
  
  $scope.user = {};
  $scope.email = "";
  $scope.showLogin = true;
  $scope.idMaster = 1;
  $scope.masterName;
  $scope.cuentaBloqueada = false; // Visibilidad del texto cuando la cuenta esta bloqueada

  angular.element(document).ready(function () {
         OneUI.init('uiForms');
         BasePagesLogin.init();
  });

  $scope.checkLogin = function(){


    $http.post('rest/login/checkuser/',$scope.user).success(function (loginResponse) {
      
      var isMaster = false;// Bandera indicando si el usuario ingresado tiene rol de master
      
      for (var i = 0; i < loginResponse.idRoles.length; i++) {
        if(loginResponse.idRoles[i] == $scope.idMaster){
          isMaster = true;
          $scope.masterName = loginResponse.firstName;
        }
      }

      if(isMaster){
        
        $scope.showInstitutions(loginResponse);

      }else{

        if(loginResponse.code == 200){

          $http.post('rest/protected/institucion/getInstituto',loginResponse.idInstitucions[loginResponse.idInstitucions.length - 1]).success(function(response) {

            //console.log("Response", loginResponse);
            //console.log("Response", response);

            if(response.institucion.hasSuscripcion == false){

              $scope.cuentaBloqueada = true;

            }else{

              $scope.user = { "userId":loginResponse.userId,
                            "firstName":loginResponse.firstName,
                            "lastName":loginResponse.lastName, 
                            "idInstitucion": response.institucion.idInstitucion,
                            "nombreInstitucion":response.institucion.nombreInstitucion,
                            "logoInstitucion":response.institucion.logoInstitucion,
                            "roles": loginResponse.idRoles};


              $scope.save($scope.user);

              var path = "/lyra/app#/home";
              window.location.href = path;

            }

          })

        }else{
            // PONER DESPUES ERROR INST
        }

      }//

    })//End Http check user
    .catch(function (error) {
      //console.error('exception', error);
      $scope.disabledAll();
      $scope.topMsj = error.status;
      $scope.bodyMsj = error.statusText;
      $scope.onPointError = true;
    }); 
  };

  $scope.save = function(u) {
    //console.log(u);
    $localStorage.user = u;
  };

  //----------------------------------------------------------------------------------------//
  //Formulario de Subsripción//

  //--Visibilidad de los formularios--//
  $scope.onPoint = true;// ng-show del Login
  $scope.onPointUserForm = false;// Visibilidad de formulario de registro de usuario
  $scope.onPointerSubscriptionForm = false;// Visibilidad del formulario de subscripcion
  $scope.onPointShowInstitutionForm = true;// Visisbilidad del formulario de la institución
  $scope.onPointMsj = false// Visibilidad de la vista de mensajes
  $scope.onPointError = false// Visibilidad de la vista de errores
  $scope.invalidLoginS = false// Visibilidad del texto de usuario invalido

  //--Cambio de colores de los botones(Registrar,Subscribir)--//
  $scope.startedR = false;
  $scope.startedS = false;

  //--Habilita los botones de regitrar usuario y subscribir institucion--//
  $scope.btnRegistrar = false;
  $scope.btnSubscribir = false;

  //--Guarda los mensajes para la vista de mensajes
  $scope.topMsj = "";
  $scope.bodyMsj = "";

  //--Validacion--//
  $scope.onlyNumbers = /^\d+$/; 

  //--objectos--//
  $scope.institucion = {};
  $scope.subscriptor = {};

  $scope.showLoginForm = function(){//Funcion de la visibilidad del login
    if($scope.onPoint){
      $scope.onPoint = false;
    }else{
      $scope.onPoint = true;
    }
    //console.log('Formulario login? ', $scope.onPoint);
  }// End showLoginForm

  $scope.showUserForm = function(){//Funcion de la visibilidad del formulario del usuario
    if($scope.onPointUserForm){
      window.location.href = "#";
    }else{
      $scope.onPointUserForm = true;
      $scope.onPoint = false;
      $scope.onPointerSubscriptionForm = false;
      $scope.startedR = true;
      $scope.startedS = false;
      $scope.btnSubscribir = true;
    }
    //console.log('Formulario usuario? ', $scope.onPointUserForm);
  }// End showUserForm

  $scope.showSubscriptionForm = function(){//Funcion de la visibilidad del formulario de subscripción
    if($scope.onPointerSubscriptionForm){
      window.location.href = "#";
    }else{
      $scope.onPointerSubscriptionForm = true;
      $scope.onPoint = false;
      $scope.onPointUserForm = false;
      $scope.startedS = true;
      $scope.startedR = false;
      $scope.btnRegistrar = true;
    }
    //console.log('Formulario Subsripción? ', $scope.onPointerSubscriptionForm);
  }// End showSubscriptionForm

  $scope.disabledAll = function(){// Desabilita todos los botones, formularios y vistas
    $scope.btnRegistrar = true;
    $scope.btnSubscribir = true;
    $scope.onPoint = false;
    $scope.onPointUserForm = false;
    $scope.onPointerSubscriptionForm = false;
    $scope.showLogin = true;
  }

  $scope.closeMSJ = function(){// Refresca la pagina
    window.location.href = "#";
  }// End closeMSJ

  $scope.checkUser = function(){// Chequea que exista un usuario

    $http.post('rest/login/checkuser/',$scope.user).success(function (loginResponse) {

      //console.log(loginResponse);

      if(loginResponse.code == 401){
        $scope.invalidLoginS = true;
      }
      
      if(loginResponse.code == 200){

        $scope.requestObject = {
            "pageNumber": 0,
            "pageSize": 0,
            "direction": "string",
            "sortBy": [
              "string"
            ],
            "searchColumn": "string",
            "searchTerm": "string",
            "usuario": {
              "idUsuario": loginResponse.userId
            },
            
          }

        $http.post('rest/protected/users/getUser',$scope.requestObject).success(function(response) {

            //console.log(response);
            $scope.subscriptor.idSubscriptor = response.usuario.idUsuario;
            $scope.subscriptor.apellido = response.usuario.apellido;
            $scope.subscriptor.cedula = response.usuario.cedula;
            $scope.subscriptor.correo = response.usuario.email;
            $scope.subscriptor.movil = response.usuario.movil;
            $scope.subscriptor.nombre = response.usuario.nombre;
            $scope.subscriptor.telefono = response.usuario.telefono;

            $scope.user = {};
            $scope.onPointShowInstitutionForm = false;

        });     
      }
    })
    .catch(function (error) {
      //console.error('exception', error);
      if(error.status == 500){
        $scope.invalidLoginS = true;
      }else{
        $scope.disabledAll();
        $scope.topMsj = error.status;
        $scope.bodyMsj = error.statusText;
        $scope.onPointError = true;
      }
    });

  }// End checkUser

  $scope.saveSubscripcion = function(){// Guarda la subscripción

    //console.log($scope.subscriptor.idSubscriptor);
    $scope.requestObjectSubscripcion = {

      "pageNumber": 0,
      "pageSize": 0,
      "direction": "string",
      "sortBy": [
        "string"
      ],
      "searchColumn": "string",
      "searchTerm": "string",
      "subscripcion": {
       
          "idSubscripcion": -1,
          "institucion": {

              "idInstitucion": -1,
              "hasSuscripcion": true,
              "logoInstitucion": $scope.institucion.urlLogo,
              "nombreInstitucion": $scope.institucion.nombre,
              "usuarios": [{"idUsuario": $scope.subscriptor.idSubscriptor}]

          },
          "activeSub": true
       }
    }// End saveSubscripcion

    //console.log($scope.requestObjectSubscripcion);

    $http.post('rest/protected/subscripcion/save',$scope.requestObjectSubscripcion).success(function(response2) {
      //console.log(response2);
      $scope.disabledAll();
      $scope.topMsj = "En buena hora!!";
      $scope.bodyMsj = "La institución ha sido subscrita";
      $scope.onPointMsj = true;
    })
    .catch(function (error) {
      //console.error('exception', error);
      $scope.disabledAll();
      $scope.topMsj = error.status;
      $scope.bodyMsj = error.statusText;
      $scope.onPointError = true;
    }); 

  }

  $scope.saveMaster = function(){// Guarda el usuario

    $scope.requestObjectUsuario = {

      "pageNumber": 0,
        "pageSize": 0,
        "direction": "string",
        "sortBy": [
          "string"
        ],
        "searchColumn": "string",
        "searchTerm": "string",
        "usuario": {

          "idUsuario": -1,
          "nombre": $scope.subscriptor.nombre, 
          'apellido':  $scope.subscriptor.apellido, 
          'cedula': $scope.subscriptor.cedula,
          "telefono": $scope.subscriptor.telefono,  
          "movil": $scope.subscriptor.movil, 
          "email": $scope.subscriptor.correo, 
          "activeUs": $scope.subscriptor.activeSub, 
          "password": $scope.subscriptor.contrasena,
          "idRoles": [1]

      }
    }

    $http.post('rest/protected/users/saveUser',$scope.requestObjectUsuario).success(function(response) {
      //console.log(response);
      $scope.disabledAll();
      $scope.topMsj = "En buena hora!!";
      $scope.bodyMsj = "Gracias por registrase en Lyra";
      $scope.onPointMsj = true;
    })
    .catch(function (error) {
      //console.error('exception', error);
      $scope.disabledAll();
      $scope.topMsj = error.status;
      $scope.bodyMsj = error.statusText;
      $scope.onPointError = true;
    });; 

  }// End saveMaster

  $scope.initScripts = function(){//Inicialización de componente del formulario

    angular.element(document).ready(function () {
        BaseFormWizard.init();
        BaseFormValidation.init();
        App.initHelpers('slimscroll');
    });

  }// End initScripts

  $scope.initScripts();

  $scope.recoverPassword = function(){


    $http.post('rest/login/getCredentials',$scope.email).success(function (loginResponse) {

      if(loginResponse.code == 200){
          $scope.disabledAll();
          $scope.topMsj =  "Recuperación de Credenciales";
          $scope.bodyMsj = "Credenciales enviadas, por favor revise su bandeja de entrada.";
          $scope.onPointMsj = true;

      }else{
        $scope.disabledAll();
        $scope.topMsj = error.status;
        $scope.bodyMsj = error.statusText;
        $scope.onPointError = true;
      }

      $scope.showLogin = true;

    })

    .catch(function (error) {
          //console.error('exception', error);
          $scope.disabledAll();
          $scope.topMsj = error.status;
          $scope.bodyMsj = error.statusText;
          $scope.onPointError = true;
        }); 

  };


  $scope.showRecoverForm = function(u) {
    if($scope.showLogin){
        $scope.btnRegistrar = true;
        $scope.btnSubscribir = true;
        $scope.showLogin = false;
        $scope.onPoint = false;
    }
  }; 

  $scope.closeRecoverForm = function(u) {
    window.location.href = "#";
  }; 


  $scope.save = function(u) {
    //console.log(u);
    $localStorage.user = u;
  };


  //-------------------------------------------------------------------------------//
  //Ventana de seleccion de institucion para el Master//

  $scope.onPointPaMaster = false; // Visibilidad de la ventana de selección de institución del master
  $scope.master // Guarda el objeto usuario
  $scope.cuentaDelMasterBloqueada = false // Visibilidad del texto cuando la cuenta del master esta bloqueada

  $scope.showInstitutions = function(obj){// Visibilidad y datos de la ventana de selección de institución del master 
    $scope.instituciones = obj.instituciones;
    $scope.master = obj;
    $scope.onPointPaMaster = true;
    $scope.disabledAll();
    //console.log($scope.instituciones);
  }

  $scope.ingresarInstituto = function(i){// Ingresa al instituto selecionado

    //console.log(i.hasSuscripcion);

    if(i.hasSuscripcion == false){
              
        $scope.cuentaDelMasterBloqueada = true;

    }else{

      $scope.user = { "userId":$scope.master.userId,
                      "firstName":$scope.master.firstName,
                      "lastName":$scope.master.lastName, 
                      "idInstitucion": i.idInstitucion,
                      "nombreInstitucion":i.nombreInstitucion,
                      "logoInstitucion":i.logoInstitucion,
                      "roles": $scope.master.idRoles};

      $scope.save($scope.user);

      var path = "/lyra/app#/home";
      window.location.href = path;
  
    }

  }
        
}]);
