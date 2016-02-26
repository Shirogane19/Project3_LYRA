'use strict';

angular.module('myApp.userView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/userView', {
 //   templateUrl: 'userView/userView.html',
    controller: 'userViewCtrl'
  });
}])


.controller('userViewCtrl', ['$scope','$http',function($scope,$http) {
  
  angular.element(document).ready(function () {
         OneUI.init();

  });

  }]);