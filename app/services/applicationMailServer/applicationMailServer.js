'use strict';

//  UserManager ApplicationMailServer Service
catwalkApp.factory('UserManagerApplicationMailServer', ['UserManagerBaseService',function (UserManagerBaseService) {
    var entityUrl = UserManagerBaseService.getEntityUrl('applicationMailServer');
    return UserManagerBaseService.getResource(entityUrl,{},{
        'listColumns':{method: 'POST', params:{},url:entityUrl + 'listColumns'}
    });
}
]);
