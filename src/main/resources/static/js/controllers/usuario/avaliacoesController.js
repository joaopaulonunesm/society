angular.module("societyApp").controller("avaliacoesController", function ($scope, avaliacaoAPI, $location, configs) {

    $scope.avaliacoes = [];

    $scope.minhasAvaliacoes = function(){

        avaliacaoAPI.avaliacoesUsuarioPorToken().then(function(response) {

            $scope.avaliacoes = response.data.data;

		}, function(response) {

            alert(response.data.error);

		});

    };

    $scope.minhasAvaliacoes();

});