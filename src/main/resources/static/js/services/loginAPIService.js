angular.module("societyApp").factory("loginAPI", function ($http, configs){
    
	var _logar = function (login){
		return $http.post(configs.baseUrl + '/login', login);
	}

	var _trocarSenha = function (trocarSenhaVO){
		return $http.put(configs.baseUrl + configs.version + '/login', trocarSenhaVO);
	}

	return {
		logar: _logar,
		trocarSenha: _trocarSenha
	};

});