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
        vm.regions = [];
        vm.tables = [];

        
        vm.logout = logout;
        vm.profil = profil;
        vm.home = home;
        vm.friends = friends;
        
        vm.show = show;
        
        vm.allReservationsMode = false;
        vm.showReservations = showReservations;
        
        vm.allInvitationsMode = false;
        vm.showInvitations = showInvitations;
        
        vm.allRestsMode = false;
        vm.showRests = showRests;
        
        vm.restModeStep1 = false;
        vm.showRestStep1 = showRestStep1;
        
        vm.restModeStep2 = false;
        vm.showRestStep2 = showRestStep2;
        
        vm.restModeStep3 = false;
        vm.showRestStep3 = showRestStep3;
        
        
        vm.step1 = step1;
        vm.step2 = step2;
        vm.step3 = step3;
        
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
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
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
            	vm.allReservationsMode = false;
            	vm.allInvitationsMode = true;
            });
        }
        
        function showRestStep1(){
        	vm.allRestsMode = false;
        	vm.restModeStep1 = true;
        	vm.restModeStep2 = false;
        	vm.restModeStep3 = false;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
        }
        
        function showRestStep2(){
        	vm.allRestsMode = false;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = true;
        	vm.restModeStep3 = false;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
        }
        function showRestStep3(){
        	vm.allRestsMode = false;
        	vm.restModeStep1 = false;
        	vm.restModeStep2 = false;
        	vm.restModeStep3 = true;
        	vm.allReservationsMode = false;
        	vm.allInvitationsMode = false;
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
      	            	
      	            	vm.currReservation = response.data;
      	            	//alert(vm.currReservation.reservationId);
      	                FlashService.Success('Reservation successfuly created.',false);
      	                geocodeAddress();
      	                loadAllRests();
      	            	vm.allRestsMode = false;
      	            	vm.restModeStep1 = false;
      	            	vm.restModeStep2 = false;
      	            	vm.restModeStep3 = true;
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
                	//showRests();
            	}
            	else{
            		
                    showA();
                    showRestStep2();
            	}
                
            });
        	
        	
      	  
      	 
      	}
        
        
        function step3(){
        	
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
            		alert("error");
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
        	
        	
        }
    }

})();