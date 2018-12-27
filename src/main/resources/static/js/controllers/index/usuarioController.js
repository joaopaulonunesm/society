angular.module("societyApp").controller("usuarioController", function ($scope, usuarioAPI, loginAPI, configs) {
    
      $scope.usuario = {};

      $scope.buscarUsuario = function () {

            if(localStorage.getItem("token")){

                  loginAPI.buscarPorToken().then(function(response) {

                        if (response.data.data.tipoLogin == 'USUARIO'){
      
                              usuarioAPI.buscarPorToken().then(function(response) {
      
                                    $scope.usuario = response.data.data;
                        
                              }, function(response) {
                                    
                              });
      
                        }
      
                  }, function(response) {
                        
                  });

            } else {

            }

      };

      $scope.buscarUsuario();

      $scope.validarSeEstaLogado = function() {

            if($scope.isEmptyObject($scope.usuario)){
                  alert("Necessário estar logado como usuário.");
                  $(location).attr('href', configs.siteUrl);
            }

      };

      $scope.sair = function() {

            localStorage.removeItem("token");
            $scope.usuario == {};
            $(location).attr('href', configs.siteUrl);
      };

      $scope.isEmptyObject = function (obj){
            return JSON.stringify(obj) === '{}';
      }

});