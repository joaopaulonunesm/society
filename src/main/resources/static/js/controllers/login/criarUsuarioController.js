angular.module("societyApp").controller("criarUsuarioController", function ($scope, usuarioAPI, $location) {
    
    $scope.usuarioVO = {};

    $scope.senhaConfirmada = "";

    $scope.salvarUsuario = function() {

        if($scope.usuarioVO.senha == $scope.senhaConfirmada){

            usuarioAPI.criar($scope.usuarioVO).then(function(response) {

                $scope.usuarioVO = {};
                $location.path("/");
    
            }, function(response) {
                      alert(response.data.error);
            });

        } else {
            alert("As senhas não são identicas");
        }

    };

});