angular.module("societyApp").controller("buscarSocietiesController", function ($scope, societyAPI) {
    
    $scope.societies = [];

    $scope.buscarSocities = function () {

        societyAPI.buscarTodos().then(function(response) {

            $scope.societies = response.data.data;

		}, function(response) {
            alert(response.data.error);
		});

    };

    $scope.buscarSocities();

});