angular.module("societyApp").controller("loginController", function ($scope, loginAPI, configs) {
    
    $scope.login = {};

    $scope.logar = function() {

        if($scope.login.senha != null && $scope.login.email != null){

            localStorage.setItem("token", "token");

            $(location).attr('href', configs.siteUrl + '/admin');

        }      

    };

});