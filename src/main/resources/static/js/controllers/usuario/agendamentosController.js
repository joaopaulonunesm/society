angular.module("societyApp").controller("agendamentosController", function ($scope, agendamentoAPI) {
    
    $scope.agendamentos = [];

    $scope.agendamentosPorUsuario = function () {

        agendamentoAPI.buscarPorUsuario().then(function(response) {

            $scope.agendamentos = response.data.data;

		}, function(response) {

		});

    };

    $scope.agendamentosPorUsuario();

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