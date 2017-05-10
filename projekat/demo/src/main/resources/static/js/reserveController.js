(function () {
    'use strict';

    angular
        .module('app')
        .controller('GuestReserveController', GuestReserveController);

    GuestReserveController.$inject = ['$location','UserService','RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function GuestReserveController($location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.rest = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.allRests = [];

        
        vm.logout = logout;
        vm.profil = profil;
        vm.home = home;
        vm.friends = friends;
        vm.show = show;
        
        vm.allRestsMode = false;
        vm.showRests = showRests;
        
        vm.restMode = false;
        vm.showRest = showRest;
        
        
        (function initController() {
        	loadAllRests();
            loadCurrentUser();
            
           
        })();
        
        function showRests(){
        	vm.allRestsMode = true;
        	vm.restMode = false;
        }
        
        function showRest(){
        	vm.allRestsMode = false;
        	vm.restMode = true;
        }
        vm.v = null;
        function loadAllRests() {
        	RestaurantService.GetAllRests()
                .then(function (rests) {
                	
                    vm.allRests = rests.data;
                    
                });
        }
        
        function show(id) {
        	RestaurantService.GetById(id)
            .then(function (response) {
            	alert("fdfs");
                vm.rest = response.data;
            });
        	showRest();
      	  var canvas = new fabric.Canvas('c');
      	  canvas.setDimensions({width:800, height:500});
      	  canvas.border = 2;
      	  fabric.Object.prototype.transparentCorners = false;

      	  var rect1 = new fabric.Rect({
      	    width: 100, height: 50, left: 50, top: 50, angle: 30,
      	    fill: 'rgba(255,0,0,0.5)'
      	  });

      	  var rect2 = new fabric.Rect({
      	    width: 100, height: 100, left: 30, top: 250, angle: -10,
      	    fill: 'rgba(0,200,0,0.5)'
      	  });

      	  var rect3 = new fabric.Rect({
      	    width: 50, height: 100, left: 275, top: 350, angle: 45,
      	    stroke: '#eee', strokeWidth: 10,
      	    fill: 'rgba(0,0,200,0.5)'
      	  });

      	  var circle = new fabric.Circle({
      	    radius: 50, left: 275, top: 75, fill: '#aac'
      	  });

      	  var triangle = new fabric.Triangle({
      	    width: 100, height: 100, left: 50, top: 300, fill: '#cca'
      	  });

      	  canvas.add(rect1, rect2, rect3, circle, triangle);
      	  canvas.on({
      	    'object:moving': onChange,
      	    'object:scaling': onChange,
      	    'object:rotating': onChange,
      	  });

      	  function onChange(options) {
      	    options.target.setCoords();
      	    canvas.forEachObject(function(obj) {
      	      if (obj === options.target) return;
      	      obj.setOpacity(options.target.intersectsWithObject(obj) ? 0.5 : 1);
      	    });
      	  }
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
        
        
        
        
        

        
    }

})();