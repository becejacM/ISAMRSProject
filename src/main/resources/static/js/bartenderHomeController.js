(function () {
    'use strict';

    angular
        .module('app')
        .controller('BartenderHomeController', BartenderHomeController);

    BartenderHomeController.$inject = ['$location','UserService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function BartenderHomeController($location,UserService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        
        vm.logout = logout;
        vm.profile = profile;
        vm.schedule = schedule;
        vm.orders = orders;
        vm.tables = tables;
        
        (function initController() {
        	alert("bartender home");
        	loadCurrentUser();
            loadAllUsers();
        })();
        
        function profile(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderProfile", vm.user.token);
        	$location.path('/bartenderProfile');
        }
        
        function schedule(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderSchedule", vm.user.token);
        	$location.path('/bartenderSchedule');
        }
        function orders(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderOrders", vm.user.token);
        	$location.path('/bartenderOrders');
        }
        function tables(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderTables", vm.user.token);
        	$location.path('/bartenderTables');
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