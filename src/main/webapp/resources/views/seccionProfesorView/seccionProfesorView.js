'use strict';

angular.module('myApp.seccionProfesorView', ['ngRoute'])

.controller('seccionProfesorViewCtrl', ['$scope','$http','$timeout','$state','$localStorage',function($scope,$http,$timeout,$state,$localStorage) {

  $scope.onPoint = true;

	$scope.initScripts = function(){

    angular.element(document).ready(function () {    
      BaseTableDatatables.init();
      App.init();
    });

  }

  $scope.init = function(){// Carga las secciones del instituto

    $scope.objectRequest = {
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

    $http.post('rest/protected/users/getUser',$scope.objectRequest).success(function(response) {
      //console.log(response.usuario.seccions);
      $scope.seccionList = response.usuario.seccions;
    })
    .catch(function (error) {

    }); 

  }

  /** Refresca la pagina, cada vez que cierre una ventana**/
  $scope.close = function(){
    $state.reload();
  }

  //---------------

  $scope.onPointWatchSeccion = false;

  $scope.showSeccion = function(s){
    //console.log(s);
    $scope.seccionName = s.nombreSeccion;

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

    })
    .catch(function (error) {
      //console.error('exception', error.status);
      $localStorage.error = error.status;
      $state.go('errorView');
    }); 

    $scope.onPoint = false;
    $scope.onPointWatchSeccion = true;

  }//

  $scope.showHistorial = function(a){
    //console.log(a);
    $state.go('registroView',{alumnoInfo: a});
  }


	$timeout( function(){ $scope.initScripts(); }, 100);
	$scope.init();

}]);