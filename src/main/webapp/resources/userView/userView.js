'use strict';

angular.module('myApp.userView', ['ngRoute'])

.controller('userViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',function($scope,$http,$timeout,$state,$localStorage) {
  
  $scope.idInstitucion = 0;
  $scope.userList = [];
  $scope.selectedItem = []
  $scope.newUser = [];
  $scope.oldUser = [];
  $scope.roleList =[];
  $scope.counter = 0;
  $scope.isCreating = true;
  $scope.onPoint = false;
  $scope.onlyNumbers = /^\d+$/;
  $scope.myId = 0;


$scope.initScripts = function(){

/** Metodo que inicia los scripts del template **/
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

$scope.redirectProfile = function(){
	$state.go('perfilView');
}

$scope.showForm = function(){
	//console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
	$scope.onPoint = true;
  }

$scope.showList = function(){

	$scope.newUser = {};
	$scope.selectedItem = [];
	$scope.onPoint = false;
	$scope.isCreating = true;
  }

 $scope.showUserToEdit = function(u){


 	if(u.idUsuario === $scope.myId){
		$scope.redirectProfile();
 	}

	$scope.selectedItem = [];
	$scope.newUser = u; // Guarda el objeto usuario a la variable temporal
	$scope.newUser.nombre = u.nombre;
	$scope.newUser.apellido = u.apellido;
	$scope.newUser.cedula = u.cedula; 
	$scope.newUser.telefono = u.telefono;
	$scope.newUser.movil = u.movil;
	$scope.newUser.email = u.email;
	$scope.newUser.activeUs = u.activeUs;
	$scope.roleList = u.rols;
	$scope.showForm();
	$scope.isCreating = false;
}

 $scope.isActive = function(u){

 	if(u.idUsuario === $scope.myId){
 		$scope.redirectProfile();
 	}

	$scope.newUser = u; // Guarda el objeto usuario a la variable temporal

	if($scope.newUser.activeUs){
		$scope.newUser.activeUs = false;
	}else{
		$scope.newUser.activeUs = true;
	}

	$scope.isCreating = false;
	$scope.saveUsuario();

}

$scope.init = function(){
	
	$scope.isCreating = true;
	$scope.myId = $localStorage.user.userId;
	$scope.idInstitucion = $localStorage.user.idInstitucion;

	$scope.requestObject = 
	{"pageNumber": 0,
	 "pageSize": 0,
	 "direction": "string",
	 "sortBy": [""],
	 "searchColumn": "string",
	 "searchTerm": 
	 "string",
	 "usuario":{"idInstitucion": $scope.idInstitucion}};

	$http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
	//	console.log("response",response)
		$scope.userList = response.usuarios;
	});
		
		console.log("my institute id", $scope.idInstitucion);
}

$scope.saveUsuario = function(){
		if($scope.isCreating){//Si esta creando setea un -1 al tipo de usuario
			$scope.newUser.idUsuario = -1;
			$scope.newUser.activeUs = true;
		}
		
		$scope.requestObject = {"pageNumber": 0,
								 "pageSize": 0,
								 "direction": "string",
								 "sortBy": [""],
								 "searchColumn": "string",
								 "searchTerm": 
								 "string","usuario":{"idUsuario": $scope.newUser.idUsuario,
								 "nombre": $scope.newUser.nombre, 
								 'apellido':  $scope.newUser.apellido, 
								 'cedula': $scope.newUser.cedula,
								 "telefono": $scope.newUser.telefono,  
								 "movil": $scope.newUser.movil, 
								 "email": $scope.newUser.email, 
								 "activeUs": $scope.newUser.activeUs, 
								 "idRoles": $scope.selectedItem,
								 "idInstitucion": $scope.idInstitucion}};

	//	console.log($scope.requestObject.usuario);

	$http.post('rest/protected/users/saveUser',$scope.requestObject).success(function(response) {		
				$state.reload();

		}); 
	}


	 $timeout( function(){ $scope.initScripts(); }, 100);
 	 $scope.init();




  }]);