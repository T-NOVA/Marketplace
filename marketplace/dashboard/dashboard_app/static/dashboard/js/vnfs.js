angular.module('dashboard').controller('VNFCreateCtrl', ['Restangular', '$scope', '$rootScope', '$state', 'ModalService', 'alertService', VNFCreateCtrl]);
angular.module('dashboard').controller('VNFEditCtrl', ['Restangular', '$scope', '$rootScope', '$state', '$stateParams', VNFEditCtrl]);
angular.module('dashboard').controller('VNFListCtrl', ['Restangular', '$scope', '$rootScope', 'ModalService', 'alertService', VNFListCtrl]);


function VNFCreateCtrl(Restangular, $scope, $rootScope, $state, ModalService, alertService) {

    $scope.vnf_types = [
        {code: "vTC", desc: "Traffic Classification"},
        {code: "vSBC", desc: "Session Border Controller"},
        {code: "vTU", desc: "Transcoder Unit"},
        {code: "vHG", desc: "Home Gateway"},
        {code: "vSA", desc: "Security Appliance"},
        {code: "vPXAAS", desc: "Proxy"}
    ];

    $scope.specific_monitoring_parameters = glob_vnf_metrics;

    $scope.new_vnf_type = {code: "", desc: ""};
    $scope.new_vnf_metric = {metric: "", desc: "", unit: ''};

    $scope.addNewVNFType = function () {
        if ($scope.new_vnf_type.code != "" && $scope.new_vnf_type.desc != "") {

            //create new empty array for the specific_monitoring_parameters
            $scope.specific_monitoring_parameters[$scope.new_vnf_type.code] = [];

            $scope.vnf_types.push(_.clone($scope.new_vnf_type));
            $scope.new_vnf_type = {code: "", desc: ""};

        }
    };

    $scope.addNewVNFMetric = function (vdu) {

        if ($scope.new_vnf_metric.metric != "" && $scope.new_vnf_metric.desc != "" && $scope.new_vnf_metric.unit != "") {
            //vdu.monitoring_parameters_specific.push(_.clone($scope.new_vnf_metric));
            $scope.specific_monitoring_parameters[$scope.vnfd.type].push(_.clone($scope.new_vnf_metric));
            $scope.new_vnf_metric = {metric: "", desc: "", unit: ""};
        }
    };

    $scope.memory_units = ['MB', 'GB'];
    $scope.storage_units = ['MB', 'GB', 'TB'];
    $scope.network_units = ['Kbps', 'Mbps', 'Gbps'];

    $scope.available_bandwidths = [
        "10Mbps",
        "100Mbps",
        "1Gbps",
        "10Gbps",
        "Unlimited"
    ];

    $scope.billing_model_types = [
        {code: "PAYG", desc: "Pay-As-You-Go"},
        {code: "RS", desc: "Revenue Sharing"}
    ];

    $scope.billing_currencies = [
        {code: "EUR", desc: "Euro"},
        {code: "USD", desc: "US Dollars"}
    ];

    $scope.connection_link_types = [
        {type: 'E-LINE', description: 'Point-2-Point (E-LINE)'},
        {type: 'E-TREE', description: 'Point-2-Multipoint (E-TREE)'},
        {type: 'E-LAN', description: 'Lan (E-LAN)'}
    ];

    $scope.available_periods = [
        {code: "D", desc: "Day"},
        {code: "W", desc: "Week"},
        {code: "M", desc: "Month"},
        {code: "Y", desc: "Year"}
    ];

    $scope.generic_monitoring_parameters = [
        {metric: "cpuidle", desc: "CPU Idle", unit: '%'},
        {metric: "cpu_util", desc: "CPU Utilization", unit: '%'},
        {metric: "fsfree", desc: "Free Storage", unit: 'GB'},
        {metric: "memfree", desc: "Free Memory", unit: 'MB'},
        {metric: "network_incoming", desc: "Network Incoming", unit: 'Mbps'},
        {metric: "network_outgoing", desc: "Network Outgoing", unit: 'Mbps'},
        {metric: "load_shortterm", desc: "Load Average (1 Minute)", unit: '%'},
        {metric: "load_midterm", desc: "Load Average (5 Minutes)", unit: '%'},
        {metric: "load_longterm", desc: "Load Average (15 Minutes)", unit: '%'},
        {metric: "processes_blocked", desc: "Blocked Processes", unit: 'INT'},
        {metric: "processes_paging", desc: "Paging Processes", unit: 'INT'},
        {metric: "processes_running", desc: "Running Processes", unit: 'INT'},
        {metric: "processes_sleeping", desc: "Sleeping Processes", unit: 'INT'},
        {metric: "processes_stopped", desc: "Stopped Processes", unit: 'INT'},
        {metric: "processes_zombie", desc: "Zombie Processes", unit: 'INT'}
    ];

    $scope.lifecycle_events_drivers = [
        {driver: "ssh", authentication_type: "PubKeyAuthentication"},
        {driver: "http", authentication_type: "HTTPBasicAuth"}
    ];

    $scope.sla_expressions = [
        {code: "EQ", desc: "Equal To", sign: "=="},
        {code: "NE", desc: "Not Equal To", sign: "!="},
        {code: "LT", desc: "Less Than", sign: "<"},
        {code: "LE", desc: "Less Than or Equal", sign: "<="},
        {code: "GT", desc: "Greater Than", sign: ">"},
        {code: "GE", desc: "Greater Than or Equal", sign: ">="}
    ];

    $scope.event_template_formats = [
        {format: "JSON"}
    ];

    $scope.penalty_types = [
        {type: "Discount"}
    ];

    $scope.available_images = {};

    $scope.loadImages = function () {
        Restangular.all('vnfs/images').getList().then(
            function (response) {
                $scope.available_images = response;
                console.log("GetImageList " + response.length + " Images found");
            }, function (response) {
                console.log("GetImageList error with status code " + response.status);
                console.log("GetImageList error message: " + response.data.detail);
            });
    };
    $scope.loadImages();

    // end of definitions
    $scope.loading_create_vnfd = false;
    $scope.vnfd = {};

    /*
     * VNFD
     */

    $scope.vnfd.release = 'T-NOVA'; //@ remove this...
    $scope.vnfd.name = '';
    $scope.vnfd.description = '';
    $scope.vnfd.descriptor_version = ''; //VNFD version
    $scope.vnfd.version = ''; //VNF version
    $scope.vnfd.type = ''; //vnf_types

    $scope.postNewVNF = function (json_vnfd) {

        $rootScope.root_loading = true;
        Restangular.all('vnfs').post(json_vnfd).then(
            function (response) {
                $rootScope.root_loading = false;
                console.log("CreateVNF " + response.name + " has been successfully completed");
                console.log(response);

                alertService.add('success', "VNF successfully created!");
                $state.go('index.vnfs.list');
            }, function (response) {
                $rootScope.root_loading = false;

                console.log("CreateVNF error with status code " + response.status);
                console.log("CreateVNF error message: " + response.data);

                alertService.add('danger', "VNF creation failed, " + response.data);
            });

        console.log($scope.vnfd);
    };


    $scope.addVDU = function (flavor) {

        var vdus = $scope.flavors[flavor].data.vdu;

        var specific_params = [];
        if ($scope.specific_monitoring_parameters[$scope.vnfd.type]) {
            specific_params = $scope.specific_monitoring_parameters[$scope.vnfd.type];
        }

        vdus.push({
            resource_requirements: {
                vcpus: 1,
                cpu_support_accelerator: "AES-NI",
                memory: 2,
                memory_unit: "GB",
                memory_parameters: {
                    large_pages_required: "",
                    numa_allocation_policy: ""
                },
                storage: {
                    size: 8,
                    size_unit: "GB",
                    persistence: false
                },
                hypervisor_parameters: {
                    type: "QEMU-KVM",
                    version: "10002|12001|2.6.32-358.el6.x86_64"
                },
                platform_pcie_parameters: {
                    device_pass_through: true,
                    'SR-IOV': true
                },
                network_interface_card_capabilities: {
                    mirroring: false,
                    'SR-IOV': true
                },
                network_interface_bandwidth: "", // @REMOVE
                network_interface_bandwidth_unit: "", // @REMOVE
                data_processing_acceleration_library: "",
                vswitch_capabilities: {
                    type: "ovs",
                    version: "2.0",
                    overlay_tunnel: "GRE"
                }
            },
            monitoring_parameters: $scope.generic_monitoring_parameters,
            monitoring_parameters_specific: _.clone(specific_params),
            networking_resources: "", // @REMOVE
            "scale_in_out": {
                "minimum": 1,
                "maximum": 1
            }
        });

    };

    $scope.removeVDU = function (flavor, vdu) {
        var vdus = $scope.flavors[flavor].data.vdu;
        vdus.splice(vdus.indexOf(vdu), 1);
    };

    $scope.getIndexVDU = function (flavor, vdu) {
        var vdus = $scope.flavors[flavor].data.vdu;
        return vdus.indexOf(vdu);
    };

    $scope.getIndexVDUNameString = function (flavor, vdu) {
        var vdus = $scope.flavors[flavor].data.vdu;
        var id = vdus.indexOf(vdu);
        if (id >= 0) {
            return 'vdu' + id;
        } else {
            return 'no vdu';
        }

    };

    $scope.addVL = function (flavor) {
        var vls = $scope.flavors[flavor].data.virtual_links;
        vls.push({
            connection_points_reference: [],
            vdu_reference: [],
            connection_points: [],
            bandwidth: "Unlimited",
            type: {type: 'E-LINE', description: 'Point-2-Point (E-LINE)'}
        });
    };

    $scope.removeVL = function (flavor, vl) {
        var vls = $scope.flavors[flavor].data.virtual_links;
        vls.splice(vls.indexOf(vl), 1);
    };

    $scope.getIndexVL = function (flavor, vl) {
        var vls = $scope.flavors[flavor].data.virtual_links;
        return vls.indexOf(vl);
    };


    $scope.addConnectionPoint = function (vl) {
        vl.connection_points.push({
            id: $scope.generateCPUID()
        });
    };

    $scope.removeConnectionPoint = function (vl, cp) {
        var cp_index = vl.connection_points.indexOf(cp);
        vl.connection_points.splice(cp_index, 1);
    };

    $scope.generateCPUID = function () {
        return 'CP' + ("0000" + (Math.random() * Math.pow(36, 4) << 0).toString(36)).slice(-4)
    };

    $scope.addAssuranceParam = function (flavor) {
        var aparams = $scope.flavors[flavor].data.assurance_parameters;
        aparams.push({
            violation: [
                {breaches_count: 2, interval: 360}
            ],
            penalty: {type: {type: "Discount"}}

        });
    };

    $scope.removeAssuranceParam = function (flavor, aparam) {
        var aparams = $scope.flavors[flavor].data.assurance_parameters;
        aparams.splice(aparams.indexOf(aparam), 1);
    };

    $scope.getIndexAssuranceParam = function (flavor, aparam) {
        var aparams = $scope.flavors[flavor].data.assurance_parameters;
        return aparams.indexOf(aparam);
    };

    $scope.addAssuranceParamViolation = function (violation) {
        violation.push({});
    };

    $scope.removeAssuranceParamViolation = function (violation_list, violation) {
        violation_list.splice(violation_list.indexOf(violation), 1);
    };

    // flavors UI ELEMENTS
    $scope.flavors = {
        gold: {
            enable: true,
            visible: true,
            data: {
                vdu: [],
                virtual_links: [],
                assurance_parameters: []
            }
        },
        silver: {
            enable: false,
            visible: true,
            data: {
                vdu: [],
                virtual_links: [],
                assurance_parameters: []
            }
        },
        bronze: {
            enable: false,
            visible: true,
            data: {
                vdu: [],
                virtual_links: [],
                assurance_parameters: []
            }
        }
    };

    $scope.toggleFlavor = function (flavor) {
        if ($scope.flavors[flavor].visible) {
            $scope.flavors[flavor].visible = false;
        } else {
            $scope.flavors[flavor].visible = true
        }
    };

    $scope.disableFlavor = function (flavor) {
        $scope.flavors[flavor].enable = false;
        $scope.flavors[flavor].visible = true;
        $scope.flavors[flavor].data.vdu = [];
        $scope.flavors[flavor].data.virtual_links = [];
    };

    $scope.enableFlavor = function (flavor) {
        $scope.flavors[flavor].enable = true;
    };

    $scope.getAllParameters = function (flavor) {
        return $scope.generic_monitoring_parameters.concat($scope.specific_monitoring_parameters[$scope.vnfd.type]);
        //return $scope.generic_monitoring_parameters;
    };

    $scope.copyFlavorTo = function (src_flavor, dst_flavor) {
        angular.copy($scope.flavors[src_flavor], $scope.flavors[dst_flavor]);
        $scope.enableFlavor(dst_flavor);
    };


    $scope.json_template = '{"controller":"get_attr[vdu0,PublicIp]", "vdu0":"get_attr[vdu1,PublicIp]"}';

    $scope.createVNF = function () {

        var vnfd = {};
        angular.copy($scope.vnfd, vnfd);

        vnfd.deployment_flavours = [];
        vnfd.vdus = [];
        vnfd.vnf_lifecycle_events = [];

        var flavor_index = 0;
        var vdu_index = 0;
        var vlink_index = 0;
        var connection_point_index = 0;
        var vlinks = [];

        angular.forEach($scope.flavors, function (flavor, flavor_key) {

            if (flavor.data.vdu.length > 0) {

                //set vlink ids
                angular.forEach(flavor.data.virtual_links, function (vlink, vlink_key) {
                    vlink.id = "vl" + vlink_index++;
                });

                //connection point ids are global (for the vnfd)

                // VDUs
                var flavor_vdus = [];
                var flavor_vdu_ref = [];
                var flavor_vlink_ref = [];

                var le;
                le = flavor.data.lifecycle_events;
                le.authentication_type = flavor.data.lifecycle_events.driver.authentication_type;
                le.driver = flavor.data.lifecycle_events.driver.driver;
                le.flavor_id_ref = 'flavor' + flavor_index;


                vnfd.vnf_lifecycle_events.push(le);


                angular.forEach(flavor.data.vdu, function (flavor_vdu, vdu_key) {

                    flavor_vdu.id = 'vdu' + vdu_index++;

                    //flavor_vdu.vdu_lifecycle_events.authentication_type = flavor_vdu.vdu_lifecycle_events.driver.authentication_type;
                    //flavor_vdu.vdu_lifecycle_events.driver = flavor_vdu.vdu_lifecycle_events.driver.driver;

                    //connections points
                    var connection_points = [];

                    angular.forEach(flavor.data.virtual_links, function (vlink, vlink_key) {
                        //vlink.connection_points_reference = [];

                        if (flavor_vlink_ref.indexOf(vlink.id) == -1) {
                            flavor_vlink_ref.push(vlink.id);
                        }

                        angular.forEach(vlink.connection_points, function (cp, cp_key) {

                            if (cp.vdu_ref == flavor_vdu) {
                                var con_point = {
                                    id: cp.id,
                                    vlink_ref: vlink.id
                                };

                                if (vlink.vdu_reference.indexOf(flavor_vdu.id) == -1) {
                                    vlink.vdu_reference.push(flavor_vdu.id);
                                }

                                vlink.connection_points_reference.push(con_point.id);

                                connection_points.push(con_point);
                                connection_point_index++;
                            }

                        });

                    });

                    flavor_vdu.connection_points = connection_points;

                    //push vdu
                    flavor_vdu_ref.push(flavor_vdu.id);
                    flavor_vdus.push(flavor_vdu);

                });


                angular.forEach(flavor.data.virtual_links, function (vlink, vlink_key) {

                    var vlink = {
                        id: vlink.id,
                        alias: vlink.alias,
                        connectivity_type: vlink.type.type,
                        connection_points_reference: vlink.connection_points_reference,
                        vdu_reference: vlink.vdu_reference,
                        root_requirement: vlink.bandwidth,
                        leaf_requirement: vlink.bandwidth,
                        qos: "",
                        access: vlink.active, //test_access
                        dhcp: vlink.dhcp,
                        net_segment: vlink.net_segment,
                        external_access: vlink.external_access
                    };
                    vlinks.push(vlink);
                });

                vnfd.vlinks = vlinks;

                //Assurance Parameters
                var assurance_params = [];

                angular.forEach(flavor.data.assurance_parameters, function (aparam, param_key) {

                    var assurance_param = {};

                    assurance_param.id = aparam.monitoring_parameter.metric;
                    assurance_param.rel_id = 'param' + param_key;
                    assurance_param.value = aparam.value;

                    assurance_param.unit = aparam.monitoring_parameter.unit;
                    assurance_param.formula = aparam.monitoring_parameter.metric + ' ' + aparam.expression.code + ' ' + aparam.value;

                    assurance_param.violation = aparam.violation;
                    //assurance_param.penalty = aparam.penalty;
                    assurance_param.penalty = {};
                    assurance_param.penalty.expression = aparam.penalty.value;
                    assurance_param.penalty.type = aparam.penalty.type.type;
                    assurance_param.penalty.unit = aparam.monitoring_parameter.unit;
                    assurance_param.penalty.validity = 'P' + aparam.penalty.validity.value + aparam.penalty.validity.period;

                    assurance_params.push(assurance_param);

                });

                //Flavors
                var flavor = {
                    id: 'flavor' + flavor_index,
                    flavour_key: flavor_key,
                    constraint: "",
                    vdu_reference: flavor_vdu_ref,
                    vlink_reference: flavor_vlink_ref,
                    assurance_parameters: assurance_params
                };

                if (vnfd.billing_model.model == "PAYG") {
                    vnfd.billing_model.period = 'P' + vnfd.billing_model.period.value + vnfd.billing_model.period.period;
                } else {
                    vnfd.billing_model.period = '';
                }

                if (vnfd.trade != true) {
                    vnfd.billing_model.price.min_per_period = vnfd.billing_model.price.max_per_period;
                }

                vnfd.deployment_flavours.push(flavor);
                vnfd.vdus.push.apply(vnfd.vdus, flavor_vdus);


            }

            flavor_index++;
        });

        console.log(angular.toJson(vnfd));

        $scope.postNewVNF(vnfd);
    };

    $scope.showImageUploadAModal = function () {

        ModalService.showModal({
            templateUrl: "/static/dashboard/templates/modals/image_upload.html",
            controller: function () {
                this.closeModal = function () {
                    $scope.loadImages();
                    close(); // close, but give 500ms for bootstrap to animate
                };
            },
            controllerAs: "ImageUploadCtrl"
        }).then(function (modal) {
            // only called on success...
            modal.element.modal();
        }).catch(function (error) {
            // error contains a detailed error message.
            console.log(error);
        });

    };


    $scope.onDriverSelectCallback = function (item, model, flavor) {
        if (item.driver == 'http') {
            flavor.data.lifecycle_events.authentication_port = 80;
        } else {
            flavor.data.lifecycle_events.authentication_port = 22;
        }
    };


    //STEPS
    $scope.active_step = 1;
    $scope.steps = {
        1: {
            enable: true
        },
        2: {
            enable: false
        },
        3: {
            enable: false
        },
        4: {
            enable: false
        },
        5: {
            enable: false
        }
    };

    $scope.gotoStep = function (target_step) {
        if ($scope.steps[target_step].enable) {
            $scope.active_step = target_step;
        }
    };

    $scope.nextStep = function (target_step) {
        if (target_step == target_step && $scope.active_step == target_step - 1) {
            $scope.steps[target_step].enable = true;
            $scope.active_step = target_step;
        }
    }

}


