angular.module("societyApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/admin/component/dashboard.html"
	});
	
	$routeProvider.when("/login", {
		templateUrl: "views/admin/login.html"
	});
		
	$routeProvider.otherwise({redirectTo: "/"});
	
});