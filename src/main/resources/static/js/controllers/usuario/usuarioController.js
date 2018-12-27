angular.module("societyApp").controller("usuarioController", function ($scope, usuarioAPI, loginAPI, configs) {
    
      $scope.usuario = {};

      $scope.buscarUsuario = function () {

            loginAPI.buscarPorToken().then(function(response) {

                  if(response.data.data.tipoLogin == 'SOCIETY'){

                        $(location).attr('href', configs.siteUrl + '/admin');

                  } else if (response.data.data.tipoLogin == 'USUARIO'){

                        usuarioAPI.buscarPorToken().then(function(response) {

                              $scope.usuario = response.data.data;
                  
                        }, function(response) {
                              
                        });

                  } else if (response.data.data.tipoLogin == 'MODERADOR'){

                        $(location).attr('href', configs.siteUrl + '/moderador');

                  }

            }, function(response) {
                  $(location).attr('href', configs.siteUrl + '/login');
            });

      };

      $scope.buscarUsuario();

      $scope.sair = function() {

            localStorage.removeItem("token");

            $(location).attr('href', configs.siteUrl);

      };

});