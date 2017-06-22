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
        vm.allFriendsIAccept = [];
        vm.allFriendsIAdd = [];
        vm.allReq = [];
        vm.deleteUser = deleteUser;

        vm.allFriendsMode = false;
        vm.myFriendsMode = false;
        vm.reqMode = false;
        
        vm.showAll = showAll;
        vm.showFriends = showFriends;
        vm.showReq = showReq;
        vm.add = add;
        vm.accept = accept;
        vm.reject = reject;
        vm.deleteF = deleteF;
        
        vm.find = find;
        vm.logout = logout;
        vm.profil = profil;
        vm.restaurants = restaurants;
        vm.home = home;
        
        (function initController() {
            loadCurrentUser();
            //loadAllUsers();
            
        })();
        
        function add(id){
        	UserService.add(vm.user.id,id)
            .then(function (response) {
            	if(angular.equals(response.data.status, "exists")){
            		FlashService.Error('Friend already added', false);
            		
            	}
            	else if(angular.equals(response.data.status, "you")){
            		FlashService.Error('It is you', false);
            	}
            	else{
            		FlashService.Success('Friend added', false);
            		
            	}
        		
            });
        }
        
        function accept(id){
        	UserService.accept(id,vm.user.id)
            .then(function (response) {
            	loadReq();
            });
        	
        }
        
        function reject(id){
        	UserService.reject(id,vm.user.id)
            .then(function (response) {
            	loadReq();
            });
        	
        }
        
        function deleteF(id){
        	UserService.deleteF(id,vm.user.id)
            .then(function (response) {
            	loadFriends();
            });
        	
        }
        function loadAllUsers() {
            UserService.GetAllGuests()
                .then(function (response) {
                    vm.allUsers = response.data;
                });
        }
        
        function loadFriends(){
           	UserService.loadFriendsIAccept(vm.user.id)
            .then(function (response) {
                vm.allFriendsIAccept = response.data;
            });
        	
        	UserService.loadFriendsIAdd(vm.user.id)
            .then(function (response) {
                vm.allFriendsIAdd = response.data;
            });
        }
        
        function loadReq(){
          	UserService.loadReq(vm.user.id)
            .then(function (response) {
            	if(response.status===204){
            		FlashService.Error('You don\'t have friend requests', false);
            		vm.reqMode = false;
                	vm.allFriendsMode = false;
                    vm.myFriendsMode = false;
            	}
            	else{
            		vm.reqMode = true;
                	vm.allFriendsMode = false;
                    vm.myFriendsMode = false;
            	}
                vm.allReq = response.data;
            });
        }
        function find() {
        	
        	UserService.find(vm.parametar.par)
            .then(function (response) {
            	vm.allUsers = response.data;
            });
        }
        function showAll() {
        	//loadAllUsers();
        	vm.allUsers=[];
        	FlashService.clearFlashMessageP();
            vm.allFriendsMode = true;
            vm.myFriendsMode = false;
            vm.reqMode = false;
        }
        function showFriends() {

        	loadFriends();
        	FlashService.clearFlashMessageP();

        	vm.myFriendsMode = true;
        	vm.allFriendsMode = false;
            vm.reqMode = false;
            
        }
        
        function showReq() {

        	FlashService.clearFlashMessageP();
        	loadReq();
        	
            
        }


        function profil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "guestProfil", vm.user.token);
        	$location.path('/guestProfil');
        }
        
        function home(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "home", vm.user.token);

        	$location.path('/home');
        }

        function restaurants(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "reserveRestaurant", vm.user.token);

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
        
        vm.SortableTableReq=SortableTableReq;
        function SortableTableReq() {

            vm.head = {
                    img: "Image",
                    firstName: "First name",
                    lastName: "Last name"
                  
                };
                        
            vm.sort = {
                column: 'firstName',
                descending: false
            };

            vm.selectedCls = function(column) {
                return column == vm.sort.column && 'sort-' + vm.sort.descending;
            };
            
            vm.changeSorting = function(column) {
                var sort = vm.sort;
                if (sort.column == column) {
                    sort.descending = !sort.descending;
                } else {
                    sort.column = column;
                    sort.descending = false;
                }
            };
        
        }
        
        vm.SortableTableF=SortableTableF;
        function SortableTableF() {

            vm.head2 = {
                    img: "Image",
                    firstName: "First name",
                    lastName: "Last name"
                  
                };
                        
            vm.sort2 = {
                column: 'firstName',
                descending: false
            };

            vm.selectedCls2 = function(column) {
                return column == vm.sort2.column && 'sort-' + vm.sort2.descending;
            };
            
            vm.changeSorting2 = function(column) {
                var sort = vm.sort2;
                if (sort.column == column) {
                    sort.descending = !sort.descending;
                } else {
                    sort.column = column;
                    sort.descending = false;
                }
            };
        
        }
    }

})();