/**
 * 
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('registerSysManagerController', registerSysManagerController);

    
    registerSysManagerController.$inject = ['UserService','AuthenticationService', '$location', '$rootScope', 'FlashService'];
    
    (function initController() {
            // reset login status
    	alert("Usao sys");
    		loadCurrentUser();
        })();
    
    function registerSysManagerController(UserService,AuthenticationService, $location, $rootScope, FlashService) {
        var vm = this;
        
        vm.registerSysManager = registerSysManager;
        vm.loadCurrentUser = loadCurrentUser;
        
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
                    	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "SysManagerHome");
                        $location.path('/SysManagerHome');
                    } else {
                        FlashService.Error(response.message);
                        //vm.dataLoading = false;
                        $location.path('/registerSysManager');
                    }
                });
        }
    }
    
    function loadCurrentUser() {
        UserService.GetByUsername($rootScope.globals.currentUser.email)
            .then(function (response) {
                vm.user = response.data;
            });
    }

})();
