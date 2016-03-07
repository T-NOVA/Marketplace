angular.module('dashboard').controller('BillingCtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', 'alertService', BillingCtrl]);
angular.module('dashboard').controller('BillingRevCtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', 'alertService', BillingRevCtrl]);

angular.module('dashboard').controller('SLACtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', SLACtrl]);
angular.module('dashboard').controller('SLAChartsCtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', '$state', '$stateParams', SLAChartsCtrl]);
angular.module('dashboard').controller('MonitoringCtrl', ['Restangular','$scope', '$rootScope', MonitoringCtrl]);


function BillingCtrl(Restangular, NoSuffixRestangular, $scope, $rootScope, alertService) {

    $scope.billing = {};

    $scope.loadBill = function () {

        var from_date = moment($scope.from_dt).format('YYYY-MM-DD%2000:00:00');
        var to_date = moment($scope.to_dt).format('YYYY-MM-DD%2023:59:59');

        var username = $scope.user_profile.username;
        var user_id = $scope.user_profile.id;

        $rootScope.root_loading = true;
        NoSuffixRestangular.one("/billing/bill?userId="+username+"&from="+from_date+"&to="+to_date).get().then(
            function (response) {
                $scope.billing = response;
                $rootScope.root_loading = false;
                console.log("Billing has been successfully loaded");
            }, function (response) {
                console.log("Billing error with status code " + response.status);
                console.log("Billing error message: " + response.data);
                $rootScope.root_loading = false;
                alertService.add('danger', 'Failed to retrieve billing information.');
            });
    };

    $scope.init = function () {
        //$scope.loadBill();
    };

    $scope.init();

    $scope.from_dt = new Date();
    $scope.to_dt = new Date();

    $scope.dateOptions = {
        formatYear: 'yy',
        maxDate: new Date(),
        startingDay: 1
    };

    $scope.open1 = function () {
        $scope.popup1.opened = true;
    };

    $scope.open2 = function () {
        $scope.popup2.opened = true;
    };

    $scope.popup1 = {
        opened: false
    };

    $scope.popup2 = {
        opened: false
    };

    $scope.format = 'yyyy-MM-dd';

    $scope.$watch("from_dt", function(newValue, oldValue) {
        $scope.loadBill();
    });

    $scope.$watch("to_dt", function(newValue, oldValue) {
        $scope.loadBill();
    });

}

function BillingRevCtrl(Restangular, NoSuffixRestangular, $scope, $rootScope, alertService) {

    $scope.billing = {};

    $scope.loadBillRevenue = function () {

        var from_date = moment($scope.from_dt).format('YYYY-MM-DD%2000:00:00');
        var to_date = moment($scope.to_dt).format('YYYY-MM-DD%2023:59:59');

        var username = $scope.user_profile.username;
        var user_id = $scope.user_profile.id;

        $rootScope.root_loading = true;
        NoSuffixRestangular.one("/billing/revenue?provider="+username+"&from="+from_date+"&to="+to_date).get().then(
            function (response) {
                $scope.billing = response;
                $rootScope.root_loading = false;
                console.log("BillingRevenue has been successfully loaded");
            }, function (response) {
                console.log("BillingRevenue error with status code " + response.status);
                console.log("BillingRevenue error message: " + response.data);
                alertService.add('danger', 'Failed to retrieve billing information.');
                $rootScope.root_loading = false;
            });
    };

    $scope.init = function () {
        //$scope.loadBillRevenue();
    };

    $scope.init();

    $scope.from_dt = new Date();
    $scope.to_dt = new Date();

    $scope.dateOptions = {
        formatYear: 'yy',
        maxDate: new Date(),
        startingDay: 1
    };

    $scope.open1 = function () {
        $scope.popup1.opened = true;
    };

    $scope.open2 = function () {
        $scope.popup2.opened = true;
    };

    $scope.popup1 = {
        opened: false
    };

    $scope.popup2 = {
        opened: false
    };

    $scope.format = 'yyyy-MM-dd';

    $scope.$watch("from_dt", function(newValue, oldValue) {
        $scope.loadBillRevenue();
    });

    $scope.$watch("to_dt", function(newValue, oldValue) {
        $scope.loadBillRevenue();
    });
}


