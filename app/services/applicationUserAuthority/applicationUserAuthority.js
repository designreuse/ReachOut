'use strict';

//  UserManager ApplicationUserAuthority Service
catwalkApp.factory('UserManagerApplicationUserAuthority', ['UserManagerBaseService',function (UserManagerBaseService) {
    var entityUrl = UserManagerBaseService.getEntityUrl('applicationUserAuthority');
    return UserManagerBaseService.getResource(entityUrl,{},{
        'listColumns':{method: 'POST', params:{},url:entityUrl + 'listColumns'}
    });
}
]);
