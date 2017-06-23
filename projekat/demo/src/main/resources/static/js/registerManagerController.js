/**
 * 
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('registerManagerController', registerManagerController);

    
    registerManagerController.$inject = ['UserService','RestaurantService','AuthenticationService', '$location', '$rootScope', 'FlashService'];
    
    
    
    function registerManagerController(UserService,RestaurantService,AuthenticationService, $location, $rootScope, FlashService) {
        var vm = this;
        vm.user = null;
        vm.cuser = null;
        vm.registerManager = registerManager;
        vm.loadCurrentUser = loadCurrentUser;
        vm.loadRestaurants = loadRestaurants;
        vm.allRestaurants = [];
        vm.logout = logout;
        /*vm.registerM = registerM;
        
        function registeM(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "SysManagerHome");
        	$location.path('/SysManagerHome');
        }*/
        vm.r = r;
        
        (function initController() {
            // reset login status
    		loadCurrentUser();
    		loadRestaurants();
    		//alert(vm.cuser.email);
        })();
        function r(){
        	AuthenticationService.SetCredentials(vm.cuser.email, vm.cuser.password, "SysManagerHome", vm.user.token);
        	$location.path('/SysManagerHome');
        }
        
        function logout(){
            AuthenticationService.ClearCredentials();
            $location.path('/login');
        }

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
                    	AuthenticationService.SetCredentials(vm.cuser.email, vm.cuser.password, "SysManagerHome", vm.user.token);
                        $location.path('/SysManagerHome');
                    } else {
                        FlashService.Error(response.message);
                        //vm.dataLoading = false;
                        $location.path('/registerManager');
                    }
                });
        }
        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.email)
                .then(function (response) {
                    vm.cuser = response.data;
                });
        }
        
        function loadRestaurants(){
        	RestaurantService.GetAllRests()
            .then(function (response) {
                vm.allRestaurants = response.data;
            });
        }
    }
    
    

})();
