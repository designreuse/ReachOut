'use strict';

//  Reachout Contactinfo Service
catwalkApp.factory('ReachoutContactinfo', ['ReachoutBaseService',function (ReachoutBaseService) {
    var entityUrl = ReachoutBaseService.getEntityUrl('contactinfo');
    return ReachoutBaseService.getResource(entityUrl,{},{
        'listColumns':{method: 'POST', params:{},url:entityUrl + 'listColumns'}
    });
}
]);
