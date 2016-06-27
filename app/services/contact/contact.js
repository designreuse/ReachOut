'use strict';

//  Reachout Contact Service
catwalkApp.factory('ReachoutContact', ['ReachoutBaseService',function (ReachoutBaseService) {
    var entityUrl = ReachoutBaseService.getEntityUrl('contact');
    return ReachoutBaseService.getResource(entityUrl,{},{
        'listColumns':{method: 'POST', params:{},url:entityUrl + 'listColumns'}
    });
}
]);
