(function () {
    'use strict';

    angular
        .module('app')
        .controller('WaiterChangePasswordController', WaiterChangePasswordController);

    WaiterChangePasswordController.$inject = ['$location','UserService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function WaiterChangePasswordController($location,UserService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;
        
        vm.editMode = false;
        vm.editProfile = editProfile;
        vm.save = save;

        
        vm.logout = logout;
        vm.profile = profile;
        vm.schedule = schedule;
        vm.orders = orders;
        vm.tables = tables;
        vm.home = home;
        
        (function initController() {
        	//alert("change pass");
        	loadCurrentUser();
            loadAllUsers();
            
            
        })();
        
        function home(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterHome", vm.user.token);
        	$location.path('/waiterHome');
        	
        }

        function profile(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterProfile", vm.user.token);
        	$location.path('/waiterProfile');
        }
        
        function schedule(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterSchedule", vm.user.token);

        	$location.path('/waiterSchedule');
        }
        function orders(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterOrders", vm.user.token);
        	$location.path('/waiterOrders');
        }
        function tables(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterTables", vm.user.token);
        	$location.path('/waiterTables');
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
        
        function editProfile() {
            vm.editMode = true;
            vm.realUser = vm.user;
        }
        
        function save() {
        	vm.editMode = false;
            UserService.Update(vm.user)
              .then(function (response) {
            		  vm.user = response.data;  
              });
            alert("asdfghhj");
            AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterHome");
    		$location.path('/waiterHome');
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