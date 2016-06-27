'use strict';

//  UserManager ApplicationUser Controller
catwalkApp.controller('UserController', ['$scope','$location','$stateParams','$global.services', 'UserManagerApplicationUser',
    function ($scope,location,$stateParams,$services, service) {
        $scope.name = "ApplicationUser";
        $scope.listParams = {rows:12,page:1,defaultsearchoper:"icn"};
        $scope.srchterm = '';

        $scope.get = function(id){
            $scope.modelData = service.get({id: id});
        };

        $scope.save = function(){
            service.save($scope.modelData,function(){
                $scope.back();
            });
        };
        $scope.setPage = function(page){
            $scope.listParams.page = page;
            $scope.list();
        };
        $scope.search = function(){
            if($scope.srchterm && $scope.srchterm !== '' ){
                $scope.listParams['filterByFields'] =  {'login':$scope.srchterm};
            }else{
                $scope.listParams['filterByFields'] = {};
            }
            $scope.list();
        };
        $scope.list = function(){

            $scope.modelList = service.query($scope.listParams,function(){
                console.log($scope.modelList);
            });
        };

        $scope.remove = function(id){
            service.delete({id: id}, function () {

            });
        };

        $scope.new= function(){
            location.path('/admin/admin/user/');
        };

        $scope.update= function(id){
            location.path('/admin/admin/user/' + id);
        };

        $scope.back = function () {
            window.history.back();
        };

        if($stateParams.id){ $scope.get($stateParams.id);}
        else{ $scope.list();}
    }
]);

//  UserManager ApplicationUser Routing
catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('admin.users', {
                url: "/admin/users",
                templateUrl: "components/admin/users/users.html",
                controller: 'UserController'
            })
            .state('admin.user', {
                url: "/admin/user/:id",
                templateUrl: "components/admin/users/userform.html",
                controller: 'UserController'
            })
    }
]);
