//Main Controller
catwalkApp.controller('layoutCtrl', ['$scope',
    function ($scope) {
        var themes = {
            "default": "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/flatly/bootstrap.min.css",
            "cerulean" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cerulean/bootstrap.min.css",
            "cosmo" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cosmo/bootstrap.min.css",
            "cyborg" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cyborg/bootstrap.min.css",
            "darkly" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/darkly/bootstrap.min.css",
            "flatly" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/flatly/bootstrap.min.css",
            "journal" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/journal/bootstrap.min.css",
            "lumen" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/lumen/bootstrap.min.css",
            "paper" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/paper/bootstrap.min.css",
            "readable" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/readable/bootstrap.min.css",
            "sandstone" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/sandstone/bootstrap.min.css",
            "simplex" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/simplex/bootstrap.min.css",
            "slate" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/slate/bootstrap.min.css",
            "spacelab" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/spacelab/bootstrap.min.css",
            "superhero" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/superhero/bootstrap.min.css",
            "united" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/united/bootstrap.min.css",
            "yeti" : "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/yeti/bootstrap.min.css"
        };
        $scope.themesheet = $('<link href="'+themes['default']+'" rel="stylesheet" />');
        $scope.themesheet.appendTo('head');
        $scope.changeTheme = function(theme){
            $scope.themesheet.attr('href',themes[theme]);
        };
    }
]);/**
 *   Routing
 */
catwalkApp.config(['$stateProvider', '$urlRouterProvider','USER_ROLES',
    function ($stateProvider, $urlRouterProvider,USER_ROLES) {
        
        $stateProvider
            .state('index', {
                abstract: true,
                url: "/index",
                templateUrl: "components/layout/layout.html",
                controller:'layoutCtrl'
            });
    }
]).run(securityHandler);
