'use strict';

angular.module('myApp.successView', [])

.controller('successViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',
                     function($scope,$http,$timeout,$state,$localStorage) {

    $scope.isReady = false;
    

    $scope.excelReady = function(){
      $scope.isReady = true;  
    }      

    $timeout( function(){ $scope.excelReady(); }, 5000);             

}]);