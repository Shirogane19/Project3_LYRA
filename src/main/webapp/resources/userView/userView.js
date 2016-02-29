'use strict';

angular.module('myApp.userView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/userView', {
    templateUrl: 'userView/userView.html',
    controller: 'userViewCtrl'
  });
}])


.controller('userViewCtrl', ['$scope','$http',function($scope,$http) {
  
  $scope.userList = [];


  angular.element(document).ready(function () {

  	console.log('quesorpresa');
         OneUI.init();
         BaseTableDatatables.init();
  });


  	$scope.init = function(){
		
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": {}};
		$http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.userList = response.usuarios;
			console.log("$scope.userList: ",$scope.userList[0])
		//	console.log("Tipo de usuario:", $scope.usuarios.tipos)
		});

	 }

	 $scope.init();




  }]);