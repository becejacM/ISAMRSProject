(function () {
    'use strict';

    angular
        .module('app')
        .controller('registerSuplierController', registerSuplierController);

    
    registerSuplierController.$inject = ['UserService','AuthenticationService', '$location', '$rootScope', 'FlashService'];
    
    
    function registerSuplierController(UserService,AuthenticationService, $location, $rootScope, FlashService) {
        var vm = this;
        
        (function initController() {
        	alert("suplier");
        	loadCurrentUser();
            loadAllUsers();
        })();

        vm.user = null;
        vm.suplier = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        
        vm.logout = logout;
        vm.manage = manage;
        vm.registerWorker = registerWorker;
        vm.registerSuplier = registerSuplier;
        vm.resManagerProfil = resManagerProfil;
        vm.r = r;

        function r(){
        	AuthenticationService.SetCredentials(vm.cuser.email, vm.cuser.password, "ResManagerHome");
        	$location.path('/ResManagerHome');
        }

        function resManagerProfil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "ResManagerProfil");
        	$location.path('/ResManagerProfil');
        }
        
        function registerSuplier(){
        	alert("Usao u suplier")
        		UserService.CreateSuplier(vm.suplier,vm.user.restaurant.name)
        		.then(function (response) {
                	if(response.data.message){
                		alert("miki");
                		FlashService.Error(response.data.message, true);
                		$location.path('/registerSuplier');
                	}
                	else if (response.data.email!==null) {
                    	FlashService.Success('Registration successful', true);
                    	alert(response.data.restaurants.length);
                    	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "ResManagerHome");
                        $location.path('/ResManagerHome');
                    } else {
                        FlashService.Error(response.message);
                        //vm.dataLoading = false;
                        $location.path('/registerSuplier');
                    }
                });
        }
        
        function manage(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "manage");
        	$location.path('/manage');
        }
        function registerWorker(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "registerWorker");
        	$location.path('/registerWorker');
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
