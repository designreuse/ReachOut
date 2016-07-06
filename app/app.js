/**
 *   Make Maven Grunt !!.
 *   type maven package on the command line. will take care of everything. Note: the 2 lines below are for fine control
 *   type bower install on the command line. will install all of the dependent javascript libraries.
 *   type grunt build on the command line. will build a minimized version of the client in the web app folder
 *
 */
var catwalkApp  = angular.module('catwalk', [
    'ui.router',                    // Routing
    'ui.bootstrap',                 // Styling
    'trNgGrid',                     // Tables
    'ngResource',                   // Ajax Server Calls
    'ngCookies',                    // Security Tokens
    'http-auth-interceptor',        // Security
    'pascalprecht.translate',        // Different Languages
    'ngTagsInput'
]);
var base_url = 'http://192.168.1.163:8080/reachout/'; //rest/catwalk/''../${projectname}/ '../ReachOut/'
