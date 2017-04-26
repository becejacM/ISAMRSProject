(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies',])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'views/login.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'views/register.html',
                controllerAs: 'vm'
            })
            
            .when('/home', {
                controller: 'HomeController',
                templateUrl: 'views/home.html',
                controllerAs: 'vm'
            })

            .when('/guestProfil', {
                controller: 'GuestProfilController',
                templateUrl: 'views/guestProfil.html',
                controllerAs: 'vm'
            })

            .when('/reserveRestaurant', {
                controller: 'GuestReserveController',
                templateUrl: 'views/reserveRestaurant.html',
                controllerAs: 'vm'
            })
            
            .when('/myFriends', {
                controller: 'GuestFriendsController',
                templateUrl: 'views/myFriends.html',
                controllerAs: 'vm'
            })
            .when('/waiterHome', {
                controller: 'WaiterHomeController',
                templateUrl: 'views/waiterHome.html',
                controllerAs: 'vm'
            })
            
            .when('/waiterProfile', {
                controller: 'WaiterProfileController',
                templateUrl: 'views/waiterProfile.html',
                controllerAs: 'vm'
            })
            
            .when('/waiterSchedule', {
                controller: 'WaiterScheduleController',
                templateUrl: 'views/waiterSchedule.html',
                controllerAs: 'vm'
            })
            
            .when('/waiterTables', {
                controller: 'WaiterTablesController',
                templateUrl: 'views/waiterTables.html',
                controllerAs: 'vm'
            })
            
            .when('/waiterOrders', {
                controller: 'WaiterOrdersController',
                templateUrl: 'views/waiterOrders.html',
                controllerAs: 'vm'
            })
            
            .when('/waiterChangePassword', {
                controller: 'WaiterChangePasswordController',
                templateUrl: 'views/waiterChangePassword.html',
                controllerAs: 'vm'
            })
            
            
            
            .when('/registerManager',{
            	controller: 'registerManagerController',
            	templateUrl: 'views/registerManager.html',
            	controllerAs: 'vm'
        	})
        	
        	.when('/SysManagerHome',{
            	controller: 'SysManagerHomeController',
            	templateUrl: 'views/SysManagerHome.html',
            	controllerAs: 'vm'
        	})
        	
        	.when('/registerRestaurant',{
            	controller: 'registerRestaurantController',
            	templateUrl: 'views/registerRestaurant.html',
            	controllerAs: 'vm'
        	})
        	
        	.when('/SysManagerProfil',{
            	controller: 'SysManagerProfilController',
            	templateUrl: 'views/SysManagerProfil.html',
            	controllerAs: 'vm'
        	})
        	
        	.when('/ResManagerProfil',{
            	controller: 'ResManagerProfilController',
            	templateUrl: 'views/ResManagerProfil.html',
            	controllerAs: 'vm'
        	})
        	
        	.when('/ResManagerHome',{
            	controller: 'ResManagerHomeController',
            	templateUrl: 'views/ResManagerHome.html',
            	controllerAs: 'vm'
        	})



            .otherwise({redirectTo: '/login.html' });
    }

    run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
    function run($rootScope, $location, $cookies, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
        	//event.preventDefault();
        	
        	//alert("menjam");
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            
            
            //alert(loggedIn.email);
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
            if(loggedIn){
            	
                $location.path('/'+loggedIn.path);
                
            }
            
        });
    }

})();