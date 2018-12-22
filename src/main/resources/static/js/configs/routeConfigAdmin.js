angular.module("societyApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/admin/home.html"
	});

	$routeProvider.when("/agendamentos", {
		templateUrl: "views/admin/agendamentos.html"
	});

	$routeProvider.when("/agendamento/:id", {
		templateUrl: "views/admin/agendamento.html",
		controller: "agendamentoIdController"
	});

	$routeProvider.when("/agendamentos/agendar/:nomeUrl", {
		templateUrl: "views/admin/agendar.html",
		controller: "agendamentoController"
	});

	$routeProvider.when("/configuracoes", {
		templateUrl: "views/admin/configuracoes.html"
	});
	
	$routeProvider.otherwise({redirectTo: "/"});
	
});