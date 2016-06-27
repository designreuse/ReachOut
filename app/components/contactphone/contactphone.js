'use strict';

//  Reachout Contactphone Controller
catwalkApp.controller('ReachoutContactphoneController', ['$scope','$location','$stateParams','$global.services', 'ReachoutContactphone',
    function ($scope,location,$stateParams,$services, service) {
        $scope.name = "Contactphone";
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
            location.path('/reachout/contactphone/');
        };

        $scope.update= function(id){
            location.path('/reachout/contactphone/' + id);
        };

        $scope.back = function () {
            window.history.back();
        };

        if($stateParams.id){ $scope.get($stateParams.id);}
        else{ $scope.list();}
    }
]);

//  Reachout Contactphone Routing
catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider
        .state('reachout.contactphone', {
            url: "/contactphone",
            templateUrl: "components/contactphone/contactphoneTable.html",
            controller: 'ReachoutContactphoneController'
        })
        .state('reachout.contactphoneForm', {
            url: "/contactphone/:id",
            templateUrl: "components/contactphone/contactphoneForm.html",
            controller: 'ReachoutContactphoneController'
        })
     }
]);
