'use strict';

angular.module('myApp.seccionView', ['ngRoute'])

.controller('seccionViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',function($scope,$http,$timeout,$state,$localStorage) {
  
  $scope.seccionList = [];// Lista de Secciones
  $scope.gradoList = [];// Lista de Grados
  $scope.AlumnosNoSeccionList = [];// Lista de Alumnos sin seccion
  $scope.AlumnosAsignados = [];// Lista de Alumnos asignados a la seccion 
  $scope.ProfesAsignados = [];// Profes asignados a la seccion 
  $scope.ProfesNoAsignados = [];//¨Profes no asignados a la seccion


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
           OneUI.init();
       //BaseFormValidation.init();
      //OneUI.initHelpers('select2');
    });

  }

  $scope.init = function(){// Carga las secciones del instituto
    $scope.isCreating = true;

    $http.post('rest/protected/institucion/getGradosDelInstituto',$scope.user.idInstitucion).success(function(response) {
      //console.log("response",response)
      //$scope.seccionList = response.institucion.grados;
      $scope.gradoList = response.institucion.grados;

      for (var g in response.institucion.grados){
        for (var s in response.institucion.grados[g].seccions) {
          //console.log(response.institucion.grados[g].seccions[s]);
          $scope.seccionList.push(response.institucion.grados[g].seccions[s]);
        }
      }

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

  }

  $scope.increment = function(){
      $scope.counter += 1;
    }


  $scope.showForm = function(){
    //console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
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

    //console.log($scope.newSec.selected_grado);

    $http.post('rest/protected/seccion/saveSeccion',$scope.requestObject).success(function(response) {

      if($scope.isCreating){//Si esta creando setea un -1 al tipo de usuario
        $state.reload();
      }else{
        $scope.showList();
        $scope.init();
      }
      $state.reload();

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 
  }

  $scope.objSeccion; // Guarda un objeto seccion que se usuará para asignar alumnos

  //** Ventana de asignacion de alumnos a secciones, obtine la lista de alumnos con y sin secciones**//
  $scope.openAlumnoToSeccion = function(s){

    $http.post('rest/protected/seccion/getSeccion',s.idSeccion).success(function(response) {

      //console.log(response.seccion.alumnos);
      $scope.AlumnosAsignados = response.seccion.alumnos;
      $scope.objSeccion = response.seccion;

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $http.post('rest/protected/institucion/getAlumnosSinSeccionDelInstituto',$scope.user.idInstitucion).success(function(response) {

      //console.log(response.institucion.alumnos);
      $scope.AlumnosNoSeccionList = response.institucion.alumnos;
      $scope.seccionName = s.nombreSeccion;
      $scope.onPointAlumnoToSeccion = true;
      $scope.onPoint = false;

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
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
    // console.log($scope.AlumnosNoSeccionList);
    // console.log($scope.AlumnosAsignados);

    // console.log($scope.objSeccion.idSeccion);
    // console.log($scope.objSeccion.nombreSeccion);
    // console.log($scope.objSeccion.activeSec);
    // console.log($scope.objSeccion.grado.idGrado);

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

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

  }

  //**********

  $scope.onPointProfeToSeccion = false;

  $scope.openProfesToSeccion = function(s){

    $http.post('rest/protected/seccion/getProfesDeSeccion',s.idSeccion).success(function(response) {

      //console.log(response.seccion);
      $scope.objSeccion = response.seccion;
      $scope.ProfesAsignados = response.seccion.profesorSeccions;// Profes asignados a la seccion 

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $http.post('rest/protected/institucion/getProfesoresDelInstituto', $scope.user.idInstitucion).success(function(response) {

      //console.log(response.institucion.usuarios);
      //$scope.ProfesNoAsignados = response.institucion.usuarios;//¨Profes no asignados a la seccion
      var found = false;

      for (var i = 0; i < response.institucion.usuarios.length; i++) {//
        for (var p = 0; p < $scope.ProfesAsignados.length; p++) {////
          if(response.institucion.usuarios[i].idUsuario != $scope.ProfesAsignados[p].idUsuario){
          }else{
            found = true; 
          }
        };////
        if(found == false){
          $scope.ProfesNoAsignados.push(response.institucion.usuarios[i]);
        }
        found = false;
      };//

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $scope.seccionName = s.nombreSeccion;
    $scope.onPointProfeToSeccion = true;
    $scope.onPoint = false;
    //console.log(s);
  }

  //--------------

  $scope.selectProfeNoAsignado;// Guarda el indice del profe sin seccion del select/option
  $scope.objProfeNoAsignado;// Guarda un objeto no asignado

  $scope.targetProfeNoAsignado = function(p){
    $scope.objProfeNoAsignado = p;
    $scope.selectProfeNoAsignado = $scope.ProfesNoAsignados.indexOf(p);
  }

  /** limina a un alumno de laa lista de sin seccion del select/option**/
  $scope.borrarProfeNoAsignado = function(){
    if(!angular.isUndefined($scope.objProfeNoAsignado)){
      $scope.ProfesAsignados.push($scope.objProfeNoAsignado);
      $scope.ProfesNoAsignados.splice($scope.selectProfeNoAsignado, 1);
      $scope.selectProfeNoAsignado = undefined;
      $scope.objProfeNoAsignado = undefined;
    }
  }

  //--------------

  $scope.selectProfeAsignado;// Guarda el indice del profe en una seccion del select/option
  $scope.objProfeAsignado;// Guarda un objeto asinado a seccion

  /** Obtiene el indice del profe que esta asignado en la seccion del select/option**/
  $scope.targetProfeAsignado = function(p){
    $scope.objProfeAsignado = p;
    $scope.selectProfeAsignado = $scope.ProfesAsignados.indexOf(p);
  }

  /** Elimina a un profe de la lista que esta asignado en la seccion del select/option**/
  $scope.borrarProfeAsignado = function(){
    if(!angular.isUndefined($scope.objProfeAsignado)){
      $scope.ProfesNoAsignados.push($scope.objProfeAsignado);
      $scope.ProfesAsignados.splice($scope.selectProfeAsignado, 1);
      $scope.objProfeAsignado = undefined;
      $scope.selectProfeAsignado = undefined;
    }
  }

  //--------------

  //** Guarda los cambios de las asignasiones o desasignaciones de los profesores**/
  $scope.registrarAsignacionesProfes = function(){
    // console.log($scope.ProfesNoAsignados);
    // console.log($scope.ProfesAsignados);

    // console.log($scope.objSeccion.idSeccion);
    // console.log($scope.objSeccion.nombreSeccion);
    // console.log($scope.objSeccion.activeSec);
    // console.log($scope.objSeccion.grado.idGrado);

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
        //"alumnos": $scope.AlumnosAsignados,
        "profesorSeccions": $scope.ProfesAsignados
         
      }
    }

    $http.post('rest/protected/seccion/saveSeccion',$scope.requestObject).success(function(response) {

      $state.reload();

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

  }

  //---------------

  $scope.onPointWatchSeccion = false;

  $scope.showSeccion = function(s){
    //console.log(s);
    $scope.seccionName = s.nombreSeccion;

    $http.post('rest/protected/seccion/getSeccion',s.idSeccion).success(function(response) {

      //console.log(response.seccion.alumnos);
      $scope.AlumnosAsignados = response.seccion.alumnos;
      $scope.objSeccion = response.seccion;

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    });

    $http.post('rest/protected/seccion/getProfesDeSeccion',s.idSeccion).success(function(response) {

      //console.log(response.seccion);
      $scope.objSeccion = response.seccion;
      $scope.ProfesAsignados = response.seccion.profesorSeccions;// Profes asignados a la seccion 

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $scope.onPoint = false;
    $scope.onPointWatchSeccion = true;
    $timeout( function(){ OneUI.initHelper('table-tools'); }, 200);

  }//

  $timeout( function(){ $scope.initScripts(); }, 1000);
  $scope.init();

}]);