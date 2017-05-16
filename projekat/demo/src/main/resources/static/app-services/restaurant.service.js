(function () {
    'use strict';

    angular
        .module('app')
        .factory('RestaurantService', RestaurantService);

    RestaurantService.$inject = ['$http','$q'];
    function RestaurantService($http, $q) {
        var service = {};

        service.GetAllRests = GetAllRests;
        service.GetById = GetById;

        service.CreateRestaurant = CreateRestaurant;

        service.GetAllTables = GetAllTables;
        service.GetAllAvailableTables = GetAllAvailableTables;
        service.find = find;
        service.GetHours = GetHours;
        service.Reserve = Reserve;
        return service;

        
        function GetAllRests() {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/restaurants')
            .then(function(response) {
                return response;
            }); 
        }
        
        function GetById(id) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('/api/restaurants/' + id)
            .then(function(response) {
                return response;
            }); 
        }
        
        function GetHours(id) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('/api/restaurants/hours/' + id)
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
        /*function handleSuccessTrue(res) {
            return res;
        }

        function handleSuccess(res) {
            return { success: true, message: error };
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };*/

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
        
        function Reserve(datum, vreme, trajanje, nameRest,idstola) {
        	
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('api/reservations/reserve/' + datum+'/'+vreme+'/'+trajanje+'/'+nameRest+'/'+idstola)
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
    }
    

})();
