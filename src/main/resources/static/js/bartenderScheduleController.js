(function () {
    'use strict';

    angular
        .module('app')
        .controller('BartenderScheduleController', BartenderScheduleController);

    BartenderScheduleController.$inject = ['calendarConfig','$location','UserService','RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService', '$scope'];
    function BartenderScheduleController(calendarConfig, $location, UserService,RestaurantService, AuthenticationService, $rootScope, FlashService, $scope) {
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
        vm.profile = profile;
        vm.schedule = schedule;
        vm.orders = orders;
        vm.tables = tables;
        
        vm.index = null;
        vm.loaded = [];
        vm.myEvent = [];
        vm.loadedColor = null;
        vm.allRegions = [];
        vm.region = null;
        $scope.setRegion = false;
        
        
        (function initController() {
        	//alert("bartender schedule");
        	loadCurrentUser();
            loadAllUsers();
        })();
        
        function profile(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderProfile", vm.user.token);
        	$location.path('/bartenderProfile');
        }
        
        function schedule(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderSchedule", vm.user.token);
        	$location.path('/bartenderSchedule');
        }
        function orders(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderOrders", vm.user.token);
        	$location.path('/bartenderOrders');
        }
        function tables(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "bartenderTables", vm.user.token);
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
                    //alert('hiihi');
                    //loadRestaurant();
                    loadAllWorkers();
                    loadAllRegions();
                    loadAllShifts();
                });
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
        	//alert(vm.user.role + " " + vm.user.restaurant.name);
            RestaurantService.GetShiftsByType(vm.user.role, vm.user.restaurant.name)
                .then(function (users) {
                	//alert(users.data.length);
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
            //alert.show('Edited', args.calendarEvent);
          }
        }, {
          label: '<i class=\'glyphicon glyphicon-remove\'></i>',
          onClick: function(args) {
            //alert.show('Deleted', args.calendarEvent);
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
          //alert(newColor.primary);
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