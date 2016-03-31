'use strict';

angular.module('myApp.tareaView', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/tareaView', {
    templateUrl: 'tareaView/tareaView.html',
    controller: 'tareaViewCtrl'
  });
}])


.controller('tareaViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',function($scope,$http,$timeout,$state,$localStorage) {
  
$scope.tareaList = [];
$scope.categoryList = {};
$scope.listaRoles = [];
$scope.userList = [];
$scope.taskUsers = [];
$scope.usuariosDeTarea = [];
$scope.tab1 = false;
$scope.tab2 = false;
$scope.tab3 = false;

$scope.scope = $scope;

$scope.newTa = [];
$scope.oldTa = [];
$scope.counter = 0;
$scope.isCreating = true;
$scope.onPoint = false;
  

$scope.initScripts = function(){

  angular.element(document).ready(function () {
        
         BaseTableDatatables.init();
     //BaseFormValidation.init();
     App.initHelpers('table-tools');
    OneUI.initHelpers('select2');
  });

}

$scope.init = function(){

  $scope.isCreating = true;
  $scope.getCategories();
  $scope.getUsers();
  $scope.idInstitucion = $localStorage.user.idInstitucion;

	$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","tareas": {}};
	
  $http.post('rest/protected/tarea/getAll',$scope.requestObject).success(function(response) {
		//console.log("response",response)
		$scope.tareaList = response.tareas;
		//console.log("$scope.tareaList: ",$scope.tareaList[0])
    //console.log("id ints",$scope.user.idInstitucion);

	});

}


$scope.getUsers = function(){

  $scope.idInstitucion = $localStorage.user.idInstitucion;

  $scope.requestObject = 
  {"pageNumber": 0,
   "pageSize": 0,
   "direction": "string",
   "sortBy": [""],
   "searchColumn": "string",
   "searchTerm": 
   "string",
   "usuario":{"idInstitucion": $scope.user.idInstitucion}};

  $http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
  //  console.log("response",response)
    $scope.userList = response.usuarios;
    //console.log("Response usuarios", response );

  });

}

$scope.getCategories = function(){

        $scope.requestObject = {"pageNumber": 0,
                "pageSize": 0,
                "direction": "",
                "sortBy": [""],
                "searchColumn": "string",
                "searchTerm": "",
                "categorias": {}};

  $http.post('rest/protected/categorias/getAll',$scope.requestObject).success(function(response) {
  $scope.categoryList = response.categorias;

  console.log("List", $scope.categoryList);

  });

}

$scope.showForm = function(){
  console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
  $scope.onPoint = true;
  $scope.getCategories();
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
  //$scope.userList = t.usuarios;
  $scope.listaRoles = t.rols;
  $scope.usuariosDeTarea = t.usuarios;
  //console.log('usuarios tarea', $scope.usuariosDeTarea);

  $scope.newTa.oldCategoria =  t.categoria.nombreCategoria;
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

$scope.validacionUsuariosTarea = function(){
  $scope.usuariosDeTarea.idUsuario;
  $scope.taskUsers

  for (var i = 0; i < $scope.taskUsers.length; i++) {
    
      for (var x = 0; x < $scope.usuariosDeTarea.length; x++) {
        
        if ($scope.taskUsers[i] = $scope.usuariosDeTarea[x].idUsuario) {};
        

      };

  };
}

$scope.changeTab1 = function(){
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

  $scope.tab1=false;
  $scope.tab2=false;
}

$scope.saveUserT = function(u){
   
  console.log('length',$scope.taskUsers );
  if($scope.taskUsers.length > 0){

    for (var i = 0; i < $scope.taskUsers.length; i++) {
          if(u.idUsuario === $scope.taskUsers[i]){
              
              $scope.taskUsers[i].splice(i,1);

          }
    };

    $scope.taskUsers.push(u.idUsuario);

  }else{

      $scope.taskUsers.push(u.idUsuario);
  }
}

$scope.saveTarea = function(){


  if($scope.taskUsers.length >0 || $scope.listaRoles.length >0){
  
    if($scope.isCreating){
      $scope.newTa.idTarea = -1;
      $scope.newTa.activeTa = true;
  }

  $scope.idCategoria = parseInt($scope.newTa.categoria, 10);  // parseInt with radix
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
        "idRols" :  $scope.listaRoles,
        "idCategoria": $scope.idCategoria
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


    }); 

}else{
    document.getElementById("advertencia").style.color = "red";

  }

  }
  

  $scope.validacionEliminar = function(){
    console.log('elim');
      if($scope.taskUsers.length < 0 ){
          $scope.taskUsers.splice();
          console.log('elim');
      }
  }

 $timeout( function(){ $scope.initScripts(); }, 100);
   $scope.init();

  }]);