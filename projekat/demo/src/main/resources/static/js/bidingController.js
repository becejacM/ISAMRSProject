(function () {
    'use strict';

    angular
        .module('app')
        .filter("formatDate", function(){
		   return function(input){
		      return  moment(input).format('DD MM YYYY, HH:MM'); 
		   }
        })
        .controller('bidingController', bidingController);

    bidingController.$inject = ['$location','RestaurantService','UserService', 'AuthenticationService', '$rootScope', 'FlashService','$scope'];
    function bidingController($location,RestaurantService,UserService,AuthenticationService, $rootScope, FlashService, $scope) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        vm.orders = [];
        vm.wanted = [];
        
        //vm.start = moment($scope.order.ord.start).format('DD MM YYYY, hh:mm'); 
        //vm.end = moment($scope.ord.end).format('DD MM YYYY, hh:mm'); 
        vm.logout = logout;
        vm.biding = biding;
        vm.allOffers = allOffers;
        vm.supplierProfil = supplierProfil;
        vm.seeOrder = seeOrder;
        vm.makeOffer = makeOffer;
        vm.saveOffer = saveOffer;
        $scope.groceryOrders = true;
        $scope.offer = false;
        vm.bbid = null;
        vm.offers = [];
        vm.myOffer = null;
        $scope.edit = false;
        vm.editOffer = editOffer;
        vm.edited = null;
        vm.edit = edit;
        
        
        (function initController() {
        	loadCurrentUser();
            loadAllUsers();
        })();
        
        
        function seeOrder(bid){
        	$scope.groceryOrders = false;
        	$scope.order = true;
        	//$scope.makeOff = true;
        	vm.bbid = bid;
        	vm.bbid.start = new Date(bid.start);
        	vm.bbid.end = new Date(bid.end);
        	loadAllWanted();
        	loadAllOffers();
        	$scope.listOffers = true;
        	if(vm.myOffer != []){
        		$scope.makeOff = false;
        	}else{
        		$scope.makeOff = true;
        	}
        }
        
        function makeOffer(){
        	$scope.offer = true;
        }
        
        function saveOffer(){
        	RestaurantService.CreateOffer(vm.offer, vm.bbid.name, vm.user.email)
        	.then(function(offer){
        		//vm.offers.push(offer.data);
        		$scope.offer = false;
        		$scope.offer = false;
        		vm.offers = [];
                vm.myOffer = null;
        		loadAllOffers();
        	});
        }
        
        function editOffer(offer){
        	var date = new Date();
        	if(date > vm.bbid.end || date < vm.bbid.start){
        		alert("The biding time is over");
        		$scope.edit = true;
        	}else{
        		vm.edited = offer;
        		$scope.editOffer = true;
        	}
        }
        
        function edit(){
        	RestaurantService.EditOffer(vm.edited, vm.bbid.name, vm.user.email)
    		.then(function(offer){
    			vm.offers = [];
    	        vm.myOffer = null;
        		loadAllOffers();
        		$scope.editOffer = false;
        	});
        }
        
        function loadAllOffers(){
        	RestaurantService.GetAllOffers(vm.bbid.name)
        	.then(function(offer){
        		for(var i = 0; i < offer.data.length; i++){
        			if(offer.data[i].suplier.email == vm.user.email){
        				vm.myOffer = offer.data[i];
        			}else{
        				vm.offers.push(offer.data[i]);
        			}
        		}
        		//vm.offers = offer.data;
        	});
        }

        function supplierProfil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "supplierProfil");
        	$location.path('/supplierProfil');
        }
        
        function biding(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "biding");
        	$location.path('/biding');
        }
        function allOffers(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "allOffers");
        	$location.path('/allOffers');
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
        
        function loadAllBids(){
        	RestaurantService.GetAllBidsSup(vm.user.email)
        	.then(function(bid){
        		vm.orders = bid.data;
        	});
        }
        
        function loadAllWanted(){
        	RestaurantService.GetAllWanted(vm.bbid.name)
        	.then(function(bid){
        		vm.wanted = bid.data;
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