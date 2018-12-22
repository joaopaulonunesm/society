angular.module("societyApp").controller("agendamentoAdminController", function ($scope, agendamentoAPI, $location) {
    
    $scope.agendamentos = [];

    $scope.agendamentosPorSociety = function () {

        agendamentoAPI.buscarPorToken().then(function(response) {

            $scope.agendamentos = response.data.data;

		}, function(response) {
            if(response.status != 401){
                alert(response.data.error);
            }
		});

    };

    $scope.agendamentosPorSociety();

    $scope.confirmarOuCancelar = function (id, confirmacao) {

        agendamentoAPI.confirmarOuCancelar(id, confirmacao).then(function(response) {

            $location.path("/agendamentos");

		}, function(response) {
            if(response.status != 401){
                alert(response.data.error);
            }
		});

    };

});