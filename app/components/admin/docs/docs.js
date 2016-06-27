catwalkApp.controller('DocsController', ['$scope','$global.services',
    function ($scope,$services) {

    }
]);

//  Home Routing
catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('admin.docs', {
                url: "/docs",
                templateUrl: "components/admin/docs/docs.html",
                controller: "DocsController"
            })
            .state('admin.docs.intro', {
                url: "/intro",
                templateUrl: "components/admin/docs/intro.html",
                controller: "DocsController"
            })
            .state('admin.docs.services', {
                url: "/services",
                templateUrl: "components/admin/docs/services.html",
                controller: "DocsController"
            })
    }
]);
