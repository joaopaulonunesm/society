angular.module("societyApp").factory("societyAPI", function ($http, configs){

	var _salvar = function (society){
		return $http.post(configs.baseUrl + '/society', society);
	}
	
	var _alterar = function (id, society){
		return $http.put(configs.baseUrl + '/society/' + id, society);
	}

	var _deletar = function (id){
		return $http.delete(configs.baseUrl + '/society/' + id);
	}
	
	var _buscarTodos = function (){
		return $http.get(configs.baseUrl + '/society');
	}
	
	var _buscarPorId = function (id){
		return $http.get(configs.baseUrl + '/society/' + id);
	}

	var _buscarPorNomeUrl = function (nomeUrl){
		return $http.get(configs.baseUrl + '/society/' + nomeUrl);
	}

	return {
		salvar: _salvar,
		alterar: _alterar,
		deletar: _deletar,
		buscarTodos: _buscarTodos,
		buscarPorId: _buscarPorId,
		buscarPorNomeUrl: _buscarPorNomeUrl
    };
	
});