angular.module('myApp')
 
.constant('AUTH_EVENTS', {
  notAuthenticated: 'auth-not-authenticated',
  notAuthorized: 'auth-not-authorized'
})
 
.constant('USER_ROLES', {
  admin: 'admin_role',
  master: 'master_role',
  profesor: 'profesor_role',
  encargado: 'encargado_role'
});