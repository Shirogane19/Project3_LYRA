'use strict';

angular.module('myApp.userView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/userView', {
    templateUrl: 'userView/userView.html',
    controller: 'userViewCtrl'
  });
}])


.controller('userViewCtrl', ['$scope','$http','$timeout',function($scope,$http,$timeout) {
  
  $scope.userList = [];
  $scope.counter = 0;


$scope.initScripts = function(){

  angular.element(document).ready(function () {

      //   OneUI.init();
         BaseTableDatatables.init();
  });

}

  $scope.increment = function(){
    $scope.counter += 1;
  }


  	$scope.init = function(){
		
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": {}};
		$http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.userList = response.usuarios;
			console.log("$scope.userList: ",$scope.userList[0])
		//	console.log("Tipo de usuario:", $scope.usuarios.tipos)
		});

	 }

	
	 $timeout( function(){ $scope.initScripts(); }, 100);
 	 $scope.init();




  }]);