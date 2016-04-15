'use strict';

angular.module('myApp.usuarios.service', [])

.service('usuariosService',function() {

  var user = [];


this.setUser = function(usuario){
      user = usuario;
      localStorage.setItem("Usuario: ", JSON.stringify(user));
  };

this.getUser = function(){
	var u;
	var result = localStorage.getItem("Usuario");
	u = JSON.parse(result); //var test is now re-loaded!
	return result;
  };

this.clearInfo = function(){
	  user = [];
  };




});
