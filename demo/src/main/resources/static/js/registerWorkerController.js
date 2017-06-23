(function () {
    'use strict';

    angular
        .module('app')
        .controller('registerWorkerController', registerWorkerController);

    
    registerWorkerController.$inject = ['UserService','AuthenticationService', '$location', '$rootScope', 'FlashService'];
    
    
    function registerWorkerController(UserService,AuthenticationService, $location, $rootScope, FlashService) {
        var vm = this;
        
        (function initController() {
        	alert("worker");
        	loadCurrentUser();
            loadAllUsers();
        })();

        vm.user = null;
        vm.employee = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        
        vm.logout = logout;
        vm.manage = manage;
        vm.registerWorker = registerWorker;
        vm.registerSuplier = registerSuplier;
        vm.resManagerProfil = resManagerProfil;
        
        vm.cookMode = false;
        vm.check = check;
        
        function check(){
        	if (vm.employee.role === 'cook'){
        		alert(vm.employee.role);
        		alert("cooook");
        		vm.cookMode = true;
        	}
        	else{
        		vm.cookMode = false;
        		alert(vm.employee.role);
        		alert("not cooook");
        	}
        	
        }


        function resManagerProfil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "ResManagerProfil", vm.user.token);
        	$location.path('/ResManagerProfil');
        }
        
        function registerWorker(){
        	alert("Usao u worker")
        	if(angular.equals(vm.employee.role, "cook")){
        		UserService.CreateCook(vm.employee, vm.user.restaurant.name)
        		.then(function (response) {
                	if(response.data.message){
                		alert("miki");
                		FlashService.Error(response.data.message, true);
                		$location.path('/registerWorker');
                	}
                	else if (response.data.email!==null) {
                    	FlashService.Success('Registration successful', true);
                    	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "ResManagerHome", vm.user.token);
                        $location.path('/ResManagerHome');
                    } else {
                        FlashService.Error(response.message);
                        //vm.dataLoading = false;
                        $location.path('/registerWorker');
                    }
                });
        	}else if(angular.equals(vm.employee.role, "waiter")){
        		UserService.CreateWaiter(vm.employee,vm.user.restaurant.name)
        		.then(function (response) {
                	if(response.data.message){
                		alert("miki");
                		FlashService.Error(response.data.message, true);
                		$location.path('/registerWorker');
                	}
                	else if (response.data.email!==null) {
                    	FlashService.Success('Registration successful', true);
                    	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "ResManagerHome", vm.user.token);
                        $location.path('/ResManagerHome');
                    } else {
                        FlashService.Error(response.message);
                        //vm.dataLoading = false;
                        $location.path('/registerWorker');
                    }
                });
        	}else if(angular.equals(vm.employee.role, "bartender")){
        		UserService.CreateBartender(vm.employee,vm.user.restaurant.name)
        		.then(function (response) {
                	if(response.data.message){
                		alert("miki");
                		FlashService.Error(response.data.message, true);
                		$location.path('/registerWorker');
                	}
                	else if (response.data.email!==null) {
                    	FlashService.Success('Registration successful', true);
                    	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "ResManagerHome", vm.user.token);
                        $location.path('/ResManagerHome');
                    } else {
                        FlashService.Error(response.message);
                        //vm.dataLoading = false;
                        $location.path('/registerWorker');
                    }
                });
        	}
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
