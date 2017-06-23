(function () {
    'use strict';

    angular
        .module('app')
        .controller('CookOrdersController', CookOrdersController);

    CookOrdersController.$inject = ['$location','UserService', 'RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function CookOrdersController($location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService) {
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
        
        vm.seeAllMode = true;
        vm.takenMode = true;
        vm.loadOrders = loadOrders;
        vm.orders = [];
        vm.take = take;
        vm.taken = [];
        vm.remove = remove;
        vm.loadTaken = loadTaken;
        vm.finish = finish;
        
        (function initController() {
        	//alert("orders");
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
                    loadOrders();
                    loadTaken();
                    //alert(vm.orders.length);
                });
        }
        
        function loadOrders(){
        	alert(vm.user.type);
        	RestaurantService.LoadAllOrders2(vm.user.restaurant.name, vm.user.type)
        	.then(function(response){
        		vm.orders = response.data;
        	});
        }
        
        function loadTaken(){
        	RestaurantService.LoadTaken(vm.user.restaurant.name)
        	.then(function(response){
        		vm.taken = response.data;
        	});
        }
        
        function take(i){
        	alert(i.menuItem.name);
        	//remove(i);
        	RestaurantService.Take(i.itemNumber)
        	.then(function(response){
        		alert("sdfdgf");
        		vm.taken.push(response.data);
        		remove(i);
        	});
        }
        
        function remove(order){
        	var i = vm.orders.indexOf(order);
    		alert(i.toString());
    		if(i === -1){
    			
    		}
    		else {
    			vm.orders.splice(i, 1);
    		}
        }
        
        function remove2(order){
        	var i = vm.taken.indexOf(order);
    		alert(i.toString());
    		if(i === -1){
    			
    		}
    		else {
    			vm.taken.splice(i, 1);
    		}
        }
        
       function finish(i){
    	   alert(i.itemNumber);
    	   RestaurantService.Finish(i.itemNumber)
    	   .then(function(response){
    		   remove2(i);
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