(function () {
    'use strict';

    angular
        .module('app')
        .controller('GuestFriendsController', GuestFriendsController);

    GuestFriendsController.$inject = ['$location','UserService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function GuestFriendsController($location,UserService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.parametar = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        vm.allFriendsMode = false;
        vm.myFriendsMode = false;
        vm.reqMode = false;
        
        vm.showAll = showAll;
        vm.showFriends = showFriends;
        vm.showReq = showReq;
        
        vm.find = find;
        vm.logout = logout;
        vm.profil = profil;
        vm.restaurants = restaurants;
        vm.home = home;
        
        (function initController() {
        	
            loadCurrentUser();
            loadAllUsers();
            showOptions();
            
        })();
        
        function loadAllUsers() {
            UserService.GetAllGuests()
                .then(function (response) {
                    vm.allUsers = response.data;
                });
        }
        
        function find() {
        	
        	UserService.find(vm.parametar.par)
            .then(function (response) {
            	vm.allUsers = response.data;
            });
        }
        function showAll() {
        	loadAllUsers();
            vm.allFriendsMode = true;
            vm.myFriendsMode = false;
            vm.reqMode = false;
        }
        function showFriends() {
        	vm.allFriendsMode = false;
            vm.myFriendsMode = true;
            vm.reqMode = false;
        }
        
        function showReq() {
        	vm.allFriendsMode = false;
            vm.myFriendsMode = false;
            vm.reqMode = true;
        }


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
        
        
        
        
        

        function deleteUser(id) {
            UserService.Delete(id)
            .then(function () {
                loadAllUsers();
            });
        }
    }

})();