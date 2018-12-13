angular.module("societyApp").controller("agendamentoAdminController", function ($scope, $location, agendamentoAPI, configs) {
    
    $scope.agendamentos = [];

    $scope.agendamentosPorSociety = function (idSociety) {

        agendamentoAPI.buscarPorSociety(idSociety).then(function(response) {

            $scope.agendamentos = response.data;

		}, function(response) {
            // erro
		});

    };

    $scope.agendamentosPorSociety(1);

});