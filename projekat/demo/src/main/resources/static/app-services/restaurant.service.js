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
        service.getCalledFriendsf = getCalledFriendsf;
        service.getMakedMeals = getMakedMeals;
        service.showInvitations=showInvitations;
        service.accept = accept;
        service.reject = reject;
        service.GetAllAcceptRest = GetAllAcceptRest;
        service.LoadAllMeals = LoadAllMeals;
        service.Order = Order;
        service.getFinished = getFinished;
        
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
        service.CreateRegion = CreateRegion;
        service.CreateShifts = CreateShifts;
        service.GetAllShifts = GetAllShifts;
        service.EditShift = EditShift;
        service.DeleteShift = DeleteShift;
        service.AsignRegion = AsignRegion;
        
        service.GetMenuItems = GetMenuItems;
        service.AddOneItem = AddOneItem;
        //service.AddOrder = AddOrder;
        service.SaveOrder = SaveOrder;
        service.LoadAllOrders = LoadAllOrders;
        service.LoadAllOrders2 = LoadAllOrders2;
        service.LoadAllOrders3 = LoadAllOrders3;
        service.Take = Take;
        service.LoadTaken = LoadTaken;
        service.LoadTaken2 = LoadTaken2;
        service.Finish = Finish;
        service.FindOrder = FindOrder;
        service.LoadFinishedOrders = LoadFinishedOrders;
        service.DeleteItem = DeleteItem;
        service.SaveEditedOrder = SaveEditedOrder;
        service.SaveEditedItem = SaveEditedItem;
        service.MakeBill = MakeBill;
        service.CreateBid = CreateBid;
        service.GetAllBids = GetAllBids;
        service.GetAllBidsSup = GetAllBidsSup;
        service.CreateWanted = CreateWanted;
        service.GetAllWanted = GetAllWanted;
        service.CreateOffer = CreateOffer;
        service.GetAllOffers = GetAllOffers;
        service.EditOffer = EditOffer;
        service.AcceptOffer = AcceptOffer;
        service.DeactivateBid = DeactivateBid;
        service.SetAccepted = SetAccepted;
        service.GetMyOffers = GetMyOffers;
        service.AddGrade = AddGrade;
        service.GetAvailableTables = GetAvailableTables;
        service.GetPendingOrders = GetPendingOrders;
        service.TakeOrder = TakeOrder;

        return service;
        
        function GetMyOffers(email) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/myoffers/'+email)
            .then(function(response) {
                return response;
            }); 
        }
        
        function SetAccepted(offer, bid) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.put('/api/offer/acc/'+ bid, offer)
            .then(function(response) {
                return response;
            }); 
        }
        
        function CreateOffer(offer, bid, email) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.post('/api/offer/' + bid + '/' + email, offer)
            .then(function(response) {
                return response;
            }); 
        }
        
        function AcceptOffer(offers, bid, email) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.put('/api/offer/accept/'+ bid + '/' + email, offers)
            .then(function(response) {
                return response;
            }); 
        }
        
        function DeactivateBid(bid) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.put('/api/bid/deactivate', bid)
            .then(function(response) {
                return response;
            }); 
        }
        
        function EditOffer(offer, bid, email) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.put('/api/offer/update/'+ bid + '/' + email, offer)
            .then(function(response) {
                return response;
            }); 
        }
        
        function GetAllOffers(bid) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/alloffers/'+bid)
            .then(function(response) {
                return response;
            }); 
        }
        
        function CreateWanted(wanted, bid) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.post('/api/wanted/' + bid, wanted)
            .then(function(response) {
                return response;
            }); 
        }
        
        function GetAllBids(name) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/bids/'+name)
            .then(function(response) {
                return response;
            }); 
        }
        
        function GetAllWanted(name) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/wanteds/'+name)
            .then(function(response) {
                return response;
            }); 
        }
        
        function GetAllBidsSup(email) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/bidssup/'+email)
            .then(function(response) {
                return response;
            }); 
        }
        
        function CreateBid(bid, id) {
            //return $http.get('/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
            return $http.post('/api/bid/'+id, bid)
            .then(function(response) {
                return response;
            }); 
        }

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
        
        function cancel(reservationId,id){
        	return $http.get('api/reservations/cancel/' + reservationId+'/'+id)
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
        
        function getCalledFriendsf(reservationId){
        	return $http.get('api/reservations/getCalledFriendsf/' + reservationId)
            .then(function(response) {
                return response;
            }); 
        }
        
        function getMakedMeals(reservationId){
        	return $http.get('api/reservations/getMakedMeals/' + reservationId)
            .then(function(response) {
                return response;
            }); 
        }
        
        function getFinished(id){
        	return $http.get('api/reservations/getFinished/' + id)
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
            	//alert(id);
                return response;
            }); 
        }
        
        function LoadAllMeals(id) {
        	
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.get('/api/menuItems/'+id)
            .then(function(response) {
            	//alert(id);
                return response;
            }); 
        }
        
        function Order(id, order) {
            //return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        	return $http.post('/api/orders/create/'+id,order)
            .then(function(response) {
            	//alert(id);
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
        
        function CreateRegion(region,id) {
            return $http.post('/api/restaurants/createRegion/'+id, region)
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
        
        function CreateShifts(shift,email,color) {
        	alert("coloooooooooooor" + color);
            return $http.post('/api/shift/createShift/'+email+'/'+color, shift)
            .then(function(response) {
                return response;
            });               
        }
        
        function EditShift(shift,email,color){
        	return $http.put('api/shift/edit/'+email + '/' + color, shift)
        	.then(function(response){
        		return response;
        	});
        }
        
        function DeleteShift(shift,email,color){
        	return $http.put('api/shift/delete/'+email + '/' + color, shift)
        	.then(function(response){
        		return response;
        	});
        }
        
        function GetAllShifts() {
        	alert("usao");
            return $http.get('/api/shifts')
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
        
        function AsignRegion(worker, region){
        	return $http.put('api/asignregion/'+region, worker)
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
        
        function AddOneItem(item, id){
        	return $http.post('/api/orders/addOne/' + id, item)
            .then(function(response) {
                return response;
            }); 
        }
        
        /*function AddOrder(order){
        	return $http.post('/api/orders/addOrder', order)
            .then(function(response) {
                return response;
            }); 
        }*/
        
        function SaveOrder(order, table){
        	return $http.put('/api/orders/saveOrder/' + table, order)
            .then(function(response) {
                return response;
            }); 
        }
        
        function LoadAllOrders(email){
        	return $http.get('/api/orders/getAll/' + email)
        	.then(function(response){
        		return response;
        	});
        }
        
        function LoadAllOrders2(rest, type){
        	return $http.get('/api/orders/getAll2/' + rest + '/' + type)
        	.then(function(response){
        		return response;
        	});
        }
        
        function LoadAllOrders3(rest){
        	return $http.get('/api/orders/getAll3/' + rest)
        	.then(function(response){
        		return response;
        	});
        }
        
        function Take(id){
        	return $http.get('/api/orders/take/' + id)
        	.then(function(response){
        		return response;
        	});
        }
        
        function LoadTaken(rest){
        	return $http.get('/api/orders/getTaken/' + rest)
        	.then(function(response){
        		return response;
        	});
        }
        
        function LoadTaken2(rest){
        	return $http.get('/api/orders/getTaken2/' + rest)
        	.then(function(response){
        		return response;
        	});
        }
        
        function Finish(id){
        	return $http.get('/api/orders/finish/' + id)
        	.then(function(response){
        		return response;
        	});
        }
        
        function LoadFinishedOrders(email){
        	return $http.get('/api/orders/getFinished/' + email)
        	.then(function(response){
        		return response;
        	});
        }
        
        function FindOrder(id){
        	return $http.get('/api/orders/getOrder/' + id)
        	.then(function(response){
        		return response;
        	});
        }
        
        function GetMenuItems(rest){
        	return $http.get('/api/orders/getMenuItems/' + rest)
        	.then(function(response){
        		return response;
        	});
        }
        
        function DeleteItem(i){
        	return $http.put('/api/orders/deleteItem/' + i)
        	.then(function(response){
        		return response;
        	});
        }
        
        function SaveEditedOrder(order){
        	return $http.put('/api/orders/saveEditedOrder/' + order)
        	.then(function(response){
        		return response;
        	});
        }
        
        function SaveEditedItem(item, amount){
        	return $http.put('/api/orders/saveEditedItem/' + item + '/' + amount)
        	.then(function(response){
        		return response;
        	});
        }
        
        function MakeBill(order, email){
        	return $http.get('/api/orders/makeBill/' + order + '/' + email)
        	.then(function(response){
        		return response;
        	});
        }
        
        function AddGrade(res){
        	return $http.post('/api/grades/add', res)
        	.then(function(response){
        		return response;
        	});
        }
        
        function GetAvailableTables(email){
        	return $http.get('/api/orders/getTables/' + email)
        	.then(function(response){
        		return response;
        	});
        }
        
        function GetPendingOrders(email){
        	return $http.get('/api/orders/getPending/' + email)
        	.then(function(response){
        		return response;
        	});
        }
        
        function TakeOrder(email, o){
        	return $http.put('/api/orders/takeOrder/' + email + '/' + o)
        	.then(function(response){
        		return response;
        	});
        }
        
    }
    

})();
