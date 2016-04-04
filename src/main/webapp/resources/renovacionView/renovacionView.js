'use strict';

angular.module('myApp.renovacionView', ['ngRoute','ngStorage'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/renovate', {
    templateUrl: 'resources/renovacionView/renovacionView.html',
    controller: 'renovacionViewCtrl'
  });
}])

.controller('renovacionViewCtrl', ['$scope','$http','$window','$localStorage',function($scope,$http,$window,$localStorage) {
  
  $scope.user = {};
  $scope.email = "";
  $scope.idMaster = 1; //id que le pertenece al rol Master
  $scope.masterName;
  $scope.onPoint = true;// Visibilidad del login de subscripcion
  $scope.onPointError = false;// Visibilidad del mensaje de error
  $scope.onPointMsj = false;// Visibilidad de la vista de mensajes
  $scope.topMsj = "";// Contiene el titulo del mensaje a mostrar 
  $scope.bodyMsj = "";// Contiene el cuerpo del mensaje a mostrar

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

        //Si no es master
      }//

    })//End Http check user
    .catch(function (error) {
      //console.error('exception', error);
      
      $scope.closeMSJ();
      $scope.onPoint = false;
      $scope.topMsj = error.status;
      $scope.bodyMsj = error.statusText;
      $scope.onPointError = true;
    }); 
  };
  
  $scope.initScripts = function(){//Inicialización de componente del formulario

    angular.element(document).ready(function () {
        BaseFormWizard.init();
        BaseFormValidation.init();
        App.initHelpers('slimscroll');
    });

  }// End initScripts

  $scope.initScripts();

  
  //-------------------------------------------------------------------------------//
  //Ventana de seleccion de institucion para el Master//

  $scope.onPointPaMaster = false; // Visibilidad de la ventana de selección de institución del master
  $scope.onPointRenovar = false;
  $scope.master; // Guarda el objeto usuario
  $scope.instituciones = [];
  $scope.institucionARenovar;
  $scope.noPendientes = false; // Visibilidad del texto "no tiene pendientes"

  $scope.showInstitutions = function(obj){// Visibilidad y datos de la ventana de selección de institución del master 
    for (var i = 0; i < obj.instituciones.length; i++) {
      //console.log(obj.instituciones[i]);
      if(obj.instituciones[i].subscripcions != null){
        //console.log(obj.instituciones[i].subscripcions);
        $scope.instituciones.push(obj.instituciones[i]);
      }
    };

    if($scope.instituciones.length == 0){
      $scope.noPendientes = true;
    }
    //$scope.instituciones = obj.instituciones;
    $scope.master = obj;
    $scope.onPoint = false;
    $scope.onPointPaMaster = true;
    //console.log($scope.instituciones);
  }

  $scope.closeMSJ = function(){// Cierra todas las vistas dejando solo la vista inicial
    $scope.instituciones = [];
    $scope.onPoint = true;
    $scope.onPointPaMaster = false;
    $scope.onPointRenovar = false;
    $scope.noPendientes = false;
    $scope.onPointError = false;

  }

  $scope.ingresarInstituto = function(i){// Ingresa al instituto selecionado
    $scope.institucionARenovar = i;
    //console.log($scope.institucionARenovar);
    //console.log($scope.master);
    $scope.onPointPaMaster = false;
    $scope.onPointRenovar = true;

  }

  $scope.renovar = function(){// Ingresa al instituto selecionado
    //console.log($scope.institucionARenovar.subscripcions[0].idSubscripcion);
    //console.log($scope.master);

    $scope.requestObject = {
      "pageNumber": 0,
      "pageSize": 0,
      "direction": "string",
      "sortBy": [
        "string"
      ],
      "searchColumn": "string",
      "searchTerm": "string",
      "subscripcion": {"idSubscripcion": $scope.institucionARenovar.subscripcions[0].idSubscripcion,
                       "institucion": {"idInstitucion":$scope.institucionARenovar.idInstitucion}}
    }

    //console.log($scope.requestObject);

    $http.post('rest/protected/subscripcion/renovarSubscripcion',$scope.requestObject).success(function(response){
      //console.log(response);
      $scope.onPointPaMaster = false;
      $scope.onPointRenovar = false;
      $scope.topMsj = "En buena hora!!";
      $scope.bodyMsj = "La cuenta ha sido renovada y vence el: " + response.subscripcion.fechaFinString;
      $scope.onPointMsj = true;
    })
    .catch(function (error) {
      //console.error('exception', error);
      
      $scope.closeMSJ();
      $scope.onPoint = false;
      $scope.topMsj = error.status;
      $scope.bodyMsj = error.statusText;
      $scope.onPointError = true;
    });   

    // $scope.topMsj = "En buena hora!!";
    //   $scope.bodyMsj = "La cuenta ha sido renovada y vence el: ";
    //   $scope.onPointMsj = true; 
    
  }

  $scope.irLogin = function(){// Envia a la página de login
     window.location.href = "#";
  }
        
}]);
