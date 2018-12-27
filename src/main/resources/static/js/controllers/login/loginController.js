angular.module("societyApp").controller("loginController", function ($scope, loginAPI, configs) {
    
    $scope.login = {};

    $scope.logar = function() {

        if($scope.login.senha != null && $scope.login.email != null){

            loginAPI.logar($scope.login).then(function(response) {

                localStorage.setItem("token", response.data.data.token);

                if(response.data.data.tipoLogin == 'SOCIETY'){

                    $(location).attr('href', configs.siteUrl + '/admin');

                } else if (response.data.data.tipoLogin == 'USUARIO'){

                    $(location).attr('href', configs.siteUrl + '/usuario');

                } else if (response.data.data.tipoLogin == 'MODERADOR'){

                    $(location).attr('href', configs.siteUrl + '/moderador');

                }
    
            }, function(response) {
			    alert(response.data.error);
            });

        }

    };

    $scope.verificaSeEstaLogado = function() {

        if(localStorage.getItem("token")){

            loginAPI.buscarPorToken().then(function(response) {

                if(response.data.data.tipoLogin == 'SOCIETY'){

                    $(location).attr('href', configs.siteUrl + '/admin');

                } else if (response.data.data.tipoLogin == 'USUARIO'){

                    $(location).attr('href', configs.siteUrl + '/usuario');

                } else if (response.data.data.tipoLogin == 'MODERADOR'){

                    $(location).attr('href', configs.siteUrl + '/moderador');

                }
    
            }, function(response) {
			    alert(response.data.error);
            });
        }

    };

    $scope.verificaSeEstaLogado();

});