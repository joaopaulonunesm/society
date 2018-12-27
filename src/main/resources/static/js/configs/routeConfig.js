angular.module("societyApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/index/home.html",
		controller: "buscarSocietiesController"
	});

	$routeProvider.when("/agendar-jogo/:nomeUrl", {
		templateUrl: "views/index/agendar-jogo.html",
		controller: "agendarJogoController"
	});

	$routeProvider.when("/society/cadastro", {
		templateUrl: "views/index/society-cadastro.html",
		controller: "cadastrarSocietyController"
	});
		
	$routeProvider.otherwise({redirectTo: "/"});
	
});