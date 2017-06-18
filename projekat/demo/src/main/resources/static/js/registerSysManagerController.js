/**
 * 
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('registerSysManagerController', registerSysManagerController);

    
    registerSysManagerController.$inject = ['UserService','AuthenticationService', '$location', '$rootScope', 'FlashService'];
    function registerSysManagerController(UserService,AuthenticationService, $location, $rootScope, FlashService) {
        var vm = this;
        vm.user = null;
        vm.cuser = null;
        
        vm.registerSysManager = registerSysManager;
        //vm.loadCurrentUser = loadCurrentUser;
        vm.r = r;

        (function initController() {
            // reset login status
    	//alert("Usao sys");
    		loadCurrentUser();
        })();
        function registerSysManager() {
            //vm.dataLoading = true;
            
            UserService.CreateSysM(vm.user)
                .then(function (response) {
                	if(response.data.message){
                		alert("miki");
                		FlashService.Error(response.data.message, true);
                		$location.path('/registerSysManager');
                	}
                	else if (response.data.email!==null) {
                    	FlashService.Success('Registration successful', true);
                    	AuthenticationService.SetCredentials(vm.cuser.email, vm.cuser.password, "SysManagerHome");
                        $location.path('/SysManagerHome');
                    } else {
                        FlashService.Error(response.message);
                        //vm.dataLoading = false;
                        $location.path('/registerSysManager');
                    }
                });
        }
        
        function r(){
        	AuthenticationService.SetCredentials(vm.cuser.email, vm.cuser.password, "SysManagerHome");
        	$location.path('/SysManagerHome');
        }
        
        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.email)
                .then(function (response) {
                    vm.cuser = response.data;
                });
        }
    }
    


})();
