angular.module("societyApp").controller("agendamentosController", function ($scope, agendamentoAPI) {
    
    $scope.agendamentos = [];

    $scope.agendamentosPorUsuario = function () {

        agendamentoAPI.buscarPorUsuario().then(function(response) {

            $scope.agendamentos = response.data.data;

		}, function(response) {

		});

    };

    $scope.agendamentosPorUsuario();

});