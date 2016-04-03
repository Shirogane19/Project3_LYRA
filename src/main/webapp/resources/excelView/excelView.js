'use strict';

angular.module('myApp.excelView', [])

.controller('excelViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',function($scope,$http,$timeout,$state,$localStorage) {


	$scope.initScripts = function(){

	/** Metodo que inicia los scripts del template **/
	  angular.element(document).ready(function () {

	  });
	}


	$scope.init = function(){

	}


  $scope.init();
  $timeout( function(){ $scope.initScripts(); }, 100);

}]);