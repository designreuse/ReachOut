'use strict';

//  UserManager ApplicationAuthority Service
catwalkApp.factory('UserManagerApplicationAuthority', ['UserManagerBaseService',function (UserManagerBaseService) {
    var entityUrl = UserManagerBaseService.getEntityUrl('applicationAuthority');
    return UserManagerBaseService.getResource(entityUrl,{},{
        'listColumns':{method: 'POST', params:{},url:entityUrl + 'listColumns'}
    });
}
]);
