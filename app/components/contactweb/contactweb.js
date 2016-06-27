'use strict';

//  Reachout Contactweb Controller
catwalkApp.controller('ReachoutContactwebController', ['$scope','$location','$stateParams','$global.services', 'ReachoutContactweb',
    function ($scope,location,$stateParams,$services, service) {
        $scope.name = "Contactweb";
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
            location.path('/reachout/contactweb/');
        };

        $scope.update= function(id){
            location.path('/reachout/contactweb/' + id);
        };

        $scope.back = function () {
            window.history.back();
        };

        if($stateParams.id){ $scope.get($stateParams.id);}
        else{ $scope.list();}
    }
]);

//  Reachout Contactweb Routing
catwalkApp.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider
        .state('reachout.contactweb', {
            url: "/contactweb",
            templateUrl: "components/contactweb/contactwebTable.html",
            controller: 'ReachoutContactwebController'
        })
        .state('reachout.contactwebForm', {
            url: "/contactweb/:id",
            templateUrl: "components/contactweb/contactwebForm.html",
            controller: 'ReachoutContactwebController'
        })
     }
]);
