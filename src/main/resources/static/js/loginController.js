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
                		AuthenticationService.SetCredentials(vm.email, vm.password, "home", response.data.token);
                		$location.path('/home');
                        showOptions();
                	}else if(angular.equals(response.data.role,"system_manager")){
                		AuthenticationService.SetCredentials(vm.email, vm.password, "SysManagerHome", response.data.token);
                		$location.path('/SysManagerHome');
                	}else if(angular.equals(response.data.role,"manager")){
                		AuthenticationService.SetCredentials(vm.email, vm.password, "ResManagerHome", response.data.token);
                		$location.path('/ResManagerHome');
                	}
                	else if(angular.equals(response.data.role, "waiter")){
                		if(angular.equals(response.data.firstTime, "yes")){
                			//alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "waiterChangePassword", response.data.token);
                    		$location.path('/waiterChangePassword');
                		}
                		else {
                			//alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "waiterHome", response.data.token);
                    		$location.path('/waiterHome');
                		}
                		
                	}
                	else if(angular.equals(response.data.role, "cook")){
                		if(angular.equals(response.data.firstTime, "yes")){
                			//alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "cookChangePassword", response.data.token);
                    		$location.path('/cookChangePassword');
                		}
                		else {
                			//alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "cookHome", response.data.token);
                    		$location.path('/cookHome');
                		}
                		
                	}
                	else if(angular.equals(response.data.role, "bartender")){
                		if(angular.equals(response.data.firstTime, "yes")){
                			//alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "bartenderChangePassword", response.data.token);
                    		$location.path('/bartenderChangePassword');
                		}
                		else {
                			//alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "bartenderHome", response.data.token);
                    		$location.path('/bartenderHome');
                		}
                		
                	}
                	else if(angular.equals(response.data.role, "suplier")){
                		if(angular.equals(response.data.firstTime, "yes")){
                			//alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "supplierChangePassword", response.data.token);
                    		$location.path('/supplierChangePassword');
                		}
                		else {
                			//alert(response.data.firstTime);
                			AuthenticationService.SetCredentials(vm.email, vm.password, "supplierHome", response.data.token);
                    		$location.path('/supplierHome');
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
