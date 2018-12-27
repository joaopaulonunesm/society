angular.module("societyApp").factory("usuarioAPI", function ($http, configs){

	var _criar = function (usuario){
		return $http.post(configs.baseUrl + '/usuario', usuario);
	}
	
	var _alterar = function (usuario){
		return $http.put(configs.baseUrl + configs.version + '/usuario', usuario);
    }

	var _buscarPorToken = function (){
		return $http.get(configs.baseUrl + configs.version + '/usuario/token');
	}

	return {
		criar: _criar,
		alterar: _alterar,
		buscarPorToken: _buscarPorToken
    };
	
});