angular.module('dashboard').controller('BillingCtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', BillingCtrl]);
angular.module('dashboard').controller('BillingRevCtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', BillingRevCtrl]);

angular.module('dashboard').controller('SLACtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', SLACtrl]);
angular.module('dashboard').controller('SLAChartsCtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', '$state', '$stateParams', SLAChartsCtrl]);
angular.module('dashboard').controller('MonitoringCtrl', ['Restangular','$scope', '$rootScope', MonitoringCtrl]);


function BillingCtrl(Restangular, NoSuffixRestangular, $scope, $rootScope) {

    $scope.loading_billing = false;
    $scope.billing = {};

    $scope.loadBill = function () {
        $scope.loading_billing = true;
        NoSuffixRestangular.one("/billing/bill?userId=c1&from=2015-12-09%2000:00:00&to=2016-12-09%2023:59:59").get().then(
            function (response) {
                $scope.billing = response;
                $scope.loading_billing = false;
                console.log("Billing has been successfully loaded");
            }, function (response) {
                console.log("Billing error with status code " + response.status);
                console.log("Billing error message: " + response.data);
                $scope.loading_billing = false;
            });
    };

    $scope.init = function () {
        $scope.loadBill();
    };

    $scope.init();

}

function BillingRevCtrl(Restangular, NoSuffixRestangular, $scope, $rootScope) {

    $scope.loading_billing= false;
    $scope.billing = {};

    $scope.loadBillRevenue = function () {
        $scope.loading_billing = true;
        NoSuffixRestangular.one("/billing/revenue?provider=f1&from=2015-12-09%2000:00:00&to=2016-12-09%2023:59:59").get().then(
            function (response) {
                $scope.billing = response;
                $scope.loading_billing = false;
                console.log("BillingRevenue has been successfully loaded");
            }, function (response) {
                console.log("BillingRevenue error with status code " + response.status);
                console.log("BillingRevenue error message: " + response.data);
                $scope.loading_billing = false;
            });
    };

    $scope.init = function () {
        $scope.loadBillRevenue();
    };

    $scope.init();

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
