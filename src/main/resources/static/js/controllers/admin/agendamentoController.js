angular.module("societyApp").controller("agendamentoController", function ($scope, agendamentoAPI, $routeParams, $location) {
    
    $scope.agendamento = {};

    $scope.agendamentoPorId = function (id) {

        agendamentoAPI.buscarPorId(id).then(function(response) {

            $scope.agendamento = response.data.data;

		}, function(response) {
            alert(response.data.error);
            $location.path("/agendamentos");
		});

    };

    $scope.agendamentoPorId($routeParams.id);

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