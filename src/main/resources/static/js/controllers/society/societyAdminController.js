angular.module("societyApp").controller("societyAdminController", function ($scope, $location, societyAPI, configs) {
    
    $scope.society = {};

    $scope.buscarSociety = function (idSociety) {

      if(localStorage.getItem("token")){

            societyAPI.buscarPorId(idSociety).then(function(response) {

            $scope.society = response.data;
      
            }, function(response) {
            // erro
            });
            

      } else {

            $(location).attr('href', configs.siteUrl + '/login');

      }

    };

    $scope.buscarSociety(1);

    $scope.alterarInformacoes = function(idSociety) {

		societyAPI.alterar(idSociety, $scope.society).then(function(response) {

			$location.path("/");

		}, function(response) {
            // erro
		});
    };

    $scope.sair = function() {

            localStorage.removeItem("token");

            $(location).attr('href', configs.siteUrl);

    };

});