angular.module("societyApp").factory("loginAPI", function ($http, configs){
    
    var _salvar = function (society){
		return $http.post(configs.baseUrl + '/society', society);
	}

	return {
		salvar: _salvar
    };

});