angular.module("societyApp").controller("avaliacaoController", function ($scope, avaliacaoAPI, $location, configs) {

    $scope.avaliacao = {};

    $scope.pendentes = [];

    $scope.avaliar = function (agendamento) {

        $scope.avaliacao.agendamento = agendamento;

        avaliacaoAPI.avaliarUsuario($scope.avaliacao).then(function(response) {

            var index = $scope.pendentes.indexOf(agendamento);
            $scope.pendentes.splice(index, 1);
            $scope.avaliacao = {};

		}, function(response) {

            alert(response.data.error);

		});
    };

    $scope.avaliacoesPendentes = function(){

        avaliacaoAPI.avaliacoesPendentesSociety().then(function(response) {

            $scope.pendentes = response.data.data;

		}, function(response) {

            alert(response.data.error);

		});

    };

    $scope.avaliacoesPendentes();

});