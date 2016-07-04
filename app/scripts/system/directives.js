/**
 * INSPINIA - Responsive Admin Theme
 * Copyright 2015 Webapplayers.com
 *
 */


/**
 * pageTitle - Directive for set Page title - mata title
 */
function pageTitle($rootScope, $timeout) {
    return {
        link: function(scope, element) {
            var listener = function(event, toState, toParams, fromState, fromParams) {
                // Default title - load on Dashboard 1
                var title = 'INSPINIA | Responsive Admin Theme';
                // Create your own title pattern
                if (toState.data && toState.data.pageTitle) title = 'INSPINIA | ' + toState.data.pageTitle;
                $timeout(function() {
                    element.text(title);
                });
            };
            $rootScope.$on('$stateChangeStart', listener);
        }
    }
};

/**
 * sideNavigation - Directive for run metsiMenu on sidebar navigation
 */
function sideNavigation($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element) {
            // Call the metsiMenu plugin and plug it to sidebar navigation
            $timeout(function(){
                element.metisMenu();
            });
        }
    };
};

/**
 * iboxTools - Directive for iBox tools elements in right corner of ibox
 */
function iboxTools($timeout) {
    return {
        restrict: 'A',
        scope: true,
        templateUrl: 'views/common/ibox_tools.html',
        controller: function ($scope, $element) {
            // Function for collapse ibox
            $scope.showhide = function () {
                var ibox = $element.closest('div.ibox');
                var icon = $element.find('i:first');
                var content = ibox.find('div.ibox-content');
                content.slideToggle(200);
                // Toggle icon from up to down
                icon.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
                ibox.toggleClass('').toggleClass('border-bottom');
                $timeout(function () {
                    ibox.resize();
                    ibox.find('[id^=map-]').resize();
                }, 50);
            },
                // Function for close ibox
                $scope.closebox = function () {
                    var ibox = $element.closest('div.ibox');
                    ibox.remove();
                }
        }
    };
};

/**
 * minimalizaSidebar - Directive for minimalize sidebar
 */
