angular.module('dashboard').controller('ServiceListCtrl', ['Restangular', '$scope', '$rootScope', '$state', 'ModalService', 'alertService', ServiceListCtrl]);
angular.module('dashboard').controller('ServiceCreateCtrl', ['Restangular', '$scope', '$rootScope', '$state', 'ModalService', '$interval', 'alertService', ServiceCreateCtrl]);
angular.module('dashboard').controller('CustomerBuyService', ['Restangular', '$scope', '$rootScope', '$state', '$stateParams', CustomerBuyService]);
angular.module('dashboard').controller('CustomerServiceList', ['Restangular', '$scope', '$rootScope', '$state', '$interval', CustomerServiceList]);


function CustomerServiceList(Restangular, $scope, $rootScope, $state, $interval) {

    $scope.services = [];
    $scope.nsds = [];

    $scope.terminateService = function (instance_id) {

        Restangular.one('service-selection/service/selection/',instance_id).one('terminate').get().then(
            function (response) {

                console.log("Service successfully terminated!");

            }, function (response) {

                console.log("Service termination error, status code " + response.status);
                console.log("Service termination error, message: " + response.data);

            });

    };

    $scope.loadNSD = function () {

        Restangular.all('service-selection/service/selection').getList().then(
            function (response) {
                $scope.services = response;

                Restangular.all('service-catalog/service/catalog').getList().then(
                    function (response2) {
                        $scope.nsds = response2;
                        console.log("GetNSDList " + response2.length + " NSDs found");
                    }, function (response) {
                        console.log("GetNSDList error with status code " + response2.status);
                        console.log("GetNSDList error message: " + response2.data);
                    });


                console.log("GetServiceList " + response.length + " Services found");
            }, function (response) {
                console.log("GetServiceList error with status code " + response.status);
                console.log("GetServiceList error message: " + response.data);
            });

    };

    $scope.loadNSD();

    var service_interval = $interval($scope.loadNSD, 10000);

    // Cancel interval on page changes
    $scope.$on('$destroy', function () {
        if (angular.isDefined(service_interval)) {
            $interval.cancel(service_interval);
            service_interval = undefined;
        }
    });

}

function CustomerBuyService(Restangular, $scope, $rootScope, $state, $stateParams) {

    $scope.nsd = {};
    $scope.selected_flavor = {};
    $scope.nap_id = '';

    $scope.loadNSD = function () {

        Restangular.one("service-catalog/service/catalog", $stateParams.nsdID).get().then(
            function (response) {
                $scope.nsd = response;
                console.log("GetNSD " + response.name + " has been successfully loaded");
            }, function (response) {
                console.log("GetNSD error with status code " + response.status);
                console.log("GetNSD error message: " + response.data.detail);
            });


        //Restangular.all('nsds/').getList().then(
        //    function (response) {
        //        $scope.services = response;
        //        console.log("GetNSDList " + response.length + " NSDs found");
        //    }, function (response) {
        //        console.log("GetNSDList error with status code " + response.status);
        //        console.log("GetNSDList error message: " + response.data.detail);
        //    });
    };

    $scope.loadNSD();



    $scope.buy=function(nsd_id){

        var selected_flavor = '';
        angular.forEach($scope.selected_flavor, function (isflavorselected, flavor_id) {
            if (isflavorselected){
                selected_flavor=flavor_id;
            }

        });

        var service = {
            customer_id: 2,
            nap_id: $scope.nap_id,
            ns_id: nsd_id,
            flavor_id: 'sla0'
        };

        console.log(service);

        $rootScope.root_loading = true;
        Restangular.all('service-selection/service/selection').post(service).then(
            function (response) {
                console.log("CreateService " + response.id + " has been successfully completed");
                console.log(response);

                $rootScope.root_loading = false;

                $state.go('index.customer-services');
            }, function (response) {
                console.log("CreateService error with status code " + response.status);
                console.log("CreateService error message: " + response.data);

                $rootScope.root_loading = false;
            });
    };
    //
    //$scope.loadUser = function () {
    //    $scope.loading_edit_user = true;
    //    Restangular.one("user-management/users", $stateParams.userID).get().then(
    //        function (response) {
    //            $scope.user = response;
    //            $scope.loading_edit_user = false;
    //            console.log("GetUser " + response.username + " has been successfully loaded");
    //        }, function (response) {
    //            console.log("GetUser error with status code " + response.status);
    //            console.log("GetUser error message: " + response.data.detail);
    //            $scope.loading_edit_user = false;
    //        });
    //};


}


