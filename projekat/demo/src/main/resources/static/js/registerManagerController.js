/**
 * 
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('registerManagerController', registerManagerController);

    
    registerManagerController.$inject = ['UserService','AuthenticationService', '$location', '$rootScope', 'FlashService'];
    
    (function initController() {
            // reset login status
    		loadCurrentUser();
        })();
    
    function registerManagerController(UserService,AuthenticationService, $location, $rootScope, FlashService) {
        var vm = this;
        
        vm.registerManager = registerManager;
        vm.loadCurrentUser = loadCurrentUser;
        
        function registerManager() {
            //vm.dataLoading = true;
            
            UserService.CreateM(vm.user)
                .then(function (response) {
                	if(response.data.message){
                		alert("miki");
                		FlashService.Error(response.data.message, true);
                		$location.path('/registerManager');
                	}
                	else if (response.data.email!==null) {
                    	FlashService.Success('Registration successful', true);
                    	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "SysManagerHome");
                        $location.path('/SysManagerHome');
                    } else {
                        FlashService.Error(response.message);
                        //vm.dataLoading = false;
                        $location.path('/registerManager');
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
