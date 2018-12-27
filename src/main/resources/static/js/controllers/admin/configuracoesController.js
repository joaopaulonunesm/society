angular.module("societyApp").controller("configuracoesController", function ($scope, loginAPI, societyAPI, $location, configs) {

    $scope.trocarSenhaVO = {};

    $scope.alterarInformacoes = function() {

		societyAPI.alterar($scope.society).then(function(response) {

			$location.path("/");

		}, function(response) {
            alert(response.data.error);
		});
    };

    $scope.trocarSenha = function() {

        if($scope.trocarSenhaVO.senhaAtual != null && $scope.trocarSenhaVO.novaSenha != null){

            loginAPI.trocarSenha($scope.trocarSenhaVO).then(function(response) {

			    $(location).attr('href', configs.siteUrl + '/admin');
    
            }, function(response) {
			    alert(response.data.error);
            });

        }

    };

});