angular.module("societyApp").controller("cadastrarSocietyController", function ($scope, $location, societyAPI) {

    $scope.society = {};

    $scope.login = {};

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