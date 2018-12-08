angular.module("societyApp").config(function ($httpProvider) {

	$httpProvider.interceptors.push("tokenInterceptor");

});