'use strict';

//  Reachout Tag Controller
catwalkApp.controller('ReachoutTagController', ['$scope','$location','$stateParams','$global.services', 'ReachoutTag',
    function ($scope,location,$stateParams,$services, service) {
        $scope.name = "Tag";
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
            location.path('/reachout/tag/');
        };

        $scope.update= function(id){
            location.path('/reachout/tag/' + id);
        };

        $scope.back = function () {
            window.history.back();
        };

        if($stateParams.id){ $scope.get($stateParams.id);}
        else{ $scope.list();}
    }
]);

//  Reachout Tag Routing
catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider
        .state('reachout.tag', {
            url: "/tag",
            templateUrl: "components/tag/tagTable.html",
            controller: 'ReachoutTagController'
        })
        .state('reachout.tagForm', {
            url: "/tag/:id",
            templateUrl: "components/tag/tagForm.html",
            controller: 'ReachoutTagController'
        })
     }
]);
