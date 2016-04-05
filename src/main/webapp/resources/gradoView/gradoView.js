'use strict';

angular.module('myApp.gradoView', ['ngRoute'])

.controller('gradoViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',function($scope,$http,$timeout,$state,$localStorage) {

	$scope.isCreating = true;
	$scope.gradosList = [];
    $scope.newGrado = [];
    $scope.oldGrado = [];
    $scope.onPointMat = true;
    $scope.ProfesAsignados = [];
  	$scope.ProfesNoAsignados = [];
  	$scope.AlumnosAsignados = [];
  	$scope.objSeccion;

  $scope.onPointAlumnoToSeccion = false; 

    /** Metodo que inicia los scripts del template **/
	$scope.initScripts = function(){
		angular.element(document).ready(function () {
	         BaseTableDatatables.init();
	         BaseFormValidation.init();
	         OneUI.initHelpers('select2');
	 	});
	}	


	$scope.init = function(){
		$scope.isCreating = true;
		//$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","grado": {}};
		$http.post('rest/protected/institucion/getGradosDelInstituto',$scope.user.idInstitucion).success(function(response) {
			//console.log("response",response)
			$scope.gradosList = response.institucion.grados;
		});
	}

	$scope.saveGrado = function(){
		if($scope.isCreating){//Si esta creando setea un -1 al tipo de grado
			$scope.newGrado.idGrado = -1;
			$scope.newGrado.isActiveGr = true;
		}
		
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
		"string","grado":{"idGrado": $scope.newGrado.idGrado,"nombre": $scope.newGrado.nombre, 'descripcion':  $scope.newGrado.descripcion, 
		"isActiveGr": $scope.newGrado.isActiveGr, "institucion": {"idInstitucion":$scope.user.idInstitucion}}};

		//console.log($scope.requestObject.usuario);

		$http.post('rest/protected/grado/saveGrade',$scope.requestObject).success(function(response) {
				
			$state.reload();

		}); 
	}


	$scope.isActive = function(g){

	$scope.newGrado = g; // Guarda el objeto grado a la variable temporal

	if($scope.newGrado.isActiveGr){
		$scope.newGrado.isActiveGr = false;
	}else{
		$scope.newGrado.isActiveGr = true;
	}
		$scope.isCreating = false;
		$scope.saveGrado();
	}

	$scope.showForm = function(){
		$scope.onPoint = true;
	  }

	$scope.showList = function(){

		$scope.newGrado = {};
		$scope.onPoint = false;
		$scope.isCreating = true;
	  }

 	$scope.showGradoToEdit = function(g){

		$scope.newGrado = g; // Guarda el objeto grado a la variable temporal

		$scope.newGrado.nombre = g.nombre;
		$scope.newGrado.descripcion = g.descripcion;
		$scope.newGrado.isActiveGr = g.isActiveGr
		$scope.newGrado.listIdMaterias = g.materias;



		$scope.showForm();
		$scope.isCreating = false;
	}

	$scope.close = function(){
	    $state.reload();
	  }
	//++++++++++++++++++++++++++++++++++++++++++++++++++
	//**********

  $scope.onPointMateria = false;
  $scope.onPointNew = true;

  $scope.openProfesToSeccion = function(g){

    $scope.requestObject = {
  "pageNumber": 0,
  "pageSize": 0,
  "direction": "string",
  "sortBy": [
    "string"
  ],
  "searchColumn": "string",
  "searchTerm": "string",
  "grado": {
"idGrado": g.idGrado
}
};

		

		$http.post('rest/protected/grado/getGrade',$scope.requestObject).success(function(response) {
				console.log("responseGetGrade", response);
				$scope.objSeccion = response.grado;
      			$scope.ProfesAsignados = response.grado.materias;



		})
		.catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $http.post('rest/protected/institucion/getMateriasDelInstituto', $scope.user.idInstitucion).success(function(response) {

     
      //$scope.MateriasNoAsignados = response.institucion.materias;//¨Profes no asignados a la seccion
     var found = false;
     	
     	console.log("profesasig", $scope.ProfesAsignados.length);
      for (var i = 0; i < response.institucion.materias.length; i++) {
        for (var p = 0; p < $scope.ProfesAsignados.length; p++) {
          
          if(response.institucion.materias[i].idMateria != $scope.ProfesAsignados[p].idMateria){
          	console.log("dif");
          }else{
            found = true; 
            console.log("igual");
            
          }
        };
        if(found == false){
          $scope.ProfesNoAsignados.push(response.institucion.materias[i]);
        }
        found = false;
      };
      
    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $scope.seccionName = g.nombre;
    $scope.onPointMateria = true;
    $scope.onPointMat = false;
    $scope.onPointNew = false;
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
    var listIdMaterias = [];

       for (var i = 0; i < $scope.ProfesAsignados.length; i++) {
       	
       		listIdMaterias.push($scope.ProfesAsignados[i].idMateria);

       };

console.log("idsMats", listIdMaterias);

		$scope.requestObject = {
  
  "pageNumber": 0,
  "pageSize": 0,
  "direction": "string",
  "sortBy": [
    "string"
  ],
  "searchColumn": "string",
  "searchTerm": "string",
  "grado": {
"idGrado" : $scope.objSeccion.idGrado,
"nombre": $scope.objSeccion.nombre,
"isActiveGr": $scope.objSeccion.isActiveGr,
"institucion":{"idInstitucion": $scope.objSeccion.idInstitucion},
 "idMaterias" : listIdMaterias
}
};

		//console.log($scope.requestObject.usuario);

		$http.post('rest/protected/grado/saveGrade',$scope.requestObject).success(function(response) {
				
			$state.reload();

		}) .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    });
 


  }

  //++++++++++++++++++++++++++++++++++++++++++++++++	  



	$timeout( function(){ $scope.initScripts(); }, 100);
	$scope.init();

}]);