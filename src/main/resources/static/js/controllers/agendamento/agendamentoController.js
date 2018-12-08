angular.module("societyApp").controller("agendamentoController", function ($scope, $location, agendamentoAPI, societyAPI, configs) {
    
    $scope.agendamento = {};

    $scope.dataAgendamento = {};

    $scope.societies = [];

    $scope.agendar = function() {

        $scope.agendamento.dataInicio = stringToDate($scope.dataAgendamento);

		agendamentoAPI.salvar($scope.agendamento).then(function(response) {

			$scope.agendamento = {};
			$location.path("/");

		}, function(response) {
            // erro
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

    $scope.buscarSocities = function () {

        societyAPI.buscarTodos().then(function(response) {

            $scope.societies = response.data;

		}, function(response) {
            // erro
		});

    };

    $scope.buscarSocities();

    $scope.preencherCamposSociety = function (idSociety) {

        var society =  $scope.societies.filter(function(society) {
            return society.id == idSociety;
        });

        $scope.agendamento.society.valorHora = society[0].valorHora;
        $scope.agendamento.society.observacao = society[0].observacao;
        $scope.agendamento.society.celular = society[0].celular;
        $scope.agendamento.society.telefone = society[0].telefone;
        $scope.agendamento.society.email = society[0].email;
        $scope.agendamento.society.endereco = society[0].endereco;
        $scope.agendamento.society.cep = society[0].cep;

    };

});