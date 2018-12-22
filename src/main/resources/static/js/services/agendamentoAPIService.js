angular.module("societyApp").factory("agendamentoAPI", function ($http, configs){
	
	var _criar = function (agendamento){
		return $http.post(configs.baseUrl + '/agendamentos', agendamento);
	}
	
	var _alterar = function (id, agendamento){
		return $http.put(configs.baseUrl + '/agendamentos/' + id, agendamento);
	}
	
	var _buscarPorId = function (id){
		return $http.get(configs.baseUrl + '/agendamentos/' + id);
	}

	var _buscarPorSociety = function (idSociety){
		return $http.get(configs.baseUrl + '/agendamentos/society/' + idSociety);
	}

	var _confirmarOuCancelar = function (id, confirmacao){
		return $http.put(configs.baseUrl + '/agendamentos/' + id + '/confirmacao/' + confirmacao);
	}

	var _buscarPorToken = function (){
		return $http.get(configs.baseUrl + configs.version + '/agendamentos/society');
	}

	return {
		criar: _criar,
		alterar: _alterar,
		buscarPorId: _buscarPorId,
		buscarPorSociety: _buscarPorSociety,
		confirmarOuCancelar: _confirmarOuCancelar,
		buscarPorToken: _buscarPorToken
	};
	
});