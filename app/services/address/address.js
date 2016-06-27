'use strict';

//  Reachout Address Service
catwalkApp.factory('ReachoutAddress', ['ReachoutBaseService',function (ReachoutBaseService) {
    var entityUrl = ReachoutBaseService.getEntityUrl('address');
    return ReachoutBaseService.getResource(entityUrl,{},{
        'listColumns':{method: 'POST', params:{},url:entityUrl + 'listColumns'}
    });
}
]);
