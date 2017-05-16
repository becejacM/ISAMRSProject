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
                	
                	//AuthenticationService.SetCredentials(vm.email, vm.password, "home");
                	if(angular.equals(response.data.role, "guest")){
                		AuthenticationService.SetCredentials(vm.email, vm.password, "home");
                		$location.path('/home');
                        showOptions();
                	}else if(angular.equals(response.data.role,"system_manager")){
                		AuthenticationService.SetCredentials(vm.email, vm.password, "SysManagerHome");
                		$location.path('/SysManagerHome');
                	}else if(angular.equals(response.data.role,"manager")){
                		AuthenticationService.SetCredentials(vm.email, vm.password, "ResManagerHome");
                		$location.path('/ResManagerHome');
                	}
                	else if(angular.equals(response.data.role, "waiter")){
                		if(angular.equals(response.data.firstTime, "yes")){
                			alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "waiterChangePassword");
                    		$location.path('/waiterChangePassword');
                		}
                		else {
                			alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "waiterHome");
                    		$location.path('/waiterHome');
                		}
                		
                	}
                	else if(angular.equals(response.data.role, "cook")){
                		if(angular.equals(response.data.firstTime, "yes")){
                			alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "cookChangePassword");
                    		$location.path('/cookChangePassword');
                		}
                		else {
                			alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "cookHome");
                    		$location.path('/cookHome');
                		}
                		
                	}
                	else if(angular.equals(response.data.role, "bartender")){
                		if(angular.equals(response.data.firstTime, "yes")){
                			alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "bartenderChangePassword");
                    		$location.path('/bartenderChangePassword');
                		}
                		else {
                			alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "bartenderHome");
                    		$location.path('/bartenderHome');
                		}
                		
                	}
                    
                } else {
                	FlashService.Error('Password or email do not exist. Please check if you are registered and verified your email',false);
                    vm.dataLoading = false;
                    $location.path('/login');
                }
            });
            
            
            
        };
        
        
    }

})();
