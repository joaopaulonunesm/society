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

	$routeProvider.when("/avaliacoes", {
		templateUrl: "views/usuario/avaliacoes.html",
		controller: "avaliacoesController"
	});

	$routeProvider.when("/avaliar", {
		templateUrl: "views/usuario/avaliar.html",
		controller: "avaliacaoController"
	});

	$routeProvider.when("/configuracoes", {
		templateUrl: "views/usuario/configuracoes.html",
		controller: "configuracoesController"
	});

	$routeProvider.when("/trocar-senha", {
		templateUrl: "views/usuario/trocar-senha.html",
		controller: "configuracoesController"
	});
	
	$routeProvider.otherwise({redirectTo: "/"});
	
});