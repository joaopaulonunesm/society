angular.module("societyApp").factory("agendamentoAPI", function ($http, configs){
	
	var _criar = function (agendamento){
		return $http.post(configs.baseUrl + '/agendamentos', agendamento);
	}
	
	var _buscarPorId = function (id){
		return $http.get(configs.baseUrl + '/agendamentos/' + id);
	}

	var _confirmarOuCancelar = function (id, confirmacao){
		return $http.put(configs.baseUrl + '/agendamentos/' + id + '/confirmacao/' + confirmacao);
	}

	var _buscarPorSociety = function (){
		return $http.get(configs.baseUrl + configs.version + '/agendamentos/society');
	}

	var _buscarPorUsuario = function (){
		return $http.get(configs.baseUrl + configs.version + '/agendamentos/usuario');
	}

	return {
		criar: _criar,
		buscarPorId: _buscarPorId,
		confirmarOuCancelar: _confirmarOuCancelar,
		buscarPorSociety: _buscarPorSociety,
		buscarPorUsuario: _buscarPorUsuario
	};
	
});