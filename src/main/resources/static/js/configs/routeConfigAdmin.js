angular.module("societyApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/admin/home.html",
		controller: "agendamentosController"
	});

	$routeProvider.when("/agendamentos", {
		templateUrl: "views/admin/agendamentos.html",
		controller: "agendamentosController"
	});

	$routeProvider.when("/agenda", {
		templateUrl: "views/admin/agenda.html",
		controller: "agendamentosController"
	});

	$routeProvider.when("/agendamento/:id", {
		templateUrl: "views/admin/agendamento.html",
		controller: "agendamentoController"
	});

	$routeProvider.when("/configuracoes", {
		templateUrl: "views/admin/configuracoes.html",
		controller: "configuracoesController"
	});
	
	$routeProvider.when("/trocar-senha", {
		templateUrl: "views/admin/trocar-senha.html",
		controller: "configuracoesController"
	});

	$routeProvider.otherwise({redirectTo: "/"});
	
});