catwalkApp.service('$global.services', [
    'ReachoutContact',
    'ReachoutAddress',
    'ReachoutCompany',
    'ReachoutContactphone',
    'ReachoutContactemail',
    'ReachoutContactinfo',
    'ReachoutContactweb',
    'ReachoutTask',
    'UserManagerApplicationAuthority',
    'UserManagerApplicationPersistentToken',
    'UserManagerApplicationUser',
    'UserManagerApplicationMailServer',
    'UserManagerApplicationSubscriptionService',
    'UserManagerApplicationUserAuthority',
    'Account',
function(
    ReachoutContact,
    ReachoutAddress,
    ReachoutCompany,
    ReachoutContactphone,
    ReachoutContactemail,
    ReachoutContactinfo,
    ReachoutContactweb,
    ReachoutTask,
    UserManagerApplicationAuthority,
    UserManagerApplicationPersistentToken,
    UserManagerApplicationUser,
    UserManagerApplicationMailServer,
    UserManagerApplicationSubscriptionService,
    UserManagerApplicationUserAuthority,
    Account
) {
            this.ReachoutContact = ReachoutContact;
            this.ReachoutAddress = ReachoutAddress;
            this.ReachoutCompany = ReachoutCompany;
            this.ReachoutContactphone = ReachoutContactphone;
            this.ReachoutContactemail = ReachoutContactemail;
            this.ReachoutContactinfo = ReachoutContactinfo;
            this.ReachoutContactweb = ReachoutContactweb;
            this.ReachoutTask = ReachoutTask;
            this.UserManagerApplicationAuthority = UserManagerApplicationAuthority;
            this.UserManagerApplicationPersistentToken = UserManagerApplicationPersistentToken;
            this.UserManagerApplicationUser = UserManagerApplicationUser;
            this.UserManagerApplicationMailServer = UserManagerApplicationMailServer;
            this.UserManagerApplicationSubscriptionService = UserManagerApplicationSubscriptionService;
            this.UserManagerApplicationUserAuthority = UserManagerApplicationUserAuthority;
            this.Account = Account;
}
]);
