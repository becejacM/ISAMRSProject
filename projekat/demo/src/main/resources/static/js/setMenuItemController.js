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
        vm.Ditem = null;
        vm.DrItem = null;
        
        vm.addDishMode = false;
        vm.addDrinkMode = false;
        vm.editDishMode = false;
        vm.editDrinkMode = false;
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
        vm.editDMode = editDMode;
        vm.editDrMode = editDrMode;
        vm.editDish = editDish;
        vm.editDrink = editDrink;
        
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
            	loadAllDishes();
            });
        }
        
        function editDMode(item){
        	vm.editDishMode = true;
        	vm.Ditem = item;
        }
        
        function editDrMode(item){
        	vm.editDrinkMode = true;
        	vm.DrItem = item;
        }
        
        function editDish(){
        	vm.editDishMode = false;
        	RestaurantService.EditDish(vm.Ditem)
        	.then(function (response) {
            	FlashService.Success('Dish edited', false);
            	loadAllDishes();
            });
        }
        
        function editDrink(){
        	vm.editDrinkMode = false;
        	RestaurantService.EditDrink(vm.DrItem)
        	.then(function (response) {
            	FlashService.Success('Drink edited', false);
            	loadAllDrinks();
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
        		loadAllDrinks();
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