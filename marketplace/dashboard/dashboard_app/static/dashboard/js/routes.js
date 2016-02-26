// states definition
angular.module('dashboard').config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/index/dashboard");

    $stateProvider
        .state('login', {
            url: "/login",
            templateUrl: "/static/dashboard/templates/login.html"
        })
        .state('register', {
            url: "/register",
            templateUrl: "/static/dashboard/templates/register.html"
        })
        .state('index', {
            url: "/index",
            templateUrl: "/static/dashboard/templates/index.html"
        })
        .state('index.profile', {
            url: "/profile",
            templateUrl: "/static/dashboard/templates/index.profile.html"
        })
        .state('index.dashboard', {
            url: "/dashboard",
            templateUrl: "/static/dashboard/templates/index.dashboard.html"
        })
        .state('index.users', {
            url: "/users",
            templateUrl: "/static/dashboard/templates/index.users.html"
        })
        .state('index.users.list', {
            url: "/list",
            templateUrl: "/static/dashboard/templates/index.users.list.html"
        })
        .state('index.users.new', {
            url: "/new",
            templateUrl: "/static/dashboard/templates/index.users.new.html"
        })
        .state('index.users.edit', {
            url: "/{userID:int}/edit",
            templateUrl: "/static/dashboard/templates/index.users.edit.html",
            controller: function ($scope, $stateParams) {
                $scope.userID = $stateParams.userID;
            }
        })
        .state('index.vnfs', {
            url: "/vnfs",
            templateUrl: "/static/dashboard/templates/index.vnfs.html"
        })
        .state('index.vnfs.list', {
            url: "/list",
            templateUrl: "/static/dashboard/templates/index.vnfs.list.html"
        })
        .state('index.vnfs.new', {
            url: "/new",
            templateUrl: "/static/dashboard/templates/index.vnfs.new.html"
        })
        .state('index.vnfs.edit', {
            url: "/{vnfdID:int}/edit",
            templateUrl: "/static/dashboard/templates/index.vnfs.edit.html",
            controller: function ($scope, $stateParams) {
                $scope.vnfdID = $stateParams.vnfdID;
            }
        })
        .state('index.images', {
            url: "/images",
            templateUrl: "/static/dashboard/templates/index.images.html"
        })
        .state('index.images.list', {
            url: "/list",
            templateUrl: "/static/dashboard/templates/index.images.list.html"
        })
        .state('index.services', {
            url: "/services",
            templateUrl: "/static/dashboard/templates/index.services.html"
        })
        .state('index.services.list', {
            url: "/list",
            templateUrl: "/static/dashboard/templates/index.services.list.html"
        })
        .state('index.services.new', {
            url: "/new",
            templateUrl: "/static/dashboard/templates/index.services.new.html"
        })
        .state('index.buy-service', {
            url: "/buy-service",
            templateUrl: "/static/dashboard/templates/index.buy-service.html"
        })
        .state('index.purchase-service', {
            url: "/purchase-service/{nsdID}",
            templateUrl: "/static/dashboard/templates/index.buy-service.new.html",
            controller: function ($scope, $stateParams) {
                $scope.nsdID = $stateParams.nsdID;
            }
        })
        .state('index.customer-services', {
            url: "/customer-services",
            templateUrl: "/static/dashboard/templates/index.customer-services.html"
        })
        .state('index.trade-requests', {
            url: "/trade-requests",
            templateUrl: "/static/dashboard/templates/index.trade-requests.html"
        })
        .state('index.billing', {
            url: "/billing",
            templateUrl: "/static/dashboard/templates/index.billing.html"
        })
        .state('index.billingrev', {
            url: "/billing_rev",
            templateUrl: "/static/dashboard/templates/index.billing.rev.html"
        })
        .state('index.sla', {
            url: "/sla",
            templateUrl: "/static/dashboard/templates/index.sla.html"
        })
        .state('index.sla.list', {
            url: "/list",
            templateUrl: "/static/dashboard/templates/index.sla.list.html"
        })
        .state('index.sla.charts', {
            url: "/charts/{agreementID}",
            templateUrl: "/static/dashboard/templates/index.sla.charts.html",
            controller: function ($scope, $stateParams) {
                $scope.agreementID = $stateParams.agreementID;
            }
        })
        .state('index.monitoring', {
            url: "/monitoring",
            templateUrl: "/static/dashboard/templates/index.monitoring.html"
        });



});