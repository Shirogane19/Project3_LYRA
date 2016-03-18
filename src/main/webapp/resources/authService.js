
angular.module('myApp',['ngStorage'])
 

.factory("login_service", function ($http, $q) {
    var isLogged = false;

    function login(user) {
        var deferred = $q.defer();

      $http.post('rest/login/checkuser/',user).success(function (loginResponse) {

      if(loginResponse.code == 200){

        var credentials = {"userId":loginResponse.userId,"firstName":loginResponse.firstName,"lastName":loginResponse.lastName};
        $scope.save(credentials);
        isLogged = true;
        deferred.resolve(credentials);

      } else {
        isLogged = false;
        deferred.reject(error);
      }
        }, function (error) {
                isLogged = false;
                deferred.reject(error);
            });

        return deferred.promise;
    }

  function saveCredentials(u) {
    console.log(u);
    $localStorage.user = u;
  };


  function logout() {
    isLogged = false;
    $localStorage.$reset();
  };

  function isLogged() {
        return isLogged;
  }

  return {
        login: login,
        logout: logout,
        isLogged: isLogged
    };
});





// .service('AuthService', function($q, $http, $localStorage, USER_ROLES) {

//   var LOCAL_TOKEN_KEY = 'yourTokenKey';
//   var userID = 0;
//   var instID = 0;
//   var username = '';
//   var roles = {};
//   var isAuthenticated = false;
//   var authToken;
 
//   function loadUserCredentials() {
//     var user = $localStorage.user;
//     if (var) {
//       useCredentials(var);
//     }
//   }
 

//   function useCredentials(token) {
//     userID   = token.userId;
//     username = token.firstName;
//     isAuthenticated = true;
//     authToken = token;
 
//     if (username == 'admin') {
//       roles.push(USER_ROLES.admin);
//     }
//     if (username == 'master') {
//       roles.push(USER_ROLES.master);
//     }
//     if (username == 'profesor') {
//       roles.push(USER_ROLES.profesor);
//     }
//     if (username == 'encargado') {
//       roles.push(USER_ROLES.encargado);
//     }
 
//     // Set the token as header for your requests!
//     $http.defaults.headers.common['X-Auth-Token'] = token;
//   }
 
//   function destroyUserCredentials() {
//     isAuthenticated = false;
//     $localStorage.$reset();

//   }
 
//   var login = function(name, pw) {
//     return $q(function(resolve, reject) {
//       if ((name == 'admin' && pw == '1') || (name == 'user' && pw == '1')) {
//         // Make a request and receive your auth token from your server
//         storeUserCredentials(name + '.yourServerToken');
//         resolve('Login success.');
//       } else {
//         reject('Login Failed.');
//       }
//     });
//   };
 
//   var logout = function() {
//     destroyUserCredentials();
//   };
 
//   var isAuthorized = function(authorizedRoles) {
//     if (!angular.isArray(authorizedRoles)) {
//       authorizedRoles = [authorizedRoles];
//     }
//     return (isAuthenticated && authorizedRoles.indexOf(role) !== -1);
//   };
 
//   loadUserCredentials();
 
//   return {
//     login: login,
//     logout: logout,
//     isAuthorized: isAuthorized,
//     isAuthenticated: function() {return isAuthenticated;},
//     userCredentials: function() {return userCredentials;},
//     roles: function() {return roles;}
//   };
// })

// .factory('AuthInterceptor', function ($rootScope, $q, AUTH_EVENTS) {
//   return {
//     responseError: function (response) {
//       $rootScope.$broadcast({
//         401: AUTH_EVENTS.notAuthenticated,
//         403: AUTH_EVENTS.notAuthorized
//       }[response.status], response);
//       return $q.reject(response);
//     }
//   };
// })
 
// .config(function ($httpProvider) {
//   $httpProvider.interceptors.push('AuthInterceptor');
// });