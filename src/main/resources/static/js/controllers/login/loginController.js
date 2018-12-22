angular.module("societyApp").controller("loginController", function ($scope, loginAPI, configs) {
    
    $scope.login = {};

    $scope.trocarSenhaVO = {};

    $scope.logar = function() {

        if($scope.login.senha != null && $scope.login.email != null){

            loginAPI.logar($scope.login).then(function(response) {

                localStorage.setItem("token", response.data.data.token);

			    $(location).attr('href', configs.siteUrl + '/admin');
    
            }, function(response) {
			    alert(response.data.error);
            });

        }

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

    $scope.verificaSeEstaLogado = function() {

        if(localStorage.getItem("token")){

			    $(location).attr('href', configs.siteUrl + '/admin');
        }

    };

    $scope.verificaSeEstaLogado();

});