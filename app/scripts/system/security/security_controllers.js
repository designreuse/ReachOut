catwalkApp.controller('ActivationController', function ($scope, $routeParams, Activate) {
    Activate.get({key: $routeParams.key},
        function (value, responseHeaders) {
            $scope.error = null;
            $scope.success = 'OK';
        },
        function (httpResponse) {
            $scope.success = null;
            $scope.error = "ERROR";
        });
});
catwalkApp.controller('RegisterController', function ($scope, $translate, Register) {
    $scope.success = null;
    $scope.error = null;
    $scope.doNotMatch = null;
    $scope.errorUserExists = null;
    $scope.register = function () {
        if ($scope.registerAccount.password != $scope.confirmPassword) {
            $scope.doNotMatch = "ERROR";
        } else {
            $scope.registerAccount.langKey = $translate.use();
            $scope.doNotMatch = null;
            Register.save($scope.registerAccount,
                function (value, responseHeaders) {
                    $scope.error = null;
                    $scope.errorUserExists = null;
                    $scope.success = 'OK';
                },
                function (httpResponse) {
                    $scope.success = null;
                    if (httpResponse.status === 304 &&
                        httpResponse.data.error && httpResponse.data.error === "Not Modified") {
                        $scope.error = null;
                        $scope.errorUserExists = "ERROR";
                    } else {
                        $scope.error = "ERROR";
                        $scope.errorUserExists = null;
                    }
                });
        }
    }
});
catwalkApp.controller('LoginController', ['$scope', '$rootScope', 'AuthenticationSharedService',
    function ($scope, $rootScope, AuthenticationSharedService) {
        $scope.rememberMe = true;
        $scope.login = function () {
            AuthenticationSharedService.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe,
                success:function(data){

                }
            })
        }
    }]);

catwalkApp.controller('LogoutController', function ($location, AuthenticationSharedService) {
    AuthenticationSharedService.logout();
});
catwalkApp.controller('SettingsController', ['$scope' ,'Account',
    function ($scope,Account) {
        $scope.success = null;
        $scope.error = null;
        $scope.settingsAccount = Account.get();

        $scope.save = function () {



            Account.save($scope.settingsAccount,
                function (value, responseHeaders) {
                    $scope.error = null;
                    $scope.success = 'OK';
                    $scope.settingsAccount = Account.get();
                    $scope.log("Success");

                },
                function (httpResponse) {
                    $scope.success = null;
                    $scope.error = "ERROR";
                    $scope.log(httpResponse);
                });
        };
    }]);

catwalkApp.controller('PasswordController', ['$scope', 'Password',
    function ($scope, Password) {
        $scope.success = null;
        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.changePassword = function () {
            if ($scope.password != $scope.confirmPassword) {
                $scope.doNotMatch = "ERROR";
            } else {
                $scope.doNotMatch = null;
                Password.save({'password':$scope.password},
                    function (value, responseHeaders) {
                        $scope.error = null;
                        $scope.success = 'OK';
                    },
                    function (httpResponse) {
                        $scope.success = null;
                        $scope.error = "ERROR";
                    });
            }
        };
    }]);

catwalkApp.controller('SessionsController', ['$scope', 'resolvedSessions', 'Sessions',
    function ($scope, resolvedSessions, Sessions) {
        $scope.success = null;
        $scope.error = null;
        $scope.sessions = resolvedSessions;
        $scope.invalidate = function (series) {
            Sessions.delete({series: encodeURIComponent(series)},
                function (value, responseHeaders) {
                    $scope.error = null;
                    $scope.success = "OK";
                    $scope.sessions = Sessions.get();
                },
                function (httpResponse) {
                    $scope.success = null;
                    $scope.error = "ERROR";
                });
        };
    }]);


