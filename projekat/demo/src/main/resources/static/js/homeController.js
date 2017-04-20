(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['UserService', '$rootScope'];
    function HomeController(UserService, $rootScope) {
        var vm = this;

        vm.user = null;
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        (function initController() {
            loadCurrentUser();
            loadAllUsers();
        })();

        function loadCurrentUser() {
        	alert($rootScope.globals.currentUser.email+"dfgdhs");
            UserService.GetByUsername($rootScope.globals.currentUser.email)
                .then(function (response) {
                	alert(response.data.email);
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