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
        service.GetAllTables = GetAllTables;
        service.GetAllAvailableTables = GetAllAvailableTables;
        service.find = find;
        service.CreateTable = CreateTable;
        service.UpdateTable = UpdateTable;
        service.DeleteTable = DeleteTable;
        
        //service.GetByName = GetByName;
        service.GetRestaurant = GetRestaurant;
        service.GetRestaurantE = GetRestaurantE;
        service.GetRestaurantRegions = GetRestaurantRegions;
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
        
        /*function GetByName(name) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('/api/getrestaurant/' + name)
            .then(function(response) {
                return response;
            }); 
        }*/
        
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
        	alert(vreme);
        	alert(trajanje);
        	alert(nameRest);
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
    }
    

})();
