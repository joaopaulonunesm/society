angular.module("societyApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/admin/home.html",
		controller: "agendamentosController"
	});

	$routeProvider.when("/agendamentos", {
		templateUrl: "views/admin/agendamentos.html",
		controller: "agendamentosController"
	});

	$routeProvider.when("/agendamento/:id", {
		templateUrl: "views/admin/agendamento.html",
		controller: "agendamentoController"
	});

	$routeProvider.when("/agendamentos/agendar/:nomeUrl", {
		templateUrl: "views/admin/agendar.html",
		controller: "agendarJogoController"
	});

	$routeProvider.when("/configuracoes", {
		templateUrl: "views/admin/configuracoes.html",
		controller: "configuracoesController"
	});
	
	$routeProvider.otherwise({redirectTo: "/"});
	
});