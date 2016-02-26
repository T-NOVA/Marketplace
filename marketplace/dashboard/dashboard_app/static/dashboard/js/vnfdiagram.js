var connection_points_map = {

    vdu: {
        0: [[0.2, 1, 0, 1, 0]],
        1: [[0.4, 1, 0, 1, 0]],
        2: [[0.6, 1, 0, 1, 0]],
        3: [[0.8, 1, 0, 1, 0]]
    },
    net: {
        1: [
            [[0.2045, 0, 2, -1]],
            [[0.3985, 0, 2, -1]],
            [[0.5925, 0, 2, -1]],
            [[0.787, 0, 2, -1]]
        ],
        2: [
            [[0.1, 0, 2, -1]],
            [[0.19, 0, 2, -1]],
            [[0.28, 0, 2, -1]],
            [[0.37, 0, 2, -1]],

            [[0.613, 0, 2, -1]],
            [[0.703, 0, 2, -1]],
            [[0.793, 0, 2, -1]],
            [[0.883, 0, 2, -1]]
        ],
        3: [
            [[0.067, 0, 2, -1]],
            [[0.124, 0, 2, -1]],
            [[0.180, 0, 2, -1]],
            [[0.237, 0, 2, -1]],

            [[0.412, 0, 2, -1]],
            [[0.470, 0, 2, -1]],
            [[0.526, 0, 2, -1]],
            [[0.583, 0, 2, -1]],

            [[0.758, 0, 2, -1]],
            [[0.814, 0, 2, -1]],
            [[0.871, 0, 2, -1]],
            [[0.928, 0, 2, -1]]
        ],
        4: [
            [[0.048, 0, 2, -1]],
            [[0.086, 0, 2, -1]],
            [[0.124, 0, 2, -1]],
            [[0.162, 0, 2, -1]],

            [[0.301, 0, 2, -1]],
            [[0.339, 0, 2, -1]],
            [[0.377, 0, 2, -1]],
            [[0.415, 0, 2, -1]],

            [[0.552, 0, 2, -1]],
            [[0.590, 0, 2, -1]],
            [[0.628, 0, 2, -1]],
            [[0.666, 0, 2, -1]],

            [[0.804, 0, 2, -1]],
            [[0.842, 0, 2, -1]],
            [[0.880, 0, 2, -1]],
            [[0.918, 0, 2, -1]]
        ]
    }
};

