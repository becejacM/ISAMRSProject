(function () {
    'use strict';

    angular
        .module('app')
        .controller('ResManagerProfilController', ResManagerProfilController);

    ResManagerProfilController.$inject = ['$location','UserService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function ResManagerProfilController($location,UserService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        vm.editMode = false;
        vm.editProfile = editProfile;
        vm.changePicMode = false;
        vm.editPicture = editPicture;
        vm.cancel = cancel;
        vm.save = save;
        
        vm.basePic=null;
        vm.previewFile = previewFile;
        vm.upload = upload;
        vm.logout = logout;
        vm.manage = manage;
        vm.registerWorker = registerWorker;
        vm.registerSuplier = registerSuplier;
        vm.resManagerProfil = resManagerProfil;
        
        (function initController() {
        	loadCurrentUser();
            loadAllUsers();
            
        })();

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
                });
        }
        
        function previewFile(){
        	   //var preview = document.querySelector('img'); //selects the query named img
        	   //var file    = document.querySelector('input[type=file]').files[0]; //sames as here
        		alert("usao u pic");
        	   var preview = document.getElementById('avatar');
        	   var file = document.getElementById("avatarFile").files[0];

        	   var reader  = new FileReader();

        	   reader.onloadend = function (fileLoadedEvent) {
        	       vm.basePic = fileLoadedEvent.target.result;
        	       preview.src = reader.result;
        	       vm.user.image=fileLoadedEvent.target.result;
            	   alert(vm.user.image);
            	   alert("Fds");
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