function SLACtrl(Restangular, NoSuffixRestangular, $scope, $rootScope) {

    $scope.loading_sla= false;
    $scope.sla = {};

    $scope.loadSLA = function () {
        $scope.loading_sla = true;

        //http://marketplace.tnova.eu/accounting/sla-info/?clientId=p1&kind=vnf
        var curl;
        if ($scope.hasRole('Customer')) {
             curl = '/accounting/sla-info/?clientId='+$scope.user_profile.id+'&kind=ns';
        } else if ($scope.hasRole('Service Provider')) {
             curl = '/accounting/sla-info/?clientId='+$scope.user_profile.id+'&kind=vnf';
        }

        NoSuffixRestangular.one(curl).get().then(
            function (response) {
                $scope.sla = response;
                $scope.loading_sla = false;
                console.log("SLA has been successfully loaded");
            }, function (response) {
                console.log("SLA error with status code " + response.status);
                console.log("SLA error message: " + response.data);
                $scope.loading_sla = false;
            });
    };

    $scope.init = function () {
        $scope.loadSLA();
    };

    $scope.init();

}

function SLAChartsCtrl(Restangular, NoSuffixRestangular, $scope, $rootScope, $state, $stateParams) {
    $scope.agreementID = $stateParams.agreementID;
    $scope.mon_data= [];

        NoSuffixRestangular.all('orchestrator/instances/10/monitoring-data/?instance_type=ns&metric=cpu').getList().then(
            function (response) {
                $scope.mon_data = response;
                //$.each($scope.mon_data, function(key, value) {
                //    $scope.data.data.push([value.date, parseInt(value.value)]);
                //    $scope.sla.data.push([value.date, 100]);
                //});

                console.log("Monitoring Data found size:" + response.length);
            }, function (response) {
                console.log("Monitoring Data error with status code " + response.status);
                console.log("Monitoring Data error message: " + response.data);
            });

    $scope.addPoints = function () {
        var seriesArray = $scope.highchartsNG.series;
        var rndIdx = Math.floor(Math.random() * seriesArray.length);
        seriesArray[rndIdx].data = seriesArray[rndIdx].data.concat([1, 10, 20])
    };

    $scope.addSeries = function () {
        var rnd = [];
        for (var i = 0; i < 10; i++) {
            rnd.push(Math.floor(Math.random() * 20) + 1)
        }
        $scope.highchartsNG.series.push({
            data: rnd
        })
    };

    $scope.removeRandomSeries = function () {
        var seriesArray = $scope.highchartsNG.series;
        var rndIdx = Math.floor(Math.random() * seriesArray.length);
        seriesArray.splice(rndIdx, 1)
    };

    $scope.options = {
        type: 'line'
    };

    //$scope.data = {
    //    name: 'data',
    //    data: []
    //};
    //$scope.sla = {
    //    name: 'sla',
    //    data: []
    //};

    //$.each($scope.mon_data, function(key, value) {
    //    $scope.data.data.push([value.date, parseInt(value.value)]);
    //    $scope.sla.data.push([value.date, 100]);
    //});

    $scope.highchartsNG = {
        options: {
            chart: {
                type: 'line'
            },
            xAxis: {
                type: 'datetime'
            }
        },
        series: [{
            type: 'area',
            name: '%CPU Utiliazation',
            data: [50, 40, 33, 45, 66]
        },
        {
            name: 'SLA Threshold',
            color: 'red',
            data: [100, 100, 100, 100, 100]
        }],
        title: {
            text: 'SLA %CPU Utiliazation'
        },
        loading: false
    };

    $scope.init = function () {


    };

    $scope.init();

}

function MonitoringCtrl(Restangular, NoSuffixRestangular, $scope, $rootScope) {

    $scope.loading_mon= false;
    $scope.mon_data = {};


    $scope.init = function () {

    };

    $scope.init();

}
