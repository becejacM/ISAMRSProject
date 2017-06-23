(function () {
    'use strict';

    angular
        .module('app')
        .controller('addRegionController', addRegionController);

    addRegionController.$inject = ['$location','UserService','RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function addRegionController($location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.region = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        
        vm.logout = logout;
        vm.manage = manage;
        vm.registerWorker = registerWorker;
        vm.registerSuplier = registerSuplier;
        vm.resManagerProfil = resManagerProfil;
        vm.saveRegion = saveRegion;
        
        (function initController() {
        	loadCurrentUser();
            loadAllUsers();
            alert("dodavanje regiona");
        })();

        function resManagerProfil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "ResManagerProfil", vm.user.token);
        	$location.path('/ResManagerProfil');
        }
        
        function saveRegion(){
        	RestaurantService.CreateRegion(vm.region,vm.user.restaurant.name)
            .then(function (response) {
            	FlashService.Success('Region created', false);
            });
        }
        
        function registerWorker(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "registerWorker", vm.user.token);
        	$location.path('/registerWorker');
        }
        function manage(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "manage", vm.user.token);
        	$location.path('/manage');
        }
        function registerSuplier(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "registerSuplier", vm.user.token);
        	$location.path('/registerSuplier');
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