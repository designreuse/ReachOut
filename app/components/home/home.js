/**
 * INSPINIA - Responsive Admin Theme
 * Copyright 2015 Webapplayers.com
 *
 */

/**
 * MainCtrl - controller
 */

//Main Controller
catwalkApp.controller('MainCtrl', ['$scope','$global.services','$state',
    function ($scope,$services,$state) {

    }
]);


//  Home Routing
catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/home");
        $stateProvider
            .state('home', {
                url: "/home",
                templateUrl: "components/home/home.html",
                controller: 'MainCtrl'
            })
    }
]);
