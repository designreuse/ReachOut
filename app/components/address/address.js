'use strict';

//  Reachout Address Controller
catwalkApp.controller('ReachoutAddressController', ['$scope','$location','$stateParams','$global.services', 'ReachoutAddress',
    function ($scope,location,$stateParams,$services, service) {
        $scope.name = "Address";
        $scope.listParams = {rows:12,page:1,defaultsearchoper:"icn"};
        $scope.srchterm = '';

        $scope.get = function(id){
            $scope.modelData = service.get({id: id});
        };
        $scope.setPage = function(page){
            $scope.listParams.page = page;
            $scope.list();
        };
        $scope.search = function(){
            if($scope.srchterm && $scope.srchterm !== '' ){
                $scope.listParams['filterByFields'] =  {'name':$scope.srchterm};
            }else{
                $scope.listParams['filterByFields'] = {};
            }
            $scope.list();
        };
        $scope.save = function(){
            service.save($scope.modelData,function(){
                  $scope.back();
            });
        };

        $scope.list = function(){
            $scope.modelList = service.query($scope.listParams);
        };

        $scope.remove = function(id){
            service.delete({id: id}, function () {

            });
        };

        $scope.new= function(){
            location.path('/reachout/address/');
        };

        $scope.update= function(id){
            location.path('/reachout/address/' + id);
        };

        $scope.back = function () {
            window.history.back();
        };

        if($stateParams.id){ $scope.get($stateParams.id);}
        else{ $scope.list();}
    }
]);

//  Reachout Address Routing
catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider
        .state('reachout.address', {
            url: "/address",
            templateUrl: "components/address/addressTable.html",
            controller: 'ReachoutAddressController'
        })
        .state('reachout.addressForm', {
            url: "/address/:id",
            templateUrl: "components/address/addressForm.html",
            controller: 'ReachoutAddressController'
        })
     }
]);
