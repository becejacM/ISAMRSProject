(function () {
    'use strict';

    angular
        .module('app')
        .controller('CookChangePasswordController', CookChangePasswordController);

    CookChangePasswordController.$inject = ['$location','UserService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function CookChangePasswordController($location,UserService,AuthenticationService, $rootScope, FlashService) {
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
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookHome", vm.user.token);
        	$location.path('/cookHome');
        	
        }

        function profile(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookProfile", vm.user.token);
        	$location.path('/cookProfile');
        }
        
        function schedule(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookSchedule", vm.user.token);

        	$location.path('/cookSchedule');
        }
        function orders(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookOrders", vm.user.token);
        	$location.path('/cookOrders');
        }
        function tables(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookTables", vm.user.token);
        	$location.path('/cookTables');
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
            //alert("asdfghhj");
            AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookHome");
    		$location.path('/cookHome');
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