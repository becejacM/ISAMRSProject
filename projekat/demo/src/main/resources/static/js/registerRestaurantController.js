(function () {
    'use strict';

    angular
        .module('app')
        .controller('registerRestaurantController', registerRestaurantController);

    registerRestaurantController.$inject = ['$location','UserService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function registerRestaurantController($location,UserService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        
        vm.logout = logout;
        vm.managerProfil = managerProfil;
        vm.registerManager = registerManager;
        vm.registerRestaurant = registerRestaurant;
        
        (function initController() {
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
        function registerRestaurant(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "registerRestaurant");
        	$location.path('/registerRestaurant');
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