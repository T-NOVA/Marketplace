angular.module('dashboard', ['ui.bootstrap', 'ngSanitize', 'angularModalService', 'ui.select', 'ui.checkbox', 'ui-rangeSlider', 'ngCookies', 'ui.router', 'restangular', 'ngFileUpload', 'ui.codemirror', 'highcharts-ng']);

angular.module('dashboard').run(['$state', '$rootScope', '$timeout', '$cookies', 'Restangular', 'alertService', function ($state, $rootScope, $timeout, $cookies, Restangular, alertService) {
    $rootScope.isAuthorized = false;
    $rootScope.$state = $state;
    $rootScope.alerts = alertService.get();
    $rootScope.root_loading = false;

    var appStarted = 0; // flag to redirect only once when app is started
    $rootScope.$on('$stateChangeStart',
        function (event, toState, toParams, fromState, fromParams) {
            if (appStarted) return;
            appStarted = 1;
            event.preventDefault(); //prevents from resolving requested url

            var token = $cookies.get('token');
            if (token) {
                console.log("JWT token exists");
                Restangular.setDefaultHeaders({Authorization: 'JWT ' + token});
                $rootScope.isAuthorized = true;
                //$state.go('index.services.new');
                $state.go('index.dashboard');
            } else {
                console.log("JWT not found");
                $state.go('login');
            }

        });
}]);

// change {{ }} default template tags to [[ ]] because conflict with django's template tags
angular.module('dashboard').config(function ($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});

// setup restangular base url
angular.module('dashboard').config(function (RestangularProvider) {
    //RestangularProvider.setBaseUrl('http://10.10.1.90');
    RestangularProvider.setBaseUrl('http://localhost:8080');
    RestangularProvider.setRequestSuffix('\/');
});

angular.module('dashboard').factory('NoSuffixRestangular', function(Restangular) {
  return Restangular.withConfig(function(RestangularConfigurer) {
    RestangularConfigurer.setRequestSuffix('');
  });
});

// Main Controllers
//angular.module('dashboard').controller('AlertCtrl', ['$scope', 'alertService', AlertCtrl]);
angular.module('dashboard').controller('RootCtrl', ['Restangular', '$scope', '$rootScope', '$cookies', '$state', '$interval', RootCtrl]);
angular.module('dashboard').controller('LoginCtrl', ['Restangular', '$scope', '$rootScope', '$cookies', 'alertService', LoginCtrl]);
angular.module('dashboard').controller('RegisterCtrl', ['Restangular', '$scope', '$rootScope', 'alertService', RegisterCtrl]);


//function AlertCtrl($scope, alertService){
//    $scope.alerts = alertService.get();
//}

function RootCtrl(Restangular, $scope, $rootScope, $cookies, $state, $interval) {


    //$scope.root_loading = false;

    // check if is not authorized
    if (!$rootScope.isAuthorized) {
        $rootScope.$state.go('login');
    }

    $scope.available_groups = [];
    $scope.available_countries = [];
    $scope.user_permissions = [];
    $scope.user_profile = {};

    $scope.getGroupName = function (group_id) {
        var group_name = '';

        angular.forEach($scope.available_groups, function (value, key) {
            if (value.id == group_id) group_name = value.name;
        });

        return group_name;
    };

    $scope.getGroupID = function (group_name) {
        var group_id = -1;

        angular.forEach($scope.available_groups, function (value, key) {
            if (value.name == group_name) group_id = value.id;
        });

        return group_id;
    };

    $scope.hasPerm = function (perm) {
        var has_perm = false;

        angular.forEach($scope.user_permissions, function (value, key) {
            if (value == perm) has_perm = true;
        });

        return has_perm;
    };

    $scope.hasRole = function (role) {
        var has_role = false;
        var group_id = $scope.getGroupID(role);

        angular.forEach($scope.user_profile.groups, function (value, key) {
            if (value == group_id) has_role = true;
        });

        return has_role;
    };

    $scope.init = function () {

        Restangular.one("/user-management/countries/").getList().then(
            function (response) {
                $scope.available_countries = response.plain();
            }, function (response) {
                console.log("GetCountries error with status code " + response.status);
                console.log("GetCountries error message: " + response.data.detail);
            });

        Restangular.one("/user-management/groups/").getList().then(
            function (response) {
                $scope.available_groups = response.plain();
                console.log("GetGroups has been successfully loaded");
            }, function (response) {
                console.log("GetGroups error with status code " + response.status);
                console.log("GetGroups error message: " + response.data.detail);
            });

        Restangular.one("/user-management/profile/").get().then(
            function (response) {
                $scope.user_profile = response;
                console.log("GetUserProfile has been successfully loaded");

    if ($scope.hasRole('Function Provider')){

                 $interval(function () {


                    Restangular.all('/broker/vnfs/trade/').getList().then(
                        function (response) {
                            $scope.trades = response;
                            console.log("tradesList " + response.length + " Trades found");
                        }, function (response) {
                            console.log("tradesList error with status code " + response.status);
                            console.log("tradesList error message: " + response.data.detail);
                        });

                 }, 2000);
    }

            }, function (response) {
                console.log("GetUserProfile error with status code " + response.status);
                console.log("GetUserProfile error message: " + response.data.detail);
            });

        Restangular.one("/user-management/profile/permissions/").get().then(
            function (response) {
                $scope.user_permissions = response.plain();
                console.log("GetUserProfilePermissions has been successfully loaded");
            }, function (response) {
                console.log("GetUserProfilePermissions error with status code " + response.status);
                console.log("GetUserProfilePermissions error message: " + response.data.detail);
            });
    };

    $scope.trades = [];
    $scope.getPendingRequests = function(){

        var pending_requests = 0;

        angular.forEach($scope.trades, function (trade, trade_key) {

            if (trade.status=='pending') {
                pending_requests++;
            }

        });

        return pending_requests;
    };

    $scope.acceptTradeRequest = function (id) {

        Restangular.one("/broker/vnfs/trade/"+id+"/accept").get().then(
            function (response) {
                console.log("Trade Request " + response.id + " accepted");
            }, function (response) {
                console.log("Accept Trade Request error with status code " + response.status);
                console.log("Accept Trade Request error message: " + response.data.detail);
            });

    };

    $scope.rejectTradeRequest = function (id) {

        Restangular.one("/broker/vnfs/trade/"+id+"/reject").get().then(
            function (response) {
                console.log("Trade Request " + response.id + " rejected");
            }, function (response) {
                console.log("Reject Trade Request error with status code " + response.status);
                console.log("Reject Trade Request error message: " + response.data.detail);
            });

    };

    $scope.init();

    // logout
    $scope.logout = function () {
        $cookies.remove('token');
        $rootScope.isAuthorized = false;
        $state.go('login');
        //$mdToast.show($mdToast.simple().content('You have successfully logged out').position('top right').hideDelay(3000));
    };

    /**
     * Sidebar Toggle & Cookie Control
     */
    var mobileView = 992;

    $scope.getWidth = function () {
        return window.innerWidth;
    };

    $scope.$watch($scope.getWidth, function (newValue, oldValue) {
        if (newValue >= mobileView) {
            if (angular.isDefined($cookies.get('toggle'))) {
                $scope.toggle = !$cookies.get('toggle') ? false : true;
            } else {
                $scope.toggle = true;
            }
        } else {
            $scope.toggle = false;
        }

    });

    $scope.toggleSidebar = function () {
        $scope.toggle = !$scope.toggle;
        $cookies.put('toggle', $scope.toggle);
    };

    window.onresize = function () {
        $scope.$apply();
    };

}


