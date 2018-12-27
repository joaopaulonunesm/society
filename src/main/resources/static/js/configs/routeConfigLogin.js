angular.module("societyApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/login/login.html"
	});

	$routeProvider.when("/cadastrar", {
		templateUrl: "views/login/cadastrar.html",
		controller: "criarUsuarioController"
	});
		
	$routeProvider.otherwise({redirectTo: "/"});
	
});