function ServiceListCtrl(Restangular, $scope, $rootScope, $state, ModalService, alertService) {

    $scope.services = {};

    $scope.loadNSDs = function () {
        Restangular.all('service-catalog/service/catalog').getList().then(
            function (response) {
                if (response==''){
                    $scope.services = [];
                }else{
                    $scope.services = response;
                }
                console.log("GetNSDList " + response.length + " NSDs found");
            }, function (response) {
                console.log("GetNSDList error with status code " + response.status);
                console.log("GetNSDList error message: " + response.data.detail);
            });
    };

    $scope.loadNSDs();

    $scope.deleteNSD = function (id, nsd_name) {
        $rootScope.root_loading=true;
        Restangular.one("service-catalog/service/catalog/", id).remove().then(
            function () {
                console.log("NSD " + nsd_name + " has been successfully deleted");
                alertService.add('success', 'NSD '+nsd_name+' has been successfully deleted.');
                $rootScope.root_loading=false;
                $scope.loadNSDs();
            }, function (response) {
                console.log("DeleteNSD error with status code " + response.status);
                console.log("DeleteNSD error message: " + response.data);

                alertService.add('danger', 'Failed to delete NSD.');
                $rootScope.root_loading=false;
            });
    };

    $scope.NSDDeleteConfirm = function (id, nsd_name) {

        //var confirm = $mdDialog.confirm()
        //    .parent(angular.element(document.body))
        //    .title('VNF Delete')
        //    .content('Are you sure you want to delete VNF ' + vnf_name + '?')
        //    .ok('YES')
        //    .cancel('NO');
        //$mdDialog.show(confirm).then(function () {
        //    $scope.deleteVNF(id, vnf_name);
        //});
        $scope.deleteNSD(id, nsd_name);
    };


    $scope.showNSDEditor = function (nsd_id) {

        ModalService.showModal({
            animation: false,
            templateUrl: "/static/dashboard/templates/modals/nsd-yaml-editor.html",
            controller: "YAMLEditController",
            inputs: {
                nsd_id: nsd_id
            }
        }).then(function (modal) {
            // only called on success...
            modal.element.modal();
        }).catch(function (error) {
            // error contains a detailed error message.
            console.log(error);
        });

  };


}

