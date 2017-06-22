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
        
        vm.seeAllMode = true;
        vm.createNewMode = false;
        vm.billsMode = false;
        vm.addedMode = true;
        vm.editMode = false;
        vm.showBillMode = false;
        vm.currentOrder = null;
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
        vm.makeBills = makeBills;
        vm.finished = [];
        vm.edit = edit;
        vm.cancel = cancel;
        vm.addNewItem = addNewItem;
        vm.deleteItem = deleteItem;
        vm.currentItem = null;
        vm.saveEdited = saveEdited;
        vm.saveEditedItems = saveEditedItems;
        vm.makeTheBill = makeTheBill;
        vm.remove = remove;
        vm.remove2 = remove;
        vm.currentBill = null;
        vm.finishBill = finishBill;
        vm.currentDate = null;
        vm.orderb = null;
        
        
        (function initController() {
        	//alert("orders");
        	loadCurrentUser();
            loadAllUsers();
            
        })();
        
        
        
        function home(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterHome", vm.user.token);
        	$location.path('/waiterHome');
        	
        }

        function profile(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterProfile", vm.user.token);
        	$location.path('/waiterProfile');
        }
        
        function schedule(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterSchedule", vm.user.token);

        	$location.path('/waiterSchedule');
        }
        function orders(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterOrders", vm.user.token);
        	$location.path('/waiterOrders');
        }
        function tables(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "waiterTables", vm.user.token);
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
                    getAllMenuItems(vm.user.restaurant.name);
                    vm.wholeOrder = new Object();
                    vm.wholeOrder.employee = vm.user;
                    vm.wholeOrder.restaurant = vm.user.restaurant;
                    loadOrders();
                });
        }
        
        function getAllMenuItems(rest){
        	RestaurantService.GetMenuItems(rest)
        	.then(function(response){
        		vm.allMenuItems = response.data;
        	});
        }
        
        function loadOrders(){
        	RestaurantService.LoadAllOrders(vm.user.email)
        	.then(function(response){
        		vm.orders = response.data;
        	});
        }
        
        function loadFinished(){
        	RestaurantService.LoadFinishedOrders(vm.user.email)
        	.then(function(response){
        		vm.finished = response.data;
        	});
        }
        
        
        
        function createNewOrder(){
        	vm.items = [];
        	vm.seeAllMode = false;
        	vm.billsMode = false;
        	vm.createNewMode = true;
        	vm.showBillMode = false;
        	vm.realUser = vm.user;
        	alert(vm.wholeOrder.restaurant.name);
        	vm.wholeOrder = new Object();
        	vm.wholeOrder.employee = vm.user;
        	RestaurantService.AddOrder(vm.wholeOrder)
        	.then(function(response){
        		vm.num = response.data.orderNumber;
        		vm.items = [];
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
        	vm.billsMode = false;
        	vm.seeAllMode = true;
        	vm.showBillMode = false;
        	loadOrders();
        }
        
        function edit(id){
        	
        	RestaurantService.FindOrder(id)
        	.then(function(response){
        		vm.currentOrder = response.data;
        		alert(vm.currentOrder.totalPrice);
        		vm.editMode = true;
        	});
        }
        
        function cancel(){
        	vm.editMode = false;
        }
        
        function addNewItem(){
        	alert(vm.currentOrder.orderNumber);
        	RestaurantService.AddOneItem(vm.orderItem, vm.currentOrder.orderNumber)
        	.then(function (response){
        		vm.currentOrder.items.push(response.data);
        		alert('added');
        	});
        }
        
        function deleteItem(i){
        	RestaurantService.DeleteItem(i.itemNumber)
        		.then(function (response){
        			//alert('deleting item no ', i.itemNumber);
        			remove(i);
        		});
        	
        }
        
        function remove(order){
        	var i = vm.currentOrder.items.indexOf(order);
    		alert(i.toString());
    		if(i === -1){
    			
    		}
    		else {
    			vm.currentOrder.items.splice(i, 1);
    		}
        }
        
        function saveEditedItems(){
        	for(var i = 0; i < vm.currentOrder.items.length; i++){
        		alert(vm.currentOrder.items[i].amount);
        		RestaurantService.SaveEditedItem(vm.currentOrder.items[i].itemNumber, vm.currentOrder.items[i].amount)
        			.then(function (response){
            			alert('edited item');
            			saveEdited();
            		});	
        	}
        }
        
        function saveEdited(){
        	//saveEditedItems();
        	alert(vm.currentOrder.items.length);
        	RestaurantService.SaveEditedOrder(vm.currentOrder.orderNumber)
        		.then(function (response){
        			alert('successfully edited');
        			vm.editMode = false;
        			loadOrders();
        		});
        }
        
        function makeBills(){
        	alert('dsdss');
        	vm.createNewMode = false;
        	vm.seeAllMode = false;
        	vm.billsMode = true;
        	vm.showBillMode = false;
        	loadFinished();
        }
        
        function makeTheBill(order){
        	vm.orderb = order;
        	vm.showBillMode = true;
        	vm.currentBill = new Object();
        	vm.currentBill.order = order;
        	vm.currentBill.employee = vm.user;
        	vm.currentBill.totalPrice = order.totalPrice;
        	var d = new Date();
        	vm.currentBill.date = d;
        	var datestring = ("0" + d.getDate()).slice(-2) + "-" + ("0"+(d.getMonth()+1)).slice(-2) + "-" +
            d.getFullYear() + " " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2);
        	vm.currentDate = datestring;
        }
        
        function finishBill(){
        	RestaurantService.MakeBill(vm.orderb, vm.user.email)
        	.then(function (response){
    			alert('finished');
    			vm.showBillMode = false;
    			remove2(vm.orderb);
    		});
        }
        
        function remove2(order){
        	var i = vm.finished.indexOf(order);
    		alert(i.toString());
    		if(i === -1){
    			
    		}
    		else {
    			vm.finished.splice(i, 1);
    		}
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