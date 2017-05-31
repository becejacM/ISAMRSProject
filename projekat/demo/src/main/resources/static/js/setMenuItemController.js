(function () {
    'use strict';

    angular
        .module('app')
        .controller('setMenuItemController', setMenuItemController);

    setMenuItemController.$inject = ['$location','UserService','RestaurantService', 'AuthenticationService', '$rootScope', 'FlashService'];
    function setMenuItemController($location,UserService,RestaurantService,AuthenticationService, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;
        vm.dish = null;
        
        vm.addDishMode = false;
        vm.addDrinkMode = false;
        vm.realUser = {};
        vm.allUsers = [];
        vm.dishes = [];
        vm.drinks = [];
        vm.deleteUser = deleteUser;

        
        vm.logout = logout;
        vm.manage = manage;
        vm.registerWorker = registerWorker;
        vm.registerSuplier = registerSuplier;
        vm.resManagerProfil = resManagerProfil;
        vm.addDish = addDish;
        vm.saveDish = saveDish;
        vm.addDrink = addDrink;
        vm.saveDrink = saveDrink;
        
        (function initController() {
        	loadCurrentUser();
            loadAllUsers();
            loadAllDishes();
            loadAllDrinks();
            alert("setMenu");
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
        
        function addDish(){
        	alert("prikazi");
        	vm.addDishMode = true;
        }
        
        function saveDish(){
        	vm.addDishMode = false;
        	RestaurantService.AddDish(vm.dish,vm.user.restaurant.name)
        	//loadAllDishes();
            .then(function (response) {
            	FlashService.Success('Dish added', false);
        		
            });
        }
        
        function addDrink(){
        	alert("prikazi");
        	vm.addDrinkMode = true;
        }
        
        function saveDrink(){
        	vm.addDrinkMode = false;
        	RestaurantService.AddDrink(vm.drink,vm.user.restaurant.name)
        	//loadAllDishes();
            .then(function (response) {
            	FlashService.Success('Drink added', false);
        		
            });
        }
        
        
        function loadAllUsers() {
            UserService.GetAll()
                .then(function (users) {
                    vm.allUsers = users;
                });
        }
        
        function loadAllDishes() {
            RestaurantService.GetAllDishes()
                .then(function (list) {
                    vm.dishes = list.data;
                });
        }
        
        function loadAllDrinks() {
            RestaurantService.GetAllDrinks()
                .then(function (list) {
                    vm.drinks = list.data;
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