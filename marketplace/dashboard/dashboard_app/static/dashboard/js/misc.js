angular.module('dashboard').controller('BillingCtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', 'alertService', BillingCtrl]);
angular.module('dashboard').controller('BillingRevCtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', 'alertService', BillingRevCtrl]);

angular.module('dashboard').controller('SLACtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', SLACtrl]);
angular.module('dashboard').controller('SLAChartsCtrl', ['Restangular', 'NoSuffixRestangular', '$scope', '$rootScope', '$state', '$stateParams', '$interval', SLAChartsCtrl]);
angular.module('dashboard').controller('MonitoringCtrl', ['Restangular','$scope', '$rootScope', MonitoringCtrl]);


function BillingCtrl(Restangular, NoSuffixRestangular, $scope, $rootScope, alertService) {

    $scope.billing = {};

    $scope.loadBill = function () {

        var from_date = moment($scope.from_dt).format('YYYY-MM-DD%2000:00:00');
        var to_date = moment($scope.to_dt).format('YYYY-MM-DD%2023:59:59');

        var username = $scope.user_profile.username;
        var user_id = $scope.user_profile.id;

        $rootScope.root_loading = true;
        NoSuffixRestangular.one("/billing/bill?userId="+user_id+"&from="+from_date+"&to="+to_date).get().then(
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
        NoSuffixRestangular.one("/billing/revenue?provider="+user_id+"&from="+from_date+"&to="+to_date).get().then(
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

function SLAChartsCtrl(Restangular, NoSuffixRestangular, $scope, $rootScope, $state, $stateParams, $interval) {
    $scope.agreementID = $stateParams.agreementID;
    $scope.productID =  $stateParams.productID;
    $scope.vnfd = {};
    $scope.nsd = {};
    $scope.graphs = [];

    if ($scope.hasRole('Customer')) {

    Restangular.one('service-catalog/service/catalog', $scope.productID).get().then(

        function (response) {
            var valueRegexp = /^.*\((.*)\)$/i;
            $scope.nsd = response;

            console.log("GetNSD " + $scope.productID);

            $.each($scope.nsd.nsd.sla[0].assurance_parameters, function (as_key, as_param) {
                var instance_id = $scope.agreementID.replace(/^ns/, "");
                NoSuffixRestangular.all('/orchestrator/instances/'+instance_id+'/monitoring-data/?instance_type=ns&metric='+as_param.id).getList().then(
                    function (response) {

                        var highchart = {
                            options: {
                                chart: {
                                    type: 'spline'
                                },
                                xAxis: {
                                    type: 'datetime'
                                },
                                yAxis: {
                                    title: {
                                        text: as_param.id
                                    }
                                }
                            },
                            series: [{
                                type: 'area',
                                name: as_param.id,
                                data: []
                            },
                            {
                                type: 'spline',
                                name: 'SLA Threshold',
                                color: 'red',
                                data: []
                            }],
                            title: {
                                text: 'SLA '+ as_param.id +' ('+as_param.unit+')'
                            },
                            loading: false,
                            size: {
                                //height: 240
                            }
                        };

                        //$scope.mon_data = response;
                        $.each(response, function(key, value) {
                            highchart.series[0].data.push([parseInt(value.date)*1000, parseInt(value.value)]);
                            highchart.series[1].data.push([parseInt(value.date)*1000, parseInt(/\d+/.exec(as_param.value)[0])]);
                        });

                        $scope.graphs.push(highchart);

                        console.log("Monitoring Data found metric:"+as_param.id+" size:" + response.length);
                    }, function (response) {
                        console.log("Monitoring Data error with status code " + response.status);
                        console.log("Monitoring Data error message: " + response.data);
                    });

            });

        }, function (response) {
            console.log("GetNSD error with status code " + response.status);
            console.log("GetNSD error message: " + response.data);
        });



    } else if ($scope.hasRole('Service Provider')) {



    Restangular.one('broker/vnfs').getList().then(
    //Restangular.one('vnfs', $scope.productID).get().then(
        function (response) {

            var vnfds = response;
            $.each(vnfds, function (vnfd_key, vnfd) {

                if ($scope.productID==vnfd.id){
                    $scope.vnfd = vnfd;
                }

            });

            console.log("GetVNF " + $scope.productID);

            $.each($scope.vnfd.deployment_flavours[0].assurance_parameters, function (as_key, as_param) {
                var instance_id = $scope.agreementID.replace(/^vnf/, "");
                NoSuffixRestangular.all('/orchestrator/instances/'+instance_id+'/monitoring-data/?instance_type=vnf&metric='+as_param.id).getList().then(
                    function (response) {

                        var highchart = {
                            options: {
                                chart: {
                                    type: 'spline'
                                },
                                xAxis: {
                                    type: 'datetime'
                                },
                                yAxis: {
                                    title: {
                                        text: as_param.id
                                    }
                                }
                            },
                            series: [{
                                type: 'area',
                                name: as_param.id,
                                data: []
                            },
                            {
                                type: 'spline',
                                name: 'SLA Threshold',
                                color: 'red',
                                data: []
                            }],
                            title: {
                                text: 'SLA '+ as_param.id +' ('+as_param.unit+')'
                            },
                            loading: false,
                            size: {
                                //height: 240
                            }
                        };

                        //$scope.mon_data = response;
                        $.each(response, function(key, value) {
                            highchart.series[0].data.push([parseInt(value.date)*1000, parseInt(value.value)]);
                            highchart.series[1].data.push([parseInt(value.date)*1000, as_param.value]);
                        });

                        $scope.graphs.push(highchart);

                        console.log("Monitoring Data found metric:"+as_param.id+" size:" + response.length);
                    }, function (response) {
                        console.log("Monitoring Data error with status code " + response.status);
                        console.log("Monitoring Data error message: " + response.data);
                    });

            });

        }, function (response) {
            console.log("GetVNF error with status code " + response.status);
            console.log("GetVNF error message: " + response.data);
        });




    }


    $scope.graphsload = function () {

        if ($scope.hasRole('Customer')) {

            $.each($scope.nsd.nsd.sla[0].assurance_parameters, function (as_key, as_param) {

                var instance_id = $scope.agreementID.replace(/^ns/, "");
                NoSuffixRestangular.all('/orchestrator/instances/' + instance_id + '/monitoring-data/?instance_type=ns&metric=' + as_param.id).getList().then(
                    function (response) {

                        $scope.graphs[as_key].series[0].data = [];
                        $scope.graphs[as_key].series[1].data = [];

                        //$scope.mon_data = response;
                        $.each(response, function (key, value) {
                            $scope.graphs[as_key].series[0].data.push([parseInt(value.date) * 1000, parseInt(value.value)]);
                            $scope.graphs[as_key].series[1].data.push([parseInt(value.date) * 1000, parseInt(/\d+/.exec(as_param.value)[0])]);
                        });

                        console.log("Monitoring Data found metric:" + as_param.id + " size:" + response.length);
                    }, function (response) {
                        console.log("Monitoring Data error with status code " + response.status);
                        console.log("Monitoring Data error message: " + response.data);
                    });

            });



        } else if ($scope.hasRole('Service Provider')) {


            $.each($scope.vnfd.deployment_flavours[0].assurance_parameters, function (as_key, as_param) {

                var instance_id = $scope.agreementID.replace(/^vnf/, "");
                NoSuffixRestangular.all('/orchestrator/instances/' + instance_id + '/monitoring-data/?instance_type=vnf&metric=' + as_param.id).getList().then(
                    function (response) {

                        $scope.graphs[as_key].series[0].data = [];
                        $scope.graphs[as_key].series[1].data = [];

                        //$scope.mon_data = response;
                        $.each(response, function (key, value) {
                            $scope.graphs[as_key].series[0].data.push([parseInt(value.date) * 1000, parseInt(value.value)]);
                            $scope.graphs[as_key].series[1].data.push([parseInt(value.date) * 1000, as_param.value]);
                        });

                        console.log("Monitoring Data found metric:" + as_param.id + " size:" + response.length);
                    }, function (response) {
                        console.log("Monitoring Data error with status code " + response.status);
                        console.log("Monitoring Data error message: " + response.data);
                    });

            });


        }


    };

    $scope.init = function () {


    };

    $scope.init();


    var service_interval = $interval($scope.graphsload, 5000);

    // Cancel interval on page changes
    $scope.$on('$destroy', function () {
        if (angular.isDefined(service_interval)) {
            $interval.cancel(service_interval);
            service_interval = undefined;
        }
    });

}

function MonitoringCtrl(Restangular, NoSuffixRestangular, $scope, $rootScope) {

    $scope.loading_mon= false;
    $scope.mon_data = {};


    $scope.init = function () {

    };

    $scope.init();

}
