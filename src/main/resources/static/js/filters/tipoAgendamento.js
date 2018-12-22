angular.module("societyApp").filter("tipoAgendamento", function () {
	return function (input, tipoAgendamento) {

        var output = null;

        if(tipoAgendamento == "CONFIRMADO"){
            output = input.filter(function(obj){ return obj.status == "CONFIRMADO"; });
        } else if (tipoAgendamento == "CANCELADO"){
            output = input.filter(function(obj){ return obj.status == "CANCELADO"; });
        } else if (tipoAgendamento == "AGUARDANDO_SOCIETY"){
            output = input.filter(function(obj){ return obj.status == "AGUARDANDO_SOCIETY"; });
        }

		return output;
	};
});