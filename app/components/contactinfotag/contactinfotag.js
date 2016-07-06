'use strict';

//  Reachout Contactinfotag Controller
catwalkApp.controller('ReachoutContactinfotagController', ['$scope','$location','$stateParams','$global.services', 'ReachoutContactinfotag',
    function ($scope,location,$stateParams,$services, service) {
        $scope.name = "Contactinfotag";
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
            location.path('/reachout/contactinfotag/');
        };

        $scope.update= function(id){
            location.path('/reachout/contactinfotag/' + id);
        };

        $scope.back = function () {
            window.history.back();
        };

        if($stateParams.id){ $scope.get($stateParams.id);}
        else{ $scope.list();}
    }
]);

//  Reachout Contactinfotag Routing
catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider
        .state('reachout.contactinfotag', {
            url: "/contactinfotag",
            templateUrl: "components/contactinfotag/contactinfotagTable.html",
            controller: 'ReachoutContactinfotagController'
        })
        .state('reachout.contactinfotagForm', {
            url: "/contactinfotag/:id",
            templateUrl: "components/contactinfotag/contactinfotagForm.html",
            controller: 'ReachoutContactinfotagController'
        })
     }
]);
