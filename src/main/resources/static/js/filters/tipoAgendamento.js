angular.module("societyApp").filter("tipoAgendamento", function () {
	return function (input, tipoAgendamento) {

        var output = null;

        if(tipoAgendamento == "CONFIRMADO"){
            output = input.filter(function(obj){ return obj.statusAgendamento == "CONFIRMADO"; });
        } else if (tipoAgendamento == "CANCELADO"){
            output = input.filter(function(obj){ return obj.statusAgendamento == "CANCELADO"; });
        } else if (tipoAgendamento == "AGUARDANDO_SOCIETY"){
            output = input.filter(function(obj){ return obj.statusAgendamento == "AGUARDANDO_SOCIETY"; });
        }

		return output;
	};
});