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
        	alert("sranje");
        	hideInfo();
        })();

        
        function login() {
            vm.dataLoading = true;
            
            AuthenticationService.Login(vm.email, vm.password)
            .then (function (response) {
            	//alert(response.data.email);
                if (response.data.email) {
                	
                	//AuthenticationService.SetCredentials(vm.email, vm.password, "home");
                	if(angular.equals(response.data.role, "guest")){
                		AuthenticationService.SetCredentials(vm.email, vm.password, "home");
                		$location.path('/home');
                        showOptions();
                	}else if(angular.equals(response.data.role,"system_manager")){
                		AuthenticationService.SetCredentials(vm.email, vm.password, "SysManagerHome");
                		$location.path('/SysManagerHome');
                	}else if(angular.equals(response.data.role,"manager")){
                		AuthenticationService.SetCredentials(vm.email, vm.password, "SysManagerHome");
                		$location.path('/SysManagerHome');
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
