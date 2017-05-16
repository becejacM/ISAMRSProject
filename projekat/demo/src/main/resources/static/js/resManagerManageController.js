(function () {
    'use strict';

    angular
        .module('app')
        .controller('ResManagerManageController', ResManagerManageController);

    ResManagerManageController.$inject = ['$location','UserService', 'RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService', '$scope'];
    function ResManagerManageController($location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService, $scope) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;
        vm.id = null;
        
        vm.regions = [];
        vm.tables = [];
        
        vm.rest = null;
        vm.loadRestaurant = loadRestaurant;
        //vm.loadTables = loadTables;
        
        vm.logout = logout;
        vm.registerWorker = registerWorker;
        vm.registerSuplier = registerSuplier;
        vm.resManagerProfil = resManagerProfil;
        
        vm.show = show;
        vm.addTable = addTable;
        vm.table = null;
        
        vm.editMode = false;
        vm.editTable = editTable;
        vm.save = save;
        vm.created = null;
        
        var canvas = new fabric.Canvas('c');
  	  	canvas.setDimensions({width:800, height:500});
  	  	canvas.border = 2;
  	  	fabric.Object.prototype.transparentCorners = false;
	  	//var rect = null;
        
        
        
        (function initController() {
        	
        	loadCurrentUser();
            loadAllUsers();
        })();
        
        function loadRestaurant() {
        	
	        	
        	
        	
        }
        
        /*function loadTables(){
        	loadRestaurant();
        	//alert(vm.rest.name);
        	
        }*/
        
        
        
        function addTable(){
        	
        	var rect = new fabric.Rect({
    	  		width: 50, height: 50, originX: 'center', originY: 'center',
    	  	    fill: '#0000ff'
    	  	});
        	var text = new fabric.Text('4', {fontSize: 20, originX: 'center', originY: 'center'});
        	var group = new fabric.Group([rect, text], {left: 400, top: 400, angle: 0});
        	canvas.add(group);
        	
        	
        	
      	  	canvas.on({
      	  		'object:moving': onChange,
      	  		'object:scaling': onChange,
      	  		'object:rotating': onChange,
      	  		'mouse:up': onObjectSelected,
      	  		
      	  	});
      	  	
      	  	
      	  vm.table = new Object();
      	  
      	  vm.table.width = 50.00;
      	  vm.table.height = 50.00;
      	  vm.table.datax = 400.00;
      	  vm.table.datay = 400.00;
      	  vm.table.numOfChairs = 4;
      	  	
      	  RestaurantService.CreateTable(vm.table)
          .then(function (response) {
        	  	//alert(vm.table.height);
              	FlashService.Success('Table successfully added', true);
              	//alert(response.data.tableInRestaurantNo);
              	group.id = response.data.tableInRestaurantNo;
          });
      	  
      	  

      	  	
      	function onObjectSelected(e) {
	      	 console.log(e.target.get('left'));
	      	 console.log(e.target.get('top'));
	      	console.log(e.target.get('id'));
	      	if(e.target != null){
	      		 var id = e.target.get('id');
	   	      	 var l = e.target.get('left');
	   	      	 var t = e.target.get('top');
	   	      	 var table = new Object();
	   	      	 table.datax = l;
	   	      	 table.datay = t;
	   	      	 table.tableInRestaurantNo = id;
	   	      	 RestaurantService.UpdateTable(table)
	   	          .then(function (response) {
	   	        	  	//alert(vm.table.height);
	   	              	FlashService.Success('Table successfully updated', true);
	   	              
	   	          });
	      	}
	      }
      	
      	  	
      	  function onChange(options) {
      	    options.target.setCoords();
      	    //var o = canvas.getActiveObject();
      	    //alert(canvas.getActiveObject().get('type'));
      	    
      	    
      	    canvas.forEachObject(function(obj) {
      	      if (obj === options.target) return;
      	      obj.setOpacity(options.target.intersectsWithObject(obj) ? 0.5 : 1);
      	    });
      	  }
        }
        
        function show() {
        	RestaurantService.GetByName(vm.user.restaurantName)
            .then(function (response) {
            	
                vm.rest = response.data;
                //alert(vm.rest.name);
                RestaurantService.GetAllTables(vm.rest.name)
	            .then(function (response) {
	                vm.regions = response.data;
	                //alert(vm.regions.length);
	            	for (var j=0; j < vm.regions.length; j++) {
	            			//alert(vm.regions[j].tableInRestaurantNo);
	            			var re = new fabric.Rect({width: vm.regions[j].width, height: vm.regions[j].height, angle: 0, originX: 'center', originY: 'center', fill: '#' + vm.regions[j].region.color});
	            			var text = new fabric.Text((vm.regions[j].numOfChairs).toString(), {fontSize: 20, originX: 'center', originY: 'center'});
        					var group = new fabric.Group([re, text], {id: vm.regions[j].tableInRestaurantNo, left: vm.regions[j].datax, top: vm.regions[j].datay, angle: 0});
        					canvas.add(group);
	            	}
	                
	            });
            });
        	
            
        	
        	
        	  
        	  canvas.on({
        	    'object:moving': onChange,
        	    'object:scaling': onChange,
        	    'object:rotating': onChange,
        	    'mouse:up': onObjectSelected,
        	    
        	  });
        	  
        	  function onObjectSelected(e) {
     	      	 console.log(e.target.get('left'));
     	      	 console.log(e.target.get('top'));
     	      	console.log(e.target.get('id'));
     	      	if(e.target != null){
     	      		var id = e.target.get('id');
        	      	 var l = e.target.get('left');
        	      	 var t = e.target.get('top');
        	      	 var table = new Object();
        	      	 table.datax = l;
        	      	 table.datay = t;
        	      	 table.tableInRestaurantNo = id;
        	      	 RestaurantService.UpdateTable(table)
        	          .then(function (response) {
        	        	  	//alert(vm.table.height);
        	              	FlashService.Success('Table successfully updated', true);
        	              
        	          });
     	      	}
     	      	
     	      	 
     	      	 //alert(e.target.get('type'));
     	      }

        	  function onChange(options) {
        	    options.target.setCoords();
        	    canvas.forEachObject(function(obj) {
        	      if (obj === options.target) return;
        	      obj.setOpacity(options.target.intersectsWithObject(obj) ? 0.5 : 1);
        	    });
        	  }
        	}

        function resManagerProfil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "ResManagerProfil");
        	$location.path('/ResManagerProfil');
        }
        
        function registerWorker(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "registerWorker");
        	$location.path('/registerWorker');
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
                });
        }
        
        function editTable() {
            vm.editMode = true;
            vm.realUser = vm.user;
        }
        
        function save() {
        	vm.editMode = false;
        	
        	
        	
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