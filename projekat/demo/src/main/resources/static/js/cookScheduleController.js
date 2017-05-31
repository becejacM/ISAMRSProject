(function () {
    'use strict';

    angular
        .module('app')
        .controller('CookScheduleController', CookScheduleController);

    CookScheduleController.$inject = ['$location','UserService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function CookScheduleController($location,UserService,AuthenticationService, $rootScope, FlashService) {
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
        vm.home = home;
        
        
        
        (function initController() {
        	//alert("schedule");
        	loadCurrentUser();
            loadAllUsers();
            //calendar();
            
            
        })();
        
        function home(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookHome");
        	$location.path('/cookHome');
        	
        }

        function profile(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookProfile");
        	$location.path('/cookProfile');
        }
        
        function schedule(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookSchedule");

        	$location.path('/cookSchedule');
        }
        function orders(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookOrders");
        	$location.path('/cookOrders');
        }
        function tables(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "cookTables");
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
        
        function calendar() {
            $('#calendar').calendar();
        };
        
    }

})();