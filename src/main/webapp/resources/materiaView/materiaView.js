'use strict';

angular.module('myApp.materiaView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/materiaView', {
    templateUrl: 'materiaView/materiaView.html',
    controller: 'materiaViewCtrl'
  });
}])


.controller('materiaViewCtrl', ['$scope','$http','$timeout','$state',function($scope,$http,$timeout,$state) {
  
  $scope.materiaList = [];
  

  $scope.initScripts = function(){

    angular.element(document).ready(function () {
          
           BaseTableDatatables.init();
       //BaseFormValidation.init();
      //OneUI.initHelpers('select2');
    });

  }

  $scope.init = function(){
    $scope.isCreating = true;
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","materias": {}};
		$http.post('rest/protected/institucion/getMateriasDelInstituto',$scope.user.idInstitucion).success(function(response) {
			console.log("response",response.institucion.materias)
			$scope.materiaList = response.institucion.materias;
	
		});

   }


  $scope.newMat = [];
  $scope.oldMat = [];
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

  $scope.newMat = {};
  $scope.onPoint = false;
  $scope.isCreating = true;
  }

  $scope.showMateriaToEdit = function(m){

  $scope.newMat = m; // Guarda el objeto usuario a la variable temporal
  $scope.newMat.nombre = m.nombre;
  
  $scope.showForm();
  $scope.isCreating = false;
}

 $scope.isActive = function(m){

  $scope.newMat = m; // Guarda el objeto usuario a la variable temporal

  if($scope.newMat.activeMat){
    $scope.newMat.activeMat = false;
  }else{
    $scope.newMat.activeMat = true;
  }

  $scope.isCreating = false;
  $scope.saveMateria();

}

$scope.saveMateria = function(){
    if($scope.isCreating){
      $scope.newMat.idMateria = -1;
      $scope.newMat.activeMat = true;
    }
    // $scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
    // "string","materia":{"idMateria": $scope.newMat.idMateria,"nombre": $scope.newMat.nombre,"activeMat": $scope.newMat.activeMat}};

console.log($scope.user.idInstitucion);
$scope.requestObject ={
  "code": 0,
  "codeMessage": "string",
  "errorMessage": "string",
  "totalPages": 0,
  "totalElements": 0,
  "materia": {
      "activeMat": $scope.newMat.activeMat,
      "idMateria": $scope.newMat.idMateria,
      "nombre": $scope.newMat.nombre,
      "institucion": {"idInstitucion":$scope.user.idInstitucion}

  }
}

    console.log($scope.requestObject.materia);

    $http.post('rest/protected/materia/saveMateria',$scope.requestObject).success(function(response) {

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