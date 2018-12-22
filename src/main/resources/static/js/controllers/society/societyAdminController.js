angular.module("societyApp").controller("societyAdminController", function ($scope, $location, societyAPI, configs) {
    
    $scope.society = {};

    $scope.buscarSociety = function () {

      if(localStorage.getItem("token")){

            societyAPI.buscarPorToken().then(function(response) {

                  $scope.society = response.data.data;
      
            }, function(response) {
                  alert(response.data.error);
            });

      } else {

            $(location).attr('href', configs.siteUrl + '/login');

      }

    };

    $scope.buscarSociety();

    $scope.alterarInformacoes = function() {

		societyAPI.alterar($scope.society).then(function(response) {

			$location.path("/");

		}, function(response) {
                  alert(response.data.error);
		});
    };

    $scope.sair = function() {

            localStorage.removeItem("token");

            $(location).attr('href', configs.siteUrl);

    };

});