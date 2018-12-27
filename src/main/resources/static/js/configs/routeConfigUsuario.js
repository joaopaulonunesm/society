angular.module("societyApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/usuario/home.html"
	});

	$routeProvider.when("/agendamentos", {
		templateUrl: "views/usuario/agendamentos.html",
		controller: "agendamentosController"
	});

	$routeProvider.when("/agendamento/:id", {
		templateUrl: "views/usuario/agendamento.html",
		controller: "agendamentoController"
	});

	$routeProvider.when("/configuracoes", {
		templateUrl: "views/usuario/configuracoes.html",
		controller: "configuracoesController"
	});
	
	$routeProvider.otherwise({redirectTo: "/"});
	
});