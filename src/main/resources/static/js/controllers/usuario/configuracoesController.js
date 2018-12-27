angular.module("societyApp").controller("configuracoesController", function ($scope, loginAPI, usuarioAPI, $location, configs) {

    $scope.trocarSenhaVO = {};

    $scope.alterarInformacoes = function() {

		usuarioAPI.alterar($scope.usuario).then(function(response) {

			$location.path("/");

		}, function(response) {
            alert(response.data.error);
		});
    };

    $scope.trocarSenha = function() {

        if($scope.trocarSenhaVO.senhaAtual != null && $scope.trocarSenhaVO.novaSenha != null){

            loginAPI.trocarSenha($scope.trocarSenhaVO).then(function(response) {

			    $(location).attr('href', configs.siteUrl + '/usuario');
    
            }, function(response) {
			    alert(response.data.error);
            });

        }

    };

});