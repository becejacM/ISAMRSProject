(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http','$q'];
    function UserService($http, $q) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        service.Upload = Upload;
        

        return service;

        function GetAll() {
            return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(email) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.get('/api/users/' + email)
            .then(function(response) {
                return response;
            }); 
        }

        function Create(user) {
               return $http.post('/api/users/register', user)
               .then(function(response) {
                   return response;
               });               

        }

        function Update(user) {
        		return $http.put('/api/user/update' , user).then(handleSuccess, handleError('Error updating user'));
        		/*return $http.put('/api/user/update', user)
                .then(function(response) {
                    return response;
                }); */
        	
        }

        function Upload(user) {
    		return $http.put('/api/user/upload' , user).then(handleSuccess, handleError('Error updating user'));
    		/*return $http.put('/api/user/update', user)
            .then(function(response) {
                return response;
            }); */
    	
    }
        function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

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
    }

})();
