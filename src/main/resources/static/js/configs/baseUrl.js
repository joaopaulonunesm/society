angular.module("societyApp").value("configs", {

	baseUrl: "http://localhost:8080",
	siteUrl: "http://localhost:8080",

	//baseUrl: "https://queroevento.herokuapp.com",
	//siteUrl: "http://www.queroevento.com",

	version: "/v1",

	// -0200 sem horario de verão -0300 para horario de verão.
	timeZone: "-0300"

});