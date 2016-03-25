'use strict';

angular.module('myApp.registroView', [])


.controller('registroViewCtrl', ['$scope','$http','$timeout','$state',function($scope,$http,$timeout,$state) {

	$scope.alumnoInfo = {};
	$scope.encargados = {};
	$scope.registros = [];
	$scope.registro = [];
	$scope.sexo = "";
	$scope.errorTxt = "";
	$scope.error = false;
	$scope.isMale = true;
	$scope.isCreating = true;
 	$scope.onPoint = false;

	$scope.isEmergencyContact = false;


	$scope.changeTab = function(){
		if($scope.isEmergencyContact){
			$scope.isEmergencyContact= false;
		}else{
			$scope.isEmergencyContact = true;
		}

		$timeout( function(){ $scope.initScripts(); }, 100);
	}


  	$scope.initScripts = function(){

	    angular.element(document).ready(function () {
	    	OneUI.initHelper('table-tools');
			OneUI.initHelper('summernote');
	    });

	  }	


 	 $scope.init = function(){

 	 	if($state.params.alumnoInfo === null){
 	 		$state.go('alumnoView'); 	 		
 	 	}else{ 
	 	 	$scope.alumnoInfo = $state.params.alumnoInfo;
			$scope.encargados = $state.params.alumnoInfo.usuarios;
		}

 	 	if($scope.alumnoInfo.genero === 'M'){
 	 		$scope.sexo = "Masculino";	 		
 	 	}else{ 
	 	 	$scope.sexo = "Femenino";	 
		}

		 $scope.requestObject = {"pageNumber": 0,
		 					     "pageSize": 0,
		 					     "direction": "",
		 					     "sortBy": [""],
		 					     "searchColumn": "string",
		 					     "searchTerm": "",
		 					     "registro":{"idAlumno": $scope.alumnoInfo.idAlumno}};


	    $http.post('rest/protected/historialMedico/getHistorialMedico',$scope.requestObject).success(function(response) {
	 //    console.log("response",response)
	      $scope.registros = response.registros;
	      console.log("registro 1", $scope.registros[0]);

	    });

	    	$scope.isCreating = true;
 			$scope.onPoint = false;
			$scope.isEmergencyContact = false;
			$scope.registro = [];
			$timeout( function(){ $scope.initScripts(); }, 100);
	  }

	$scope.saveRegistro = function(){

		if($scope.isCreating){
			$scope.registro.idRegistro = -1;
			$scope.registro.idAlumno = $scope.alumnoInfo.idAlumno;
		}

		$scope.requestObject = {"pageNumber": 0,
								 "pageSize": 0,
								 "direction": "string",
								 "sortBy": [""],
								 "searchColumn": "string",
								 "searchTerm": 
								 "string","registro":{

								 "idRegistro": $scope.registro.idRegistro ,
								 "nombreRegistro": $scope.registro.nombre, 
								 'descripcion': $scope.registro.descripcion,
								 "idAlumno": $scope.registro.idAlumno}};

		
	$http.post('rest/protected/historialMedico/saveRegistroMedico',$scope.requestObject).success(function(response) {
	
				$scope.init();

		})

		 .catch(function (error) {
          console.error('exception', error);
		  $scope.checkResponse(error);	
        });  
	}

	/** Valida si hay conexcion **/
	$scope.checkResponse = function(response){

		var status = response.status; // error code

            if ((status >= 500) && (status < 600)) {
				$state.go('500');
            }

	}

    $scope.editRegistro = function (r) {
                
           $scope.registro.idRegistro = r.idRegistro;
           $scope.registro.idAlumno   = $scope.alumnoInfo.idAlumno;
           $scope.registro.nombre = r.nombreRegistro;
           $scope.registro.descripcion = r.descripcion;

           $scope.onPoint = true;
           $scope.isCreating = false;

           //console.log($scope.registro)
    };

    $scope.newRegistro = function () {
                
           $scope.onPoint = true;
           $scope.isCreating = true ;
    };

    $scope.closeForm = function () {
                
           $scope.onPoint = false;
           $scope.isCreating = true ;
    };

    $scope.enableEdit = function (r) {
                
            r.onPoint = true;
    };

    $scope.disableEdit = function (r) {
                
			r.onPoint = false;

    };

  	$scope.toString = function (){
			console.log($state.params.alumnoInfo);
		}



	$timeout( function(){ $scope.initScripts(); }, 100);
	$scope.toString();
	$scope.init();

}]);

// .directive('contenteditable', function() {
//     return {
//       require: '?ngModel',
//       link: function(scope, element, attr, ngModel) {
//         var read;
//         if (!ngModel) {
//           return;
//         }
//         ngModel.$render = function() {
//           return element.html(ngModel.$viewValue);
//         };
//         element.bind('blur', function() {
//           if (ngModel.$viewValue !== $.trim(element.html())) {
//             return scope.$apply(read);
//           }
//         });
//         return read = function() {
//           console.log("read()");
//           return ngModel.$setViewValue($.trim(element.html()));
//         };
//       }
//     };
//   });