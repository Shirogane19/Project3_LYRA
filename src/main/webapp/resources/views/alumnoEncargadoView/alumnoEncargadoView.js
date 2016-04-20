'use strict';

angular.module('myApp.alumnoEncargadoView', [])

.controller('alumnoEncargadoViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',function($scope,$http,$timeout,$state,$localStorage) {

  $scope.onPoint = true;

  $scope.noData = false;

  $scope.alumnos = []; // Coleccion de alumnos

	$scope.initScripts = function(){

    angular.element(document).ready(function () {    
      BaseTableDatatables.init();
      OneUI.init();
      //OneUI.initHelper('table-tools');
      //OneUI.initHelper('summernote');
    });

  }

  $scope.init = function(){// Carga las secciones del instituto

    $scope.requestObject = {
                            "pageNumber": 0,
                            "pageSize": 0,
                            "direction": "string",
                            "sortBy": [
                              "string"
                            ],
                            "searchColumn": "string",
                            "searchTerm": "string",
                            "usuario": {"idUsuario": $localStorage.user.userId}
                            }
    //console.log($localStorage.user.userId);              
    $http.post('rest/protected/users/getAlumnosDelEncargado',$scope.requestObject).success(function(response) {
      //console.log("response",response.usuario.alumnos)
      $scope.alumnos = response.usuario.alumnos;
    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

  }

  $scope.showHistorial = function(a){  
    $state.go('registroView',{alumnoInfo: a});
  }

  /** Refresca la pagina, cada vez que cierre una ventana**/
  $scope.close = function(){
    $state.reload();
  }

  //---------------

  $scope.onPointWatchSeccion = false;

  $scope.showSeccion = function(s){
    //console.log(s.idSeccion);
    $scope.seccionName = s.nombreSeccion;

    if(s.idSeccion > 0){

      $http.post('rest/protected/seccion/getSeccion',s.idSeccion).success(function(response) {

        //console.log(response.seccion.alumnos);
        $scope.AlumnosAsignados = response.seccion.alumnos;
        $scope.objSeccion = response.seccion;

      })
      .catch(function (error) {
        //console.error('exception', error.status);
        $localStorage.error = error.status;
        $state.go('errorView');
      });

      $http.post('rest/protected/seccion/getProfesDeSeccion',s.idSeccion).success(function(response) {

        //console.log(response.seccion);
        $scope.objSeccion = response.seccion;
        $scope.ProfesAsignados = response.seccion.profesorSeccions;// Profes asignados a la seccion 
        console.log($scope.ProfesAsignados);
      })
      .catch(function (error) {
        //console.error('exception', error.status);
        $localStorage.error = error.status;
        $state.go('errorView');
      }); 

      $scope.onPoint = false;
      $scope.onPointWatchSeccion = true;
      $timeout( function(){ OneUI.initHelper('table-tools'); }, 200);

    }else{

      $scope.noData = true;
      
    }
    
  }

  $scope.showHistorial = function(a){
    //console.log(a);
    $state.go('registroView',{alumnoInfo: a});
  }


  $timeout( function(){ $scope.initScripts(); }, 1000);
  $scope.init();


}]);