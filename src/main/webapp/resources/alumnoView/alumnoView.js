'use strict';

angular.module('myApp.alumnoView', [])


.controller('alumnoViewCtrl', ['$scope','$http','$timeout','$state',function($scope,$http,$timeout,$state) {
  
  $scope.alumnos = [];
  $scope.newAlumno = [];
  $scope.oldAlumno = [];
  $scope.counter = 0;
  $scope.onPoint = false;
  $scope.isCreating = true;
  $scope.onlyNumbers = /^\d+$/;

  $scope.newAlumno.genero = "M";

  $scope.initScripts = function(){

    angular.element(document).ready(function () {
        //   OneUI.init();
           BaseTableDatatables.init();
           BaseFormValidation.init();
           OneUI.initHelpers('select2');
    });

  }

  $scope.increment = function(){
    $scope.counter += 1;
  }

  $scope.showForm = function(){
    console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
    $scope.onPoint = true;
    console.log('Creando? ', $scope.isCreating, 'Formulario? ', $scope.onPoint);
  }

  $scope.showList = function(){

    $scope.newAlumno = {};
    $scope.onPoint = false;
    $scope.isCreating = true;
  }

  $scope.showAlumnoToEdit = function(a){

    $scope.newAlumno = a;
    $scope.newAlumno.nombre = a.nombre;
    $scope.newAlumno.apellido1 = a.apellido1;
    $scope.newAlumno.apellido2 = a.apellido2;
    $scope.newAlumno.cedula = a.cedula; 
    $scope.newAlumno.genero = a.genero; 
    $scope.newAlumno.activeAl = a.activeAl;
    console.log($scope.newAlumno);
    $scope.showForm();
    $scope.isCreating = false;
  }

  $scope.isActive = function(a){

    $scope.newAlumno = a; 

    if($scope.newAlumno.activeAl){
      $scope.newAlumno.activeAl = false;
    }else{
      $scope.newAlumno.activeAl = true;
    }

    console.log($scope.newAlumno.activeAl);
    $scope.isCreating = false;
    $scope.saveAlumno();

  }

  $scope.init = function(){
		
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","alumno": {}};
		$http.post('rest/protected/alumno/getAll',$scope.requestObject).success(function(response) {
			console.log("response",response)
			$scope.alumnos = response.alumnos;
		});

	}

  $scope.saveAlumno = function(){

    if($scope.isCreating){
      $scope.newAlumno.idAlumno = -1;
      $scope.newAlumno.activeAl = true;
    }

    $scope.requestObject = {
        "pageNumber": 0,
        "pageSize": 0,
        "direction": "string",
        "sortBy": [
          "string"
        ],
        "searchColumn": "string",
        "searchTerm": "string",
        "alumno": {

            "idAlumno": $scope.newAlumno.idAlumno,
            "apellido1": $scope.newAlumno.apellido1,
            "apellido2": $scope.newAlumno.apellido2,
            "cedula": $scope.newAlumno.cedula,
            "genero": $scope.newAlumno.genero,
            "nombre": $scope.newAlumno.nombre,
            "institucion": {},
            "seccion": {},
            "encargadosAlumnos": [],
            "historialMedicos": [],
            "activeAl": $scope.newAlumno.activeAl

         }
      }


    $http.post('rest/protected/alumno/save',$scope.requestObject).success(function(response) {

      if($scope.isCreating){
        $state.reload();
      }else{
        console.log($scope.requestObject.alumno);
        $scope.showList();
        $scope.init();
      }


    }); 
  }

  $timeout( function(){ $scope.initScripts(); }, 100);
	$scope.init();

}]);