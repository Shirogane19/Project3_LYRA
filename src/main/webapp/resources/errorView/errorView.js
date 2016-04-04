'use strict';

angular.module('myApp.errorView', [])

.controller('errorViewCtrl', ['$scope','$http','$timeout','$state','$localStorage', function($scope,$http,$timeout,$state,$localStorage) {

	//console.log($localStorage.error);
	$scope.errorNumber = $localStorage.error;
	$scope.errorText;

	if($scope.errorNumber == 500){
		$scope.errorText = "Lo sentimos pero nuestro servidor se encontró con un error interno..";
	}
	



}]);