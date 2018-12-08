angular.module("societyApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/index/home.html"
	});

	$routeProvider.when("/agendar-jogo", {
		templateUrl: "views/index/agendar-jogo.html",
		controller: "agendamentoController"
	});

	$routeProvider.when("/login", {
		templateUrl: "views/index/login.html"
	});
		
	$routeProvider.otherwise({redirectTo: "/"});
	
});