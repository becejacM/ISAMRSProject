(function () {
    'use strict';

    angular
        .module('app')
        .controller('calendarShiftController', calendarShiftController);

    calendarShiftController.$inject = ['calendarConfig','$location','UserService', 'RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService', '$scope'];
    function calendarShiftController(calendarConfig,$location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService, $scope) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.events = [];
        vm.allWorkers = [];
        vm.deleteUser = deleteUser;
        vm.id = null;
        vm.savedShift = null;
        vm.worker = null;
        $scope.isClicked = false;
        $scope.isClickedColor = false;
        $scope.isClickedWorker = false;
        vm.isEdited = false;
        //vm.color = null;
        vm.events = [];
        vm.myEvents = [];
        vm.temp = [];
        vm.colorP = null;

        vm.logout = logout;
        vm.manage = manage;
        vm.registerWorker = registerWorker;
        vm.registerSuplier = registerSuplier;
        vm.resManagerProfil = resManagerProfil;
        vm.save = save;
        vm.saveEvent = saveEvent;
        vm.addEvent = addEvent;
        vm.editEvent = editEvent;
        vm.edit = edit;
        vm.index = null;
        vm.changed = changed;
        vm.changedColor = changedColor;
        vm.loaded = [];
        vm.myEvent = [];
        vm.loadedColor = null;
        vm.deleteShift = deleteShift;
        vm.allRegions = [];
        vm.region = null;
        vm.asignRegion = asignRegion;
        vm.asign = asign;
        $scope.setRegion = false;
        
        

        (function initController() {
        	
        	loadCurrentUser();
            loadAllUsers();
            //loadAllWorkers();
            loadAllShifts();
            //loadAllRegions();
        })();
        
        function resManagerProfil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "ResManagerProfil", vm.user.token);
        	$location.path('/ResManagerProfil');
        }
        
        function registerWorker(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "registerWorker", vm.user.token);
        	$location.path('/registerWorker');
        }
        
        function manage(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "manage", vm.user.token);
        	$location.path('/manage');
        }
        
        function registerSuplier(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "registerSuplier", vm.user.token);
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
                    //loadRestaurant();
                    loadAllWorkers();
                    loadAllRegions();
                });
        }
        
        function asign(){
        	if(vm.worker.role == 'cook' || vm.worker.role=='bartender'){
        		alert("You can only asign regions to waiters");
        		$scope.setRegion = false;
        	}else{
        		$scope.setRegion = true;
        	}
        }
        
        function asignRegion(){
        	RestaurantService.AsignRegion(vm.worker, vm.region.regionNo)
            .then(function (response) {
            	FlashService.Success('Region asigned', false);
            	$scope.setRegion = false;
            });
        }
        
        function deleteShift(nevent){
        	var colorr = {primary: vm.colorP, secondary: vm.colorP};
        	var counter = vm.myEvent.indexOf(nevent);
        	alert(counter);
        	var event = vm.myEvent[counter];
        	event.color = colorr.primary.toString();
        	event.title = vm.worker.email;
        	var col = colorr.primary.toString().substring(1);
        	RestaurantService.DeleteShift(event,vm.worker.email,col)
            .then(function (response) {
            	FlashService.Success('Shift deleted', false);
            	$scope.editMode = false;
            	vm.myEvents = [];
            	vm.myEvent = [];
            	vm.loaded = [];
            	loadAllShifts();
            });
        	
        }
        
        function editEvent(){
        	vm.temp = vm.myEvents;
        	$scope.editMode = true;
        	alert("boja "+vm.myEvent[0].color.toString());
        	vm.loadedColor = '#'+ vm.myEvent[0].color.toString();
        	//vm.myEvents = vm.events;
        	for(var i = 0; i < vm.events.length; i++){
        		//vm.myEvents[i].color = { primary: vm.myEvents[i].color.substring(1), secondary : vm.myEvents[i].color.substring(1)};
        		if(vm.events[i].title == vm.worker.email){
        			vm.myEvents.push(vm.events[i]);
        		}
        	}
        	$scope.isClickedWorker = true;
        	
        }
        
        function done(){
        	vm.myEvents = vm.temp;
        }
        
        function edit(nevent, ncolor){
        	var colorr = {primary: vm.colorP, secondary: vm.colorP};
        	var counter = vm.myEvent.indexOf(nevent);
        	alert(counter);
        	var event = vm.myEvent[counter];
        	event.color = colorr.primary.toString();
        	event.title = vm.worker.email;
        	var col = colorr.primary.toString().substring(1);
        	RestaurantService.EditShift(event,vm.worker.email,col)
            .then(function (response) {
            	FlashService.Success('Shift edited', false);
            	$scope.editMode = false;
            	vm.myEvents = [];
            	vm.myEvent = [];
            	vm.loaded = [];
            	loadAllShifts();
            });
        }
        
        function saveEvent(cevent,color) {
        	//alert(color);
        	var col = color.primary.substring(1);
        	//alert(cevent.startsAt);
            RestaurantService.CreateShifts(cevent, cevent.title, col)
                .then(function (response) {
                	FlashService.Success('Shift added', false);
                	//$scope.isClicked = true;
                	var col= { primary: '#'+response.data.color, secondary : '#'+response.data.color};
                	vm.events.push({
                        title: response.data.employee.email,
                        startsAt: response.data.startsAt,
                        endsAt: response.data.endsAt,
                        color: col,
                        draggable: true,
                        resizable: true
                      });
                	vm.myEvents = [];
                	vm.myEvent = [];
                	vm.loaded = [];
                	loadAllShifts();
                	vm.worker = null;
                	$scope.saveMode = false;
                });
        }
        
        function save(event,color){
        	//alert(calendarConfig.colorTypes.important.primary);
        	var colorr = {primary: color, secondary: color};
        	var date = event.startsAt;
        	var strDate = date.toString();
        	//alert(typeof(strDate));
        	event.color = colorr;
        	vm.savedShift = event;
        	vm.savedShift.color = colorr.primary.toString();
        	//alert(vm.savedShift.startsAt);
        	saveEvent(vm.savedShift, colorr);
        }
        
        function changed(){
        	//vm.worker = item;
        	//alert(vm.worker.email);
        	$scope.isClickedWorker = false;
        	vm.myEvent = [];
        	vm.count = [];
            for(var i = 0; i < vm.events.length; i++){
            	if(vm.worker.email == vm.events[i].title){
            		vm.colorP = vm.events[i].color.primary;
            		$scope.isClickedColor = true;
            		break;
            	}else{
            		$scope.isClickedColor = false;
            	}
            }
            for(var j = 0; j < vm.loaded.length; j++){
            	if(vm.worker.email == vm.loaded[j].employee.email){
            		//alert("Usao "+vm.loaded[j].startsAt);
            		vm.myEvent.push(vm.loaded[j]);
            	}
            }
        }
        
        function changedColor(){
        	for(var i = 0; i < vm.loaded.length; i++){
        		//alert(vm.colorP + " uporedi " + vm.loaded[i].color)
        		if(vm.colorP == ('#'+vm.loaded[i].color)){
        			alert("The color you have chosen has already been set to another emloyee");
        			$scope.colorCheck = true;
        			break;
        		}
        		$scope.colorCheck = false;
        	}
        }
        
        function loadAllUsers() {
            UserService.GetAll()
                .then(function (users) {
                    vm.allUsers = users;
                });
        }
        
        function loadAllWorkers() {
            UserService.GetAllWorkers(vm.user.restaurant.name)
                .then(function (users) {
                    vm.allWorkers = users.data;
                });
        }
        
        function loadAllRegions() {
            RestaurantService.GetRestaurantRegions(vm.user.restaurant.name)
                .then(function (users) {
                    vm.allRegions = users.data;
                });
        }
        
        function loadAllShifts() {
            RestaurantService.GetAllShifts()
                .then(function (users) {
                	alert(users.data.length);
                	vm.events = [];
                	for(var i = 0; i < users.data.length; i++){
                		vm.loaded.push(users.data[i]);
                		var col= { primary: '#'+users.data[i].color, secondary : '#'+users.data[i].color};
                		vm.events.push({
                            title: users.data[i].employee.email,
                            startsAt: users.data[i].startsAt,
                            endsAt: users.data[i].endsAt,
                            color: col,
                            draggable: true,
                            resizable: true
                          });
                	}
                    //vm.allWorkers = users.data;
                });
        }

        function deleteUser(id) {
            UserService.Delete(id)
            .then(function () {
                loadAllUsers();
            });
        }
        
        vm.calendarView = 'month';
        vm.viewDate = new Date();
        var actions = [{
          label: '<i class=\'glyphicon glyphicon-pencil\'></i>',
          onClick: function(args) {
            alert.show('Edited', args.calendarEvent);
          }
        }, {
          label: '<i class=\'glyphicon glyphicon-remove\'></i>',
          onClick: function(args) {
            alert.show('Deleted', args.calendarEvent);
          }
        }];
        

        vm.cellIsOpen = true;
        //calendarConfig.colorTypes.important = '#ff0000';
        //calendarConfig.colorTypes.important.primary = '#00ff00'
        var newColor = { primary: '#00ff00', secondary: '#0000ff'};
        


        function addEvent() {
        $scope.saveMode = true;
        $scope.isClickedWorker = false;
        for(var i = 0; i < vm.events.length; i++){
        	if(vm.worker.email == vm.events[i].title){
        		vm.colorP = vm.events[i].color.primary;
        		$scope.isClickedColor = true;
        		break;
        	}else{
        		$scope.isClickedColor = false;
        	}
        }
        var newColor = {primary: vm.colorP, secondary: vm.colorP};
          vm.myEvents.push({
              title: vm.worker.email,
              startsAt: moment().startOf('day').toDate(),
              endsAt: moment().endOf('day').toDate(),
              color: newColor,
              draggable: true,
              resizable: true
            });
          alert(newColor.primary);
          //vm.events.push(vm.myEvents[vm.myEvents.length - 1]);
        };

        vm.toggle = function($event, field, event) {
          $event.preventDefault();
          $event.stopPropagation();
          event[field] = !event[field];
        };

        vm.timespanClicked = function(date, cell) {

          if (vm.calendarView === 'month') {
            if ((vm.cellIsOpen && moment(date).startOf('day').isSame(moment(vm.viewDate).startOf('day'))) || cell.events.length === 0 || !cell.inMonth) {
              vm.cellIsOpen = false;
            } else {
              vm.cellIsOpen = true;
              vm.viewDate = date;
            }
          } else if (vm.calendarView === 'year') {
            if ((vm.cellIsOpen && moment(date).startOf('month').isSame(moment(vm.viewDate).startOf('month'))) || cell.events.length === 0) {
              vm.cellIsOpen = false;
            } else {
              vm.cellIsOpen = true;
              vm.viewDate = date;
            }
          }

        };

    }

})();