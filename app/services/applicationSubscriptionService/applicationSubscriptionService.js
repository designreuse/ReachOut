'use strict';

//  UserManager ApplicationSubscriptionService Service
catwalkApp.factory('UserManagerApplicationSubscriptionService', ['UserManagerBaseService',function (UserManagerBaseService) {
    var entityUrl = UserManagerBaseService.getEntityUrl('applicationSubscriptionService');
    return UserManagerBaseService.getResource(entityUrl,{},{
        'listColumns':{method: 'POST', params:{},url:entityUrl + 'listColumns'}
    });
}
]);
