'use strict';

//  UserManager ApplicationPersistentToken Service
catwalkApp.factory('UserManagerApplicationPersistentToken', ['UserManagerBaseService',function (UserManagerBaseService) {
    var entityUrl = UserManagerBaseService.getEntityUrl('applicationPersistentToken');
    return UserManagerBaseService.getResource(entityUrl,{},{
        'listColumns':{method: 'POST', params:{},url:entityUrl + 'listColumns'}
    });
}
]);
