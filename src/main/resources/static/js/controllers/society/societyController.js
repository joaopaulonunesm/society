angular.module("societyApp").controller("societyController", function ($scope, $location, societyAPI) {
    
    $scope.societies = [];

    $scope.society = {};

    $scope.buscarSocities = function () {

        societyAPI.buscarTodos().then(function(response) {

            $scope.societies = response.data;

		}, function(response) {
            // erro
		});

    };

    $scope.buscarSocities();

    $scope.salvarSociety = function() {

		societyAPI.salvar($scope.society).then(function(response) {

			$scope.society = {};
			$location.path("/");

		}, function(response) {
            // erro
		});
    };

});