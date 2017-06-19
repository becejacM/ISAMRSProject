(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies',])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider','$httpProvider','$provide'];
    function config($routeProvider, $locationProvider,$httpProvider,$provide) {
        $routeProvider
            
        	/*.when('/', {
        		controller: 'LoginController',
        		templateUrl: 'views/login.html',
        		controllerAs: 'vm'
        	})*/
            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'views/login.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'views/guest/register.html',
                controllerAs: 'vm'
            })
            
            .when('/home', {
                controller: 'HomeController',
                templateUrl: 'views/guest/home.html',
                controllerAs: 'vm'
            })

            .when('/guestProfil', {
                controller: 'GuestProfilController',
                templateUrl: 'views/guest/guestProfil.html',
                controllerAs: 'vm'
            })

            .when('/reserveRestaurant', {
                controller: 'GuestReserveController',
                templateUrl: 'views/guest/reserveRestaurant.html',
                controllerAs: 'vm'
            })
            
            .when('/myFriends', {
                controller: 'GuestFriendsController',
                templateUrl: 'views/guest/myFriends.html',
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
            
            .when('/cookHome', {
                controller: 'CookHomeController',
                templateUrl: 'views/cookHome.html',
                controllerAs: 'vm'
            })
            
            .when('/cookProfile', {
                controller: 'CookProfileController',
                templateUrl: 'views/cookProfile.html',
                controllerAs: 'vm'
            })
            
            .when('/cookSchedule', {
                controller: 'CookScheduleController',
                templateUrl: 'views/cookSchedule.html',
                controllerAs: 'vm'
            })
            
            .when('/cookTables', {
                controller: 'CookTablesController',
                templateUrl: 'views/cookTables.html',
                controllerAs: 'vm'
            })
            
            .when('/cookOrders', {
                controller: 'CookOrdersController',
                templateUrl: 'views/cookOrders.html',
                controllerAs: 'vm'
            })
            
            .when('/cookChangePassword', {
                controller: 'CookChangePasswordController',
                templateUrl: 'views/cookChangePassword.html',
                controllerAs: 'vm'
            })
            
            .when('/bartenderHome', {
                controller: 'BartenderHomeController',
                templateUrl: 'views/bartenderHome.html',
                controllerAs: 'vm'
            })
            
            .when('/bartenderProfile', {
                controller: 'BartenderProfileController',
                templateUrl: 'views/bartenderProfile.html',
                controllerAs: 'vm'
            })
            
            .when('/bartenderSchedule', {
                controller: 'BartenderScheduleController',
                templateUrl: 'views/bartenderSchedule.html',
                controllerAs: 'vm'
            })
            
            .when('/bartenderTables', {
                controller: 'BartenderTablesController',
                templateUrl: 'views/bartenderTables.html',
                controllerAs: 'vm'
            })
            
            .when('/bartenderOrders', {
                controller: 'BartenderOrdersController',
                templateUrl: 'views/bartenderOrders.html',
                controllerAs: 'vm'
            })
            
            .when('/bartenderChangePassword', {
                controller: 'BartenderChangePasswordController',
                templateUrl: 'views/bartenderChangePassword.html',
                controllerAs: 'vm'
            })
            
            
            .when('/registerManager',{
            	controller: 'registerManagerController',
            	templateUrl: 'views/registerManager.html',
            	controllerAs: 'vm'
        	})
        	
        	.when('/registerSysManager',{
            	controller: 'registerSysManagerController',
            	templateUrl: 'views/registerSysManager.html',
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
        	
        	.when('/manage',{
            	controller: 'ResManagerManageController',
            	templateUrl: 'views/resManagerManage.html',
            	controllerAs: 'vm'
        	})
        	
        	.when('/registerWorker',{
            	controller: 'registerWorkerController',
            	templateUrl: 'views/registerWorker.html',
            	controllerAs: 'vm'
        	})
        	
        	.when('/setMenuItem',{
            	controller: 'setMenuItemController',
            	templateUrl: 'views/setMenuItem.html',
            	controllerAs: 'vm'
        	})




            .otherwise({controller: 'LoginController',
                templateUrl: 'views/login.html',
                controllerAs: 'vm' });
        
        
        
        $httpProvider.interceptors.push(['$q', '$window', '$location', function($q, $window, $location) {
            return {
              // Set Header to Request if user is logged
              'request': function (config) {
                    var token = $window.sessionStorage.getItem('AUTH_TOKEN');
                        if (token !== null && token!=="null") {
                            config.headers.Authorization = 'Basic ' + token;
                        }
                        return config;
                },
              // When try to get Unauthorized page
              'responseError': function(response) {
                  /*var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
                  var loggedIn = $rootScope.globals.currentUser;
                  if (restrictedPage && !loggedIn) {
                      $location.path('/');
                  }*/
                    if(response.status === 401 || response.status === 403) {
                        $location.path('/login');
                    }
                    return $q.reject(response);
                }
              };
            }]);
    }

    
    
    run.$inject = ['$rootScope', '$location', '$cookies', '$http', '$window'];
    function run($rootScope, $location, $cookies, $http, $window) {
        // keep user logged in after page refresh
        
        $rootScope.globals = $cookies.getObject('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
        	//event.preventDefault();
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            var log = $window.localStorage.getItem('AUTH_TOKEN');


            var prvi = $.inArray($location.path(), ['/login','']) === -1;

            if (restrictedPage && !log) {
                $location.path('/login');
            }
            if(!prvi && log){
                $location.path('/'+loggedIn.path);
            }
            /*if(loggedIn){
            	
                $location.path('/'+loggedIn.path);
                
            }*/
            
        });
    }

})();