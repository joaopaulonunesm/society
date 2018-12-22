angular.module("societyApp").factory("societyAPI", function ($http, configs){

	var _criar = function (society){
		return $http.post(configs.baseUrl + '/society', society);
	}
	
	var _alterar = function (society){
		return $http.put(configs.baseUrl + configs.version + '/society', society);
	}
	
	var _buscarTodos = function (){
		return $http.get(configs.baseUrl + '/society');
	}

	var _buscarPorNomeUrl = function (nomeUrl){
		return $http.get(configs.baseUrl + '/society/nome/' + nomeUrl);
	}

	var _ativarOuInvativar = function (id, confirmacao){
		return $http.put(configs.baseUrl + '/society/' + id + '/confirmacao/' + confirmacao);
	}

	var _buscarPorToken = function (){
		return $http.get(configs.baseUrl + configs.version + '/society/token');
	}

	return {
		criar: _criar,
		alterar: _alterar,
		buscarTodos: _buscarTodos,
		buscarPorNomeUrl: _buscarPorNomeUrl,
		ativarOuInvativar: _ativarOuInvativar,
		buscarPorToken: _buscarPorToken
    };
	
});