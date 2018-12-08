angular.module("societyApp").factory("tokenInterceptor", function($q, $location){

	return {

		'request': function(config){
			
			config.headers.Authorization = 'Bearer ' + localStorage.getItem("token");

			return config;
		},


		'responseError': function(rejection){
			
			if(rejection.status == 401){
				localStorage.removeItem("token");
			}

			if(rejection.status < 0){
				$location.path("/connectionrefused");
			}

			return $q.reject(rejection);
		}

	};
});