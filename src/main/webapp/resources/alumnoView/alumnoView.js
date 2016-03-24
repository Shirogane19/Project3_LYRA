'use strict';

angular.module('myApp.alumnoView', [])


.controller('alumnoViewCtrl', ['$scope','$http','$timeout','$state',function($scope,$http,$timeout,$state) {
  
  $scope.alumnos = [];
  $scope.newAlumno = [];
  $scope.oldAlumno = [];
  $scope.encargados = [];
  $scope.counter = 0;
  $scope.onPoint = false;
  $scope.isCreating = true;
  $scope.onlyNumbers = /^\d+$/;

  $scope.newAlumno.genero = "M";
  $scope.newAlumno.apellido2 ="";
  $scope.encargado;

  $scope.onPointFindUser = false;// Visibilidad de la ventana de busqueda de usuario
  $scope.onPointShowUser = false;// Visibilidad de la ventana de datos del usuario

  $scope.invalidUser = false;

  $scope.cedulaInput = false;

  $scope.initScripts = function(){

    angular.element(document).ready(function () {
        //   OneUI.init();
           BaseTableDatatables.init();
           BaseFormValidation.init();
           OneUI.initHelpers('select2');
    });

  }

  $scope.increment = function(){
    $scope.counter += 1;
  }

  $scope.showForm = function(){
    $scope.onPoint = true;
    console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
  }

  $scope.showList = function(){
    $state.reload();
    // $scope.newAlumno = {};
    // $scope.newAlumno.genero = "M";
    // $scope.newAlumno.apellido2 ="";
    // $scope.onPoint = false;
    // $scope.isCreating = true;
    // $scope.onPointFindUser = false;
    // $scope.onPointShowUser = false;
    // $scope.encargados = {};
  }

  $scope.showAlumnoToEdit = function(a){

    $scope.newAlumno = a;
    $scope.newAlumno.nombre = a.nombre;
    $scope.newAlumno.apellido1 = a.apellido1;
    $scope.newAlumno.apellido2 = a.apellido2;
    $scope.newAlumno.cedula = a.cedula; 
    $scope.newAlumno.genero = a.genero; 
    $scope.newAlumno.activeAl = a.activeAl;
    $scope.encargados = a.usuarios;
    console.log($scope.newAlumno);
    $scope.showForm();
    $scope.isCreating = false;
  }

  $scope.isActive = function(a){

    $scope.newAlumno = a; 

    if($scope.newAlumno.activeAl){
      $scope.newAlumno.activeAl = false;
    }else{
      $scope.newAlumno.activeAl = true;
    }

    console.log($scope.newAlumno.activeAl);
    $scope.isCreating = false;
    $scope.saveAlumno();

  }

  $scope.changeFindUserVisibility = function(){
    $scope.cedula = "";
    if($scope.onPointFindUser){
      $scope.onPointFindUser = false;
      $scope.onPointShowUser = false;
      $scope.encargado = {};
    }else{
      $scope.cedulaInput = false;
      $scope.onPointFindUser = true;
    }
    console.log('Find User? ', $scope.onPointFindUser);
  }

  $scope.changeShowUserVisibility = function(){
    if($scope.onPointShowUser){
      $scope.onPointShowUser = false;
      $scope.cedulaInput = false;
    }else{
      $scope.onPointShowUser = true;
    }
    console.log('Find User? ', $scope.onPointShowUser);
  }

  $scope.init = function(){
    console.log($scope.user.idInstitucion);
    $scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","alumno": {}};
    $http.post('rest/protected/institucion/getAlumnosDelInstituto',$scope.user.idInstitucion).success(function(response) {
      console.log("response",response)
      $scope.alumnos = response.institucion.alumnos;
    });

  }

  $scope.saveAlumno = function(){

    if($scope.isCreating){
      $scope.newAlumno.idAlumno = -1;
      $scope.newAlumno.activeAl = true;
    }

    $scope.requestObject = {
        "pageNumber": 0,
        "pageSize": 0,
        "direction": "string",
        "sortBy": [
          "string"
        ],
        "searchColumn": "string",
        "searchTerm": "string",
        "alumno": {

            "idAlumno": $scope.newAlumno.idAlumno,
            "apellido1": $scope.newAlumno.apellido1,
            "apellido2": $scope.newAlumno.apellido2,
            "cedula": $scope.newAlumno.cedula,
            "genero": $scope.newAlumno.genero,
            "nombre": $scope.newAlumno.nombre,
            "institucion": {"idInstitucion":$scope.user.idInstitucion},
            "seccion": {},
            "usuarios": $scope.encargados,
            "historialMedicos": [],
            "activeAl": $scope.newAlumno.activeAl

         }
      }

      console.log($scope.encargados);

    $http.post('rest/protected/alumno/save',$scope.requestObject).success(function(response) {

      $state.reload();

    }); 
  }

  $scope.findUserForm = function(){
    $scope.changeFindUserVisibility();
  }

  $scope.getUser = function(){
    $scope.requestObject = {
        "pageNumber": 0,
        "pageSize": 0,
        "direction": "string",
        "sortBy": [
          "string"
        ],
        "searchColumn": "string",
        "searchTerm": "string",
        "usuario": {"cedula":$scope.cedula}
    }

    $http.post('rest/protected/users/getUserByCedula',$scope.requestObject).success(function(response) {

      console.log(response);
      if(response.usuario.idUsuario){
        $scope.encargado=response.usuario;
        $scope.invalidUser = false;
        $scope.onPointShowUser = true;
        $scope.cedulaInput = true;
      }else{
        $scope.invalidUser = true;
        $scope.encargado;
      }

    }); 

  }//

  $scope.agregarEncargado = function(){ // Agrega un encargado al select/option
    var existe = false;// variable que indica si existe un encargado en el select/option
    if ($scope.encargados.length === 0) {
      $scope.encargados.push($scope.encargado);
    }else{

      for (var i in $scope.encargados){
        console.log($scope.encargados[i].idUsuario);
        console.log($scope.encargado.idUsuario);
        if ($scope.encargados[i].idUsuario === $scope.encargado.idUsuario) {
          existe = true;
          break;
        }
      }

      if(!existe){
        $scope.encargados.push($scope.encargado);
      }

    }//
    console.log($scope.encargados);
    $scope.onPointFindUser = false;
    $scope.onPointShowUser = false;
  }//

  $scope.selectEncargado;// Guarda el indice del encargado del select/option

  $scope.targetEncargado = function(e){//Obtiene el indice del encargado del select/option
    console.log(e);
    $scope.selectEncargado = $scope.encargados.indexOf(e);
  }

  $scope.borrarEncargado = function(){// Elimina un encargado del select/option
    if(!angular.isUndefined($scope.selectEncargado)){
      $scope.encargados.splice($scope.selectEncargado, 1);
    }
  }

  $scope.showHistorial = function(a){

    console.log(a);
    $state.go('registroView',{alumnoInfo: a});

  }

  $timeout( function(){ $scope.initScripts(); }, 100);
  $scope.init();

}]);