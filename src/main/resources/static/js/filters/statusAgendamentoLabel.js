angular.module("societyApp").filter("statusAgendamentoLabel", function () {
	return function (input) {

        if(input == "CONFIRMADO"){
            input = "Confirmado";
        } else if (input == "CANCELADO"){
            input = "Cancelado";
        } else if (input == "AGUARDANDO_SOCIETY"){
            input = "Aguardando Society";
        }

		return input;
	};
});