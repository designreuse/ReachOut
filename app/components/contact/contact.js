'use strict';

//  Reachout Contact Controller
catwalkApp.controller('ReachoutContactController', ['$scope','$location','$stateParams','$global.services', 'ReachoutContact', '$http',
    function ($scope,location,$stateParams,$services, service, $http) {
        $scope.name = "Contact";
        $scope.listParams = {sidx:'firstName',rows:12,page:1,defaultsearchoper:"icn",or:true};
        $scope.srchterm = '';
        $scope.phones = [{phone:'',type:'Office'}];
        $scope.emails = [{email:'',type:'Office'}];
        $scope.urls = [{webUrl:'',type:'Website'}];
        $scope.imageSrc ="";
        
        $scope.addPhone=function(){
            $scope.phones.push({phone:'',type:'Home'});
        };
        $scope.addEmail=function(){
            $scope.emails.push({email:'',type:'Home'});
        };
        $scope.addUrl=function(){
            $scope.urls.push({webUrl:'',type:'Linkedin'});
        };
        $scope.onFilesSelected=function(files){
            $scope.file = files[0].File;
            
             
        };
        $scope.get = function(id){
            service.get({id: id},function(contact){
                $scope.modelData = contact;
                if(contact && contact.contactInfo){
                    $services.ReachoutContactphone.query({filterByFields:{'contactInfo.id':contact.contactInfo.id}},function(data){
                        if(data && data.totalrecords){
                            $scope.phones = data.rows;
                        }
                    });
                    $services.ReachoutContactemail.query({filterByFields:{'contactInfo.id':contact.contactInfo.id}},function(data){

                        if(data && data.totalrecords > 0){
                            $scope.emails = data.rows;
                        }
                    });
                    $services.ReachoutContactweb.query({filterByFields:{'contactInfo.id':contact.contactInfo.id}},function(data){
                        if(data && data.totalrecords){
                            $scope.urls = data.rows;
                        }
                    });
                }
                if(contact.imgSrc){
                    $scope.imageSrc =contact.imgSrc;
                }

            });


        };
        $scope.getLocation = function(val) {
            return $http.get('//maps.googleapis.com/maps/api/geocode/json', {
                params: {
                    address: val,
                    sensor: false
                },
                withCredentials:false
            }).then(function(response){
                return response.data.results.map(function(item){
                    return item.formatted_address;
                });
            });
        };
        $scope.companySelected = function(item, model, label){
            $scope.modelData.company = item;
        };
        $scope.getCompany= function(val) {
            return $services.ReachoutCompany.query({
                    defaultsearchoper:"icn",
                    filterByFields:{name:val}
            }).$promise.then(
                function(response){
                    return response.rows;
                }
            );
        };

        $scope.setPage = function(page){
            $scope.listParams.page = page;
            $scope.list();
        };
        $scope.search = function(){
            if($scope.srchterm && $scope.srchterm !== '' ){
                $scope.listParams['filterByFields'] =  {'firstName':$scope.srchterm,'lastName':$scope.srchterm,'company.name':$scope.srchterm};
            }else{
                $scope.listParams['filterByFields'] = {};
            }
            $scope.list();
        };
        $scope.save = function(){
             
            if(!$scope.modelData['contactInfo']){
                $scope.modelData['contactInfo'] ={};
            }
            $scope.modelData['contactInfo']['phones'] = $scope.phones;
            $scope.modelData['contactInfo']['emails'] = $scope.emails;
            $scope.modelData['contactInfo']['urls'] = $scope.urls;
            $scope.modelData['imgSrc'] = $scope.imageSrc;
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
