(function () {
    'use strict';

    angular
        .module('app')
        .controller('GuestFriendsController', GuestFriendsController);

    GuestFriendsController.$inject = ['$location','UserService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function GuestFriendsController($location,UserService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        
        vm.logout = logout;
        vm.profil = profil;
        vm.restaurants = restaurants;
        vm.home = home;
        
        (function initController() {
        	
            loadCurrentUser();
            loadAllUsers();
            showOptions();
            
        })();

        function profil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "guestProfil");
        	$location.path('/guestProfil');
        }
        
        function home(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "home");

        	$location.path('/home');
        }

        function restaurants(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "reserveRestaurant");

        	$location.path('/reserveRestaurant');
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