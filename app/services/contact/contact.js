'use strict';

//  Reachout Contact Service
catwalkApp.factory('ReachoutContact', ['ReachoutBaseService',function (ReachoutBaseService) {
    var entityUrl = ReachoutBaseService.getEntityUrl('contact');
    return ReachoutBaseService.getResource(entityUrl,{},{
        'listColumns':{method: 'POST', params:{},url:entityUrl + 'listColumns'},
        'importCsv': {
            method: 'POST',
            url:entityUrl + 'importCsv',
            transformRequest: formDataObject,
            headers: {'Content-Type':undefined, enctype:'multipart/form-data'}
        }
    });

    function formDataObject (data) {
        /* console.log(data);
         var fd = new FormData();
         angular.forEach(data, function(value, key) {
         fd.append(key, value);
         });*/
        return data;
    }
}
]);
