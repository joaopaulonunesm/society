angular.module("societyApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/admin/home.html"
	});

	$routeProvider.when("/jogos-agendados", {
		templateUrl: "views/admin/jogos-agendados.html"
	});

	$routeProvider.when("/jogos-agendados/agendar", {
		templateUrl: "views/admin/agendar.html",
		controller: "agendamentoController"
	});

	$routeProvider.when("/configuracoes", {
		templateUrl: "views/admin/configuracoes.html"
	});
	
	$routeProvider.otherwise({redirectTo: "/"});
	
});