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
  $scope.onDetail = false;
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

/** Metodo que redirecciona el usuario a su perfil cuando intenta
 ** presionar el boton de modificar o eliminar. 
 **/

$scope.redirectProfile = function(){
	$state.go('perfilView');
}

/** Metodo que muestra el formulario de registro  **/

$scope.showForm = function(){

	$scope.onPoint = true;
  }

/** Metodo que muestra la lista de usuarios en el sistema **/

$scope.showList = function(){

	$scope.newUser = {};
	$scope.selectedItem = [];
	$scope.onPoint = false;
	$scope.onDetail = false;
	$scope.isCreating = true;
  }

/** metodo que carga la informacion del usuario seleccionado a el formulario de guardar **/

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

/** Metodo que muestra un formulario con la informacion del usuario, sin tener que modificarlo **/

 $scope.showUserDetails = function(u){

 	console.log($scope.roleList);

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
	$scope.onDetail = true;

	console.log($scope.roleList);

}

/** Metodo que cambia el estado de un usuario de activo a inactivo y vice versa **/

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

/** Metodo que inicializa todas la variables necesarias para la funcion de la vista como la carga de inicial
 ** de la lista de usuarios en la institucion **/

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
	})
	.catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 
		
	//	console.log("my institute id", $scope.idInstitucion);
}

/** Metodo que guarda un usuario por medio del consumo de un servicio REST del API Lyra **/

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

	})
	.catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

		 // .catch(function (error) {
   //        //console.error('exception', error);
		 //  $scope.checkResponse(error);	
   //      });  
	}

	/** Valida si hay conexcion **/
	$scope.checkResponse = function(response){

		var status = response.status; // error code

            if ((status >= 500) && (status < 600)) {
				$state.go('500');
            }

	}

	/**Funcion que retarsa la carga de los scripts del template hasta que Angular este listo **/
	 $timeout( function(){ $scope.initScripts(); }, 2000);
 	 $scope.init();




  }]);