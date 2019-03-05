angular.module("societyApp").factory("avaliacaoAPI", function ($http, configs){
    
	var _avaliarSociety = function (avaliacao){
		return $http.post(configs.baseUrl + configs.version + '/avaliacao/society', avaliacao);
	}

	var _avaliarUsuario = function (avaliacao){
		return $http.post(configs.baseUrl + configs.version + '/avaliacao/usuario', avaliacao);
	}
    
	var _avaliacoesSociety = function (idSociety){
		return $http.get(configs.baseUrl + '/avaliacao/society/' + idSociety);
	}

	var _avaliacoesUsuario = function (idUsuario){
		return $http.get(configs.baseUrl + '/avaliacao/usuario/' + idUsuario);
	}

	var _avaliacoesSocietyPorToken = function (){
		return $http.get(configs.baseUrl + '/avaliacao/society');
	}

	var _avaliacoesUsuarioPorToken = function (){
		return $http.get(configs.baseUrl + '/avaliacao/usuario');
	}

	var _avaliacoesPendentesSociety = function (){
		return $http.get(configs.baseUrl + configs.version + '/avaliacao/society/pendente');
	}

	var _avaliacoesPendentesUsuario = function (){
		return $http.get(configs.baseUrl + configs.version + '/avaliacao/usuario/pendente');
	}

	return {
		avaliarSociety: _avaliarSociety,
		avaliarUsuario: _avaliarUsuario,
		avaliacoesSociety: _avaliacoesSociety,
		avaliacoesUsuario: _avaliacoesUsuario,
		avaliacoesSocietyPorToken: _avaliacoesSocietyPorToken,
		avaliacoesUsuarioPorToken: _avaliacoesUsuarioPorToken,
		avaliacoesPendentesSociety: _avaliacoesPendentesSociety,
		avaliacoesPendentesUsuario: _avaliacoesPendentesUsuario
	};

});