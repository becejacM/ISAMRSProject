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
        vm.activeObject = null;
        vm.table = null;
        
        vm.regions = [];
        vm.tables = [];
        vm.regs = [];
        
        vm.rest = null;
        vm.loadRestaurant = loadRestaurant;
        vm.loadRegions = loadRegions;
        
        vm.logout = logout;
        vm.manage = manage;
        vm.registerWorker = registerWorker;
        vm.registerSuplier = registerSuplier;
        vm.resManagerProfil = resManagerProfil;
        vm.setMenu = setMenu;
        vm.addRegion = addRegion;
        vm.calendarShift = calendarShift;
        
        vm.show = show;
        vm.addTable = addTable;
        vm.deleteTable = deleteTable;
        vm.table = null;
        
        vm.editMode = false;
        vm.enableButton = false;
        vm.editTable = editTable;
        vm.save = save;
        vm.created = null;
        vm.selectedMode = false;
        
        var canvas = new fabric.Canvas('c');
  	  	canvas.setDimensions({width:800, height:500});
  	  	canvas.border = 2;
  	  	fabric.Object.prototype.transparentCorners = false;
	  	//var rect = null;
        
        
        
        (function initController() {
        	
        	loadCurrentUser();
            loadAllUsers();
            
        })();
        
        function loadRestaurant(){
        	RestaurantService.GetRestaurant(vm.user.email)
            .then(function (response) {
                vm.rest = response.data;
                loadRegions();
            });
        }
        
        function setMenu(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "setMenuItem");
        	$location.path('/setMenuItem');
        }
        
        function addRegion(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "addRegion");
        	$location.path('/addRegion');
        }
        
        function calendarShift(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "calendarShift");
        	$location.path('/calendarShift');
        }
        
        function loadRegions(){
        	RestaurantService.GetRestaurantRegions(vm.rest.name)
        	.then(function(response){
        		vm.regs = response.data;
        		//alert(vm.regs.length);
        	});
        }
        
        function changeTable(){
        	if(canvas.getActiveObject() === null) 
        		alert("No table selected!");
        		return;
        	var o = canvas.getActiveObject();
        	
        	
        }
        
        function deleteTable(){
        	if(canvas.getActiveObject() === null) return;
        	
        	var o = canvas.getActiveObject();
        	
        	canvas.remove(o);
        	
        	var id = o.id;
        	alert(id);
        	
        	var table = new Object();
  	      	table.tableInRestaurantNo = id;
        	
        	RestaurantService.DeleteTable(table)
            .then(function (response) {
          	  	//alert(vm.table.height);
                	//FlashService.Success('Table successfully deleted', true);
                	//alert("asdfghj");
                	
                	
            });
        }
        
        function addTable(){

      	  vm.table = new Object();
      	  
      	  vm.table.width = 50.00;
      	  vm.table.height = 50.00;
      	  vm.table.datax = 400.00;
      	  vm.table.datay = 400.00;
      	  vm.table.numOfChairs = 4;
      	  
      	  //alert(vm.rest.name);
      	  
      	  for (var j=0; j < vm.regs.length; j++){
      		  var v = vm.regs[j];
      		  //alert(v.color + " " + v.name + " " + v.regionNo + " " + v.restaurant);
      	  }
      	  
	      //alert(vm.regs.length);
	      if(vm.regs.length != 0)
	    	  //alert(vm.regs[0]);
		      vm.table.region = vm.regs[0];
    	  
    	  var rect = new fabric.Rect({
  	  		width: 50, height: 50, originX: 'center', originY: 'center', fill: "#" + vm.table.region.color
  	  	  });
      	  var text = new fabric.Text('4', {fontSize: 20, originX: 'center', originY: 'center'});
      	  var group = new fabric.Group([rect, text], {left: 400, top: 400, angle: 0});
      	  canvas.add(group);

    	  	canvas.on({
    	  		'object:moving': onChange,
    	  		'object:scaling': onChange,
    	  		'object:rotating': onChange,
    	  		'mouse:up': onMouseUp,
    	  		'object:selected': onObjectSelected,
    	  		'after:selection:cleared': onObjectDeselected,
    	  		
    	  	});

      	  	
      	  RestaurantService.CreateTable(vm.table)
          .then(function (response) {
        	  	//alert(vm.table.height);
              	//FlashService.Success('Table successfully added', true);
              	alert(response.data.tableInRestaurantNo);
              	group.id = response.data.tableInRestaurantNo;
          });
      	  
		  	function onObjectDeselected(e){
			  vm.selectedMode = false;
		  }
		  
		  	function onObjectSelected(e){
      		  if(e.target === null) 
      			  vm.selectedMode = false;
      		  else {
      			  vm.selectedMode = true;
      		  }
      		  
      	  }
      	  	
      	function onMouseUp(e) {
      		 if(e.target === null) return;
	      	 console.log(e.target.get('left'));
	      	 console.log(e.target.get('top'));
	      	 console.log(e.target.get('id'));
	      	
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
   	              	//FlashService.Success('Table successfully updated', true);
   	              
   	          });
	      	
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
        	/*RestaurantService.GetRestaurant(vm.user.email)
            .then(function (response) {
            	
                vm.rest = response.data;*/
                //alert(vm.rest.name);
                RestaurantService.GetAllTables(vm.rest.name)
	            .then(function (response) {
	                vm.regions = response.data;
	                //alert(vm.regions.length);
	            	for (var j=0; j < vm.regions.length; j++) {
	            		//alert(vm.regions[j].deleted);
	            		if(vm.regions[j].deleted === "no"){
	            			var re = new fabric.Rect({width: vm.regions[j].width, height: vm.regions[j].height, angle: 0, originX: 'center', originY: 'center', fill: '#' + vm.regions[j].region.color});
	            			var text = new fabric.Text((vm.regions[j].numOfChairs).toString(), {fontSize: 20, originX: 'center', originY: 'center'});
        					var group = new fabric.Group([re, text], {id: vm.regions[j].tableInRestaurantNo, left: vm.regions[j].datax, top: vm.regions[j].datay, angle: 0});
        					canvas.add(group);
	            		}
	            			
	            	}
	                
	            //});
            });
        	
            
        	
        	
        	  
        	  canvas.on({
        	    'object:moving': onChange,
        	    'object:scaling': onChange,
        	    'object:rotating': onChange,
        	    'mouse:up': onMouseUp,
        	    'object:selected': onObjectSelected,
        	    'after:selection:cleared': onObjectDeselected,
        	    
        	  });
        	  
        	  function onObjectDeselected(e){
        		  vm.selectedMode = false;
        	  }
        	  
        	  function onObjectSelected(e){
        		  if(e.target === null) 
        			  vm.selectedMode = false;
        		  else {
        			  vm.selectedMode = true;
        		  }
        		  
        	  }
        	  
        	  function onMouseUp(e) {
        		 if(e.target === null) return;
     	      	 console.log(e.target.get('left'));
     	      	 console.log(e.target.get('top'));
     	      	 console.log(e.target.get('id'));
     	      	
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
    	              	//FlashService.Success('Table successfully updated', true);
    	              
    	          });
     	      
     	      	
     	      	 
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
                    loadRestaurant();
                });
        }
        
        function editTable() {
        	if(canvas.getActiveObject() === null) return;
            vm.editMode = true;
            vm.realUser = vm.user;
            vm.activeObject = canvas.getActiveObject();
            //alert(canvas.getActiveObject().get('id'));
            var id = canvas.getActiveObject().get('id');
            //t.tableInRestaurantNo = id;
            RestaurantService.FindTable(id)
	          .then(function (response) {
	        	  	vm.table = response.data;
	        	  	//alert('broj stola: ' + vm.table.tableInRestaurantNo);
	              
	          });
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
        	  
        	  
        	  
        	  RestaurantService.UpdateTable2(vm.table)
	          .then(function (response) {
	        	  
	        	  alert(canvas.getActiveObject().item(0).get('type'));
	        	  alert(canvas.getActiveObject().item(1).get('type'));
	        	  canvas.getActiveObject().item(0).set('fill', "#" + response.data.region.color);
	        	  //canvas.renderAll();
	        	  canvas.getActiveObject().item(1).setText((response.data.numOfChairs).toString());
	        	  canvas.renderAll();
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