(function () {
    'use strict';

    angular
        .module('app')
        .controller('WaiterOrdersController', WaiterOrdersController);

    WaiterOrdersController.$inject = ['$location','UserService', 'RestaurantService','AuthenticationService', '$rootScope', 'FlashService'];
    function WaiterOrdersController($location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService) {
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
        
        vm.seeAllMode = false;
        vm.createNewMode = false;
        vm.addedMode = true;
        vm.createNewOrder = createNewOrder;
        vm.allMenuItems = [];
        vm.addOneItem = addOneItem;
        vm.orderItem = null;
        vm.wholeOrder = null;
        vm.items = [];
        vm.save = save;
        vm.num = null;
        vm.orders = [];
        vm.seeOrders = seeOrders;
        vm.loadOrders = loadOrders;
        
        
        (function initController() {
        	//alert("orders");
        	loadCurrentUser();
            loadAllUsers();
            
        })();
        
        
        
        function home(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterHome");
        	$location.path('/waiterHome');
        	
        }

        function profile(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterProfile");
        	$location.path('/waiterProfile');
        }
        
        function schedule(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterSchedule");

        	$location.path('/waiterSchedule');
        }
        function orders(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterOrders");
        	$location.path('/waiterOrders');
        }
        function tables(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterTables");
        	$location.path('/waiterTables');
        }
        function logout(){
            AuthenticationService.ClearCredentials();
            $location.path('/login');
        }
        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.email)
                .then(function (response) {
                    vm.user = response.data;
                    vm.allMenuItems = vm.user.restaurant.menuItemMenu;
                    vm.wholeOrder = new Object();
                    vm.wholeOrder.employee = vm.user;
                    vm.wholeOrder.restaurant = vm.user.restaurant;
                });
        }
        
        function loadOrders(){
        	RestaurantService.LoadAllOrders(vm.user.email)
        	.then(function(response){
        		vm.orders = response.data;
        	});
        }
        
        
        
        function createNewOrder(){
        	vm.seeAllMode = false;
        	vm.createNewMode = true;
        	vm.realUser = vm.user;
        	alert(vm.wholeOrder.restaurant.name);
        	vm.wholeOrder = new Object();
        	vm.wholeOrder.employee = vm.user;
        	RestaurantService.AddOrder(vm.wholeOrder)
        	.then(function(response){
        		vm.num = response.data.orderNumber;
        	});
        }
        
        function addOneItem(){
        	alert(vm.orderItem.amount + " " + vm.orderItem.menuItem.name);
        	vm.addedMode = false;
        	RestaurantService.AddOneItem(vm.orderItem, vm.num)
        	.then(function (response){
        		vm.items.push(response.data);
        		alert('added');
        	});
        }
        
        function save(){
        	alert(vm.items.length);
        	vm.createNewMode = false;
        	RestaurantService.SaveOrder(vm.items, vm.num)
        	.then(function(response){
        		//FlashService.Success('Order successfully made!', true);
        		alert('order made');
        	});
        }
        
        function seeOrders(){
        	vm.createNewMode = false;
        	vm.seeAllMode = true;
        	loadOrders();
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