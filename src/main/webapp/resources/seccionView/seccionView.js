'use strict';

angular.module('myApp.seccionView', ['ngRoute'])

.controller('seccionViewCtrl', ['$scope','$http','$timeout','$state',function($scope,$http,$timeout,$state) {
  
  $scope.seccionList = [];// Lista de Secciones
  $scope.gradoList = [];// Lista de Grados
  $scope.AlumnosNoSeccionList = [];// Lista de Alumnos sin seccion
  $scope.AlumnosAsignados = [];// Lista de Alumnos asignados a la seccion 

  $scope.newSec = [];
  $scope.oldSec = [];
  $scope.counter = 0;
  $scope.isCreating = true;
  $scope.onPoint = true;// Visibilidad de la ventana de listado de secciones
  $scope.onPointFormSeccion = false;// Visibilidad del formulario de registro de seccion
  $scope.onPointAlumnoToSeccion = false; // Visibilidad de la ventana de asignacion de estudiantes a seccion
  
  $scope.initScripts = function(){

    angular.element(document).ready(function () {
          
           BaseTableDatatables.init();
       //BaseFormValidation.init();
      //OneUI.initHelpers('select2');
    });

  }

  $scope.init = function(){// Carga las secciones del instituto
    $scope.isCreating = true;

    $http.post('rest/protected/institucion/getGradosDelInstituto',$scope.user.idInstitucion).success(function(response) {
      console.log("response",response)
      //$scope.seccionList = response.institucion.grados;
      $scope.gradoList = response.institucion.grados;

      for (var g in response.institucion.grados){
        for (var s in response.institucion.grados[g].seccions) {
          console.log(response.institucion.grados[g].seccions[s]);
          $scope.seccionList.push(response.institucion.grados[g].seccions[s]);
        }
      }

    });

  }

  $scope.increment = function(){
      $scope.counter += 1;
    }


  $scope.showForm = function(){
    console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
    $scope.onPointFormSeccion = true;
    $scope.onPoint = false;
  }

  $scope.showList = function(){

    $scope.newSec = {};
    $scope.onPoint = false;
    $scope.isCreating = true;
  }

  /** Muestra el formulario con la informacion de la seccion a modificar**/
  $scope.showSeccionToEdit = function(s){

    $scope.newSec = s; // Guarda el objeto usuario a la variable temporal
    $scope.newSec.nombreSeccion = s.nombreSeccion;
    $scope.newSec.selected_grado = s.grado.idGrado;
    //console.log(s.grado.idGrado);
    //console.log(s.grado.nombre);
    //$scope.newSec.grselected_gradoado = {nombre: s.grado.nombre, idGrado: s.grado.idGrado};
    $scope.onPoint = false;
    $scope.onPointFormSeccion = true;
    $scope.isCreating = false;
  }

  /** Cambia el estado de la seccion**/
  $scope.isActive = function(s){

    $scope.newSec = s; // Guarda el objeto usuario a la variable temporal

    if($scope.newSec.activeSec){
      $scope.newSec.activeSec = false;
    }else{
      $scope.newSec.activeSec = true;
    }
    $scope.newSec.selected_grado = s.grado.idGrado;
    $scope.isCreating = false;
    $scope.saveSeccion();

  }

  /** Guarda la seccion creada o modificada**/
  $scope.saveSeccion = function(){
    if($scope.isCreating){
      $scope.newSec.idSeccion = -1;
      $scope.newSec.activeSec = true;
    }
    // $scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
    // "string","materia":{"idMateria": $scope.newMat.idMateria,"nombre": $scope.newMat.nombre,"activeMat": $scope.newMat.activeMat}};

    $scope.requestObject ={
      "code": 0,
      "codeMessage": "string",
      "errorMessage": "string",
      "totalPages": 0,
      "totalElements": 0,
      "seccion": {
        "idSeccion": $scope.newSec.idSeccion,
          "nombreSeccion": $scope.newSec.nombreSeccion,
          "activeSec": $scope.newSec.activeSec,
          "grado": {"idGrado": $scope.newSec.selected_grado}
         

      }
    }

    console.log($scope.newSec.selected_grado);

    $http.post('rest/protected/seccion/saveSeccion',$scope.requestObject).success(function(response) {

      if($scope.isCreating){//Si esta creando setea un -1 al tipo de usuario
        $state.reload();
      }else{
        $scope.showList();
        $scope.init();
      }
      $state.reload();

    }); 
  }

  $scope.objSeccion; // Guarda un objeto seccion que se usuará para asignar alumnos

  //** Ventana de asignacion de alumnos a secciones, obtine la lista de alumnos con y sin secciones**//
  $scope.openAlumnoToSeccion = function(s){

    $http.post('rest/protected/seccion/getSeccion',s.idSeccion).success(function(response) {

      console.log(response.seccion.alumnos);
      $scope.AlumnosAsignados = response.seccion.alumnos;
      $scope.objSeccion = response.seccion;

    }); 

    $http.post('rest/protected/institucion/getAlumnosSinSeccionDelInstituto',$scope.user.idInstitucion).success(function(response) {

      console.log(response.institucion.alumnos);
      $scope.AlumnosNoSeccionList = response.institucion.alumnos;
      $scope.seccionName = s.nombreSeccion;
      $scope.onPointAlumnoToSeccion = true;
      $scope.onPoint = false;

    }); 
    
  }

  /** Refresca la pagina, cada vez que cierre una ventana**/
  $scope.close = function(){
    $state.reload();
  }

  //---------
  $scope.selectNoAsignado;// Guarda el indice del alumno sin seccion del select/option
  $scope.objNoAsignado;// Guarda un objeto no asignado

  /** Obtiene el indice del alumno sin seccion del select/option**/
  $scope.targetNoAsignado = function(a){
    $scope.objNoAsignado = a;
    $scope.selectNoAsignado = $scope.AlumnosNoSeccionList.indexOf(a);
  }

  /** limina a un alumno de laa lista de sin seccion del select/option**/
  $scope.borrarNoAsignado = function(){
    if(!angular.isUndefined($scope.selectNoAsignado)){
      $scope.AlumnosAsignados.push($scope.objNoAsignado);
      $scope.AlumnosNoSeccionList.splice($scope.selectNoAsignado, 1);
      $scope.objNoAsignado = undefined;
      $scope.selectNoAsignado = undefined;
    }
  }

  //---------
  $scope.selectAsignado;// Guarda el indice del alumno en una seccion del select/option
  $scope.objAsignado;// Guarda un objeto asinado a seccion

  /** Obtiene el indice del alumno que esta asignado en la seccion del select/option**/
  $scope.targetAsignado = function(a){
    $scope.objAsignado = a;
    $scope.selectAsignado = $scope.AlumnosAsignados.indexOf(a);
  }

  /** Elimina a un alumno de la lista que esta asignado en la seccion del select/option**/
  $scope.borrarAsignado = function(){
    if(!angular.isUndefined($scope.selectAsignado)){
      $scope.AlumnosNoSeccionList.push($scope.objAsignado);
      $scope.AlumnosAsignados.splice($scope.selectAsignado, 1);
      $scope.objAsignado = undefined;
      $scope.selectAsignado = undefined;
    }
  }

  /**Remueve a todos los estudiantes de una seccion, los pasa a estado de no tener seccion**/
  $scope.todosDesasignarlos = function(){
    for (var i = 0; i < $scope.AlumnosAsignados.length; i++) {
      $scope.AlumnosNoSeccionList.push($scope.AlumnosAsignados[i]);
    };
    $scope.AlumnosAsignados.splice(0);
    $scope.objAsignado = undefined;
    $scope.selectAsignado = undefined;
    $scope.objNoAsignado = undefined;
    $scope.selectNoAsignado = undefined;
  }

 /** Asigna a  todos los estudiantes a seccion**/ 
  $scope.todosAsignarlos = function(){
    for (var i = 0; i < $scope.AlumnosNoSeccionList.length; i++) {
      $scope.AlumnosAsignados.push($scope.AlumnosNoSeccionList[i]);
    };
    $scope.AlumnosNoSeccionList.splice(0);
    $scope.objAsignado = undefined;
    $scope.selectAsignado = undefined;
    $scope.objNoAsignado = undefined;
    $scope.selectNoAsignado = undefined;
  }


  //** Guarda los cambios de las asignasiones o desasignaciones**/
  $scope.registrarAsignaciones = function(){
    console.log($scope.AlumnosNoSeccionList);
    console.log($scope.AlumnosAsignados);

    console.log($scope.objSeccion.idSeccion);
    console.log($scope.objSeccion.nombreSeccion);
    console.log($scope.objSeccion.activeSec);
    console.log($scope.objSeccion.grado.idGrado);

    $scope.requestObject = {
      "code": 0,
      "codeMessage": "string",
      "errorMessage": "string",
      "totalPages": 0,
      "totalElements": 0,
      "seccion": {
        "idSeccion": $scope.objSeccion.idSeccion,
        "nombreSeccion": $scope.objSeccion.nombreSeccion,
        "activeSec": $scope.objSeccion.activeSec,
        "grado": {"idGrado": $scope.objSeccion.grado.idGrado},
        "alumnos": $scope.AlumnosAsignados
         
      }
    }

    $http.post('rest/protected/seccion/saveSeccion',$scope.requestObject).success(function(response) {

      $state.reload();

    }); 

  }

  $timeout( function(){ $scope.initScripts(); }, 100);
  $scope.init();

}]);