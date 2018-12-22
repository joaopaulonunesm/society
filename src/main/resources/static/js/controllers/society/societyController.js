angular.module("societyApp").controller("societyController", function ($scope, $location, societyAPI) {
    
    $scope.societies = [];

    $scope.society = {};

    $scope.login = {};

    $scope.buscarSocities = function () {

        societyAPI.buscarTodos().then(function(response) {

            $scope.societies = response.data.data;

		}, function(response) {
                  alert(response.data.error);
		});

    };

    $scope.buscarSocities();

    $scope.salvarSociety = function() {

        $scope.society.login = $scope.login;

		societyAPI.criar($scope.society).then(function(response) {

			$scope.society = {};
			$location.path("/");

		}, function(response) {
                  alert(response.data.error);
		});
    };

});