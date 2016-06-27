'use strict';

//  Reachout Contactemail Controller
catwalkApp.controller('ReachoutContactemailController', ['$scope','$location','$stateParams','$global.services', 'ReachoutContactemail',
    function ($scope,location,$stateParams,$services, service) {
        $scope.name = "Contactemail";
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
            location.path('/reachout/contactemail/');
        };

        $scope.update= function(id){
            location.path('/reachout/contactemail/' + id);
        };

        $scope.back = function () {
            window.history.back();
        };

        if($stateParams.id){ $scope.get($stateParams.id);}
        else{ $scope.list();}
    }
]);

//  Reachout Contactemail Routing
catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider
        .state('reachout.contactemail', {
            url: "/contactemail",
            templateUrl: "components/contactemail/contactemailTable.html",
            controller: 'ReachoutContactemailController'
        })
        .state('reachout.contactemailForm', {
            url: "/contactemail/:id",
            templateUrl: "components/contactemail/contactemailForm.html",
            controller: 'ReachoutContactemailController'
        })
     }
]);
