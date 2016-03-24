'use strict';

angular.module('myApp.registroView', [])


.controller('registroViewCtrl', ['$scope','$http','$timeout','$state',function($scope,$http,$timeout,$state) {


  $scope.initScripts = function(){

    angular.element(document).ready(function () {
    	
    });

  }	


  $scope.init = function(){

  }

  $scope.toString = function (){
		console.log($state.params.alumnoInfo);
	}


	$timeout( function(){ $scope.initScripts(); }, 100);
	$scope.init();

}]);