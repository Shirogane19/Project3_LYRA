'use strict';

angular.module('myApp.tareaView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/tareaView', {
    templateUrl: 'tareaView/tareaView.html',
    controller: 'tareaViewCtrl'
  });
}])


.controller('tareaViewCtrl', ['$scope','$http','$timeout','$state',function($scope,$http,$timeout,$state) {
  
  $scope.tareaList = [];
  //ESTO ESTA QUEMADOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO!!!
  $scope.listaRoles = [];
  $scope.userList = [];
  $scope.taskUsers = [];
  $scope.tab1 = false;
  $scope.tab2 = false;
  $scope.tab3 = false;

  

    $scope.initScripts = function(){

      angular.element(document).ready(function () {
            
             BaseTableDatatables.init();
         //BaseFormValidation.init();
         App.initHelpers('table-tools');
        //OneUI.initHelpers('select2');
      });

    }

  	$scope.init = function(){
    $scope.isCreating = true;
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","tareas": {}};
		$http.post('rest/protected/tarea/getAll',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.tareaList = response.tareas;
			console.log("$scope.tareaList: ",$scope.tareaList[0])

      $scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": {}};
  $http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
  //  console.log("response",response)
    $scope.userList = response.usuarios;
    console.log("Response usuarios", response.usuarios );
  });

	
		});

   }


 $scope.newTa = [];
  $scope.oldTa = [];
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

  $scope.newTa = {};
  $scope.onPoint = false;
  $scope.isCreating = true;
  }

  $scope.showTareaToEdit = function(t){

  $scope.newTa = t; // Guarda el objeto usuario a la variable temporal
  $scope.newTa.tituloTarea = t.tituloTarea;
  $scope.newTa.descripcionTarea = t.descripcionTarea;
  $scope.showForm();
  $scope.isCreating = false;
}

 $scope.isActive = function(t){

  $scope.newTa = t; // Guarda el objeto usuario a la variable temporal

  if($scope.newTa.activeTa){
    $scope.newTa.activeTa = false;
  }else{
    $scope.newTa.activeTa = true;
  }

  $scope.isCreating = false;
  $scope.saveTarea();

}

$scope.changeTab1 = function(){
  console.log("tab 1", $scope.tab1);
  if($scope.tab1){
    $scope.tab1 = false;
  }else{
    $scope.tab1 = true;
  }

    $scope.tab2=false;
    $scope.tab3=false;
}

$scope.changeTab2 = function(){
if($scope.tab2){
$scope.tab2 = false;
}else{
  $scope.tab2 = true;
}

$scope.tab1=false;
$scope.tab3=false;

}
$scope.changeTab3 = function(){
if($scope.tab3){
$scope.tab3 = false;
}else{
  $scope.tab3= true;
}
console.log("userlist!!!!!!" , $scope.taskUsers);
$scope.tab1=false;
$scope.tab2=false;


}

$scope.saveUserT = function(u){
    $scope.taskUsers.push(u.idUsuario);
    console.log("usuario lista para tarea", $scope.taskUsers);
}

$scope.saveTarea = function(){
    if($scope.isCreating){
      $scope.newTa.idTarea = -1;
      $scope.newTa.activeTa = true;
    }
    // $scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
    // "string","materia":{"idMateria": $scope.newMat.idMateria,"nombre": $scope.newMat.nombre,"activeMat": $scope.newMat.activeMat}};

$scope.requestObject ={
  "code": 0,
  "codeMessage": "string",
  "errorMessage": "string",
  "totalPages": 0,
  "totalElements": 0,
  "tarea": {
    "idTarea": $scope.newTa.idTarea,
      "tituloTarea": $scope.newTa.tituloTarea,
      "descripcionTarea": $scope.newTa.descripcionTarea,
      "activeTa": $scope.newTa.activeTa,
      "readTa": "false",
      "idUsuarios" : $scope.taskUsers,
      "idRols" :  $scope.listaRoles    
}

}

    console.log($scope.requestObject.tarea);

    $http.post('rest/protected/tarea/saveTarea',$scope.requestObject).success(function(response) {

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