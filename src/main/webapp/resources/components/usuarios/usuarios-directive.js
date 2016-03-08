.directive( 'elemReady', function( $parse ) {
   return {
       restrict: 'A',
       link: function( $scope, elem, attrs ) {    
          elem.ready(function(){
            $scope.$apply(function(){
                var func = $parse(attrs.elemReady);
                func($scope);
            })
          })
       }
    }
})


'use strict';

angular.module('myApp.usuarios.directive', [])

.directive('userList', [function() {
    return {
        restrict: 'A',
        templateUrl: 'templates/userListTemplate.html',
        link : function(scope, elem, attrs){
          
        elem.ready(function(){
            $scope.$apply(function(){
                var func = $parse(attrs.elemReady);
                func($scope);
            })
          })

            scope.isEdit  = function (){
                scope.$emit('userList.isEdit', scope.item, scope.itemSwitch);

            }
            scope.isDelete = function (){
             //   console.log(scope.item);
                scope.$emit('userList.isDelete', scope.item, scope.itemSwitch);

            }

            
        }
    }
}])