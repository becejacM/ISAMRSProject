(function () {
    'use strict';

    angular
        .module('app')
        .filter("formatDate", function(){
		   return function(input){
		      return  moment(input).format('DD MM YYYY, HH:MM'); 
		   }
        })
        .controller('BartenderProfileController', BartenderProfileController);

    BartenderProfileController.$inject = ['$location','UserService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function BartenderProfileController($location,UserService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        
        vm.logout = logout;
        vm.profile = profile;
        vm.schedule = schedule;
        vm.orders = orders;
        vm.tables = tables;
        
        (function initController() {
        	//alert("bartender profile");
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
                });
        }
        
        function previewFile(){
      	   //var preview = document.querySelector('img'); //selects the query named img
      	   //var file    = document.querySelector('input[type=file]').files[0]; //sames as here
      		//alert("usao u pic");
      	   var preview = document.getElementById('avatar');
      	   var file = document.getElementById("avatarFile").files[0];

      	   var reader  = new FileReader();

      	   reader.onloadend = function (fileLoadedEvent) {
      	       vm.basePic = fileLoadedEvent.target.result;
      	       preview.src = reader.result;
      	       vm.user.image=fileLoadedEvent.target.result;
          	   //alert(vm.user.image);
          	   //alert("Fds");
      	   }

      	   if (file) {
      	       reader.readAsDataURL(file); //reads the data as a URL
      	   } else {
      	       preview.src = "";
      	   }
      	   
      	   
      	}
      
 	     //za upload .... ne radi
 	     function upload(){
 	     	vm.changePicMode = false;
 	     	UserService.Upload(vm.user)
 	         .then(function (response) {
 	       		  vm.user = response.data;
 	       	  
 	         });
 	     	
 	     }
 	     function editProfile() {
 	         vm.editMode = true;
 	         vm.realUser = vm.user;
 	     }
 	     function editPicture() {
 	         vm.changePicMode = true;
 	         vm.realUser = vm.user;
 	     }
 	     function cancel(){
 	     	vm.editMode = false;
 	     }
 	     
 	     function save() {
 	     	vm.editMode = false;
 	         UserService.Update(vm.user)
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