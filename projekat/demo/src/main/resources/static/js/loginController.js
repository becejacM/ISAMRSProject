(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService','$rootScope', 'FlashService'];
    function LoginController($location, AuthenticationService,$rootScope, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
        	hideInfo();
        })();

        
        function login() {
            vm.dataLoading = true;
            
            AuthenticationService.Login(vm.email, vm.password)
            .then (function (response) {
            	//alert(response.data.email);
                if (response.data.email) {
                	
                	AuthenticationService.SetCredentials(vm.email, vm.password, "home");
                	if(angular.equals(response.data.role, "guest")){
                		$location.path('/home');
                        showOptions();
                	}
                    
                } else {
                	FlashService.Error('password or email do not exist',false);
                    vm.dataLoading = false;
                    $location.path('/login');
                }
            });
            
            
            
        };
        
        
    }

})();