jsPlumb.ready(function () {

    var vnfd = {};
    var vnfd_id = $("#canvas").data('id');


    $.getJSON( "/vnfs/"+vnfd_id+"/vnfd", function( data ) {

        vnfd = data;

    //init elements
    var number_of_vdus = vnfd.vdus.length;

    $.each(vnfd.vdus, function (key, vdu) {
        $(".vdus").append('<div class="vdu" id="'+vdu.id+'">'+vdu.alias+' <strong>'+vdu.id+' </strong></div>');
    });

    $.each(vnfd.networks, function (key, net) {
       $(".networks").append('<div class="network '+net.id+'" id="'+net.id+'">'+net.network_alias+' <small> '+net.network_subnet+' </small></div>');
    });



    var instance = jsPlumb.getInstance({
        Container: "canvas"
    });


    //var instance = jsPlumb.getInstance({
    //    // default drag options
    //    DragOptions: { cursor: 'pointer', zIndex: 2000 },
    //    // the overlays to decorate each connection with.  note that the label overlay uses a function to generate the label text; in this
    //    // case it returns the 'labelText' member that we set on each connection in the 'init' method below.
    //    ConnectionOverlays: [
    //        [ "Arrow", { location: 1 } ],
    //        [ "Label", {
    //            location: 0.1,
    //            id: "label",
    //            cssClass: "aLabel"
    //        }]
    //    ],
    //    Container: "canvas"
    //});


    //instance.importDefaults({
    //  //Connector : [ "Straight", { stub: 50 } ],
    //  Connector : [ "Straight", { stub: 50, cornerRadius: 5 } ],
    //  //Anchors : [ "BottomCenter" ]
    //});


    var basicType = {
        connector: "StateMachine",
        paintStyle: {strokeStyle: "red", lineWidth: 4},
        hoverPaintStyle: {strokeStyle: "blue"},
        overlays: [
            "Arrow"
        ]
    };

    instance.registerConnectionType("basic", basicType);

    // this is the paint style for the connecting lines..
    var connectorPaintStyle = {
            lineWidth: 4,
            strokeStyle: "black",
            joinstyle: "round",
            //outlineColor: "white",
            outlineWidth: 2
        },
    // .. and this is the hover style.
        connectorHoverStyle = {
            lineWidth: 4,
            strokeStyle: "#216477",
            outlineWidth: 2
            //outlineColor: "white"
        },
        endpointHoverStyle = {
            fillStyle: "#216477",
            strokeStyle: "#216477"
        };

    function getSourceEndpoint(label){
        return {
            endpoint: "Rectangle",
            paintStyle: {
                fillStyle: "orange"
                //strokeStyle: "orange",
                //fillStyle: "transparent",
                //radius: 7,
                //lineWidth: 3
            },
            isSource: true,
            connector: ["Straight", {}],
            connectorStyle: connectorPaintStyle,
            hoverPaintStyle: endpointHoverStyle,
            connectorHoverStyle: connectorHoverStyle,
            dragOptions: {},
            overlays: [
                ["Label", {
                    location: [0.5, -0.5],
                    label: label,
                    cssClass: "endpointSourceLabel"
                }]
            ]
        }
    }

    function getTargetEndpoint(label){
        return {
            endpoint: "Rectangle",
            paintStyle: {
                fillStyle: "orange"
                //radius: 11
            },
            hoverPaintStyle: endpointHoverStyle,
            maxConnections: -1,
            dropOptions: {hoverClass: "hover", activeClass: "active"},
            isTarget: true,
            overlays: [
                ["Label",
                    {
                        location: [2, -0.5],
                        label: label,
                        cssClass: "endpointTargetLabel"
                    }
                ]
            ]
        }
    }

    //init = function (connection) {
    //    connection.getOverlay("label").setLabel(connection.sourceId.substring(15) + "-" + connection.targetId.substring(15));
    //};

    var _addEndpoints = function (toId, sourceAnchors, targetAnchors, label) {
        for (var i = 0; i < sourceAnchors.length; i++) {
            //var sourceUUID = toId + sourceAnchors[i];
            var sourceUUID = toId + label;
            console.log(sourceUUID);
            instance.addEndpoint(toId, getSourceEndpoint(label), {
                anchor: sourceAnchors[i], uuid: sourceUUID
            });
        }
        for (var j = 0; j < targetAnchors.length; j++) {
            //var targetUUID = toId + targetAnchors[j];
            var targetUUID = toId + label;
            console.log(targetUUID);
            instance.addEndpoint(toId, getTargetEndpoint(label), {anchor: targetAnchors[j], uuid: targetUUID});
        }
    };

    // suspend drawing and initialise.
    instance.batch(function () {

            // add vdus connection points
            $.each(vnfd.vdus, function (key_vdu, vdu) {

                $.each(vdu.connection_points, function (key_cp, cp) {

                    _addEndpoints(vdu.id, connection_points_map['vdu'][key_cp], [], cp.id);

                    $.each(vnfd.vlinks, function (key_vlinks, vlink) {
                       // _addEndpoints(vdu.id, connection_points_map['vdu'][key_cp], [], cp.id);

                        // check if cp id is in virtual link connection_points_reference
                        if ($.inArray(cp.id, vlink.connection_points_reference ) > -1){

                            var source_obj = '';
                            var target_obj = '';
                            if (vlink.connection_obj_reference[0] == vdu.id) {
                                source_obj = vlink.connection_obj_reference[0];
                                target_obj = vlink.connection_obj_reference[1];

                            } else {
                                target_obj = vlink.connection_obj_reference[0];
                                source_obj = vlink.connection_obj_reference[1];
                            }

                            var source_cp = '';
                            var target_cp = '';
                            if (vlink.connection_points_reference[0] == cp.id) {
                                source_cp = vlink.connection_points_reference[0];
                                target_cp = vlink.connection_points_reference[1];
                            } else {
                                target_cp = vlink.connection_points_reference[0];
                                source_cp = vlink.connection_points_reference[1];
                            }

                            //console.log(target_obj,target_cp);

                            _addEndpoints(target_obj, [], connection_points_map['net'][number_of_vdus][key_vdu * 4 + key_cp], target_cp);

                            instance.connect({
                                uuids: [source_obj + source_cp, target_obj + target_cp],
                                label: vlink.id,
                                overlays: [
                                        //[ "Arrow", { location:0.5 } ],
                                        [ "Label", {
                                                location:0.9,
                                                labelStyle:{color:"red"}
                                        }]
                                ]
                            })
                        }


                    });

                });

            });




            // add networks connection points
            //$.each(vnfd.networks, function (key_net, net) {
            //    $.each(net.connection_points, function (key_cp, cp) {
            //        _addEndpoints(net.id, connection_points_map['net'][number_of_vdus][key_cp], [], cp.id);
            //        //console.log(net.id);
            //    });
            //});


        //var number_of_vdus = $("#canvas").find(".vdus").children().length;

        //for (var n = 0; n < 4; n++) {
        //    _addEndpoints("vdu" + n, connection_points_map['vdu'][0], []);
        //    _addEndpoints("vdu" + n, connection_points_map['vdu'][1], []);
        //    _addEndpoints("vdu" + n, connection_points_map['vdu'][2], []);
        //    _addEndpoints("vdu" + n, connection_points_map['vdu'][3], []);
        //}

        //_addEndpoints("vdu0", [[0.2, 1, 0, 1, 0]], [], 'cp1');
        //_addEndpoints("vdu0", [[0.4, 1, 0, 1, 0]], [], 'cp2');
        //_addEndpoints("vdu0", [[0.6, 1, 0, 1, 0]], [], 'cp3');
        //_addEndpoints("vdu0", [[0.8, 1, 0, 1, 0]], [], 'cp4');

        //
        //_addEndpoints("vdu1", [[0.2, 1, 0, 1, 0]], []);
        //_addEndpoints("vdu1", [[0.4, 1, 0, 1, 0]], []);
        //_addEndpoints("vdu1", [[0.6, 1, 0, 1, 0]], []);
        //_addEndpoints("vdu1", [[0.8, 1, 0, 1, 0]], []);
        //
        //_addEndpoints("vdu2", [[0.2, 1, 0, 1, 0]], []);
        //_addEndpoints("vdu2", [[0.4, 1, 0, 1, 0]], []);
        //_addEndpoints("vdu2", [[0.6, 1, 0, 1, 0]], []);
        //_addEndpoints("vdu2", [[0.8, 1, 0, 1, 0]], []);
        //
        //_addEndpoints("vdu3", [[0.2, 1, 0, 1, 0]], []);
        //_addEndpoints("vdu3", [[0.4, 1, 0, 1, 0]], []);
        //_addEndpoints("vdu3", [[0.6, 1, 0, 1, 0]], []);
        //_addEndpoints("vdu3", [[0.8, 1, 0, 1, 0]], []);


        //4VDUs

        //_addEndpoints("net0", [],  [[0.048, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.086, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.124, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.162, 0, 2, -1]]);
        //
        //_addEndpoints("net0", [],  [[0.301, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.339, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.377, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.415, 0, 2, -1]]);
        //
        //_addEndpoints("net0", [],  [[0.552, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.590, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.628, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.666, 0, 2, -1]]);
        //
        //_addEndpoints("net0", [],  [[0.804, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.842, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.880, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.918, 0, 2, -1]]);


        //3VDUS

        //_addEndpoints("net0", [],  [[0.067, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.124, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.180, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.237, 0, 2, -1]]);
        //
        //_addEndpoints("net0", [],  [[0.412, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.470, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.526, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.583, 0, 2, -1]]);
        //
        //_addEndpoints("net0", [],  [[0.758, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.814, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.871, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.928, 0, 2, -1]]);


        //2VDUS
        //
        //_addEndpoints("net0", [],  [[0.1, 0, 2, -1]], 'cp5');
        //_addEndpoints("net0", [],  [[0.19, 0, 2, -1]], 'cp6');
        //_addEndpoints("net0", [],  [[0.28, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.37, 0, 2, -1]]);
        //
        //_addEndpoints("net0", [],  [[0.613, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.703, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.793, 0, 2, -1]]);
        //_addEndpoints("net0", [],  [[0.883, 0, 2, -1]]);

        //1VDUS

        //_addEndpoints("net0", [], [[0.2045, 0, 2, -1]]);
        //_addEndpoints("net0", [], [[0.3985, 0, 2, -1]]);
        //_addEndpoints("net0", [], [[0.5925, 0, 2, -1]]);
        //_addEndpoints("net0", [], [[0.787, 0, 2, -1]]);

        //instance.connect({
        //  source:"vdu0cp3",
        //  target:"net0cp0",
        //  scope:"someScope"
        //}).toggleType("basic");

    });

    //instance.connect({
    //  source:"vdu0cp3",
    //  target:"net0cp0",
    //  scope:"someScope"
    //}).toggleType("basic");
    //
    //instance.connect({
    //  source:"vdu1",
    //  target:"vdu2",
    //  scope:"someScope"
    //}).toggleType("basic");
    //
    //instance.connect({
    //  source:"vdu1",
    //  target:"vdu3",
    //  scope:"someScope"
    //}).toggleType("basic");
    //
    //instance.connect({
    //  source:"vdu3",
    //  target:"net0",
    //  scope:"someScope"
    //}).toggleType("basic");




    });



});