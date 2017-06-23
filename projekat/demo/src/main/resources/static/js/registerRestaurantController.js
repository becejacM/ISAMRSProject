(function () {
    'use strict';

    angular
        .module('app')
        .controller('registerRestaurantController', registerRestaurantController);

    registerRestaurantController.$inject = ['$location','UserService','RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function registerRestaurantController($location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.restaurant = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        
        vm.logout = logout;
        vm.managerProfil = managerProfil;
        vm.registerManager = registerManager;
        vm.registerRestaurant = registerRestaurant;
        
        vm.r = r;
        
        (function initController() {
        	//alert("restoraniiiiii");
        	loadCurrentUser();
            loadAllUsers();
        })();

        function managerProfil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "SysManagerProfil");
        	$location.path('/SysManagerProfil');
        }
        
        function registerManager(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "registerManager");
        	$location.path('/registerManager');
        }
        function registerRestaurant() {
            //vm.dataLoading = true;
            //alert("Usao u rest")
            RestaurantService.CreateRestaurant(vm.restaurant, vm.user.email)
                .then(function (response) {
                	alert("Milana");
                	if(response.data.message){
                		alert("miki");
                		FlashService.Error(response.data.message, true);
                		$location.path('/registerRestaurant');
                	}
                	/*else if (response.data.email!==null) {
                    	FlashService.Success('Registration successful', true);
                    	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "SysManagerHome");
                        $location.path('/SysManagerHome');
                    } else {
                        FlashService.Error(response.message);
                        //vm.dataLoading = false;
                        $location.path('/registerRestaurant');
                    }*/
                	FlashService.Success('Registration successful', true);
                	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "SysManagerHome");
                    $location.path('/SysManagerHome');
                });
        }
        function logout(){
            AuthenticationService.ClearCredentials();
            $location.path('/login');
        }
        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.email)
                .then(function (response) {
                    vm.user = response.data;
                });
        }
        
        
        function r(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "SysManagerHome");
        	$location.path('/SysManagerHome');
        }
        
        function loadAllUsers() {
            UserService.GetAll()
                .then(function (users) {
                    vm.allUsers = users;
                });
        }

        function deleteUser(id) {
            UserService.Delete(id)
            .then(function () {
                loadAllUsers();
            });
        }
    }

})();