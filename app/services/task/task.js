'use strict';

//  Reachout Task Service
catwalkApp.factory('ReachoutTask', ['ReachoutBaseService',function (ReachoutBaseService) {
    var entityUrl = ReachoutBaseService.getEntityUrl('task');
    return ReachoutBaseService.getResource(entityUrl,{},{
        'listColumns':{method: 'POST', params:{},url:entityUrl + 'listColumns'}
    });
}
]);
