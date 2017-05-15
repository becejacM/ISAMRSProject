(function () {
    'use strict';

    angular
        .module('app')
        .controller('GuestReserveController', GuestReserveController);

    GuestReserveController.$inject = ['$location','UserService','RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService','$scope'];
    function GuestReserveController($location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService,$scope) {
        var vm = this;

        vm.user = null;
        vm.rest = null;
        vm.step1par = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.allRests = [];
        vm.regions = [];
        vm.tables = [];

        
        vm.logout = logout;
        vm.profil = profil;
        vm.home = home;
        vm.friends = friends;
        
        vm.show = show;
        
        vm.allRestsMode = false;
        vm.showRests = showRests;
        
        vm.restModeStep1 = false;
        vm.showRestStep1 = showRestStep1;
        
        vm.restModeStep2 = false;
        vm.showRestStep2 = showRestStep2;
        
        vm.step1 = step1;
        vm.step2 = step2;
        
        vm.find = find
        vm.parametar = null;
        
        (function initController() {
        	loadAllRests();
            loadCurrentUser();
            
           
        })();
        
        
        function showRests(){
        	loadAllRests();
        	vm.allRestsMode = true;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = false;
        }
        
        function showRestStep1(){
        	vm.allRestsMode = false;
        	vm.restModeStep1 = true;
        	vm.restModeStep2 = false;
        }
        
        function showRestStep2(){
        	vm.allRestsMode = false;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = true;
        }
        vm.v = null;
        function loadAllRests() {
        	RestaurantService.GetAllRests()
                .then(function (rests) {
                	
                    vm.allRests = rests.data;
                    
                });
        }
        
        function step1(id){
        	RestaurantService.GetById(id)
            .then(function (response) {
                vm.rest = response.data;
                $scope.time = ["00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00",
           "10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"];
                $scope.duration = ["00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00",
                    "10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"];
            });
        	RestaurantService.GetAllTables(id)
            .then(function (response) {
                vm.regions = response.data;
                show();
            });
        	
        	showRestStep1();
        	
        }
        
        function show(){
        	var canvas = new fabric.Canvas('c');
        	canvas.setDimensions({width:800, height:500});
        	canvas.border = 2;
        	for (var j=0; j < vm.regions.length; j++) {
        			canvas.add(new fabric.Rect({
        				width: vm.regions[j].width, height: vm.regions[j].height, left: vm.regions[j].datax, top: vm.regions[j].datay, angle: 0,fill: '#'+vm.regions[j].region.color}));
        		}
        }
        
        function showA(){
        	var canvas = new fabric.Canvas('c1');
        	canvas.setDimensions({width:800, height:500});
        	canvas.border = 2;
        	for (var j=0; j < vm.regions.length; j++) {
        			canvas.add(new fabric.Rect({
        				width: vm.regions[j].width, height: vm.regions[j].height, left: vm.regions[j].datax, top: vm.regions[j].datay, angle: 0,fill: '#'+vm.regions[j].region.color}));
        		}
        }
        function step2() {
        	RestaurantService.GetAllAvailableTables(vm.step1par.datum,vm.step1par.vreme,vm.step1par.trajanje, vm.rest.name)
            .then(function (response) {
                vm.regions = response.data;
                showA();
            });
        	showRestStep2();
        	
      	  
      	 
      	}

        function profil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "guestProfil");
        	$location.path('/guestProfil');
        }
        
        function home(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "home");

        	$location.path('/home');
        }
        function friends(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "myFriends");
        	$location.path('/myFriends');
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
        
        
        function find() {
        	RestaurantService.find(vm.parametar.par, vm.parametar.par2)
            .then(function (response) {
            	vm.allRests = response.data;
            });
        }
        
        
        
        

        
    }

})();