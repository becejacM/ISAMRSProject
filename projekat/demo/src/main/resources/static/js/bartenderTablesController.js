(function () {
    'use strict';

    angular
        .module('app')
        .controller('BartenderTablesController', BartenderTablesController);

    BartenderTablesController.$inject = ['$location','UserService', 'RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function BartenderTablesController($location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        vm.regions = [];
        
        vm.rest = null;
        
        vm.show = show;
        //vm.loadRegion = loadRegion;
        
        vm.logout = logout;
        vm.profile = profile;
        vm.schedule = schedule;
        vm.orders = orders;
        vm.tables = tables;
        
        var canvas = new fabric.Canvas('c');
  	  	canvas.setDimensions({width:800, height:500});
  	  	canvas.border = 2;
  	  	fabric.Object.prototype.transparentCorners = false;
        
        (function initController() {
        	//alert("bartender tables");
        	loadCurrentUser();
            loadAllUsers();
            //loadRegion();
        })();
        
        /*function loadRegion(){
        	RestaurantService.GetRegion(vm.user.region);
        	.then(function (response) {
                vm.region = response.data;
                alert(vm.region.name);
            });
        }*/
        
        function show() {
        	RestaurantService.GetByName(vm.user.restaurant)
            .then(function (response) {
                vm.rest = response.data;
                //alert(vm.rest.name);
                RestaurantService.GetAllTables(vm.rest.name)
	            .then(function (response) {
	                vm.regions = response.data;
	                //alert(vm.regions.length);
	            	for (var j=0; j < vm.regions.length; j++) {
	            			//alert(vm.regions[j].numOfChairs);
	            			var re = new fabric.Rect({width: vm.regions[j].width, height: vm.regions[j].height, angle: 0, originX: 'center', originY: 'center', fill: '#' + vm.regions[j].region.color});
	            			var text = new fabric.Text((vm.regions[j].numOfChairs).toString(), {fontSize: 20, originX: 'center', originY: 'center'});
        					var group = new fabric.Group([re, text], {left: vm.regions[j].datax, top: vm.regions[j].datay, angle: 0});
        					canvas.add(group);
	            	}
	                
	            });
            });

        	  canvas.on({
        	    'object:moving': onChange,
        	    'object:scaling': onChange,
        	    'object:rotating': onChange,
        	    
        	  });

        	  function onChange(options) {
        	    //options.target.setCoords();
        	    canvas.forEachObject(function(obj) {
        	      //if (obj === options.target) return;
        	      //obj.setOpacity(options.target.intersectsWithObject(obj) ? 0.5 : 1);
        	      obj.lockMovementX = obj.lockMovementY = obj.lockScalingX = obj.lockScalingY = obj.lockRotation = true;
        	    });
        	  }
        	}
        
        function profile(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderProfile");
        	$location.path('/bartenderProfile');
        }
        
        function schedule(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderSchedule");
        	$location.path('/bartenderSchedule');
        }
        function orders(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderOrders");
        	$location.path('/bartenderOrders');
        }
        function tables(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderTables");
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