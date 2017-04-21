(function () {
    'use strict';

    angular
        .module('app')
        .controller('GuestProfilController', GuestProfilController);

    GuestProfilController.$inject = ['UserService', '$rootScope', 'FlashService'];
    function GuestProfilController(UserService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.realUser = {};
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        vm.editMode = false;
        vm.editProfile = editProfile;
        vm.cancel = cancel;
        vm.save = save;
        
        vm.upload = upload;
        (function initController() {
            loadCurrentUser();
            loadAllUsers();
            showOptions();
        })();

        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.email)
                .then(function (response) {
                    vm.user = response.data;
                });
        }
        
        /*function previewFile(){
        	   //var preview = document.querySelector('img'); //selects the query named img
        	   //var file    = document.querySelector('input[type=file]').files[0]; //sames as here
        		alert("usao u pic");
        	   var preview = document.getElementById('avatar');
        	   var file = document.getElementById("avatarFile").files[0];

        	   var reader  = new FileReader();

        	   reader.onloadend = function (fileLoadedEvent) {
        	       basePic = fileLoadedEvent.target.result;
        	       preview.src = reader.result;
        	   }

        	   if (file) {
        	       reader.readAsDataURL(file); //reads the data as a URL
        	   } else {
        	       preview.src = "";
        	   }
        	}*/
        
        //za upload .... ne radi
        function upload(){
            $flow.opts.target = 'api/upload/users/' + vm.user.id ;
            $flow.upload();
            vm.user.image = 'pictures/users_' + vm.user.id + '.png';
            UserService.Update(vm.user)
                .then(function(response) {
                    vm.user = response.data;
                    alert('Fotografija uspešno promenjena.');
                })
        }
        function editProfile() {
            vm.editMode = true;
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