catwalkApp.config(['$stateProvider', '$urlRouterProvider', '$translateProvider','USER_ROLES',
    function ($stateProvider, $urlRouterProvider,$translateProvider,USER_ROLES) {
        $stateProvider
            .state('register', {
                url: "/register",
                templateUrl: 'views/common/security/register.html',
                controller: 'RegisterController',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            })
            .state('login', {
                url: "/login",
                templateUrl: "views/common/security/login.html",
                controller: 'LoginController',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            })
            .state('logout', {
                url: "/logout",
                templateUrl: 'components/home/home.html',
                controller: 'LogoutController',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            })
            .state('index.settings',  {
                url: "/settings",
                templateUrl: 'views/common/security/settings.html',
                controller: 'SettingsController',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            })
            .state('index.sessions',  {
                url: "/sessions",
                templateUrl: 'views/common/security/sessions.html',
                controller: 'SessionsController',
                resolve:{
                    resolvedSessions:['Sessions', function (Sessions) {
                        return Sessions.get();
                    }]
                },
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            })
            .state('index.password', {
                url: "/password",
                templateUrl: 'views/common/security/password.html',
                controller: 'PasswordController',
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            });
            // Initialize angular-translate
            $translateProvider.useStaticFilesLoader({
                prefix: 'i18n/',
                suffix: '.json'
            });
            $translateProvider.preferredLanguage('en');
            $translateProvider.useCookieStorage();
    }
]);
