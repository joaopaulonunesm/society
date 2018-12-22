angular.module("societyApp").factory("tokenInterceptor", function($q, configs){

	return {

		'request': function(config){
			
			config.headers.Authorization = 'Bearer ' + localStorage.getItem("token");

			return config;
		},


		'responseError': function(rejection){
			
			if(rejection.status == 401){
				localStorage.removeItem("token");
				$(location).attr('href', configs.siteUrl + '/login');
			}

			if(rejection.status < 0){
				$(location).attr('href', configs.siteUrl + '/login');
			}

			return $q.reject(rejection);
		}

	};
});