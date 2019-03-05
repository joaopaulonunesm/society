angular.module("societyApp").controller("agendamentosController", function ($scope, agendamentoAPI) {
    
    $scope.agendamentos = [];

    $scope.agendamentosPorSociety = function () {

        agendamentoAPI.buscarPorSociety().then(function(response) {

            $scope.agendamentos = response.data.data;

		}, function(response) {

		});

    };

    $scope.agendamentosPorSociety();

    $scope.statusAgendamento = function (agendamento) {

        dataAtual = new Date();

        dataInicio = new Date(agendamento.dataInicio);

        dataFim = new Date(agendamento.dataFim);

        if(dataAtual > dataInicio && dataAtual < dataFim){
            return "Bola rolando!"
        } else if (dataAtual > dataFim){
            return "Jogo encerrado!"
        } else if (dataAtual < dataInicio){
            return "Próximo jogo!"
        }

    }

});