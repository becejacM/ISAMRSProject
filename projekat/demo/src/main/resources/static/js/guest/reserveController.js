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
        }
        
        function showInvitations(){
        	FlashService.clearFlashMessageP();
        	RestaurantService.showInvitations(vm.user.email)
            .then(function (rests) {
                vm.allInvitations = rests.data;
                vm.allRestsMode = false;
            	vm.restModeStep1 = false;
            	vm.restModeStep2 = false;
            	vm.restModeStep3 = false;
            	vm.restModeStep4 = false;
            	vm.allReservationsMode = false;
            	vm.allInvitationsMode = true;
            	vm.allFinishedMode = false;
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

        }
        
        vm.finished=finished;
        function finished(){
        	loadAllFinished();
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
        }
        
        function showRestStep4(){
        	vm.allRestsMode = false;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = false;
        	vm.restModeStep3 = false;
        	vm.restModeStep4 = true;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
        	vm.allFinishedMode = false;
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

        		if(vm.regions[j].deleted === "no")
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
    			group.on('selected', function() {
    				
    				//alert(t);
    				/*RestaurantService.Reserve(vm.c,vm.step1par.vreme,vm.step1par.trajanje, vm.rest.name, t, vm.user.email)
    	            .then(function (response) {
    	            	
    	                FlashService.Success('Reservation successfuly created.',false);

    	                loadAllRests();
    	            	vm.allRestsMode = false;
    	            	vm.restModeStep1 = false;
    	            	vm.restModeStep2 = false;
    	            	vm.restModeStep3 = true;
    	            });*/
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
        			  RestaurantService.Reserve(vm.c,vm.step1par.vreme,vm.step1par.trajanje, vm.rest.name, id, vm.user.email)
      	            .then(function (response) {
      	            	if(response.status===204){
      	                	FlashService.Error('You can\'t create this reservation.',false);
      	            	}
      	            	else{
      	            		vm.currReservation = response.data;
          	            	//alert(vm.currReservation.reservationId);
              			  step22();
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
                	FlashService.Error('This restaurant has no table for this date.',false);
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
        	alert("call f "+r.reservationId);
        	//geocodeAddress();
        	//loadAllRests();
        	vm.currReservation = r;
        	//vm.rest.name=r.id;
        	//vm.step1par.vreme=r.time;
        	//vm.step1par.trajanje=r.length;
        	
          	showRestStep3();
        }
        function step4(){
        	loadAllMI();
        	showRestStep4();
        }
        
        function loadAllMI(){
        	RestaurantService.LoadAllMeals(vm.rest.restaurantId)
            .then(function (response) {
            	vm.allMI = response.data;
            	
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
            	alert(response.status)
            	if(response.status===204){
                	FlashService.Success('Reservation successfuly finished.',false);
            	}
            	else{
                	FlashService.Success('You successfuly create order and reservation successfuly finished.',false);
            	}
            	vm.allRestsMode = false;
            	vm.restModeStep1 = false;
            	vm.restModeStep2 = false;
            	vm.restModeStep3 = false;
            	vm.restModeStep4 = false;
            	vm.allReservationsMode = false;
            	vm.allInvitationsMode = false;
            });
        }
        
        function loadAllFinished(){
        	RestaurantService.getFinished(vm.user.id)
            .then(function (response) {
            	if(response.status===204){
                	FlashService.Success('Your history of visits is empty.',false);
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
        	RestaurantService.find(vm.parametar.par, vm.parametar.par2)
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
            	FlashService.Success('Friend successfuly called.',false);
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
    }

})();