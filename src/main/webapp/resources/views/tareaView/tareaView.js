'use strict';

angular.module('myApp.tareaView', ['ngRoute'])

.controller('tareaViewCtrl', ['$scope','$http','$timeout','$state',
  '$localStorage',function($scope,$http,$timeout,$state,$localStorage) {

  $scope.tareaList = []; //Lista de las tareas por usuario
  $scope.categoryList = {}; //Lista de las categorias de la inst
  $scope.listaRoles = []; //lista de de los roles
  $scope.idsRoles = []; //lista de ids de los roles
  $scope.userList = [];
  $scope.taskUsers = []; //se guardan los ids de los usuarios asignados a la tarea 
  $scope.usuariosDeTarea = []; //Usuarios de la tarea al editar
  $scope.usTotal = [];
  $scope.tab1 = false;
  $scope.tab2 = false;
  $scope.tab3 = false;
  $scope.t = [];
  $scope.onPointMat = true;
  $scope.UsuariosAsignados = [];
  $scope.UsuariosNoAsignados = [];
  $scope.AlumnosAsignados = [];
  $scope.objSeccion;
  $scope.edit = false;

  $scope.scope = $scope;

  $scope.newTa = [];
  $scope.oldTa = [];
  $scope.counter = 0;
  $scope.isCreating = true;
  $scope.onPoint = false;
  $scope.NotOwner = true;
  $scope.showWarning = false;
  $scope.userMaster = [];
  $scope.userAdmin = [];
  $scope.userProf = [];
  $scope.userEnc = [];
  

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
    //$scope.getUsers();
    $scope.idInstitucion = $localStorage.user.idInstitucion;

    $scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
    "string","tarea":{"idUsuario": $localStorage.user.userId}};

    $http.post('rest/protected/tarea/getByUser',$scope.requestObject).success(function(response) {

      $scope.tareaList = response.tareas;
      console.log("tareas ", $scope.tareaList);

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

  }

   $scope.getCategories = function(){

    $scope.requestObject = {"pageNumber": 0,
            "pageSize": 0,
            "direction": "",
            "sortBy": [""],
            "searchColumn": "string",
            "searchTerm": "",
            "categoria": {"idInstitucion": $scope.user.idInstitucion}};
    

    $http.post('rest/protected/categorias/getAll',$scope.requestObject).success(function(response) {
      $scope.categoryList = response.categorias;
      //console.log("List", $scope.categoryList);
    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

  }
  $scope.showForm = function(){

    //console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
    $scope.onPoint = true;
    $scope.getCategories();
    $scope.getAllUsers();
  }

  $scope.getAllUsers = function(){

     $scope.idInstitucion = $localStorage.user.idInstitucion;

      $scope.requestObject = { "pageNumber": 0,
                               "pageSize": 0,
                               "direction": "string",
                               "sortBy": [""],
                               "searchColumn": "string",
                               "searchTerm": 
                               "string",
                               "usuario":{"idInstitucion": $scope.user.idInstitucion}
                              };

      $http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
      
        $scope.UsuariosNoAsignados = response.usuarios;
        console.log("usuarios no asignados reg", $scope.UsuariosNoAsignados);
        
      }).catch(function (error) {
        //console.error('exception', error.status);
        $localStorage.error = error.status;
        $state.go('errorView');
      }); 

  }

  $scope.showList = function(){
    $state.reload();
    // $scope.newTa = {};
    // $scope.onPoint = false;
    // $scope.isCreating = true;
  }

  $scope.isActive = function(t){

    console.log("active" , t.rols);

    if(t.idOwner == $localStorage.user.userId){

    $scope.newTa = t; // Guarda el objeto usuario a la variable temporal
    //$scope.listaRoles = t.rols;
    //$scope.getIdsRoles();
    for (var i = 0; i < t.rols.length; i++) {
                
          $scope.listaRoles[i] = t.rols[i].idRol;
        
    };
    $scope.getIdsRoles();
    $scope.usuariosDeTarea = t.usuarios;


    if($scope.newTa.activeTa){
      $scope.newTa.activeTa = false;
    }else{
      $scope.newTa.activeTa = true;
    }

    //console.log($scope.newTa.activeTa);

    $scope.isCreating = false;
    $scope.saveTarea();
    
    }else{
      $scope.showWarning = true;
      document.getElementById("warning").style.color = "red";
    }

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

    $scope.close = function(){
      $state.reload();
    }

  //----------------SAVE TAREA -----------

   /*$scope.saveUserT = function(u){
     
    if($scope.taskUsers.length > 0){

      for (var i = 0; i < $scope.taskUsers.length; i++) {
            if(u.idUsuario === $scope.taskUsers[i]){
                $scope.taskUsers.splice(i,1);
            }
      };

      $scope.taskUsers.push(u.idUsuario);

    }else{
      $scope.taskUsers.push(u.idUsuario);
    }

  }
*/
  $scope.getIdsUsuarios = function(){
    var found = false;

    for (var i = 0; i < $scope.usuariosDeTarea.length; i++) {
      
          
          $scope.taskUsers[i] = $scope.usuariosDeTarea[i].idUsuario;
          if($scope.usuariosDeTarea[i].idUsuario == $localStorage.user.userId){
              found=true;
          }
        
    };
    if(found == false){
        $scope.taskUsers.push($localStorage.user.userId);

      }
  }

  $scope.getIdsRoles = function(){
      console.log("lista roles edit" , $scope.listaRoles);
  for (var i = 0; i < $scope.listaRoles.length; i++) {
                
          $scope.listaRoles[i] = $scope.listaRoles[i];
        
    };

    console.log("lista roles edit 2" , $scope.listaRoles);

  }

  $scope.saveTarea = function(){

    console.log("new ta", $scope.newTa);

    if($scope.usuariosDeTarea.length >0 || $scope.listaRoles.length >0){

      //console.log("entre a Save Tarea");
    
      if($scope.isCreating){
        $scope.newTa.idTarea = -1;
        $scope.newTa.activeTa = true;
      }//End if creating

      $scope.getIdsUsuarios();
      $scope.getIdsRoles();

      $scope.idCategoria = parseInt($scope.newTa.categoria, 10);  // parseInt with radix
    // $scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "string","sortBy": [""],"searchColumn": "string","searchTerm": 
    // "string","materia":{"idMateria": $scope.newMat.idMateria,"nombre": $scope.newMat.nombre,"activeMat": $scope.newMat.activeMat}};

    console.log("lista roles" , $scope.listaRoles);
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
          "idCategoria": $scope.idCategoria,
          "idOwner" : $localStorage.user.userId
      }

    } //Fin de RO

    // console.log("lista roles ids", $scope.listaRoles);
    // console.log($scope.requestObject.tarea);

    $http.post('rest/protected/tarea/saveTarea',$scope.requestObject).success(function(response) {

      // if($scope.isCreating){//Si esta creando setea un -1 al tipo de usuario
      //   $state.reload();
      // }else{
      //   $scope.showList();
      //   $scope.init();
      // }

      $state.reload();

    }) // End HTTP
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    }else{
      //console.log("else");
      document.getElementById("advertencia").style.color = "red";
    }

  }
  //------------------END----------------------
  //-----------------EDIT--------------------
    $scope.showTareaToEdit = function(t){
        console.log("T", t);

      if(t.idOwner == $localStorage.user.userId){

          $scope.onPoint = true;
          $scope.getCategories();
          //$scope.getAllUsers();
          $scope.edit = true;
          $scope.newTa = t;
          $scope.newTa.tituloTarea = t.tituloTarea;
          $scope.newTa.descripcionTarea = t.descripcionTarea;
          $scope.listaRoles = t.rols;
          //$scope.getIdsRoles();
          $scope.usuariosDeTarea = t.usuarios;
          $scope.newTa.oldCategoria =  t.categoria.nombreCategoria;


          $scope.requestObject = { "pageNumber": 0,
                                   "pageSize": 0,
                                   "direction": "string",
                                   "sortBy": [""],
                                   "searchColumn": "string",
                                   "searchTerm": 
                                   "string",
                                   "usuario":{"idInstitucion": $scope.user.idInstitucion}
                                  };

          $http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
                   
                      var found = false;

                      for (var i = 0; i < response.usuarios.length; i++) {
    
                

                          for (var x = 0; x < $scope.usuariosDeTarea.length; x++) {

                              if (response.usuarios[i].idUsuario ==  $scope.usuariosDeTarea[x].idUsuario) {
                                console.log("igual", response.usuarios[i].idUsuario);
                                 found = true; 

                              };
    
                          }
    
                          if(found == false){

                          $scope.UsuariosNoAsignados.push(response.usuarios[i]);

                        }

                        found = false;
                      };//

                    }).catch(function (error) {
                      //console.error('exception', error.status);
                      $localStorage.error = error.status;
                      $state.go('errorView');
                    }); 



      }else{

          $scope.NotOwner = false;
          $scope.onPoint = true;
          $scope.getCategories();
          $scope.getAllUsers();
          $scope.edit = true;
          $scope.newTa = t;
          $scope.newTa.tituloTarea = t.tituloTarea;
          $scope.newTa.descripcionTarea = t.descripcionTarea;
          $scope.listaRoles = t.rols;
          //$scope.getIdsRoles();
          $scope.usuariosDeTarea = t.usuarios;
          $scope.newTa.oldCategoria =  t.categoria.nombreCategoria;

          $scope.requestObject = { "pageNumber": 0,
                                   "pageSize": 0,
                                   "direction": "string",
                                   "sortBy": [""],
                                   "searchColumn": "string",
                                   "searchTerm": 
                                   "string",
                                   "usuario":{"idInstitucion": $scope.user.idInstitucion}
                                  };

          $http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
          
            var found = false;

            for (var i = 0; i < response.usuarios.length; i++) {//
              for (var p = 0; p < $scope.usuariosDeTarea.length; p++) {////
                if(response.usuarios[i].idUsuario != $scope.usuariosDeTarea[p].idUsuario){
                }else{
                  found = true; 
                }
              };////
              if(found == false){
                $scope.UsuariosNoAsignados.push(response.usuarios[i]);
              }
              found = false;
            };//

          }).catch(function (error) {
            //console.error('exception', error.status);
            $localStorage.error = error.status;
            $state.go('errorView');
          }); 

      }


    //$scope.showForm();
    $scope.isCreating = false;
  }
  //------------------END--------------------
  //-----------Tablas-------------

  $scope.onPointProfeToSeccion = false;

  $scope.openProfesToSeccion = function(s){

    $http.post('rest/protected/seccion/getProfesDeSeccion',s.idSeccion).success(function(response) {

      //console.log(response.seccion);
      $scope.objSeccion = response.seccion;
      $scope.UsuariosAsignados = response.seccion.profesorSeccions;// Profes asignados a la seccion 

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $http.post('rest/protected/institucion/getProfesoresDelInstituto', $scope.user.idInstitucion).success(function(response) {

      //console.log(response.institucion.usuarios);
      //$scope.ProfesNoAsignados = response.institucion.usuarios;//¨Profes no asignados a la seccion
      var found = false;

      for (var i = 0; i < response.institucion.usuarios.length; i++) {//
        for (var p = 0; p < $scope.UsuariosAsignados.length; p++) {////
          if(response.institucion.usuarios[i].idUsuario != $scope.UsuariosAsignados[p].idUsuario){
          }else{
            found = true; 
          }
        };////
        if(found == false){
          $scope.UsuariosNoAsignados.push(response.institucion.usuarios[i]);
        }
        found = false;
      };//

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $scope.seccionName = s.nombreSeccion;
    $scope.onPointProfeToSeccion = true;
    $scope.onPoint = false;
    //console.log(s);
  }

  //--------------

  $scope.selectProfeNoAsignado;// Guarda el indice del profe sin seccion del select/option
  $scope.objProfeNoAsignado;// Guarda un objeto no asignado

  $scope.targetProfeNoAsignado = function(p){
    $scope.objProfeNoAsignado = p;
    $scope.selectProfeNoAsignado = $scope.UsuariosNoAsignados.indexOf(p);
  }

  /** limina a un alumno de laa lista de sin seccion del select/option**/
  $scope.borrarProfeNoAsignado = function(){
    if(!angular.isUndefined($scope.objProfeNoAsignado)){
      $scope.usuariosDeTarea.push($scope.objProfeNoAsignado);
      $scope.UsuariosNoAsignados.splice($scope.selectProfeNoAsignado, 1);
      $scope.selectProfeNoAsignado = undefined;
      $scope.objProfeNoAsignado = undefined;
    }
  }

  //--------------

  $scope.selectProfeAsignado;// Guarda el indice del profe en una seccion del select/option
  $scope.objProfeAsignado;// Guarda un objeto asinado a seccion

  /** Obtiene el indice del profe que esta asignado en la seccion del select/option**/
  $scope.targetProfeAsignado = function(p){

    $scope.objProfeAsignado = p;
    $scope.selectProfeAsignado = $scope.usuariosDeTarea.indexOf(p);
  }

  /** Elimina a un profe de la lista que esta asignado en la seccion del select/option**/
  $scope.borrarProfeAsignado = function(){
    if(!angular.isUndefined($scope.objProfeAsignado)){
      $scope.UsuariosNoAsignados.push($scope.objProfeAsignado);
      $scope.usuariosDeTarea.splice($scope.selectProfeAsignado, 1);
      $scope.objProfeAsignado = undefined;
      $scope.selectProfeAsignado = undefined;
    }
  }

  //--------------


  //--------------end tablas
  $timeout( function(){ $scope.initScripts(); }, 100);
  $scope.init();

}]);
