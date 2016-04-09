'use strict';

angular.module('myApp.startView', [])

.controller('StartViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',
					 function($scope,$http,$timeout,$state,$localStorage) {

	$scope.stats = {};
	$scope.isAdmin = false;


	$scope.initScripts = function(){

	/** Metodo que inicia los scripts del template **/
	  angular.element(document).ready(function () {


	         OneUI.initHelpers(['appear', 'appear-countTo']);
	  });
	}



	$scope.init = function(){
		
		$scope.idInstitucion = $localStorage.user.idInstitucion;

		$scope.requestObject = 
		{"pageNumber": 0,
		 "pageSize": 0,
		 "direction": "string",
		 "sortBy": [""],
		 "searchColumn": "string",
		 "searchTerm": 
		 "string",
		 "stats":{"idInstitucion": $scope.idInstitucion}};

		$http.post('rest/protected/stats/getStats',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.stats = response.stats;
		})
		.catch(function (error) {
	      //console.error('exception', error.status);
	      $localStorage.error = error.status;
	      $state.go('errorView');
	    }); 

	    $scope.checkForAdmin();
	}


	$scope.checkForAdmin = function(){
		//console.log( $scope.title);

		var master = "Master";
		var admin  = "Admin."

		if(angular.equals($scope.title, master) || angular.equals($scope.title, admin)){
			//console.log( $scope.title);
		//	$scope.isAdmin = true;
		}


	}


	/**Funcion que retarsa la carga de los scripts del template hasta que Angular este listo **/
	 $timeout( function(){ $scope.initScripts(); }, 100);
 	 $scope.init();


}]);