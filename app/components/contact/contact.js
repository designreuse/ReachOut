'use strict';

//  Reachout Contact Controller
catwalkApp.controller('ReachoutContactController', ['$scope','$location','$stateParams','$global.services', 'ReachoutContact',
    function ($scope,location,$stateParams,$services, service) {
        $scope.name = "Contact";
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
            location.path('/reachout/contact/');
        };

        $scope.update= function(id){
            location.path('/reachout/contact/' + id);
        };

        $scope.back = function () {
            window.history.back();
        };

        if($stateParams.id){ $scope.get($stateParams.id);}
        else{ $scope.list();}
    }
]);

//  Reachout Contact Routing
catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider
        .state('reachout.contact', {
            url: "/contact",
            templateUrl: "components/contact/contactTable.html",
            controller: 'ReachoutContactController'
        })
        .state('reachout.contactForm', {
            url: "/contact/:id",
            templateUrl: "components/contact/contactForm.html",
            controller: 'ReachoutContactController'
        })
     }
]);
