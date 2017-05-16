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
        service.CreateM = CreateM;
        service.CreateSysM = CreateSysM;
        service.Update = Update;
        service.Delete = Delete;
        service.Upload = Upload;
        service.GetAllGuests = GetAllGuests;
        service.find = find;
        service.add = add;
        service.loadFriendsIAccept = loadFriendsIAccept;
        service.loadFriendsIAdd = loadFriendsIAdd;
        service.loadReq = loadReq;
        service.accept = accept;
        service.deleteF = deleteF;
        service.reject = reject;

        return service;

        function GetAll() {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/users')
            .then(function(response) {
                return response;
            }); 
        }
        
        function find(parametar) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/guests/'+ parametar)
            .then(function(response) {
                return response;
            }); 
        }
        
        function add(ids,idr) {
        	
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.post('/api/guest/addf/'+ ids+'/'+idr)
            .then(function(response) {
                return response;
            }); 
        }
        
        function accept(ids,idr) {
        	
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.post('/api/guest/accept/'+ ids+'/'+idr)
            .then(function(response) {
                return response;
            }); 
        }
        
        function reject(ids,idr) {
        	
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.post('/api/guest/reject/'+ ids+'/'+idr)
            .then(function(response) {
                return response;
            }); 
        }
        
        function deleteF(ids,idr) {
        	
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.post('/api/guest/deleteF/'+ ids+'/'+idr)
            .then(function(response) {
                return response;
            }); 
        }
        function GetAllGuests() {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/guests')
            .then(function(response) {
                return response;
            }); 
        }
        
        function loadFriendsIAccept(id) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/guests/loadFriendsIAccept/'+id)
            .then(function(response) {
                return response;
            }); 
        }
        
        function loadFriendsIAdd(id) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/guests/loadFriendsIAdd/'+id)
            .then(function(response) {
                return response;
            }); 
        }
        function loadReq(id) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/guests/loadReq/'+id)
            .then(function(response) {
                return response;
            }); 
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
        
        function CreateM(user) {
            return $http.post('/api/users/registerManager', user)
            .then(function(response) {
                return response;
            });               

     }
        
        function CreateSysM(user) {
            return $http.post('/api/users/registerSysManager', user)
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
