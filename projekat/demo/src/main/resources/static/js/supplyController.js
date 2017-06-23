(function () {
    'use strict';

    angular
        .module('app')
        .filter("formatDate", function(){
		   return function(input){
		      return  moment(input).format('DD MM YYYY, HH:MM'); 
		   }
        })
        .controller('supplyController', supplyController);

    supplyController.$inject = ['$location','RestaurantService','UserService', 'AuthenticationService', '$rootScope', 'FlashService','$scope'];
    function supplyController($location,RestaurantService,UserService,AuthenticationService, $rootScope, FlashService, $scope) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;
        vm.bid = null;
        vm.grocery = null;

        
        vm.logout = logout;
        vm.manage = manage;
        vm.registerWorker = registerWorker;
        vm.registerSuplier = registerSuplier;
        vm.resManagerProfil = resManagerProfil;
        
        vm.createBid = createBid;
        $scope.createdOrder = false;
        $scope.createOrder = false;
        $scope.addGrocery = false;
        vm.r = r;
        vm.saveBid = saveBid;
        vm.orders = [];
        vm.start = null;
        vm.end = null;
        vm.saveGrocery = saveGrocery;
        vm.addGrocery = addGrocery;
        vm.makeOrder = makeOrder;
        vm.offers = [];
        vm.seeOrder = seeOrder;
        vm.bid = null;
        $scope.listOffers = false;
        $scope.see = true;
        $scope.list = true;
        vm.acceptOffer = acceptOffer;
        //vm.start = moment($scope.order.ord.start).format('DD MM YYYY, hh:mm'); 
        //vm.end = moment($scope.ord.end).format('DD MM YYYY, hh:mm'); 

        
        function seeOrder(bid){
        	vm.bid = bid;
        	vm.bid.start = new Date(bid.start);
        	vm.bid.end = new Date(bid.end);
        	$scope.see = false;
        	$scope.listOffers = true;
        	$scope.list = false;
        	loadAllOffers();
        }
        
        function makeOrder(){
        	$scope.addGrocery = false;
        	$scope.createdOrder = false;
        }
        
        function acceptOffer(offer){
        	var all = [];
        	var acc = null;
        	for(var i = 0; i < vm.offers.length; i++){
        		if(vm.offers[i] != offer){
        			all.push(vm.offers[i]);
        		}else{
        			acc = vm.offers[i];
        		}
        	}
        	RestaurantService.AcceptOffer(all,vm.bid.name, vm.user.email)
        	.then(function(offer){
        		RestaurantService.DeactivateBid(vm.bid)
        		.then(function(offer){
        			loadAllBids();
        		});	
        	});
        	RestaurantService.SetAccepted(acc,vm.bid.name)
    		.then(function(offer){
    			loadAllOffers();
    		});
        }
        
        function addGrocery(){
        	$scope.addGrocery = true;
        	
        }
        
        function loadAllOffers(){
        	RestaurantService.GetAllOffers(vm.bid.name)
        	.then(function(offer){
        		vm.offers = offer.data;
        	});
        }
        
        function saveGrocery(){
        	RestaurantService.CreateWanted(vm.grocery, vm.bid.name)
        	.then(function (response) {
                $scope.addGrocery = false;
                vm.grocery = null;
            });
        }
        
        function r(){
        	AuthenticationService.SetCredentials(vm.cuser.email, vm.cuser.password, "supply");
        	$location.path('/supply');
        }
        
        (function initController() {
        	loadCurrentUser();
            loadAllUsers();
        })();

        function resManagerProfil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "ResManagerProfil");
        	$location.path('/ResManagerProfil');
        }
        
        function registerWorker(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "registerWorker");
        	$location.path('/registerWorker');
        }
        function manage(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "manage");
        	$location.path('/manage');
        }
        function registerSuplier(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "registerSuplier");
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
                    loadAllBids();
                });
        }
        
        function createBid(){
        	vm.bid = null;
        	$scope.createOrder = true;
        	$scope.createdOrder = true;
        }
        
        function saveBid(){
        	RestaurantService.CreateBid(vm.bid, vm.user.restaurant.name)
        	.then(function(bid){
        		FlashService.Success('Order added', false);
        		$scope.createOrder = false;
        		//vm.bid = null;
        		loadAllBids();
        	});
        }
        
        function loadAllBids(){
        	RestaurantService.GetAllBids(vm.user.restaurant.name)
        	.then(function(bid){
        		vm.orders = bid.data;
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