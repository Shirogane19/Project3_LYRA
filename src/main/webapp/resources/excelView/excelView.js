'use strict';

angular.module('myApp.excelView', [])

.controller('excelViewCtrl', ['$scope','$http','$timeout','$state','$localStorage','$upload',function($scope,$http,$timeout,$state,$localStorage,$upload) {

  $scope.idInstitucion = $localStorage.user.idInstitucion;
  $scope.files = {};

/** Metodo que guarda un excel por medio del consumo de un servicio REST del API Lyra **/

    $scope.submitExcel = function(event){

    	
    	if(this.excelForm.$valid){
    		
    		//$files: an array of files selected, each file has name, size, and type.
    		for ( var i = 0; i < $scope.files.length; i++) {
    			var file = $scope.files[i];
    			$scope.upload = $upload.upload({
    				url : 'rest/protected/xml/bulkUpload',
    				data : {
    					idInstitucion: $scope.idInstitucion,
    				},
    				file : file,
    			}).progress(
					function(evt) {
						console.log('percent: '+ parseInt(100.0 * evt.loaded / evt.total));
					}).success(function(data, status, headers, config) {
                        console.log(data);
						$state.go('successView');
						

					}).catch(function (error) {
                      //console.error('exception', error.status);
                      $localStorage.error = error.status;
                      $state.go('errorView');
                    }); 
	    			//.error(...)
	    			//.then(success, error, progress); 
    		}
    	}else{
    		$scope.onError = true;
    	}
    };

	/**set files **/
    $scope.onFileSelect = function($files) {
    	$scope.files = $files;
    };

	/** Valida si hay conexcion **/
	$scope.checkResponse = function(response){

		var status = response.status; // error code

            if ((status >= 500) && (status < 600)) {
				$state.go('500');
            }

	}


}]);