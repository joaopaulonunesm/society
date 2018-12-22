angular.module("societyApp").controller("loginAdminController", function ($scope, loginAPI, configs) {
    
    $scope.trocarSenhaVO = {};

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