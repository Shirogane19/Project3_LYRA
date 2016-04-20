'use strict';

angular.module('myApp.materiaView', ['ngRoute'])


.controller('materiaViewCtrl', ['$scope','$http','$timeout','$state','$localStorage', function($scope,$http,$timeout,$state,$localStorage) {
  
  $scope.materiaList = [];
  $scope.objMateria  = [];
  $scope.ProfesAsignados   = []; // Profes asignados a la materia 
  $scope.ProfesNoAsignados = []; //¨Profes no asignados a la materia

  $scope.initScripts = function(){

    angular.element(document).ready(function () {
          
      BaseTableDatatables.init();

    });
  }

  $scope.init = function(){
    $scope.isCreating = true;
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","materias": {}};
		$http.post('rest/protected/institucion/getMateriasDelInstituto',$scope.user.idInstitucion).success(function(response) {
			$scope.materiaList = response.institucion.materias;
	
		})
    .catch(function (error) {

      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

   }


  $scope.newMat = [];
  $scope.oldMat = [];
  $scope.counter = 0;
  $scope.isCreating = true;
  $scope.onPoint = false;

  $scope.increment = function(){
    $scope.counter += 1;
  }

  /** Refresca la pagina, cada vez que cierre una ventana**/
$scope.close = function(){
    $state.reload();
  }

$scope.showForm = function(){
  //console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
  $scope.onPoint = true;
  }

$scope.showList = function(){

  $scope.newMat = {};
  $scope.onPoint = false;
  $scope.isCreating = true;
  }

  $scope.showMateriaToEdit = function(m){

  $scope.newMat = m; // Guarda el objeto usuario a la variable temporal
  $scope.newMat.nombre = m.nombre;
  
  $scope.showForm();
  $scope.isCreating = false;
}

 $scope.isActive = function(m){

  $scope.newMat = m; // Guarda el objeto usuario a la variable temporal

  if($scope.newMat.activeMat){
    $scope.newMat.activeMat = false;
  }else{
    $scope.newMat.activeMat = true;
  }

  $scope.isCreating = false;
  $scope.saveMateria();

}

$scope.saveMateria = function(){
    if($scope.isCreating){
      $scope.newMat.idMateria = -1;
      $scope.newMat.activeMat = true;
    }

$scope.requestObject ={
  "code": 0,
  "codeMessage": "string",
  "errorMessage": "string",
  "totalPages": 0,
  "totalElements": 0,
  "materia": {
      "activeMat": $scope.newMat.activeMat,
      "idMateria": $scope.newMat.idMateria,
      "nombre": $scope.newMat.nombre,
      "institucion": {"idInstitucion":$scope.user.idInstitucion}

  }
}


    $http.post('rest/protected/materia/saveMateria',$scope.requestObject).success(function(response) {

      if($scope.isCreating){//Si esta creando setea un -1 al tipo de usuario
        $state.reload();
      }else{
        $scope.showList();
        $scope.init();
      }
      $state.reload();

    })
    .catch(function (error) {

      $localStorage.error = error.status;
      $state.go('errorView');
    }); 
  }


  //**********

  $scope.onPointProfeToMateria = false;
  $scope.materiaList = true;

  $scope.openProfesToMateria = function(m){

    $http.post('rest/protected/materia/getProfesDeMateria', m.idMateria).success(function(response) {

      $scope.objMateria = response.materia;
      $scope.ProfesAsignados = response.materia.profesorMateria;// Profes asignados a la materia 

    })
    .catch(function (error) {
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $http.post('rest/protected/institucion/getProfesoresDelInstituto', $scope.user.idInstitucion).success(function(response) {


      var found = false;

      for (var i = 0; i < response.institucion.usuarios.length; i++) {//
        for (var p = 0; p < $scope.ProfesAsignados.length; p++) {////
          if(response.institucion.usuarios[i].idUsuario != $scope.ProfesAsignados[p].idUsuario){
          }else{
            found = true; 
          }
        };
        if(found == false){
          $scope.ProfesNoAsignados.push(response.institucion.usuarios[i]);
        }
        found = false;
      };

    })
    .catch(function (error) {
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $scope.materiaName = m.nombre;
    $scope.onPointProfeToMateria = true;
    $scope.materiaList = false;

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

   // console.log('Materia', $scope.objMateria);

    $scope.requestObject = {
      "code": 0,
      "codeMessage": "string",
      "errorMessage": "string",
      "totalPages": 0,
      "totalElements": 0,
      "materia": {
        "idMateria": $scope.objMateria.idMateria,
        "nombre": $scope.objMateria.nombre,
        "activeMat": $scope.objMateria.activeMat,
        "profesorMateria": $scope.ProfesAsignados,
        "institucion": {"idInstitucion":$scope.user.idInstitucion}
      }
    }

    $http.post('rest/protected/materia/saveMateria',$scope.requestObject).success(function(response) {

      $state.reload();

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

  }

  //---------------

  $scope.tempProfesNoAsignados;// Profes selecionados de la lista de profes sin seccion
  /** Version mejorada del paso de profes sin seccion a la lista de profes con seccion,permite multiple seleciones **/
  $scope.borrarProfeNoAsignado2 = function(){
    if(!angular.isUndefined($scope.tempProfesNoAsignados)){

      for (var i = 0; i < $scope.tempProfesNoAsignados.length; i++) {
        $scope.ProfesAsignados.push($scope.tempProfesNoAsignados[i]);

        $scope.indexList = $scope.ProfesNoAsignados.indexOf($scope.tempProfesNoAsignados[i]);
        $scope.ProfesNoAsignados.splice($scope.indexList, 1);
      };

      $scope.tempProfesNoAsignados = undefined;
    }
  }

  $scope.tempProfesAsignados;// Profes selecionados de la lista de profes con seccion
  /** Version mejorada del paso de profes con seccion a la lista de profes sin seccion,permite multiple seleciones **/
  $scope.borrarProfeAsignado2 = function(){
    //console.log($scope.tempProfesAsignados);
    if(!angular.isUndefined($scope.tempProfesAsignados)){
      
      for (var i = 0; i < $scope.tempProfesAsignados.length; i++) {
        $scope.ProfesNoAsignados.push($scope.tempProfesAsignados[i]);

        $scope.indexList = $scope.ProfesAsignados.indexOf($scope.tempProfesAsignados[i]);
        $scope.ProfesAsignados.splice($scope.indexList, 1);
      };

      $scope.tempProfesAsignados = undefined;
    }
  }

  //---------------

 $timeout( function(){ $scope.initScripts(); }, 100);
   $scope.init();

  }]);