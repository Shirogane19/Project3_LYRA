'use strict';

angular.module('myApp.materiaView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/materiaView', {
    templateUrl: 'materiaView/materiaView.html',
    controller: 'materiaViewCtrl'
  });
}])


.controller('materiaViewCtrl', ['$scope','$http','$timeout',function($scope,$http,$timeout) {
  
  $scope.materiaList = [];
  

    $scope.initScripts = function(){

      angular.element(document).ready(function () {
            
             BaseTableDatatables.init();
      });

    }

  	$scope.init = function(){
		console.log( "init")
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","materias": {}};
		$http.post('rest/protected/materia/getAll',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.materiaList = response.materias;
			console.log("$scope.materiaList: ",$scope.materiaList[0])
	
		});

	 }
  $timeout( function(){ $scope.initScripts(); }, 100);
	 $scope.init();




  }]);