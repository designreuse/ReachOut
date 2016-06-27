

'use strict';
var reachout_base_url  = base_url  + 'rest/reachout/';
catwalkApp.factory('ReachoutBaseService', ['$resource',
    function ($resource) {
            var factory = {};
            factory.getBaseUrl = function(){return reachout_base_url;};
            factory.getEntityUrl = function(entity){ return factory.getBaseUrl() + entity + "/";};
            factory.getResource = function(url,paramDefaults,actions,options){
                actions=angular.extend(actions,{
                    'query':{method:'GET',
                        transformResponse:function(data,headers){
                            return JSON.parse(data);
                        }
                    },
                    'get':{method:'GET'}
                    });
                return $resource(url+':id',paramDefaults,actions,options);
            };
            return factory;
    }
]);
