(function () {
    'use strict';

    angular
        .module('app')
        .controller('GuestReserveController', GuestReserveController);

    var geocoder = null;
    var mapa = null;
    angular.module('app').directive("initMap", function($window) {
        return {
            restrict: "E",
            template: '<div style="height: 400px;">XXX</div>',
            link: function(scope,elem,attrs) {
                var jElem = elem.find('div')[0];
                console.log(jElem);
                mapa = new google.maps.Map(jElem, {
                    center: {lat: -34.397, lng: 150.644},
                    zoom: 16
                });
                geocoder = new google.maps.Geocoder();
                var address = "Novi Sad, Srbija";
                geocoder.geocode({'address': address}, function(results, status) {
        			
        		    if (status === google.maps.GeocoderStatus.OK) {
        		    	//alert("ok");
        		    	mapa.setCenter(results[0].geometry.location);
        		      var marker = new google.maps.Marker({
        		        map: resultsMap,
        		        position: results[0].geometry.location
        		      });
        		    } else {
        		      alert('Geocode was not successful for the following reason: ' + status);
        		    }
        		  });
                console.log(jElem);
            }
        };
    });
    GuestReserveController.$inject = ['$window','$document','$location','UserService','RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService','$scope'];
    function GuestReserveController($window,$document,$location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService,$scope) {
        var vm = this;

        vm.user = null;
        vm.rest = null;
        vm.step1par = null;
        vm.parametar = null;
        vm.parametar2=null;
        vm.currReservation = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.allRests = [];
        vm.AllReservations = [];
        vm.AllInvitations = [];
        vm.AllAcceptInvitations = [];
        vm.AllFinished=[];
        vm.regions = [];
        vm.tables = [];
        vm.AllMI = [];
        vm.orderItems = [];
        vm.listOfTables=[];
        
        vm.order = null;
        
        vm.logout = logout;
        vm.profil = profil;
        vm.home = home;
        vm.friends = friends;
        
        vm.show = show;
        
        vm.allReservationsMode = false;
        vm.showReservations = showReservations;
        
        vm.allInvitationsMode = false;
        vm.showInvitations = showInvitations;
        
        vm.allFinishedMode = false;
        vm.showFinished = showFinished;
        
        vm.allRestsMode = false;
        vm.showRests = showRests;
        
        vm.restModeStep1 = false;
        vm.showRestStep1 = showRestStep1;
        
        vm.restModeStep2 = false;
        vm.showRestStep2 = showRestStep2;
        
        vm.restModeStep3 = false;
        vm.showRestStep3 = showRestStep3;
        
        vm.restModeStep4 = false;
        vm.showRestStep4 = showRestStep4;
        
        vm.callFMode=false;
        vm.addMealMode=false;

        vm.noMode = noMode;
        
        vm.step1 = step1;
        vm.step2 = step2;
        vm.step3 = step3;
        vm.step4 = step4;
        
        vm.find = find;
        vm.findUsers = findUsers;
        vm.cancel = cancel;
        vm.parametar = null;
        vm.c = "";
        vm.time=[];
        vm.time2=[];
        
        vm.tid = "";
        
        vm.call = call;
        vm.accept = accept;
        vm.reject = reject;
        vm.loadAllMI = loadAllMI;
        vm.loadAllFinished = loadAllFinished;
        vm.addOrder = addOrder;
        vm.removeOrder = removeOrder;
        vm.finish=finish;
        
        vm.callF = callF;
        vm.showFinished = showFinished;
        vm.gradeMode = false;
        vm.grade = grade;
        vm.currentR = null;
        vm.currentGrade = null;
        vm.addGrade = addGrade;
        vm.ifGraded = ifGraded;
        
        vm.buttons = true;
        (function initController() {
        	loadAllRests();
            loadCurrentUser();
           
            
        })();
        
        function showRests(){
        	FlashService.clearFlashMessageP();
        	loadAllRests();
        	vm.allRestsMode = true;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = false;
        	vm.restModeStep3 = false;
        	vm.restModeStep4 = false;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
        	vm.allFinishedMode = false;
            vm.callFMode=false;
            vm.addMealMode=false;
            vm.gradeMode = false;
        }
        
        function noMode(){
        	vm.allRestsMode = false;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = false;
        	vm.restModeStep3 = false;
        	vm.restModeStep4 = false;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
        	vm.allFinishedMode = false;
            vm.callFMode=false;
            vm.addMealMode=false;
            vm.gradeMode = false;
        }
        
        function showInvitations(){
        	FlashService.clearFlashMessageP();
        	RestaurantService.showInvitations(vm.user.email)
            .then(function (rests) {
            	if(rests.status===204){
            		FlashService.Error('You don\'t have any invitations.',false);
                	noMode();
            	}
            	else{
            		vm.allInvitations = rests.data;
                    vm.allRestsMode = false;
                	vm.restModeStep1 = false;
                	vm.restModeStep2 = false;
                	vm.restModeStep3 = false;
                	vm.restModeStep4 = false;
                	vm.allReservationsMode = false;
                	vm.allInvitationsMode = true;
                	vm.allFinishedMode = false;
                    vm.callFMode=false;
                    vm.addMealMode=false;
                    vm.gradeMode = false;
            	}
                
            });
        }
        
        function showFinished(){
        	FlashService.clearFlashMessageP();
        	vm.allRestsMode = false;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = false;
        	vm.restModeStep3 = false;
        	vm.restModeStep4 = false;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
        	vm.allFinishedMode = true;
            vm.callFMode=false;
            vm.addMealMode=false;
            vm.gradeMode = false;
        }
        
        vm.finished=finished;
        function finished(){
        	loadAllFinished();
        }
        
        function grade(r){
        	vm.gradeMode = true;
        	vm.currentR = r;
        	vm.currentGrade = new Object();
        	//vm.currentGrade.reservation = r;
        	vm.currentGrade.restaurant = r.restaurant;
        	alert(vm.currentGrade.restaurant.name);
        }
        
        function ifGraded(r){
        	if(r.grade === null){
        		return true;
        	}
        	return false;
        }
        
        /*function addGrade(){
        	alert('ocena ' + vm.currentGrade.generalGrade);
        	RestaurantService.AddGrade(vm.currentGrade)
        	.then(function (response) {
        		alert(response.data.generalGrade);
        		updateRes();
            });
        }*/
        
        function addGrade(){
        	//alert('update ressss');
        	vm.currentR.grade = vm.currentGrade;
        	//alert('ssss ' + vm.currentR.grade.generalGrade);
        	RestaurantService.AddGrade(vm.currentR)
        	.then(function (response) {
                vm.gradeMode = false;
                
            });
        }
        
        function showRestStep1(){
        	vm.allRestsMode = false;
        	vm.restModeStep1 = true;
        	vm.restModeStep2 = false;
        	vm.restModeStep3 = false;
        	vm.restModeStep4 = false;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
        	vm.allFinishedMode = false;
            vm.callFMode=false;
            vm.addMealMode=false;
        }
        
        function showRestStep2(){
        	vm.allRestsMode = false;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = true;
        	vm.restModeStep3 = false;
        	vm.restModeStep4 = false;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
        	vm.allFinishedMode = false;
            vm.callFMode=false;
            vm.addMealMode=false;
        }
        function showRestStep3(){
        	vm.allRestsMode = false;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = false;
        	vm.restModeStep3 = true;
        	vm.restModeStep4 = false;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
        	vm.allFinishedMode = false;
            vm.callFMode=false;
            vm.addMealMode=false;
        }
        
        function showRestStep4(){
        	FlashService.clearFlashMessageP();
        	vm.allRestsMode = false;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = false;
        	vm.restModeStep3 = false;
        	vm.restModeStep4 = true;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
        	vm.allFinishedMode = false;
            vm.callFMode=false;
            vm.addMealMode=false;
        }
        
        vm.v = null;
        function loadAllRests() {
        	RestaurantService.GetAllRests()
                .then(function (rests) {
                	
                    vm.allRests = rests.data;
                    
                });
        	
        }
        
        function accept(id){
        	RestaurantService.accept(id)
            .then(function (response) {
            	showInvitations();
            });
        	
        }
        
        function reject(id){
        	RestaurantService.reject(id)
            .then(function (response) {
            	showInvitations();
            });
        	
        }
        function showReservations(){
        	FlashService.clearFlashMessageP();
        	RestaurantService.getRestByUserEmail(vm.user.email)
            .then(function (rests) {
            	RestaurantService.GetAllAcceptRest(vm.user.id)
                .then(function (res) {
                	//alert("mmm");
                    vm.allAcceptInvitations = res.data;
                    /*angular.forEach(vm.allAcceptInvitations, function(value, key){
                 	     alert(key + ': ' + value);
                 	     angular.forEach(value, function(value, key){
                     	     alert(key + ': ' + value);
                     	});
                 	});*/
                    
                });
                vm.allReservations = rests.data;
                loadAllRests();
            	vm.allRestsMode = false;
            	vm.restModeStep1 = false;
            	vm.restModeStep2 = false;
            	vm.restModeStep3 = false;
                vm.allReservationsMode = true;
                vm.allInvitationsMode=false;
                vm.allFinishedMode=false;
                vm.callFMode=false;
                vm.addMealMode=false;
            });
        	
        	
        }
        
        var map;
        var marker;
        //vm.initMap = initMap;
        vm.showOnMap = showOnMap;
        
        function showOnMap(id){
        	
        	FlashService.clearFlashMessageP();
        	RestaurantService.GetById(id)
            .then(function (response) {
                vm.rest = response.data; 
                
                //initMap();
                geocodeAddress();
            });        	
        }
        /*function initMap() {
        	alert("usao");
    	     map = new google.maps.Map(document.getElementById('map'), {
    	     center: {lat: -34.397, lng: 150.644},
    	     zoom: 16
    	     });
    	     var geocoder = new google.maps.Geocoder();
    	     geocodeAddress(geocoder, map);

        }*/
        function geocodeAddress() {
        	//if(user!=null && user!=""){
        	var address = vm.rest.address +", Srbija";
        	geocoder.geocode({'address': address}, function(results, status) {
    			
    		    if (status === google.maps.GeocoderStatus.OK) {
    		    	mapa.setCenter(results[0].geometry.location);
    		      var marker = new google.maps.Marker({
    		        map: resultsMap,
    		        position: results[0].geometry.location
    		      });
    		    } else {
    		      alert('Geocode was not successful for the following reason: ' + status);
    		    }
    		  });
        	//}
    }
        
        function step1(id){
        	FlashService.clearFlashMessageP();
        	vm.buttons=false;

        	RestaurantService.GetById(id)
            .then(function (response) {
                vm.rest = response.data;
                geocodeAddress();
                RestaurantService.GetHours(id)
                .then(function (response) {
                	vm.time = response.data;
                	$scope.time = vm.time;
                	$scope.duration = vm.time;
                });
                
                /*$scope.time = ["00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00",
           "10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"];
                $scope.duration = ["00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00",
                    "10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"];*/
            });
        	RestaurantService.GetAllTables(id)
            .then(function (response) {
                vm.regions = response.data;
                show();
            });
        	
        	showRestStep1();
        	
        }
        
        function show(){
        	FlashService.clearFlashMessageP();
        	var canvas = new fabric.Canvas('c');
        	canvas.setDimensions({width:800, height:500});
        	canvas.border = 2;
        	for (var j=0; j < vm.regions.length; j++) {

        		if(vm.regions[j].deleted === "no"){
        			canvas.add(new fabric.Rect({
        				width: vm.regions[j].width, height: vm.regions[j].height, left: vm.regions[j].datax, top: vm.regions[j].datay, angle: 0,fill: '#'+vm.regions[j].region.color}));

        			var t = (vm.regions[j].numOfChairs).toString();
        			var text = new fabric.Text(t, {
        			  fontSize: 15,
        			  originX: 'center',
        			  originY: 'center'
        			});
        			var rect = new fabric.Rect({
        				width: vm.regions[j].width, 
        				height: vm.regions[j].height, 
        				originX: 'center',
        				originY: 'center',
        				angle: 0,
        				fill: '#'+vm.regions[j].region.color});
        			var group = new fabric.Group([ rect, text ], {
        				
        				left: vm.regions[j].datax, 
        				top: vm.regions[j].datay, 
        				angle: 0
        			});
        			canvas.add(group);
        		}
        	}
        }
        
        function showA(){
        	FlashService.clearFlashMessageP();
        	
        	var canvas = new fabric.Canvas('c1');
        	canvas.setDimensions({width:800, height:500});
        	canvas.border = 2;
        	for (var j=0; j < vm.regions.length; j++) {
        		var t = (vm.regions[j].numOfChairs).toString();
    			var text = new fabric.Text(t, {
    			  fontSize: 15,
    			  originX: 'center',
    			  originY: 'center'
    			});
    			var rect = new fabric.Rect({
    				width: vm.regions[j].width, 
    				height: vm.regions[j].height, 
    				originX: 'center',
    				originY: 'center',
    				angle: 0,
    				fill: '#'+vm.regions[j].region.color});
    			var group = new fabric.Group([ rect, text ], {
    				id : vm.regions[j].tableInRestaurantNo,
    				left: vm.regions[j].datax, 
    				top: vm.regions[j].datay, 
    				angle: 0
    			});
    			canvas.add(group);
        	}
        	canvas.on({
        	    'object:selected': onObjectSelected
        	    
        	  });
        	  
        	  
        	  function onObjectSelected(e){
        		  if(e.target === null) 
        			  alert("no");
        		  else {
        			  var id = e.target.get('id');
	            		var o = canvas.getActiveObject();
        			  RestaurantService.Reserve(vm.c,vm.step1par.vreme,vm.step1par.trajanje, vm.rest.name, id, vm.user.email)
      	            .then(function (response) {

      	            	if(response.status===204){
      	                	FlashService.Error('You can\'t create this reservation.',false);

      	                	//step22();
      	            	}
      	            	else{
      	            		vm.currReservation = response.data;
      	            		canvas.remove(o);
          	            	//alert(vm.currReservation.reservationId);
            			  //step22();

      	            	}
      	            	

      	            });
        		  }
        		  
        	  }
        }
        
        function step2() {
        	var d=new Date(document.getElementById("dt").value);
        	var dt=d.getDate();
        	var mn=d.getMonth();
        	mn++;
        	var yy=d.getFullYear();
        	vm.c =dt+"."+mn+"."+yy;
        	RestaurantService.GetAllAvailableTables(vm.c,vm.step1par.vreme,vm.step1par.trajanje, vm.rest.name)
            .then(function (response) {
                vm.regions = response.data;
            	if(vm.regions.length===0){
                	FlashService.Error('This restaurant don\'t have any tables for this date.',false);
                	//showRestStep2();
                    //showA();
            	}
            	else{
                    showRestStep2();
                    showA();

            	}
            });
        	
        	
      	  
      	 
      	}
        vm.step22=step22;
        function step22() {
        	var d=new Date(document.getElementById("dt").value);
        	var dt=d.getDate();
        	var mn=d.getMonth();
        	mn++;
        	var yy=d.getFullYear();
        	vm.c =dt+"."+mn+"."+yy;
        	RestaurantService.GetAllAvailableTables(vm.c,vm.step1par.vreme,vm.step1par.trajanje, vm.rest.name)
            .then(function (response) {
                vm.regions = response.data;
                showRestStep2();
                showA();
            	if(vm.regions.length===0){
                	showRestStep2();
                    showA();
            	}
            	else{
                    showRestStep2();
                    showA();

            	}
            });
        	
        	
      	  
      	 
      	}
        function step3(){
        	FlashService.Success('Reservation successfuly created.',false);
              geocodeAddress();
              loadAllRests();
          	vm.allReservationsMode = false;
          	vm.allRestsMode = false;
          	vm.restModeStep1 = false;
          	vm.restModeStep2 = false;
          	vm.restModeStep3 = true;
        }
        
        function callF(r){
        	vm.currReservation = r;
        	
          	noMode();
            vm.callFMode=true;
        }
        vm.addM = addM;
        function addM(r){

        	vm.currReservation = r;
        	RestaurantService.GetById(vm.currReservation.nameRest)
            .then(function (response) {
                vm.rest = response.data;
                geocodeAddress();
            });
        	loadAllMI();
          	noMode();
            vm.addMealMode=true;
            
        }
        function step4(){
        	loadAllMI();
        	showRestStep4();
        }
        
        function loadAllMI(){
        	RestaurantService.LoadAllMeals(vm.rest.restaurantId)
            .then(function (response) {
            	if(response.status===204){
            		finish();
                	FlashService.Error('This restaurant don\'t have any meals. Reservation successfuly finished.',false);
            	}
            	else{
                	vm.allMI = response.data;
            	}
            	
            });
        }

        function addOrder(meal){
        	var idx = vm.allMI.indexOf(meal);
            var idx2 = vm.orderItems.indexOf(meal);
            
            if (idx2 === -1) {
                vm.orderItems.push(meal);
                meal.count=1;
            }
            else {
                meal.count+=1;
            }
        }
        
        function removeOrder(meal){
        	var idx = vm.allMI.indexOf(meal);
            var idx2 = vm.orderItems.indexOf(meal);
            
            if (idx2 === -1) {
            }
            else {
                vm.orderItems.splice(idx2, 1);
                meal.count=0;
            }
        }
        function finish(){
        	vm.order = createOrder();
        	RestaurantService.Order(vm.rest.restaurantId, vm.order)
            .then(function (response) {
            	
            	vm.allRestsMode = false;
            	vm.restModeStep1 = false;
            	vm.restModeStep2 = false;
            	vm.restModeStep3 = false;
            	vm.restModeStep4 = false;
            	vm.allReservationsMode = false;
            	vm.allInvitationsMode = false;
            	$window.location.reload();
            });
        }
        
        function loadAllFinished(){
        	RestaurantService.getFinished(vm.user.id)
            .then(function (response) {
            	if(response.status===204){
                	FlashService.Error('Your history of visits is empty.',false);
                	noMode();
            	}
            	else{
            		vm.allFinished=response.data;
            		alert(vm.allFinished.length);
            		showFinished();
            	}
            });
        }
        
        
        function createOrder(){
        	var order2 = {
                    date : new Date(),
                    clientId: vm.user.id,
                    reservation: vm.currReservation,
                    items : [],
                    status: "created"
            };
            vm.orderItems.forEach(function (meal) {
                    var count = meal.count;
                    delete meal.count;
                    
                    var item = {
                        state : "created",
                        menuItem : meal,
                        amount : count
                    };
                    order2.items.push(item);
            });
            
                return order2;
        };
        function profil(){
        	
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "guestProfil", vm.user.token);
        	$location.path('/guestProfil');
        }
        
        function home(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "home", vm.user.token);

        	$location.path('/home');
        }
        function friends(){
        	AuthenticationService.SetCredentials(vm.user.email, vm.user.password, "myFriends", vm.user.token);
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
        	if(!vm.parametar){
        		vm.parametar="a";
        	}
        	if(!vm.parametar2){
        		vm.parametar2="a";
        	}
        	RestaurantService.find(vm.parametar, vm.parametar2)
            .then(function (response) {
            	vm.allRests = response.data;
            });
        }
        
        
        
        function findUsers() {
        	
        	UserService.findUsers(vm.parametar.par, vm.user.email)
            .then(function (response) {
            	vm.allUsers = response.data;
            });
        }

        function cancel(reservationId){
        	RestaurantService.cancel(reservationId, vm.user.id)
            .then(function (response) {
            	if(angular.equals(response.data.status,"no")){
            		//alert("error");
                	FlashService.Error('You can not cancel this reservation.',false);
            	}
            	else{
            		showReservations();
            	}
            });
        }
        
        function call(email){
        	RestaurantService.callFriend(email, vm.user.email, vm.currReservation.reservationId)
            .then(function (response) {
            	if(response.status==204){
                	FlashService.Error('This friend is allready added on this reservation.',false);
            	}
            	else if(response.status==403){
                	FlashService.Error('This reservation has no more chairs for your friends.',false);
            	}
            	else{
                	FlashService.Success('Friend successfuly called.',false);
            	}
            });
        }
        vm.getCalledFriends = getCalledFriends;
        vm.friends = [];
        vm.makedMeals = [];
        function getCalledFriends(reservationId)
        {
        	vm.friends = [];
        	RestaurantService.getCalledFriends(reservationId)
            .then(function (response) {
            	vm.friends[reservationId]= response.data;
            	/*angular.forEach(vm.friends, function(value, key){
              	     alert(key + ': ' + value);
              	     angular.forEach(value, function(value, key){
                  	     alert(key + ': ' + value);
                  	});
              	});*/
            });
        	
        	vm.makedMeals = [];
        	RestaurantService.getMakedMeals(reservationId)
            .then(function (response) {
            	vm.makedMeals[reservationId]= response.data;
            	

            });

        }
        vm.SortableTableRest=SortableTableRest;
        function SortableTableRest() {

            vm.head2 = {
                    image: "Image",
                    name: "Name",
                    type: "Type",
                    startTime: "Start time",
                    endTime: "EndTime",
                    address: "Address",
                    info: "Info"
                  
                };
                        
            
            vm.sort2 = {
                column: 'name',
                descending: false
            };

            vm.selectedCls2 = function(column) {
                return column == vm.sort2.column && 'sort-' + vm.sort2.descending;
            };
            
            vm.changeSorting2 = function(column) {
                var sort = vm.sort2;
                if (sort.column == column) {
                    sort.descending = !sort.descending;
                } else {
                    sort.column = column;
                    sort.descending = false;
                }
            };
        
        }
        
        vm.SortableTableCtrl=SortableTableCtrl;
        function SortableTableCtrl() {
            var scope = this;

            vm.head = {
                    id: "Name",
                    reservationDateTime: "Date",
                    time: "From",
                    length: "To",
                    friends: "Friends",
                    orders: "Orders"
                  
                };
                        
            vm.sort = {
                column: 'id',
                descending: false
            };

            vm.selectedCls = function(column) {
                return column == vm.sort.column && 'sort-' + vm.sort.descending;
            };
            
            vm.changeSorting = function(column) {
                var sort = vm.sort;
                if (sort.column == column) {
                    sort.descending = !sort.descending;
                } else {
                    sort.column = column;
                    sort.descending = false;
                }
            };
        
        }
        
        vm.SortableTableFin=SortableTableFin;
        function SortableTableFin() {

            vm.head3 = {
                    id: "Name",
                    reservationDateTime: "Date",
                    time: "From",
                    length: "To",
                    friends: "Friends",
                    orders: "Orders"
                  
                };
                        
            vm.sort3 = {
                column: 'id',
                descending: false
            };

            vm.selectedCls3 = function(column) {
                return column == vm.sort3.column && 'sort-' + vm.sort3.descending;
            };
            
            vm.changeSorting3 = function(column) {
                var sort = vm.sort3;
                if (sort.column == column) {
                    sort.descending = !sort.descending;
                } else {
                    sort.column = column;
                    sort.descending = false;
                }
            };
        
        }
        
        vm.SortableTableI=SortableTableI;
        function SortableTableI() {

            vm.head4 = {
            		image: "Image",
            		firstName: "firstName",
                    id: "Name",
                    reservationDateTime: "Date",
                    time: "From",
                    length: "To",
                    friends: "Friends",
                    orders: "Orders"
                  
                };
                        
            vm.sort4 = {
                column: 'id',
                descending: false
            };

            vm.selectedCls4 = function(column) {
                return column == vm.sort4.column && 'sort-' + vm.sort4.descending;
            };
            
            vm.changeSorting4 = function(column) {
                var sort = vm.sort4;
                if (sort.column == column) {
                    sort.descending = !sort.descending;
                } else {
                    sort.column = column;
                    sort.descending = false;
                }
            };
        
        }
    }

})();