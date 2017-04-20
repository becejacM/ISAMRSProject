(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService','$rootScope', 'FlashService'];
    function LoginController($location, AuthenticationService,$rootScope, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
        	hideInfo();
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            vm.dataLoading = true;
            
            AuthenticationService.Login(vm.email, vm.password)
            .then (function (response) {
            	//alert(response.data.email);
                if (response.data.email) {
                	AuthenticationService.SetCredentials(vm.email, vm.password);
                    $location.path('/home');
                    showOptions();
                } else {
                	FlashService.Error('password or email do not exist',true);
                    vm.dataLoading = false;
                    //$location.path('/login');
                }
            });
            
            
            
        };
        
        
    }

})();