angular.module('dashboard').controller('YAMLEditController', ['$scope', '$element', '$http', 'close', 'nsd_id', function ($scope, $element, $http, close, nsd_id) {

        $scope.code = '';
        $http({
            method: 'GET',
            url: '/nsds/' + nsd_id + '/yaml'
        }).then(function successCallback(response) {
            //alert('ggg');
            //console.log(response.data);
            $scope.code = response.data;
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    }]);

function ServiceCreateCtrl(Restangular, $scope, $rootScope, $state, ModalService, $interval, alertService) {

    $scope.dynamicPopover = {
        templateUrl: 'myPopoverTemplate.html'
    };

    $scope.generic_monitoring_parameters = [
        {metric: "availability", desc: "Availability", unit: '%'},
        {metric: "cpu_usage", desc: "CPU Usage", unit: '%'},
        {metric: "memory_usage", desc: "Memory Usage", unit: '%'},
        {metric: "bps_in", desc: "Incoming Bandwidth", unit: 'Mbps'},
        {metric: "bps_out", desc: "Outgoing Bandwidth", unit: 'Mbps'}
    ];

    $scope.sla_expressions = [
        {code: "EQ", desc: "Equal To", sign: "=="},
        {code: "NE", desc: "Not Equal To", sign: "!="},
        {code: "LT", desc: "Less Than", sign: "<"},
        {code: "LE", desc: "Less Than or Equal", sign: "<="},
        {code: "GT", desc: "Greater Than", sign: ">"},
        {code: "GE", desc: "Greater Than or Equal", sign: ">="}
    ];

    $scope.available_periods = [
        {code: "D", desc: "Day"},
        {code: "W", desc: "Week"},
        {code: "M", desc: "Month"},
        {code: "Y", desc: "Year"}
    ];

    $scope.billing_model_types = [
        {code: "PAYG", desc: "Pay-As-You-Go"}
    ];

    $scope.billing_currencies = [
        {code: "EUR", desc: "Euro"},
        {code: "USD", desc: "US Dollars"}
    ];

    $scope.penalty_types = [
        {type: "Discount"}
    ];

    $scope.redundancy_models = [
        {type: "Active"},
        {type: "Standby"}
    ];

    $scope.sla_expressions2 = [
        {code: "MIN", desc: "Minimum"},
        {code: "MAX", desc: "Maximum"},
        {code: "AVG", desc: "Average"},
        {code: "SUM", desc: "Sum"}
    ];

    $scope.connection_link_types = [
        {type:'E-LINE', description:'Point-2-Point (E-LINE)'},
        {type:'E-TREE', description:'Point-2-Multipoint (E-TREE)'},
        {type:'E-LAN', description:'Lan (E-LAN)'}
    ];

    $scope.available_bandwidths = [
        "10Mbps",
        "100Mbps",
        "1Gbps",
        "10Gbps",
        "Unlimeted"
    ];

    $scope.vnf_events = [
        {type:'start', description:'Start'},
        {type:'stop', description:'Stop'},
        {type:'scale_out', description:'Scale Out'}
    ];

    $scope.generic_monitoring_parameters = [
        {metric: "availability", desc: "Availability", unit: '%'},
        {metric: "end-to-end bandwidth", desc: "End-to-End Bandwidth", unit: 'Mbps'}
    ];

    $scope.monitoring_parameters_selected = [
        {metric: "availability", desc: "Availability", unit: '%'},
        {metric: "end-to-end bandwidth", desc: "End-to-End Bandwidth", unit: 'Mbps'}
    ];

    $scope.loading_broker_vnfs = false;
    $scope.vnfs = {};

    $scope.loadBrokerVNFs = function () {
        $scope.loading_broker_vnfs = true;
        Restangular.all('broker/vnfs').getList().then(
            function (response) {
                $scope.vnfs = response;
                console.log("BROKER::GetVNFList " + response.length + " VNFs found");
                $scope.loading_broker_vnfs = false;

                $scope.updateFilteredVNFs('ALL');

            }, function (response) {
                console.log("BROKER::GetVNFList error with status code " + response.status);
                console.log("BROKER::GetVNFList error message: " + response.data.detail);
                $scope.loading_broker_vnfs = false;
            });
    };


    $scope.init = function () {
         $scope.loadBrokerVNFs();
    };

    $scope.init();

    $scope.nsd = {
        vnfds: [],
        vld: {},
        vnffgd: {
            vnffgs: []
        },
        vnf_dependency: [],
        auto_scale_policy: {},
        lifecycle_events: {},
        monitoring_parameters: []
    };

    $scope.flavors = [{
        constituent_vnf:[],
        assurance_parameters:[],
        billing_model:{},
        virtual_links:[],
        lifecycle_events:{
            start:[],
            stop:[],
            scale_out:[]
        }
    }];

    $scope.addFlavor = function () {
        $scope.flavors.push({
            constituent_vnf:[],
            assurance_parameters:[],
            billing_model:{},
            virtual_links:[],
            lifecycle_events:{
                start:[],
                stop:[],
                scale_out:[]
            }
        });
    };

    $scope.removeFlavor = function (flavor) {
        $scope.flavors.splice($scope.flavors.indexOf(flavor), 1);
    };

    $scope.getIndexFlavor = function (flavor) {
        return $scope.flavors.indexOf(flavor);
    };

    $scope.addConstituentVNF = function (flavor){
        flavor.constituent_vnf.push({

        });
    };

    $scope.removeConstituentVNF = function (flavor, vnf){
        flavor.constituent_vnf.splice(flavor.constituent_vnf.indexOf(vnf), 1);
    };

    $scope.addAssuranceParameter = function (flavor) {
        flavor.assurance_parameters.push({
            constituent_vnfs:[{}],
            violation:[
                {breaches_count:2,interval:360}
            ],
            penalty:{type:{type: "Discount"}}

        });
    };

    $scope.removeAssuranceParameter = function (flavor, aparam) {
        flavor.assurance_parameters.splice(flavor.assurance_parameters.indexOf(aparam), 1);
    };


    $scope.getIndexAssuranceParameter = function (flavor, aparam) {
        return flavor.assurance_parameters.indexOf(aparam)
    };

    $scope.addConstituentVNFAssuranceParam = function (constituent_vnfs) {
        constituent_vnfs.push({

        });
    };

    $scope.removeConstituentVNFAssuranceParam = function (constituent_vnfs, vnf) {
        constituent_vnfs.splice(constituent_vnfs.indexOf(vnf), 1);
    };

    $scope.addAssuranceParamViolation = function (violation) {
        violation.push({});
    };

    $scope.removeAssuranceParamViolation = function (violation_list, violation) {
        violation_list.splice(violation_list.indexOf(violation), 1);
    };

    $scope.addVL = function (flavor) {
        flavor.virtual_links.push({
            connection_points_reference:[],
            vdu_reference:[],
            connection_points:[],
            bandwidth: "Unlimeted",
            type: {type:'E-LINE', description:'Point-2-Point (E-LINE)'}
        });
    };

    $scope.removeVL = function (flavor, vl) {
        flavor.virtual_links.splice(flavor.virtual_links.indexOf(vl), 1);
    };

    $scope.getIndexVL = function (flavor, vl) {
        return flavor.virtual_links.indexOf(vl);
    };

    $scope.addConnectionPoint = function (vl) {
        vl.connection_points.push({});
    };

    $scope.removeConnectionPoint = function (vl, cp) {
        var cp_index = vl.connection_points.indexOf(cp);
        vl.connection_points.splice(cp_index, 1);
    };

    $scope.addLifeCycleEvent = function (flavor, event) {
        flavor.lifecycle_events[event].push({
            vnf_id:null,
            vnf_event:null
        });
    };

    $scope.removeLifeCycleEvent = function (flavor, event, le) {
        flavor.lifecycle_events[event].splice(flavor.lifecycle_events[event].indexOf(le), 1);
    };

    $scope.getVNFexternalPoints = function(flavor){
        var points=[];
        angular.forEach(flavor.constituent_vnf, function (vnf, vnf_key) {
            var vnfd = $scope.getVNFbyID(vnf.vnf_reference);

            angular.forEach(vnfd.deployment_flavours, function (flavor, flavor_key) {
                if (flavor.flavour_key == vnf.vnf_flavour_id_reference) {
                    var links_ref = flavor.vlink_reference;
                    angular.forEach(vnfd.vlinks, function (link, link_key) {
                        if (_.contains( links_ref, link.id ) && link.external_access){
                            points.push('VNF#'+vnfd.id+":ext_"+link.alias);
                        }
                    });

                }
            });

        });
        return points;
    };

    $scope.getConstituentVNFs= function(flavor){
        var vnfs=[];
        angular.forEach(flavor.constituent_vnf, function (vnf, vnf_key) {
            var vnfd = $scope.getVNFbyID(vnf.vnf_reference);
            vnfs.push(vnfd);
        });
        return vnfs;
    };

    $scope.getMonitoringParamaters = function (flavor) {
        var mons = [];
        angular.forEach(flavor.constituent_vnf, function (vnf, vnf_key) {
            var vnfd = $scope.getVNFbyID(vnf.vnf_reference);

            //for all vdus
            angular.forEach(vnfd.vdu, function (vdu, vdu_key) {

                //generic
                angular.forEach(vdu.monitoring_parameters, function (mon, mon_key) {

                    if (!_.contains(mons, mon)) {
                        mons.push(mon);
                    }

                });
                //specific
                angular.forEach(vdu.monitoring_parameters_specific, function (mon, mon_key) {

                    if (!_.contains(mons, mon)) {
                        mons.push(mon);
                    }
                });

            });

        });
        return mons;
    };

    $scope.selected_vnfs = {};
    $scope.selected_vnfs_trade = {};

    $scope.filtered_vnfs = $scope.vnfs;
    $scope.selected_vnf_filter_type = 'ALL';
    $scope.selected_price_range={min:0, max:300};

    $scope.updateFilteredVNFs = function(selected_vnf_filter_type){
        // reset selected vnfds trade
        angular.forEach($scope.selected_vnfs_trade, function (vnfd_selected, vnfd_id) {
                $scope.selected_vnfs_trade[vnfd_id] = false;
        });

        if (selected_vnf_filter_type) {
            $scope.selected_vnf_filter_type = selected_vnf_filter_type;
        }

        var filtered_vnfs = [];

        angular.forEach($scope.vnfs, function (vnfd, vnfd_key) {

            if ($scope.selected_vnf_filter_type == vnfd.type || $scope.selected_vnf_filter_type == 'ALL') {

                if (vnfd.billing_model.price.max_per_period >= $scope.selected_price_range.min && vnfd.billing_model.price.max_per_period <= $scope.selected_price_range.max) {
                    filtered_vnfs.push(vnfd);
                }

            }

        });

        $scope.filtered_vnfs = filtered_vnfs;

        //check if a $digest is already in progress by checking $scope.$$phase.
        if (!$scope.$$phase) {
            $scope.$apply(); //this triggers a $digest
        }

    };

    $scope.updateFilteredVNFsPrice = function () {
        console.log($scope.selected_price_range);
    };

    $scope.vnf_filter_types = [
        {code: "ALL", desc: "All Types"},
        {code:"vTC", desc: "Traffic Classification"},
        {code:"vSBC", desc: "Session Border Controller"},
        {code:"vTU", desc: "Transcoder Unit"},
        {code:"vHG", desc: "Home Gateway"},
        {code:"vSA", desc: "Security Appliance"},
        {code:"vPXAAS", desc: "Proxy"}
    ];

    $scope.getDeploymentCost = function (vnf, flavor_name) {
        var vdu_cost = 0.03425;
        var cpu_cost = 0.034;
        var ram_gb_cost = 0.02125;
        var storage_gb_cost = 0.0003;

        var number_of_vdus = 0;
        var number_of_cores = 0;
        var number_of_ram_gb = 0;
        var number_of_storage_gb = 0;

        var vdu_reference = [];
        angular.forEach(vnf.deployment_flavours, function (flavor, flavor_key) {
            if (flavor.flavour_key==flavor_name){
                vdu_reference = flavor.vdu_reference;
            }
        });

        angular.forEach(vnf.vdu, function (vdu, vdu_key) {
            if (_.contains( vdu_reference, vdu.id )){
                number_of_vdus += vdu.resource_requirements.vcpus || 0;
                number_of_ram_gb += vdu.resource_requirements.memory || 0;
                number_of_storage_gb += vdu.resource_requirements.storage.size || 0;
                number_of_vdus += 1;
            }

        });

        var total = (number_of_vdus * vdu_cost) + (number_of_cores * cpu_cost) + (number_of_ram_gb * ram_gb_cost) + (number_of_storage_gb * storage_gb_cost);
        return (total.toFixed(2));
    };


    $scope.generateNSD = function(){

        //get selected vnfd_ids
        $scope.nsd.vnfds = [];
        angular.forEach($scope.selected_vnfs, function (vnfd_selected, vnfd_id) {
                $scope.nsd.vnfds.push(vnfd_selected.id);
        });

        console.log($scope.nsd);

    };

    $scope.postNewNSD = function (json_nsd) {

        $rootScope.root_loading = true;
        Restangular.all('service-catalog/service/catalog').post(json_nsd).then(
            function (response) {
                console.log("CreateNSD " + response.name + " has been successfully completed");
                console.log(response);
                $rootScope.root_loading = false;

                $state.go('index.services.list');

                alertService.add('success', 'NSD successfully created!')
            }, function (response) {
                $rootScope.root_loading = false;
                console.log("CreateNSD error with status code " + response.status);
                console.log("CreateNSD error message: " + response.data);

                alertService.add('danger', 'Failed to create NSD, '+ response.plain())
            });

    };

    $scope.createNSD = function(){

        $scope.nsd.vnfds = [];
        angular.forEach($scope.getSelectedVNFs(), function (vnf, vnfd_id) {
                $scope.nsd.vnfds.push(vnf.id);
        });

        $scope.nsd.sla = [];
        $scope.nsd.vld = {
            virtual_links: []
        };
        $scope.nsd.lifecycle_events = {
            start: [],
            stop:[],
            scale_out:[]
        };
        $scope.nsd.monitoring_parameters = $scope.monitoring_parameters_selected;

        $scope.nsd.vendor="3";
        $scope.nsd.provider="T-Nova";
        $scope.nsd.provider_id="3";

        var flavor_index=0;
        var vl_link_index = 0;
        var le_index = 0;
        var vnffg_index = 0;

        angular.forEach($scope.flavors, function (flavor, flavor_key) {
            var le_rel_index = 0;

            // need this var to keep the virtual links per flavor for the vnffg
            var vnffg_flavor_vlinks = [];

            angular.forEach(flavor.virtual_links, function (virtual_link, virtual_link_key) {

                var connections = [];

                angular.forEach(virtual_link.connection_points, function (cp, cp_key) {
                    connections.push(cp.vdu_ref);
                });

                var vl = {
                    vld_id: 'vld'+ vl_link_index,
                    alias:virtual_link.alias,
                    sla_ref_id:'sla'+flavor_index,
                    root_requirements: virtual_link.bandwidth,
                    leaf_requirement: virtual_link.bandwidth,
                    qos:{average: '',peak: '', burst: ''},
                    connections: connections,
                    connectivity_type: virtual_link.type.type,
                    net_segment: virtual_link.net_segment,
                    external_access: virtual_link.external_access,
                    merge: virtual_link.merge
                };

                $scope.nsd.vld.virtual_links.push(vl);
                vnffg_flavor_vlinks.push(vl);

                vl_link_index++;

            });


            // VNFFG GENERATION
            var number_of_endpoints = 0;
            var number_of_virtual_links = 0;
            var dependent_virtual_links = [];

            var connection_points = [];

            angular.forEach(vnffg_flavor_vlinks, function (virtual_link, virtual_link_key) {

                //ignore storage and management network
                if (virtual_link.alias != 'management' && virtual_link.alias != 'storage') {


                    if (virtual_link.external_access) {
                        number_of_endpoints++;

                        connection_points.push('ns_ext_'+virtual_link.alias)
                    }

                    number_of_virtual_links++;
                    dependent_virtual_links.push(virtual_link.vld_id);

                    angular.forEach(virtual_link.connections, function (conn, conn_key) {

                        connection_points.push(conn)

                    });

                }

            });

            var con_vnfs = [];
            angular.forEach(flavor.constituent_vnf, function (conv, conv_key) {
                con_vnfs.push({vnf_ref_id:conv.vnf_reference, vnf_flavor_key_ref:conv.vnf_flavour_id_reference});
            });

            $scope.nsd.vnffgd.vnffgs.push(
                {
                    vnffg_id: 'vnffg' + vnffg_index,
                    number_of_endpoints: number_of_endpoints,
                    number_of_virtual_links: number_of_virtual_links,
                    dependent_virtual_links: dependent_virtual_links,
                    network_forwarding_path:[
                        {
                            nfp_id:'nfp0',
                            graph: dependent_virtual_links,
                            connection_points:connection_points,
                            constituent_vnfs:con_vnfs
                        }
                    ]
                }
            );

            vnffg_index++;

            angular.forEach(flavor.lifecycle_events.start, function (le, le_key) {

                $scope.nsd.lifecycle_events.start.push({
                    event_id: 'le'+le_index,
                    event_rel_id: 'rel_le'+le_rel_index,
                    sla_ref_id:'sla'+flavor_index,
                    vnf_id:le.vnf_id,
                    vnf_event:le.vnf_event
                });
                le_index++;
                le_rel_index++;
            });

            var nsd_flavor = {
                id:'sla'+flavor_index,
                sla_key:flavor.flavor_key,
                constituent_vnf: flavor.constituent_vnf,
                assurance_parameters:[],
                billing:flavor.billing_model
            };


            angular.forEach(flavor.assurance_parameters, function (assurance_parameter, assurance_parameter_key) {

                    var formula_in = '';

                    angular.forEach(assurance_parameter.constituent_vnfs, function (vnf, vnf_key) {
                        formula_in+='VNF:'+ vnf.vnf.id +'.'+ assurance_parameter.monitoring_parameter.metric+',';
                    });

                    var formula = assurance_parameter.expression2.code + '('+formula_in.replace(/(^[,\s]+)|([,\s]+$)/g, '')+')';

                    var aparam = {
                        id:assurance_parameter.monitoring_parameter.metric,
                        name:assurance_parameter.monitoring_parameter.metric,
                        value: assurance_parameter.expression.code+'('+assurance_parameter.value+')',
                        unit: assurance_parameter.monitoring_parameter.unit,
                        //formula: assurance_parameter.expression2.code+'('+')',
                        formula: formula,
                        violations:assurance_parameter.violation,
                        //penalty:assurance_param.penalty,

                    };

                    aparam.penalty = {};
                    aparam.penalty.type = assurance_parameter.penalty.type.type;
                    aparam.penalty.value = assurance_parameter.penalty.value;
                    aparam.penalty.unit = assurance_parameter.monitoring_parameter.unit;
                    aparam.penalty.validity = 'P'+ assurance_parameter.penalty.validity.value + assurance_parameter.penalty.validity.period;

                    //nsd_flavor.billing = flavor.billing_model;
                    nsd_flavor.assurance_parameters.push(aparam);
            });

            $scope.nsd.sla.push(nsd_flavor);

            flavor_index++;
        });



        //$scope.nsd
        $scope.postNewNSD({nsd:$scope.nsd});
        //$state.go('index.services.list');
        console.log({nsd:$scope.nsd});

    };

    $scope.getSelectedVNFs = function(){
        var selected_vnfs = [];
        var selected_vnf_ids = [];

        angular.forEach($scope.selected_vnfs, function (vnfd_selected, vnfd_id) {
                if (vnfd_selected){
                    selected_vnf_ids.push(parseInt(vnfd_id));
                }
        });

        angular.forEach($scope.vnfs, function (vnfd, vnfd_key) {
                if (selected_vnf_ids.indexOf(parseInt(vnfd.id)) !== -1){
                    selected_vnfs.push(vnfd);
                }
        });

        //console.log(selected_vnfs);
        return selected_vnfs;
    };

    $scope.getVNFbyID = function(vnf_id){
        var v;
        angular.forEach($scope.vnfs, function (vnfd, vnfd_key) {
                if (vnfd.id == vnf_id){

                    v = vnfd;

                }
        });
        return v;
    };


    $scope.getSelectedVNFsTrade = function(){
        var selected_vnfs = [];
        var selected_vnf_ids = [];

        angular.forEach($scope.selected_vnfs_trade, function (vnfd_selected, vnfd_id) {
                if (vnfd_selected){
                    selected_vnf_ids.push(parseInt(vnfd_id));
                }
        });

        angular.forEach($scope.vnfs, function (vnfd, vnfd_key) {
                if (selected_vnf_ids.indexOf(parseInt(vnfd.id)) !== -1){
                    selected_vnfs.push(vnfd);
                }
        });

        //console.log(selected_vnfs);
        return selected_vnfs;
    };

    $scope.getVNFFlavors = function(vnf_id){

        var flavors = [];
        angular.forEach($scope.vnfs, function (vnfd, vnfd_key) {
                if (vnfd.id == vnf_id){

                    angular.forEach(vnfd.deployment_flavours, function (flavor, flavor_key) {
                            flavors.push(flavor.flavour_key);
                    });

                }
        });

        //console.log(selected_vnfs);
        return flavors;

    };

    //STEPS
    $scope.active_step = 1;
    $scope.steps = {
        1:{
            enable:true
        },
        2:{
            enable:false
        },
        3:{
            enable:false
        },
        4:{
            enable:false
        },
        5:{
            enable:false
        },
        6:{
            enable:false
        },
        7:{
            enable:false
        },
        8:{
            enable:false
        }
    };

    $scope.gotoStep = function(target_step){
        if ($scope.steps[target_step].enable) {
            $scope.active_step = target_step;
        }
    };

    $scope.nextStep = function(target_step){

        if (target_step == target_step && $scope.active_step == target_step - 1) {
            $scope.steps[target_step].enable = true;
            $scope.active_step = target_step;
        }
    };

    $scope.trades ={};

    $scope.showTrade = function() {

         ModalService.showModal({
          animation: false,
          templateUrl: "/static/dashboard/templates/modals/trade.html",
          controller: "TradeRequestController",
          inputs: {
            vnfds: $scope.getSelectedVNFsTrade()
          }
        }).then(function(modal) {
          // only called on success...
             modal.element.modal();
             modal.close.then(function (trades) {

                angular.forEach(trades, function (trade, trade_key) {

                     $scope.trades[trade.vnfd_id] ={};
                     $scope.trades[trade.vnfd_id].inter = $interval(function () {

                        Restangular.one("/broker/vnfs/trade/", trade.id).get().then(
                            function (response) {
                                $scope.trades[trade.vnfd_id].status=response.status;
                                $scope.trades[trade.vnfd_id].price=response.price_override;
                                $scope.trades[trade.vnfd_id].setup_price=response.setup_price_override;

                                if (response.status!='pending'){
                                    $interval.cancel($scope.trades[trade.vnfd_id].inter);
                                }
                                console.log("GetTrade Update Status:" + response.status);
                            }, function (response) {
                                //console.log("GetNSD error with status code " + response.status);
                                //console.log("GetNSD error message: " + response.data.detail);
                            });

                     }, 2000);

                });

             });

        }).catch(function(error) {
          // error contains a detailed error message.
          console.log(error);
        });

  };

}

angular.module('dashboard').controller('TradeRequestController', [
    '$scope', '$element', '$http', 'Restangular', 'close', 'vnfds',
    function ($scope, $element, $http, Restangular, close, vnfds) {

        $scope.new_price = 0;
        $scope.new_setup_price = 0;
        $scope.vnfds = vnfds;


        var trades = [];
        $scope.sendTradeRequest = function () {

            angular.forEach(vnfds, function (vnfd, vnfd_key) {
                var data = {
                    vnfd_id: vnfd.id,
                    provider_id: vnfd.provider_id,
                    price_override: $scope.new_price,
                    setup_price_override: $scope.new_setup_price
                };
                Restangular.all('/broker/vnfs/trade/').post(data).then(
                    function (response) {
                        console.log("Trade Request " + response.id + " has been successfully completed");


                        trades.push(response);
                        if (trades.length == 2) {
                            $('.modal-backdrop').remove();
                            close(trades);
                        }


                    }, function (response) {
                        console.log("Trade Request error with status code " + response.status);
                        console.log("Trade Request error message: " + response.data.detail);
                    });

            });

        };

    }]);