function VNFEditCtrl(Restangular, $scope, $rootScope, $state, $stateParams) {

    $scope.loadVNF = function () {
        $scope.loading_edit_vnfd = true;
        Restangular.one("vnfs", $stateParams.vnfdID).get().then(
            function (response) {
                $scope.vnfd = response;
                $scope.loading_edit_vnfd = false;
                console.log("GetVNF " + response.id + " has been successfully loaded");
            }, function (response) {
                console.log("GetVNF error with status code " + response.status);
                console.log("GetVNF error message: " + response.data.detail);
                $scope.loading_edit_vnfd = false;
            });
    };

    $scope.init = function () {
        $scope.loadVNF();
    };

    // $scope.init();

}


function VNFListCtrl(Restangular, $scope, $rootScope, ModalService, alertService) {

    $scope.vnfs = {};

    $scope.loadVNFs = function () {
        $rootScope.root_loading = true;
        Restangular.all('vnfs').getList().then(
            function (response) {
                $scope.vnfs = response;
                console.log("GetVNFList " + response.length + " VNFs found");
                $rootScope.root_loading = false;
            }, function (response) {
                console.log("GetVNFList error with status code " + response.status);
                console.log("GetVNFList error message: " + response.data.detail);
                $rootScope.root_loading = false;
            });
    };

    $scope.deleteVNF = function (id, vnf_name) {
        $rootScope.root_loading = true;
        Restangular.one("vnfs", id).remove().then(
            function () {
                $rootScope.root_loading = false;
                console.log("VNF " + vnf_name + " has been successfully deleted");
                $scope.loadVNFs();
                alertService.add('success', "VNF " + vnf_name + " has been successfully deleted");
            }, function (response) {
                console.log("DeleteVNF error with status code " + response.status);
                console.log("DeleteVNF error message: " + response.data.detail);
                $rootScope.root_loading = false;
                alertService.add('danger', "Failed to delete VNF " + vnf_name + ".");
            });
    };

    $scope.VNFDeleteConfirm = function (id, vnf_name) {

        //var confirm = $mdDialog.confirm()
        //    .parent(angular.element(document.body))
        //    .title('VNF Delete')
        //    .content('Are you sure you want to delete VNF ' + vnf_name + '?')
        //    .ok('YES')
        //    .cancel('NO');
        //$mdDialog.show(confirm).then(function () {
        //    $scope.deleteVNF(id, vnf_name);
        //});
        $scope.deleteVNF(id, vnf_name);
    };

    $scope.init = function () {

        $scope.loadVNFs();

    };

    $scope.init();


    $scope.showAModal = function (vnfd_id) {

        ModalService.showModal({
            templateUrl: "/static/dashboard/templates/modals/jsplump.html",
            controller: function () {
                this.jsplumb_vnfd_id = vnfd_id;
                this.jsplumb_vnfd_url = '/dashboard/vnf-diagram/' + vnfd_id
            },
            controllerAs: "jsPlumpCtrl"
        }).then(function (modal) {
            // only called on success...
            modal.element.modal();
            modal.jsplumb_vnfd_id = vnfd_id;
            //modal.vnfd_id = vnfd_id
        }).catch(function (error) {
            // error contains a detailed error message.
            console.log(error);
        });

    };

    $scope.showVNFDEditor = function (vnfd_id) {

        ModalService.showModal({
            animation: false,
            templateUrl: "/static/dashboard/templates/modals/vnfd-yaml-editor.html",
            controller: "VNFYAMLEditController",
            inputs: {
                vnfd_id: vnfd_id
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

angular.module('dashboard').controller('VNFYAMLEditController', [
    '$scope', '$element', '$http', 'close', 'vnfd_id',
    function ($scope, $element, $http, close, vnfd_id) {

        $scope.code = '';
        $http({
            method: 'GET',
            url: '/vnfs/' + vnfd_id + '/yaml'
        }).then(function successCallback(response) {
            //console.log(response.data);
            $scope.code = response.data;
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    }]);

