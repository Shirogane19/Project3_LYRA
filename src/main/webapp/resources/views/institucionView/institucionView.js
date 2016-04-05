'use strict';

angular.module('myApp.institucionView', [])

.controller('institucionViewCtrl', ['$scope','$http','$timeout','$state','$localStorage', function($scope,$http,$timeout,$state,$localStorage) {

	$scope.onPoint = true;// Visibilidad del formulario de la institucion
	$scope.respInstitucion = {"nombre":$scope.user.nombreInstitucion, "logo":$scope.user.logoInstitucion};// Guarda los valores en una variable temporal

	$scope.openForm = function(){// Controla la visibilidad la vista y formulario de institucion
		if($scope.onPoint){
	      $scope.onPoint = false;
	    }else{
	      $scope.onPoint = true;
	      $scope.returnInstitutionData();
	    }
	    //console.log('Formulario login? ', $scope.onPoint);
	}

	$scope.returnInstitutionData = function(){// Recupera los valores anteriores de la institucion
		$scope.user.nombreInstitucion = $scope.respInstitucion.nombre;
		$scope.user.logoInstitucion = $scope.respInstitucion.logo;
	}

	$scope.saveInstitucion = function(){// Guarda los cambios de institucion

		$scope.requestObject = {

			"pageNumber": 0,
			"pageSize": 0,
			"direction": "string",
			"sortBy": [
			  "string"
			],
			"searchColumn": "string",
			"searchTerm": "string",
			"institucion": {
			 
			    "idInstitucion": $scope.user.idInstitucion,
			    "logoInstitucion": $scope.user.logoInstitucion,
			    "nombreInstitucion": $scope.user.nombreInstitucion
			}
	    }

	    //console.log($scope.requestObject);

	    $http.post('rest/protected/institucion/save',$scope.requestObject).success(function(response) {
	        //console.log(response);
	        $scope.respInstitucion = {"nombre":$scope.user.nombreInstitucion, "logo":$scope.user.logoInstitucion};
	        $scope.openForm();
	    })
	    .catch(function (error) {
	      //console.error('exception', error.status);
	      $localStorage.error = error.status;
	      $state.go('errorView');
	    }); 

	}// End saveInstitucion

}]);