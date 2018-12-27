angular.module("societyApp").controller("societyController", function ($scope, societyAPI, loginAPI, configs) {
    
      $scope.society = {};

      $scope.buscarSociety = function () {

            loginAPI.buscarPorToken().then(function(response) {

                  if(response.data.data.tipoLogin == 'SOCIETY'){

                        societyAPI.buscarPorToken().then(function(response) {

                              $scope.society = response.data.data;
                  
                        }, function(response) {
                              
                        });

                  } else if (response.data.data.tipoLogin == 'USUARIO'){

                        $(location).attr('href', configs.siteUrl + '/usuario');

                  } else if (response.data.data.tipoLogin == 'MODERADOR'){

                        $(location).attr('href', configs.siteUrl + '/moderador');

                  }

            }, function(response) {
                  $(location).attr('href', configs.siteUrl + '/login');
            });

      };

      $scope.buscarSociety();

      $scope.sair = function() {

            localStorage.removeItem("token");

            $(location).attr('href', configs.siteUrl);

      };

});