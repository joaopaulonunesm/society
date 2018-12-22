angular.module("societyApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/index/home.html"
	});

	$routeProvider.when("/agendar-jogo/:nomeUrl", {
		templateUrl: "views/index/agendar-jogo.html",
		controller: "agendamentoController"
	});

	$routeProvider.when("/society/cadastro", {
		templateUrl: "views/index/society-cadastro.html"
	});
		
	$routeProvider.otherwise({redirectTo: "/"});
	
});