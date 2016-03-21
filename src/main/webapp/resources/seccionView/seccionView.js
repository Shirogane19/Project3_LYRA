'use strict';

angular.module('myApp.seccionView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/seccionView', {
    templateUrl: 'seccionView/seccionView.html',
    controller: 'seccionViewCtrl'
  });
}])


.controller('seccionViewCtrl', ['$scope','$http','$timeout','$state',function($scope,$http,$timeout,$state) {
  
  $scope.seccionList = [];
  

    $scope.initScripts = function(){

      angular.element(document).ready(function () {
            
             BaseTableDatatables.init();
         //BaseFormValidation.init();
        //OneUI.initHelpers('select2');
      });

    }

  	$scope.init = function(){
    $scope.isCreating = true;
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","secciones": {}};
		$http.post('rest/protected/seccion/getAll',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.seccionList = response.secciones;
			console.log("$scope.seccionList: ",$scope.seccionList[0])
	
		});

   }


 $scope.newSec = [];
  $scope.oldSec = [];
  $scope.counter = 0;
  $scope.isCreating = true;
  $scope.onPoint = false;

$scope.increment = function(){
    $scope.counter += 1;
  }


$scope.showForm = function(){
  console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
  $scope.onPoint = true;
  }

$scope.showList = function(){

  $scope.newSec = {};
  $scope.onPoint = false;
  $scope.isCreating = true;
  }

  $scope.showSeccionToEdit = function(s){

  $scope.newSec = s; // Guarda el objeto usuario a la variable temporal
  $scope.newSec.nombreSeccion = s.nombreSeccion;
  
  $scope.showForm();
  $scope.isCreating = false;
}

 $scope.isActive = function(s){

  $scope.newSec = s; // Guarda el objeto usuario a la variable temporal

  if($scope.newSec.activeSec){
    $scope.newSec.activeSec = false;
  }else{
    $scope.newSec.activeSec = true;
  }

  $scope.isCreating = false;
  $scope.saveSeccion();

}

$scope.saveSeccion = function(){
    if($scope.isCreating){
      $scope.newSec.idSeccion = -1;
      $scope.newSec.activeSec = true;
    }
    // $scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
    // "string","materia":{"idMateria": $scope.newMat.idMateria,"nombre": $scope.newMat.nombre,"activeMat": $scope.newMat.activeMat}};

$scope.requestObject ={
  "code": 0,
  "codeMessage": "string",
  "errorMessage": "string",
  "totalPages": 0,
  "totalElements": 0,
  "seccion": {
    "idSeccion": $scope.newSec.idSeccion,
      "nombreSeccion": $scope.newSec.nombreSeccion,
      "activeSec": $scope.newSec.activeSec
     

}
}

    console.log($scope.requestObject.seccion);

    $http.post('rest/protected/seccion/saveSeccion',$scope.requestObject).success(function(response) {

      if($scope.isCreating){//Si esta creando setea un -1 al tipo de usuario
        $state.reload();
      }else{
        $scope.showList();
        $scope.init();
      }
      $state.reload();

    }); 
  }


 $timeout( function(){ $scope.initScripts(); }, 100);
   $scope.init();

  }]);