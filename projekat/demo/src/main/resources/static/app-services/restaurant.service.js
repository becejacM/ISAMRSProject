(function () {
    'use strict';

    angular
        .module('app')
        .factory('RestaurantService', RestaurantService);

    RestaurantService.$inject = ['$http','$q'];
    function RestaurantService($http, $q) {
        var service = {};

        service.Reserve = Reserve;
        service.GetHours = GetHours;
        service.getRestByUserEmail = getRestByUserEmail;
        service.cancel = cancel;
        service.callFriend = callFriend;
        service.getCalledFriends = getCalledFriends;
        service.showInvitations=showInvitations;
        service.accept = accept;
        service.reject = reject;
        service.GetAllAcceptRest = GetAllAcceptRest;
        
        service.GetAllRests = GetAllRests;
        service.GetById = GetById;

        service.CreateRestaurant = CreateRestaurant;

        service.GetAllTables = GetAllTables;
        service.GetAllAvailableTables = GetAllAvailableTables;
        service.find = find;

        service.CreateTable = CreateTable;
        service.UpdateTable = UpdateTable;
        service.DeleteTable = DeleteTable;
        service.FindTable = FindTable;
        service.UpdateTable2 = UpdateTable2;
        
        //service.GetByName = GetByName;
        service.GetRestaurant = GetRestaurant;
        service.GetRestaurantE = GetRestaurantE;
        service.GetRestaurantRegions = GetRestaurantRegions;
        service.GetAllDishes = GetAllDishes;
        service.GetAllDrinks = GetAllDrinks;

        service.AddDish = AddDish;
        service.AddDrink = AddDrink;
        service.EditDish = EditDish;
        service.EditDrink = EditDrink;
        //service.GetRegion = GetRegion;

        return service;

        function Reserve(datum, vreme, trajanje, nameRest,idstola, iduser) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('api/reservations/reserve/' + datum+'/'+vreme+'/'+trajanje+'/'+nameRest+'/'+idstola+'/'+iduser)
            .then(function(response) {
                return response;
            }); 
        }
        
        function AddDish(dish, name) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.post('/api/dishes/' + name, dish)
            .then(function(response) {
                return response;
            }); 
        }
       
        function EditDish(dish){
        	return $http.put('api/dishes/edit', dish)
        	.then(function(response){
        		return response;
        	});
        }
        
        function EditDrink(drink){
        	return $http.put('api/drinks/edit', drink)
        	.then(function(response){
        		return response;
        	});
        }
        
        function GetAllDishes() {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/dishes')
            .then(function(response) {
                return response;
            }); 
        }
        
        function AddDrink(drink, name) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.post('/api/drinks/' + name, drink)
            .then(function(response) {
                return response;
            }); 
        }
        
        function GetAllDrinks() {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/drinks')
            .then(function(response) {
                return response;
            }); 
        }
        
        function getRestByUserEmail(email){
        	return $http.get('api/reservations/getByUserEmail/' + email)
            .then(function(response) {
                return response;
            }); 
        }
        
        function cancel(reservationId){
        	return $http.get('api/reservations/cancel/' + reservationId)
            .then(function(response) {
                return response;
            }); 
        }
        
        function callFriend(parametar, email, reservationId){
        	return $http.get('api/reservations/call/' + parametar+'/' + email+'/' + reservationId)
            .then(function(response) {
                return response;
            }); 
        }
        
        function getCalledFriends(reservationId){
        	return $http.get('api/reservations/getCalledFriends/' + reservationId)
            .then(function(response) {
                return response;
            }); 
        }
        
        
        function showInvitations(email){
        	return $http.get('api/reservations/findFI/' + email)
            .then(function(response) {
                return response;
            }); 
        }
        
        function accept(ids) {
        	
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.post('/api/reservations/accept/'+ ids)
            .then(function(response) {
                return response;
            }); 
        }
        
        function reject(ids) {
        	
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.post('/api/reservations/reject/'+ ids)
            .then(function(response) {
                return response;
            }); 
        }
        
        function GetAllRests() {
        	
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/restaurants')
            .then(function(response) {
                return response;
            }); 
        }
        function GetAllAcceptRest(id) {
        	
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/reservations/findFIAccept/'+id)
            .then(function(response) {
            	alert(id);
                return response;
            }); 
        }
        
        function GetById(id) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('/api/restaurants/get/' + id)
            .then(function(response) {
                return response;
            }); 
        }
        
       /* function GetByName(name) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('/api/getrestaurant/get/' + name)
        }*/
        function GetHours(id) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('/api/restaurants/hours/' + id)

            .then(function(response) {
                return response;
            }); 
        }
        
        function CreateTable(table) {
            return $http.post('/api/users/createTable', table)
            .then(function(response) {
                return response;
            });               

     }
        function UpdateTable(table) {
            return $http.put('/api/users/updateTable', table).then(handleSuccess, handleError('Error updating table'));
            /*.then(function(response) {
                return response;
            }); */              

     }
        
        function FindTable(id) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('/api/users/findTable/' + id)
            .then(function(response) {
                return response;
            }); 
        }
        
        
        function CreateRestaurant(restaurant, email) {
        	console.log("create");
            return $http.post('/api/restaurants/' + email,restaurant)
            .then(function (response) {
                return response;
            });               

        }
        function handleSuccessTrue(res) {
            return res;
        }

        function handleSuccess(res) {
            return { success: true, message: error };
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };

            };
        }
        

           

        function GetAllTables(id) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('/api/restaurants/getAllTables/' + id)
            .then(function(response) {
                return response;
            }); 
        }
        
        function GetAllAvailableTables(datum, vreme, trajanje, nameRest) {
        	
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('api/restaurants/getAllATables/' + datum+'/'+vreme+'/'+trajanje+'/'+nameRest)
            .then(function(response) {
                return response;
            }); 
        }
        
        
        
        function find(parametar,parametar2) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/restaurants/find/'+ parametar+"/"+parametar2)
            .then(function(response) {
                return response;
            }); 

        }
        
        function GetRestaurant(id){
        	return $http.get('/api/getrestaurant/' + id)
        	.then(function(response){
        		return response;
        	});
        }
        
        function GetRestaurantE(id){
        	return $http.get('/api/getrestaurantE/' + id)
        	.then(function(response){
        		return response;
        	});
        }
        
        function GetRestaurantRegions(id){
        	return $http.get('/api/getregions/' + id)
        	.then(function(response){
        		return response;
        	});
        }
        
        function DeleteTable(table) {
            return $http.put('/api/users/deleteTable', table).then(handleSuccess, handleError('Error deleting table'));
            /*.then(function(response) {
                return response;
            });  */             

        }
        
        function UpdateTable2(table) {
            return $http.put('/api/users/updateTable2', table)
            .then(function(response) {
                return response;
            });              

     }
    }
    

})();
