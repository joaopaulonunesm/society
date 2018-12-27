angular.module("societyApp").controller("agendarJogoController", function ($scope, $location, agendamentoAPI, societyAPI, $routeParams, configs) {
    
    $scope.agendamento = {};

    $scope.dataAgendamento = {};

    $scope.agendar = function() {

        $scope.agendamento.dataInicio = stringToDate($scope.dataAgendamento);

		agendamentoAPI.criar($scope.agendamento).then(function(response) {

			$scope.agendamento = {};
			$location.path("/");

		}, function(response) {
            alert(response.data.error);
		});
    };
    
    function stringToDate(dataAgendamento) {

        var dateFormated = null;

        if(dataAgendamento.data !== undefined){
            var split = dataAgendamento.data.split("/");
            var dateFormat = split[2] + "-" + split[1] + "-" + split[0];
            
            dateFormated =  dateFormat + "T" + dataAgendamento.hora + configs.timeZone;
        }
		
		return dateFormated; 
    };

    $scope.carregarPaginaComSociety = function(nomeUrl) {

        societyAPI.buscarPorNomeUrl(nomeUrl).then(function(response) {

            $scope.agendamento.society = response.data.data;

        }, function(response) {
            alert(response.data.error);
        });

    };

    $scope.carregarPaginaComSociety($routeParams.nomeUrl);

});