

catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider
        .state('reachout', {
            abstract: false,
            url: "/reachout",
            templateUrl: "components/admin/layout/reachout_layout.html"
        })

     }
]);
