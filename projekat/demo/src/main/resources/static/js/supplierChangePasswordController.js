(function () {
    'use strict';

    angular
        .module('app')
        .controller('supplierChangePasswordController', supplierChangePasswordController);

    supplierChangePasswordController.$inject = ['$location','UserService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function supplierChangePasswordController($location,UserService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;
        
        vm.editMode = false;
        vm.editProfile = editProfile;
        vm.save = save;

        
        vm.logout = logout;
        
        (function initController() {
        	//alert("change pass");
        	loadCurrentUser();
            loadAllUsers();
            
            
        })();

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
            AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "supplierHome");
    		$location.path('/supplierHome');
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