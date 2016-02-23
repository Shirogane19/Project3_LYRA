// 'use strict';

// angular.module('myApp.loginView', ['ngRoute'])

// .config(['$routeProvider', function($routeProvider) {
//   $routeProvider.when('/login', {
//     templateUrl: 'resources/loginView/loginView.html',
//     controller: 'LoginFormController'
//   });
// }])

// .controller('LoginFormController', ['$scope','$http',function($scope,$http) {
// 	$scope.user = {email:"jcorellam@ucenfotec.ac.cr",password:"1234"};
	
// 	$scope.checkLogin = function(){
// 		$scope.authError = null;

//         // Try to login 

//     	$http.post('rest/login/checkuser/',$scope.user).success(function (loginResponse) {

//       .then(function(response) {
//     		if(loginResponse.code == 200){
//     			var usuario = {"idUser":loginResponse.idUsuario,"firstName":loginResponse.firstName,"lastName":loginResponse.lastName};
//     			var path = "/lyra/app#/view1";
//     			window.location.href = path;
//     		}else{
//     			$scope.authError = 'Email or Password not right';
//     		}
//          }, function(x) {
//                  $scope.authError = 'Server Error';
//                 });
//     	});
    	
//     };
// }]);




'use strict';

angular.module('myApp.loginView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'resources/loginView/loginView.html',
    controller: 'LoginViewCtrl'
  });
}])

.controller('LoginViewCtrl', ['$scope','$http',function($scope,$http) {
  $scope.user = {email:"jcorellm@ucenfotec.ac.cr",password:"1234"};
  
  $scope.checkLogin = function(){
    
      //$http.post('http://localhost:8090/lyra/rest/login/checkuser/',$scope.user).success(function (loginResponse) {

$http.post('/rest/login/checkuser/',$scope.user).success(function (loginResponse) {



        if(loginResponse.code == 200){
          var usuario = {"idUser":loginResponse.idUsuario,"firstName":loginResponse.firstName,"lastName":loginResponse.lastName};
          var path = "/lyra/app#/view1";
          window.location.href = path;
        }else{
          alert("invalido");
        }
      });
      
    };
}]);


//      $http.post('http://localhost:8090/rest/login/checkuser/',$scope.user).success(function (loginResponse) {