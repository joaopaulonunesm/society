angular.module("societyApp").factory("agendamentoAPI", function ($http, configs){
	
	var _salvar = function (agendamento){
		return $http.post(configs.baseUrl + '/agendamentos', agendamento);
	}
	
	var _alterar = function (id, agendamento){
		return $http.put(configs.baseUrl + '/agendamentos/' + id, agendamento);
	}

	var _deletar = function (id){
		return $http.delete(configs.baseUrl + '/agendamentos/' + id);
	}
	
	var _buscarTodos = function (){
		return $http.get(configs.baseUrl + '/agendamentos');
	}
	
	var _buscarPorId = function (id){
		return $http.get(configs.baseUrl + '/agendamentos/' + id);
	}

	var _buscarHorariosReservados = function (dataVO){
		return $http.get(configs.baseUrl + '/agendamentos/horarios-reservados', dataVO);
	}

	var _buscarPorSociety = function (idSociety){
		return $http.get(configs.baseUrl + '/agendamentos/society/' + idSociety);
	}

	return {
		salvar: _salvar,
		alterar: _alterar,
		deletar: _deletar,
		buscarTodos: _buscarTodos,
		buscarPorId: _buscarPorId,
		buscarHorariosReservados: _buscarHorariosReservados,
		buscarPorSociety: _buscarPorSociety
    };
	
	
});