function minimalizaSidebar($timeout) {
    return {
        restrict: 'A',
        template: '<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="" ng-click="minimalize()"><i class="fa fa-bars"></i></a>',
        controller: function ($scope, $element) {
            $scope.minimalize = function () {
                $("body").toggleClass("mini-navbar");
                if (!$('body').hasClass('mini-navbar') || $('body').hasClass('body-small')) {
                    // Hide menu in order to smoothly turn on when maximize menu
                    $('#side-menu').hide();
                    // For smoothly turn on menu
                    setTimeout(
                        function () {
                            $('#side-menu').fadeIn(500);
                        }, 100);
                } else if ($('body').hasClass('fixed-sidebar')){
                    $('#side-menu').hide();
                    setTimeout(
                        function () {
                            $('#side-menu').fadeIn(500);
                        }, 300);
                } else {
                    // Remove all inline style from jquery fadeIn function to reset menu state
                    $('#side-menu').removeAttr('style');
                }
            }
        }
    };
}
function passwordStrength(){
    return {
        replace: true,
        restrict: 'E',
        template: '<div id="strength">' +
        '<small translate="password.messages.validate.newpassword.strength">Password strength:</small>' +
        '<ul id="strengthBar">' +
        '<li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li>' +
        '</ul>' +
        '</div>',
        link: function(scope, iElement, attr) {
            var strength = {
                colors: ['#F00', '#F90', '#FF0', '#9F0', '#0F0'],
                mesureStrength: function (p) {

                    var _force = 0;
                    var _regex = /[$-/:-?{-~!"^_`\[\]]/g; // "

                    var _lowerLetters = /[a-z]+/.test(p);
                    var _upperLetters = /[A-Z]+/.test(p);
                    var _numbers = /[0-9]+/.test(p);
                    var _symbols = _regex.test(p);

                    var _flags = [_lowerLetters, _upperLetters, _numbers, _symbols];
                    var _passedMatches = jQuery.grep(_flags, function (el) { return el === true; }).length;

                    _force += 2 * p.length + ((p.length >= 10) ? 1 : 0);
                    _force += _passedMatches * 10;

                    // penality (short password)
                    _force = (p.length <= 6) ? Math.min(_force, 10) : _force;

                    // penality (poor variety of characters)
                    _force = (_passedMatches == 1) ? Math.min(_force, 10) : _force;
                    _force = (_passedMatches == 2) ? Math.min(_force, 20) : _force;
                    _force = (_passedMatches == 3) ? Math.min(_force, 40) : _force;

                    return _force;

                },
                getColor: function (s) {

                    var idx = 0;
                    if (s <= 10) { idx = 0; }
                    else if (s <= 20) { idx = 1; }
                    else if (s <= 30) { idx = 2; }
                    else if (s <= 40) { idx = 3; }
                    else { idx = 4; }

                    return { idx: idx + 1, col: this.colors[idx] };
                }
            };
            scope.$watch(attr.passwordToCheck, function(password) {
                if (password) {
                    var c = strength.getColor(strength.mesureStrength(password));
                    iElement.removeClass('ng-hide');
                    iElement.find('ul').children('li')
                        .css({ "background": "#DDD" })
                        .slice(0, c.idx)
                        .css({ "background": c.col });
                }
            });
        }
    }


}
function prism(){
    return {
        restrict: 'A',
        link: function ($scope, element, attrs) {
            element.ready(function() {
                Prism.highlightElement(element[0]);
            });
        }
    }
}
catwalkApp.directive('ngPrism',['$interpolate', function ($interpolate) {
    "use strict";
    return {
        restrict: 'E',
        template: '<pre><code ng-transclude></code></pre>',
        replace: true,
        transclude: true,
        link: function (scope, elm) {
            var tmp = $interpolate(elm.find('code').text())(scope);
            if(tmp){
                elm.find('code').html(Prism.highlightElement(tmp).value);
            }
        }
    };
}]);

catwalkApp.directive('fileField', function() {
    return {
        require:'ngModel',
        restrict: 'E',
        link: function (scope, element, attrs, ngModel) {
            //set default bootstrap class
            if(!attrs.class && !attrs.ngClass){
                element.addClass('btn');
            }

            var fileField = element.find('input');

            fileField.bind('change', function(event){
                scope.$evalAsync(function () {
                    ngModel.$setViewValue(event.target.files[0]);
                    if(attrs.preview){
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            scope.$evalAsync(function(){
                                scope[attrs.preview]=e.target.result;
                            });
                        };
                        reader.readAsDataURL(event.target.files[0]);
                    }
                });
            });
            fileField.bind('click',function(e){
                e.stopPropagation();
            });
            element.bind('click',function(e){
                e.preventDefault();
                fileField[0].click()
            });
        },
        template:'<button type="button"><ng-transclude></ng-transclude><input type="file" style="display:none"></button>',
        replace:true,
        transclude:true
    };
});
 
catwalkApp.directive("imageResize", ["$parse", function($parse) {
        return {
            link: function(scope, elm, attrs) {
                var imageWidth = $parse(attrs.imageWidth)(scope);
                return elm.one("load", function() {
                    var image =  elm[0];
                    var imagePercent = (imageWidth/elm[0].width) * 100;
                    var neededHeight = image.height * imagePercent / 100;
                    var neededWidth = image.width * imagePercent / 100;
                    var canvas = document.createElement("canvas");
                    canvas.width = neededWidth;
                    canvas.height = neededHeight;
                    var ctx = canvas.getContext("2d");
                    ctx.drawImage(image, 0, 0, neededWidth, neededHeight);
                    return elm.attr('src', canvas.toDataURL("image/jpeg"));
                });
            }
        };
    }
]);
catwalkApp.directive('keyDown',['$document',function($document){
    return{
        restrict:'A',
        scope: {
            keyDown: '&'
        },
        link:function(scope,elem,attrs,ctrl){
            var elemFocus = false;
            elem.on('mouseenter',function(){
                elemFocus = true;
            });
            elem.on('mouseleave',function(){
                elemFocus = false;
            });
            $document.bind('keydown',function(e){
                if(elemFocus){
                    scope.keyDown({$event:e});
                }
            });
        }
    };
}]);
catwalkApp.directive('simpleUpload', [function ( ) {
    return {
        scope: {
            uploadFunction: '=',
            buttonId: '@'
        },
        link: function (scope, element, attrs) {
            // if button id value exists
            if (scope.buttonId) {
                jQuery('#' + scope.buttonId).on('click', function () {
                    // retrieves files from file input
                    var files = element[0].files;
                    // will not fire until file(s) are selected
                    if (files.length == 0) {
                        console.log('No files detected.');
                        return false;
                    }

                    Upload(files);
                });
            }
            else {
                // original code, trigger upload on change
                element.on('change', function (evt) {
                    var files = evt.__files_ || (evt.target && evt.target.files);

                    Upload(files);

                    // removes file(s) from input
                    jQuery(this).val('');
                });
            }

            function Upload(files) {

                var fd = new FormData();
                angular.forEach(files, function (v, k) {
                    fd.append('file', files[k]);
                });
                scope.uploadFunction(fd);

            }
        }
    }
}]);

catwalkApp.directive('slimscroll', ['$timeout', function ($timeout) {
    'use strict';

    return {
        restrict: 'A',
        link: function ($scope, $elem, $attr) {
            var off = [];
            var option = {};

            var refresh = function () {
                $timeout(function () {
                    if (angular.isDefined($attr.slimscroll)) {
                        option = $scope.$eval($attr.slimscroll) || {};
                    } else if ($attr.slimscrollOption) {
                        option = $scope.$eval($attr.slimscrollOption) || {};
                    }

                    var el = angular.element($elem);

                    el.slimScroll({ destroy: true });
                    el.slimScroll(option);
                });
            };

            angular.element($window).bind('resize', function() {
                if ($attr.slimscroll) {
                    option = $scope.$eval($attr.slimscroll);
                } else if ($attr.slimscrollOption) {
                    option = $scope.$eval($attr.slimscrollOption);
                }

                $($elem).slimScroll(option);
            });

            var registerWatch = function () {
                if (angular.isDefined($attr.slimscroll) && !option.noWatch) {
                    off.push($scope.$watchCollection($attr.slimscroll, refresh));
                }

                if ($attr.slimscrollWatch) {
                    off.push($scope.$watchCollection($attr.slimscrollWatch, refresh));
                }

                if ($attr.slimscrolllistento) {
                    off.push($scope.$on($attr.slimscrolllistento, refresh));
                }
            };

            var destructor = function () {
                angular.element($elem).slimScroll({destroy: true});
                off.forEach(function (unbind) {
                    unbind();
                });
                off = null;
            };

            off.push($scope.$on('$destroy', destructor));

            registerWatch();
        }
    };
}]);

catwalkApp.directive('setHeight', function($window){
    return{
        link: function(scope, element, attrs){
            element.css('height', $window.innerHeight - 260 + 'px');
            angular.element($window).bind('resize', function(){
                element.css('height', $window.innerHeight - 260 + 'px');
            });
        }
    }
});
/**
 *
 * Pass all functions into module
 */
catwalkApp
    .directive('prism', prism)
    .directive('pageTitle', pageTitle)
    .directive('sideNavigation', sideNavigation)
    .directive('iboxTools', iboxTools)
    .directive('minimalizaSidebar', minimalizaSidebar)
    .directive('passwordStrengthBar', passwordStrength);
