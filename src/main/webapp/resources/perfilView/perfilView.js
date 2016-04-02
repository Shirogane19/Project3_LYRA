'use strict';

angular.module('myApp.perfilView', ['ngRoute'])

.controller('perfilViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',function($scope,$http,$timeout,$state,$localStorage) {

	$scope.isAccOwner = true;
	$scope.newPass = false;
	$scope.isPassword = false;

	$scope.isSameUser = false;
	$scope.isValid = false;
	$scope.newEqualsOld = false;

	$scope.invalidPass = false;
	$scope.invalidMail = false;
	$scope.onPointMsj = false;
	$scope.onPointError = false;

	$scope.isError = false;
	$scope.profile = [];

	$scope.onlyNumbers = /^\d+$/;

	$scope.changeTab = function(){
		if($scope.isPassword){
			$scope.isPassword = false;
		}else{
			$scope.isPassword = true;
		}
	}

	$scope.saveChanges = function(){
			if($scope.isPassword){
				$scope.saveProfile();

			}else{

				$scope.matchPassword();

				if($scope.isValid){
					$scope.newPass = true;
					$scope.saveProfile();
				}
			}		
	}


	$scope.init = function(){

		$scope.idUsuario = $localStorage.user.userId;

		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
		"string","usuario":{"idUsuario": $scope.idUsuario}};

		$http.post('rest/protected/users/getUser',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.profile = response.usuario;
		});
	}

	$scope.saveProfile = function(){


		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
		"string","usuario":{"idUsuario": $scope.profile.idUsuario,"nombre": $scope.profile.nombre, 'apellido':  $scope.profile.apellido, 'cedula': 
		 $scope.profile.cedula,"telefono": $scope.profile.telefono,  "movil": $scope.profile.movil, "email": $scope.profile.email, "activeUs": 
		 $scope.profile.activeUs, "accOwner": $scope.isAccOwner, "newPass": $scope.newPass, "password": $scope.profile.password}};

		console.log($scope.requestObject.usuario);

		$http.post('rest/protected/users/saveUser',$scope.requestObject).success(function(response) {
		
		$scope.newPass = false;
		$scope.isPassword = false;
		$scope.isValid = false;

		$scope.save($scope.profile);
		$state.reload();

		}) 

	.catch(function (error) {
          console.error('exception', error);
          $scope.disabledAll();
          $scope.showError(error);
        }); 
	}

	$scope.matchPassword = function(){



		if(angular.equals($scope.profile.email, $scope.profile.confirmEmail)){
			$scope.invalidMail = false;
			$scope.isSameUser = true;

		}else{
			$scope.invalidMail= true;
		}

		if(angular.equals($scope.profile.confirmPassword, $scope.profile.newPassword)){
			$scope.invalidPass = false;
			$scope.newEqualsOld = true;
		}else{
			$scope.invalidPass = true;
			//showMessage("Contraseña Invalida", "Las contraseñas no concuerdan");
		}

		if($scope.isSameUser && $scope.newEqualsOld){

			$scope.isValid = true;
			$scope.profile.password = $scope.profile.newPassword;
		}	

	}


  $scope.showMessage = function(msjHeader, msjBody) {
        $scope.disabledAll();
        $scope.topMsj = msjHeader;
        $scope.bodyMsj = errorBody;
        $scope.onPointError = true;
  };

  $scope.showError = function(e) {
        $scope.disabledAll();
        $scope.topMsj = e.status;
        $scope.bodyMsj = e.statusText;
        $scope.onPointError = true;
  };

  $scope.disabledAll = function(){// Desabilita todos los botones, formularios y vistas
    $scope.isError = true;
  }

   $scope.closeMSJ = function() {
        $state.reload();
  };

  $scope.save = function(u) {
    console.log(u);
    $localStorage.user.firstName = u.nombre;
    $localStorage.user.lastName = u.apellido;
  };

 	 $scope.init();


}]);