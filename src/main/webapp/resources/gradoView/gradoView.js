'use strict';

angular.module('myApp.gradoView', ['ngRoute'])

.controller('gradoViewCtrl', ['$scope','$http','$timeout','$state',function($scope,$http,$timeout,$state) {

	$scope.isCreating = true;
	$scope.gradosList = [];
    $scope.newGrado = [];
    $scope.oldGrado = [];

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

		$scope.showForm();
		$scope.isCreating = false;
	}	  



	$timeout( function(){ $scope.initScripts(); }, 100);
	$scope.init();

}]);