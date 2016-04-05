'use strict';

angular.module('myApp.categoriaView', ['ngRoute'])

.controller('categoriaViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',function($scope,$http,$timeout,$state,$localStorage) {
  
  $scope.categoriaList = [];
  $scope.idInstitucion = 0;

    $scope.initScripts = function(){

      angular.element(document).ready(function () {
            
             BaseTableDatatables.init();
         //BaseFormValidation.init();
        //OneUI.initHelpers('select2');
      });

    }

  	$scope.init = function(){

      $scope.idInstitucion = $localStorage.user.idInstitucion;
      $scope.isCreating = true;

  		$scope.requestObject = {"pageNumber": 0,
                              "pageSize": 0,
                              "direction": "",
                              "sortBy": [""],
                              "searchColumn": "string",
                              "searchTerm": "",
                              "categoria": {"idInstitucion": $scope.idInstitucion}};

  		$http.post('rest/protected/categorias/getAll',$scope.requestObject).success(function(response) {

  			$scope.categoriaList = response.categorias;
  			//console.log("$scope.categoriaList: ",$scope.categoriaList[0])
	
		})
      .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

   }


 $scope.newCat = [];
  $scope.oldCat = [];
  $scope.counter = 0;
  $scope.isCreating = true;
  $scope.onPoint = false;

$scope.increment = function(){
    $scope.counter += 1;
  }


$scope.showForm = function(){
  //console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
  $scope.onPoint = true;
  }

$scope.showList = function(){

  $scope.newCat = {};
  $scope.onPoint = false;
  $scope.isCreating = true;
  }

  $scope.showCategoriaToEdit = function(c){

  $scope.newCat = c; // Guarda el objeto categoria a la variable temporal
  $scope.newCat.nombreCategoria = c.nombreCategoria;
  $scope.newCat.descripcionCategoria = c.descripcionCategoria;
  $scope.showForm();
  $scope.isCreating = false;
}

 $scope.isActive = function(c){

  $scope.newCat = c; // Guarda el objeto categoria a la variable temporal

  if($scope.newCat.activeCat){
    $scope.newCat.activeCat = false;
  }else{
    $scope.newCat.activeCat = true;
  }

  $scope.isCreating = false;
  $scope.saveCategoria();

}

$scope.saveCategoria = function(){
    if($scope.isCreating){
      $scope.newCat.idCategoria = -1;
      $scope.newCat.activeCat = true;
    }
    // $scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
    // "string","categoria":{"idCategoria": $scope.newCat.idCategoria,"nombre": $scope.newCat.nombre,"activeCat": $scope.newCat.activeCat}};

$scope.requestObject ={

   "code": 0,
  "codeMessage": "string",
  "errorMessage": "string",
  "totalPages": 0,
  "totalElements": 0,
  "categoria": {
      "idCategoria": $scope.newCat.idCategoria,
      "descripcionCategoria":$scope.newCat.descripcionCategoria,
      "nombreCategoria": $scope.newCat.nombreCategoria,
      "activeCat": $scope.newCat.activeCat,
      "idInstitucion": $scope.idInstitucion}
}



    //console.log($scope.requestObject.categoria);

    $http.post('rest/protected/categorias/saveCategoria',$scope.requestObject).success(function(response) {

      if($scope.isCreating){//Si esta creando setea un -1 al tipo de categoria
        $state.reload();
      }else{
        $scope.showList();
        $scope.init();
      }
      $state.reload();
      
    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    });  
  }


 $timeout( function(){ $scope.initScripts(); }, 100);
   $scope.init();

  }]);