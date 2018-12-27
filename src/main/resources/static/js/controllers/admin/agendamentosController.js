angular.module("societyApp").controller("agendamentosController", function ($scope, agendamentoAPI) {
    
    $scope.agendamentos = [];

    $scope.agendamentosPorSociety = function () {

        agendamentoAPI.buscarPorSociety().then(function(response) {

            $scope.agendamentos = response.data.data;

		}, function(response) {

		});

    };

    $scope.agendamentosPorSociety();

});