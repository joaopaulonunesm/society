angular.module("societyApp").factory("loginAPI", function ($http, configs){
    
	var _logar = function (login){
		return $http.post(configs.baseUrl + '/login', login);
	}

	var _trocarSenha = function (trocarSenhaVO){
		return $http.put(configs.baseUrl + configs.version + '/login', trocarSenhaVO);
	}

	var _buscarPorToken = function(){
		return $http.get(configs.baseUrl + configs.version + '/login');
	}

	return {
		logar: _logar,
		trocarSenha: _trocarSenha,
		buscarPorToken: _buscarPorToken
	};

});