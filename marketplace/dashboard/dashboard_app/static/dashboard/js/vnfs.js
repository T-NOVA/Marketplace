angular.module('dashboard').controller('VNFCreateCtrl', ['Restangular', '$scope', '$rootScope', '$state', 'ModalService', VNFCreateCtrl]);
angular.module('dashboard').controller('VNFEditCtrl', ['Restangular', '$scope', '$rootScope', '$state', '$stateParams', VNFEditCtrl]);
angular.module('dashboard').controller('VNFListCtrl', ['Restangular', '$scope', '$rootScope', 'ModalService', VNFListCtrl]);


function VNFCreateCtrl(Restangular, $scope, $rootScope, $state, ModalService){

    // definitions
    //$scope.vnf_types = [
    //    {code: "TC", desc: "Traffic Classification"},
    //    {code: "FW", desc: "Firewall"},
    //    {code: "HG", desc: "Home Gateway"},
    //    {code: "SA", desc: "Security Appliance"}
    //];

    //$scope.vnf_types = [
    //    {code:"TC", desc: "Traffic Classification"},
    //    {code:"FW", desc: "Firewall"},
    //    {code:"HG", desc: "Home Gateway"},
    //    {code:"SA", desc: "Security Appliance"}
    //];

//15	VNF integration: vSA	Fraunhofer OK
//16	VNF integration: vSBC	Italtel
//17	VNF integration: vTU	Italtel
//18	VNF integration: vTC	Demokritos OK
//19	VNF integration: vHG	Viotech OK        Trancoder Unit

//20	VNF integration: vProxy	PrimeTel OK

    $scope.vnf_types = [
        {code:"vTC", desc: "Traffic Classification"},
        {code:"vSBC", desc: "Session Border Controller"},
        {code:"vTU", desc: "Transcoder Unit"},
        {code:"vHG", desc: "Home Gateway"},
        {code:"vSA", desc: "Security Appliance"},
        {code:"vPXAAS", desc: "Proxy"}
    ];

    $scope.memory_units = ['MB', 'GB'];
    $scope.storage_units = ['MB', 'GB', 'TB'];
    $scope.network_units = ['Kbps', 'Mbps', 'Gbps'];

    $scope.available_bandwidths = [
        "10Mbps",
        "100Mbps",
        "1Gbps",
        "10Gbps",
        "Unlimeted"
    ];

    $scope.billing_model_types = [
        {code: "PAYG", desc: "Pay-As-You-Go"},
        {code: "RS", desc: "Revenue Sharing"}
    ];

    $scope.billing_currencies = [
        {code: "EUR", desc: "Euro"},
        {code: "USD", desc: "US Dollars"}
    ];

    //$scope.connection_point_types = [
    //    {type:'vNIC.mac.addr', description:'Virtual NIC (Layer 2)'},
    //    {type:'floating.IP', description:'Floating IP (Layer 3)'},
    //    {type:'VPN.IP', description:'VPN IP (Layer 3)'}
    //];

    $scope.connection_link_types = [
        {type:'E-LINE', description:'Point-2-Point (E-LINE)'},
        {type:'E-TREE', description:'Point-2-Multipoint (E-TREE)'},
        {type:'E-LAN', description:'Lan (E-LAN)'}
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


    $scope.specific_monitoring_parameters = {
        'vTC':[],
        'vSBC': [
            {metric: "total_sip_sessions", desc: "Total SIP Sessions", unit: 'INT'},
            {metric: "rtp_pack_in", desc: "RTP pack IN", unit: 'INT'},
            {metric: "rtp_pack_out", desc: "RTP pack OUT", unit: 'INT'},
            {metric: "rtp_pack_in_byte", desc: "RTP pack in byte IN", unit: 'Byte'},
            {metric: "rtp_pack_out_byte", desc: "RTP pack in byte OUT", unit: 'Byte'},
            {metric: "rtp_frame_loss", desc: "RTP frame loss", unit: 'INT'},
            {metric: "average_latency", desc: "Average Latency (RTP delay)", unit: 'Msec'},
            {metric: "max_latency", desc: "Max Latency (RTP delay)", unit: 'Msec'},
            {metric: "average_interarrival_jitter", desc: "Average Interarrival Jitter", unit: 'Msec'},
            {metric: "max_interarrival_jitter", desc: "Max Interarrival Jitter", unit: 'Msec'},
            {metric: "number_of_in_transcoding", desc: "Number of IN Transcoding", unit: 'INT'},
            {metric: "number_of_out_transcoding", desc: "Number of OUT Transcoding", unit: 'INT'},
            {metric: "number_of_in_transrating", desc: "Number of OUT Transrating", unit: 'INT'},
            {metric: "number_of_out_transrating", desc: "Number of OUT Transrating", unit: 'INT'}
        ],
        'vTU':[],
        'vHG':[],
        'vSA':[],
        'vPXAAS':[
            {
                "metric": "httpnum",
                "desc": "Number of HTTP requests received by Squid",
                "unit": "INT"
            },
            {
                "metric": "hits",
                "desc": "Cache hits percentage of all requests for the last 5 minutes",
                "unit": "%"
            },
            {
                "metric": "hits_bytes",
                "desc": "Cache hits percentage of bytes sent for the last 5 minutes",
                "unit": "%"
            },
            {
                "metric": "memoryhits",
                "desc": "Memory hits percentage for the last 5 minutes (hits that are logged as TCP_MEM_HIT)",
                "unit": "%"
            },
            {
                "metric": "diskhits",
                "desc": "Disk hits percentage for the last 5 minutes (hits that are logged as TCP_HIT)",
                "unit": "%"
            },
            {
                "metric": "cachediskutilization",
                "desc": "Cache disk utilization",
                "unit": "%"
            },
            {
                "metric": "cachememkutilization",
                "desc": "Cache memory utilization",
                "unit": "%"
            },
            {
                "metric": "usernum",
                "desc": "Number of users accessing the proxy",
                "unit": "INT"
            },
            {
                "metric": "cpuusage",
                "desc": "CPU consumed by Squid for the last 5 minutes",
                "unit": "%"
            }
        ]
    };

    $scope.lifecycle_events_drivers = [
        {driver:"ssh", authentication_type: "PubKeyAuthentication"},
        {driver:"http", authentication_type: "Digest"}
    ];

    $scope.sla_expressions = [
        {code: "EQ", desc: "Equal To", sign: "=="},
        {code: "NE", desc: "Not Equal To", sign: "!="},
        {code: "LT", desc: "Less Than", sign: "<"},
        {code: "LE", desc: "Less Than or Equal", sign: "<="},
        {code: "GT", desc: "Greater Than", sign: ">"},
        {code: "GE", desc: "Greater Than or Equal", sign: ">="}
    ];


    $scope.event_template_formats= [
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

    $scope.vnfd.release='T-NOVA'; //@ remove this...

    //$scope.vnfd.id='52439e7c-c85c-4bae-88c4-8ee8da4c5485'; //@NFSTORE
    //$scope.vnfd.provider=''; //@VNFS module
    //$scope.vnfd.provider_id= 23  //@VNFS module

    $scope.vnfd.name='';
    $scope.vnfd.description='';
    $scope.vnfd.descriptor_version=''; //VNFD version
    $scope.vnfd.version=''; //VNF version

    //$scope.vnfd.manifest_file_md5='fa8773350c4c236268f0bd7807c8a3b2'; //@NFSTORE

    $scope.vnfd.type=''; //vnf_types

    // add default connection points
    //$scope.vnfd.connection_points = {
    //    management: {
    //        id: "1",
    //        name: "Management",
    //        type: "floating.IP"
    //    },
    //    datapath_in: {
    //        id: "2",
    //        name: "Datapath In",
    //        type: "floating.IP"
    //    },
    //    datapath_out: {
    //        id: "3",
    //        name: "Datapath Out",
    //        type: "vNIC.mac.addr"
    //    },
    //    storage: {
    //        id: "4",
    //        name: "Storage",
    //        type: "vNIC.mac.addr"
    //    },
    //    monitoring: {
    //        id: "5",
    //        name: "Monitoring",
    //        type: "VPN.IP"
    //    }
    //};

    // add default virtual links
    //$scope.vnfd.virtual_link = {
    //    management: {
    //        id: "1",
    //        connectivity_type: "E-LAN",
    //        //connection_point_references: "1.1,2.1,management",
    //        root_requirements: "",
    //        leaf_requirements: "",
    //        qos: "",
    //        test_access: ""
    //    },
    //    datapath:{
    //        id:"2",
    //        connectivity_type:"E-LAN",
    //        //connection_point_references:"1.2,2.2,datapath-in,datapath-out",
    //        root_requirements:"",
    //        leaf_requirements:"",
    //        qos:"",
    //        test_access:""
    //     },
    //     monitoring:{
    //        id:"3",
    //        connectivity_type:"E-LAN",
    //        //connection_point_references:"1.3,2.3,monitoring-point",
    //        root_requirements:"",
    //        leaf_requirements:"",
    //        qos:"",
    //        test_access:""
    //     },
    //    storage:{
    //        id:"4",
    //        connectivity_type:"E-LAN",
    //        //connection_point_references:"2.4,storage-point",
    //        root_requirements:"",
    //        leaf_requirements:"",
    //        qos:"",
    //        test_access:""
    //     }
    //};


    // @TODO NFS must change this to vdus
    //$scope.vnfd.Vdu = [];

    //$scope.addVDU = function(){
    //    // add an empty VDU
    //
    //    //$scope.vnfd.Vdu.push({});
    //
    //    $scope.vnfd.Vdu.push(
    //        {
    //            vm_image: 'image.img', //@TODO NFS image uploading does not working...
    //            id: '___EMPTY', //@TODO VDU ID NFS exception if missing...
    //            vnfc: {
    //                connection_points: []
    //            }
    //        }
    //    );
    //};
    //
    //$scope.removeVDU = function(index){
    //     $scope.vnfd.Vdu.splice(index, 1);
    //};
    //
    //
    //$scope.addConnectionPoint = function(vdu){
    //     vdu.vnfc.connection_points.push({});
    //};
    //
    //
    //
    //$scope.removeConnectionPoint = function(vdu, index){
    //     vdu.vnfc.connection_points.splice(index, 1);
    //};

    $scope.postNewVNF = function (json_vnfd) {

        $scope.loading_create_vnfd = true;
        Restangular.all('vnfs').post(json_vnfd).then(
            function (response) {
                $scope.loading_create_vnfd = false;
                console.log("CreateVNF " + response.name + " has been successfully completed");
                console.log(response);
                //$alert({title: '', content: "VNF successfully created!", placement: 'bot-right', type: 'success', show: true, duration:3});
                //$mdToast.show($mdToast.simple().content("VNF successfully created!").position('top right').hideDelay(3000));
                $state.go('index.vnfs.list');
            }, function (response) {
                console.log("CreateVNF error with status code " + response.status);
                console.log("CreateVNF error message: " + response.data.detail);
                $scope.loading_create_vnfd = false;
                //$alert({title: '', content: "Failed to create VNF.", placement: 'bot-right', type: 'danger', show: true, duration:3});
                //$mdToast.show($mdToast.simple().content("Failed to create VNF").position('top right').hideDelay(3000));
            });

        console.log($scope.vnfd);
    };



    $scope.addVDU = function (flavor) {

        var vdus = $scope.flavors[flavor].data.vdu;

        vdus.push({
            //_id: vdus.length,
            //id: 'vdu' + vdus.length, //@add id on generation
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
            monitoring_parameters:$scope.generic_monitoring_parameters,
            monitoring_parameters_specific:$scope.specific_monitoring_parameters[$scope.vnfd.type],
            networking_resources: "", // @REMOVE
            //vdu_lifecycle_events: {
            //    //drivers: [],
            //    //driver:{},
            //    driver:$scope.lifecycle_events_drivers[0],
            //    events: {
            //        start: {
            //            command: "service apache start",
            //            template_file_format: "JSON",
            //            template_file: "{ \"controller\":\"cntr_IP\", \"vdu1\":\"vdu1_IP\", \"vdu2\":\"vdu2_IP\" }"
            //        },
            //        stop: {
            //            command: "service apache stop",
            //            template_file_format: "JSON",
            //            template_file: "{\"controller\":\"cntr_IP\",\"vdu1\":\"vdu1_IP\",\"vdu2\":\"vdu2_IP\"}"
            //        },
            //        restart: {
            //            command: "",
            //            template_file_format: "",
            //            template_file: ""
            //        },
            //        'scale-in': "",
            //        'scale-out': ""
            //    }
            //},
            "scale_in_out": {
              "minimum": 1,
              "maximum": 1
            }
            //"vnfc": {
            //    //"id": "vdu0:vnfc0", // complete later
            //    "networking": []
            //}
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
        if (id>=0){
            return 'vdu'+id;
        }else{
            return 'no vdu';
        }

    };

    //$scope.addNetwork = function (flavor) {
    //    var nets = $scope.flavors[flavor].data.networks;
    //    nets.push({
    //        network_alias:""
    //    });
    //};
    //
    //$scope.removeNetwork = function (flavor, net) {
    //    var nets = $scope.flavors[flavor].data.networks;
    //    nets.splice(nets.indexOf(net), 1);
    //};
    //
    //$scope.getIndexNetwork = function (flavor, net) {
    //    var nets = $scope.flavors[flavor].data.networks;
    //    return nets.indexOf(net);
    //};

    $scope.addVL = function (flavor) {
        var vls = $scope.flavors[flavor].data.virtual_links;
        vls.push({
            connection_points_reference:[],
            vdu_reference:[],
            connection_points:[],
            bandwidth: "Unlimeted",
            type: {type:'E-LINE', description:'Point-2-Point (E-LINE)'}
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
        //var vls = $scope.flavors[flavor].data.virtual_links;
        //return vls.indexOf(vl);
        vl.connection_points.push({
            id:$scope.generateCPUID()
        });
    };

    $scope.removeConnectionPoint = function (vl, cp) {
        var cp_index = vl.connection_points.indexOf(cp);
        vl.connection_points.splice(cp_index, 1);
    };

    $scope.generateCPUID = function(){
        return 'CP'+("0000" + (Math.random()*Math.pow(36,4) << 0).toString(36)).slice(-4)
    };

    $scope.addAssuranceParam = function (flavor) {
        var aparams = $scope.flavors[flavor].data.assurance_parameters;
        aparams.push({
            violation:[
                {breaches_count:2,interval:360}
            ],
            penalty:{type:{type: "Discount"}}

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
            enable:true,
            visible: true,
            data:{
                vdu:[],
                //networks: [
                //    {
                //        network_alias: "management",
                //        network_subnet: "192.168.10.0/24"
                //    },
                //    {
                //        network_alias: "storage",
                //        network_subnet: "192.168.20.0/24"
                //    }
                //],
                virtual_links:[],
                assurance_parameters: []
            }
        },
        silver: {
            enable:false,
            visible: true,
            data:{
                vdu:[],
                //networks:[],
                virtual_links:[],
                assurance_parameters: []
            }
        },
        bronze: {
            enable:false,
            visible: true,
            data:{
                vdu:[],
                //networks:[],
                virtual_links:[],
                assurance_parameters: []
            }
        }
    };

    $scope.toggleFlavor = function (flavor) {
        if ($scope.flavors[flavor].visible){
            $scope.flavors[flavor].visible = false;
        }else{
            $scope.flavors[flavor].visible = true
        }
    };

    $scope.disableFlavor = function (flavor) {
        $scope.flavors[flavor].enable = false;
        $scope.flavors[flavor].visible = true;
        $scope.flavors[flavor].data.vdu = [];
        //$scope.flavors[flavor].data.networks = [];
        $scope.flavors[flavor].data.virtual_links = [];
        //$scope.flavors[flavor].data.assurance_parameters = []
    };

    $scope.enableFlavor = function (flavor) {
        $scope.flavors[flavor].enable = true;
    };


    //$scope.flavorsdropdownmenu = [
    //    {
    //        "text": "<span>Add Flavor <span class=\"label flavor pill gold\">Gold</span></span>",
    //        "click": "enableFlavor('gold')"
    //    },
    //    {
    //        "text": "<span>Add Flavor <span class=\"label flavor pill silver\">Silver</span></span>",
    //        "click": "enableFlavor('silver')"
    //    },
    //    {
    //        "text": "<span>Add Flavor <span class=\"label flavor pill bronze\">Bronze</span></span>",
    //        "click": "enableFlavor('bronze')"
    //    }
    //];


    //$scope.copygolddropdownmenu = [
    //    {
    //        "text": "<span>Copy to <span class=\"label flavor pill silver\">Silver</span></span>",
    //        "click": "copyFlavorTo('gold', 'silver')"
    //    },
    //    {
    //        "text": "<span>Copy to <span class=\"label flavor pill bronze\">Bronze</span></span>",
    //        "click": "copyFlavorTo('gold', 'bronze')"
    //    }
    //];
    //
    //$scope.copysilverdropdownmenu = [
    //    {
    //        "text": "<span>Copy to <span class=\"label flavor pill gold\">Gold</span></span>",
    //        "click": "copyFlavorTo('silver', 'gold')"
    //    },
    //    {
    //        "text": "<span>Copy to <span class=\"label flavor pill bronze\">Bronze</span></span>",
    //        "click": "copyFlavorTo('silver', 'bronze')"
    //    }
    //];
    //
    //$scope.copybronzedropdownmenu = [
    //    {
    //        "text": "<span>Copy to <span class=\"label flavor pill gold\">Gold</span></span>",
    //        "click": "copyFlavorTo('bronze', 'gold')"
    //    },
    //    {
    //        "text": "<span>Copy to <span class=\"label flavor pill silver\">Silver</span></span>",
    //        "click": "copyFlavorTo('bronze', 'silver')"
    //    }
    //];

    $scope.copyFlavorTo = function(src_flavor, dst_flavor){
        angular.copy($scope.flavors[src_flavor], $scope.flavors[dst_flavor]);
        $scope.enableFlavor(dst_flavor);
    };


    $scope.createVNF = function(){

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

            if (flavor.data.vdu.length>0){

                //set vlink ids
                angular.forEach(flavor.data.virtual_links, function (vlink, vlink_key) {
                    vlink.id = "vl" + vlink_index++;
                });

                //set network ids and assign connection points
                //angular.forEach(flavor.data.networks, function (network, network_key) {
                //
                //    network.id='net'+network_key;
                //
                //    //assign connection points
                //    var connection_points = [];
                //
                //    angular.forEach(flavor.data.virtual_links, function (vlink, vlink_key) {
                //
                //        if (vlink.source==network || vlink.target==network){
                //            var con_point = {
                //                id:"cp"+connection_point_index,
                //                vlink_ref: vlink.id
                //            };
                //
                //            vlink.connection_points_reference.push(con_point.id);
                //
                //            connection_points.push(con_point);
                //            connection_point_index++;
                //        }
                //    });
                //
                //    network.connection_points = connection_points;
                //});

                //vnfd.networks = flavor.data.networks;

                //connection point ids are global (for the vnfd)

                // VDUs
                var flavor_vdus = [];
                var flavor_vdu_ref = [];
                var flavor_vlink_ref = [];

                var le;
                le = flavor.data.lifecycle_events;
                le.authentication_type = flavor.data.lifecycle_events.driver.authentication_type;
                le.driver = flavor.data.lifecycle_events.driver.driver;
                le.flavor_id_ref = 'flavor'+flavor_index;


                vnfd.vnf_lifecycle_events.push(le);


                angular.forEach(flavor.data.vdu, function (flavor_vdu, vdu_key) {

                    flavor_vdu.id = 'vdu' + vdu_index++;

                    //flavor_vdu.vdu_lifecycle_events.authentication_type = flavor_vdu.vdu_lifecycle_events.driver.authentication_type;
                    //flavor_vdu.vdu_lifecycle_events.driver = flavor_vdu.vdu_lifecycle_events.driver.driver;

                    //connections points
                    var connection_points = [];

                    angular.forEach(flavor.data.virtual_links, function (vlink, vlink_key) {
                        //vlink.connection_points_reference = [];

                        if(flavor_vlink_ref.indexOf(vlink.id) == -1){
                            flavor_vlink_ref.push(vlink.id);
                        }

                        angular.forEach(vlink.connection_points, function (cp, cp_key) {

                            //console.log('pame mori cp');
                            //    console.log(cp);

                                if (cp.vdu_ref == flavor_vdu){
                                    var con_point = {
                                        //id:"cp"+connection_point_index,
                                        id: cp.id,
                                        vlink_ref: vlink.id
                                    };

                                    //if(!$.inArray(flavor_vdu.id, vlink.vdu_reference)){
                                    if(vlink.vdu_reference.indexOf(flavor_vdu.id) == -1){
                                        vlink.vdu_reference.push(flavor_vdu.id);
                                    }

                                    vlink.connection_points_reference.push(con_point.id);

                                    connection_points.push(con_point);
                                    connection_point_index++;
                                }

                        });

                        //if (vlink.source==flavor_vdu || vlink.target==flavor_vdu){
                        //    var con_point = {
                        //        id:"cp"+connection_point_index,
                        //        vlink_ref: vlink.id
                        //    };
                        //
                        //    vlink.connection_points_reference.push(con_point.id);
                        //
                        //    connection_points.push(con_point);
                        //    connection_point_index++;
                        //}
                    });

                    flavor_vdu.connection_points = connection_points;

                    //push vdu
                    flavor_vdu_ref.push(flavor_vdu.id);
                    flavor_vdus.push(flavor_vdu);

                });


                angular.forEach(flavor.data.virtual_links, function (vlink, vlink_key) {

                    var vlink = {
                        id:vlink.id,
                        alias: vlink.alias,
                        connectivity_type:vlink.type.type,
                        //connection_obj_reference:[vlink.source.id, vlink.target.id],
                        connection_points_reference:vlink.connection_points_reference,
                        vdu_reference:vlink.vdu_reference,
                        root_requirement:vlink.bandwidth,
                        leaf_requirement:vlink.bandwidth,
                        qos:"",
                        access:vlink.active, //test_access
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
                    assurance_param.formula = aparam.monitoring_parameter.metric +' '+ aparam.expression.code  + ' ' + aparam.value;

                    assurance_param.violation = aparam.violation;
                    //assurance_param.penalty = aparam.penalty;
                    assurance_param.penalty = {};
                    assurance_param.penalty.expression = aparam.penalty.value;
                    assurance_param.penalty.type = aparam.penalty.type.type;
                    assurance_param.penalty.unit = aparam.monitoring_parameter.unit;
                    assurance_param.penalty.validity = aparam.penalty.validity.value + 'P' + aparam.penalty.validity.period;

                    assurance_params.push(assurance_param);

                });

                 //Flavors

                var flavor = {
                    id: 'flavor'+flavor_index,
                    flavour_key: flavor_key,
                    constraint: "",
                    vdu_reference: flavor_vdu_ref,
                    vlink_reference: flavor_vlink_ref,
                    //constituent_vdu: {
                    //  "vdu_reference": "vdu0",
                    //  "number_of_instances": 1,
                    //  "constituent_vnfc": "vdu0:vnfc0"
                    //},
                    assurance_parameters: assurance_params
                };


                vnfd.deployment_flavours.push(flavor);
                vnfd.vdus.push.apply(vnfd.vdus, flavor_vdus);


            }

            flavor_index++;
        });

        console.log(angular.toJson(vnfd));
        $scope.postNewVNF(vnfd);



    };

    $scope.showImageUploadAModal = function() {

         ModalService.showModal({
          templateUrl: "/static/dashboard/templates/modals/image_upload.html",
             controller: function () {
                 this.closeModal = function () {
                     $scope.loadImages();
                     close(); // close, but give 500ms for bootstrap to animate
                 };
             },
          controllerAs : "ImageUploadCtrl"
        }).then(function(modal) {
          // only called on success...
             modal.element.modal();
        }).catch(function(error) {
          // error contains a detailed error message.
          console.log(error);
        });

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
    }

}


function VNFEditCtrl(Restangular, $scope, $rootScope, $state, $stateParams){

    // definitions
    //$scope.vnf_types = [
    //    {code: "TC", desc: "Traffic Classification"},
    //    {code: "FW", desc: "Firewall"},
    //    {code: "HG", desc: "Home Gateway"},
    //    {code: "SA", desc: "Security Appliance"}
    //];

    $scope.vnf_types = [
        {code:"vTC", desc: "Traffic Classification"},
        {code:"vSBC", desc: "Session Border Controller"},
        {code:"vTU", desc: "Transcoder Unit"},
        //{code:"vFW", desc: "Firewall"},
        {code:"vHG", desc: "Home Gateway"},
        {code:"vSA", desc: "Security Appliance"},
        {code:"vPXaas", desc: "PXaas"}
    ];

    $scope.memory_units = ['MB', 'GB'];
    $scope.storage_units = ['MB', 'GB', 'TB'];

    $scope.billing_model_types = [
        {code: "PAYG", desc: "Pay-As-You-Go"},
        {code: "RS", desc: "Revenue Sharing"}
    ];

    $scope.billing_currencies = [
        {code: "EUR", desc: "Euro"},
        {code: "USD", desc: "US Dollars"}
    ];

    $scope.connection_point_types = [
        {type:'vNIC.mac.addr', description:'Virtual NIC (Layer 2)'},
        {type:'floating.IP', description:'Floating IP (Layer 3)'},
        {type:'VPN.IP', description:'VPN IP (Layer 3)'}
    ];

    //$scope.connection_link_types = [
    //    {type:'E-TREE', description:'E-TREE'},
    //    {type:'E-LAN', description:'E-LAN'}
    //];

    $scope.available_periods = [
        {code: "D", desc: "Day"},
        {code: "W", desc: "Week"},
        {code: "M", desc: "Month"},
        {code: "Y", desc: "Year"}
    ];

    $scope.computation_requirements_list = ['High','Average','Low'];


    // end of definitions




    $scope.loading_edit_vnfd = false;
    $scope.vnfd = {};


    $scope.removeVDU = function(index){
         $scope.vnfd.Vdu.splice(index, 1);
    };


    //$scope.addConnectionPoint = function(vdu){
    //     vdu.vnfc.connection_points.push({});
    //};
    //
    //$scope.removeConnectionPoint = function(vdu, index){
    //     vdu.vnfc.connection_points.splice(index, 1);
    //};

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

    $scope.init();


}


function VNFListCtrl(Restangular, $scope, $rootScope, ModalService) {

    $scope.loading_vnfs = false;
    $scope.vnfs = {};

    $scope.loadVNFs = function () {
        $scope.loading_vnfs = true;
        Restangular.all('vnfs').getList().then(
            function (response) {
                $scope.vnfs = response;
                console.log("GetVNFList " + response.length + " VNFs found");
                $scope.loading_vnfs = false;
            }, function (response) {
                console.log("GetVNFList error with status code " + response.status);
                console.log("GetVNFList error message: " + response.data.detail);
                $scope.loading_vnfs = false;
            });
    };

    $scope.deleteVNF = function (id, vnf_name) {
        $scope.loading_vnfs = true;
        Restangular.one("vnfs", id).remove().then(
            function () {
                $scope.loading_vnfs = false;
                console.log("VNF " + vnf_name + " has been successfully deleted");
                $scope.loadVNFs();
                //$alert({title: '', content: "VNF " + vnf_name + " has been successfully deleted", placement: 'bot-right', type: 'success', show: true, duration:3});
                //$mdToast.show($mdToast.simple().content("VNF " + vnf_name + " has been successfully deleted").position('top right').hideDelay(3000));
            }, function (response) {
                console.log("DeleteVNF error with status code " + response.status);
                console.log("DeleteVNF error message: " + response.data.detail);
                $scope.loading_vnfs = false;
                //$alert({title: '', content: "Failed to delete vnf " + vnf_name + ".", placement: 'bot-right', type: 'danger', show: true, duration:3});
                //$mdToast.show($mdToast.simple().content("Failed to delete vnf " + vnf_name).position('top right').hideDelay(3000));
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


    $scope.showAModal = function(vnfd_id) {


         ModalService.showModal({
          templateUrl: "/static/dashboard/templates/modals/jsplump.html",
          //controller: "jsPlumpCtrl"
          controller: function() {
            this.jsplumb_vnfd_id = vnfd_id;///dashboard/vnf-diagram/[[ jsPlumpCtrl.jsplumb_vnfd_id ]]
            this.jsplumb_vnfd_url = '/dashboard/vnf-diagram/'+vnfd_id
          },
          controllerAs : "jsPlumpCtrl"
        }).then(function(modal) {
          // only called on success...
             modal.element.modal();
             modal.jsplumb_vnfd_id = vnfd_id;
             //modal.vnfd_id = vnfd_id
        }).catch(function(error) {
          // error contains a detailed error message.
          console.log(error);
        });

  };

        $scope.showVNFDEditor = function(vnfd_id) {


         ModalService.showModal({
          animation: false,
          templateUrl: "/static/dashboard/templates/modals/vnfd-yaml-editor.html",
          controller: "VNFYAMLEditController",
          inputs: {
            vnfd_id: vnfd_id
          },
          //controller: function () {
          //
          //    //$http({
          //    //    method: 'GET',
          //    //    url: '/vnfs/409/yaml'
          //    //  }).then(function successCallback(response) {
          //    //    alert('ggg')
          //    //      // this callback will be called asynchronously
          //    //      // when the response is available
          //    //    }, function errorCallback(response) {
          //    //      // called asynchronously if an error occurs
          //    //      // or server returns response with an error status.
          //    //    });
          //
          //    this.code = 'ggggg';
          //    this.editorOptions = {
          //        //lineWrapping: true,
          //        //lineNumbers: true,
          //        //readOnly: 'nocursor',
          //        //mode: 'yaml'
          //    }
          //},
          //controllerAs : "VNFDEditor"
        }).then(function(modal) {
          // only called on success...
             modal.element.modal();
             //modal.jsplumb_vnfd_id = vnfd_id;
             //modal.vnfd_id = vnfd_id
        }).catch(function(error) {
          // error contains a detailed error message.
          console.log(error);
        });

  };



}

    angular.module('dashboard').controller('VNFYAMLEditController', [
        '$scope', '$element', '$http', 'close', 'vnfd_id',
        function ($scope, $element, $http, close, vnfd_id) {

              $scope.code= '';
              $http({
                  method: 'GET',
                  url: '/vnfs/'+vnfd_id+'/yaml'
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

            //$scope.code = 'gggg';
            //$scope.closeModal = function () {
            //
            //    //  Manually hide the modal using bootstrap.
            //    //$element.modal('hide');
            //
            //    //  Now close as normal, but give 500ms for bootstrap to animate
            //    close();
            //};

        }]);

//angular.module('dashboard').controller('jsPlumpCtrl', function($scope, close) {
//
//modal.jsplumb_vnfd_id = vnfd_id;
//
//});