function LoginCtrl(Restangular, $scope, $rootScope, $cookies, alertService) {

    $scope.init = function () {
        // check if is already authorized
        if ($rootScope.isAuthorized) {
            $rootScope.$state.go('index.dashboard');
        }
    };

    $scope.init();

    $scope.login = {};
    $scope.doLogin = function () {
        Restangular.one('auth').customPOST({username: $scope.login.username, password: $scope.login.password}).then(
            function (response) {
                Restangular.setDefaultHeaders({Authorization: 'JWT ' + response.token});

                var exp_date = new Date();
                exp_date.setDate(exp_date.getDate + 15); //15 days exp date
                $cookies.put('token', response.token, {expires: exp_date});

                $rootScope.isAuthorized = true;
                $rootScope.$state.go('index.dashboard');

            }, function (response) {
                console.log("Authentication error with status code " + response.status);
                if (response.status == 400) {
                    var error_message = response.data.non_field_errors[0];
                    console.log("Authentication error message: " + error_message);
                    alertService.add('danger', error_message);
                    //$alert({title: '',content: error_message,placement: 'top-right',type: 'danger',show: true,duration: 3});
                }
            });
    };

}

function RegisterCtrl(Restangular, $scope, $rootScope, alertService) {

    $scope.init = function () {
        // check if is already authorized
        if ($rootScope.isAuthorized) {
            $rootScope.$state.go('index.dashboard');
        }
    };

    $scope.init();

    $scope.user = {};
    $scope.user.group = '2';

    $scope.doRegister = function () {

        console.log($scope.user);
        $scope.user.groups = [$scope.user.group];

        Restangular.one('/user-management/register/').customPOST($scope.user).then(
            function (response) {
                //Restangular.setDefaultHeaders({Authorization: 'JWT ' + response.token});
                //
                //var exp_date = new Date();
                //exp_date.setDate(exp_date.getDate + 1); //1 days exp date
                //$cookies.put('token', response.token, {expires: exp_date});

                //$alert({title: '',content: 'You have successfully registered!', placement:'top-right',type:'success',show: true,duration: 3});
                alertService.add('success', 'You have successfully registered!');
                $rootScope.$state.go('login');

            }, function (response) {
                console.log("Registration error with status code " + response.status);
                if (response.status == 400) {
                    alertService.add('danger', 'Registration failed.');

                    var html_error = '<ul>';
                    angular.forEach(response.data, function (value, key) {
                        html_error = html_error+ '<li> Invalid '+key+'</li>';
                    });
                    html_error = html_error+ '</ul>';

                    alertService.add('danger', html_error);

                    //var error_message = response.data.non_field_errors[0];
                    //console.log("Registration errors: " + response.data);
                    //$alert({title: '',content: 'Registration failed.', placement: 'top-right',type: 'danger',show: true,duration: 3});
                }
            });
    };

}