/*
 * Copyright 2016  CloudStreet Oy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tnova.service.catalog.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tnova.service.catalog.domain.nsd.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helpers
{
    private static final String NETWORK_SERVICE_NAME = "network-service-";
    private static final String VENDOR = "T-NOVA";
    private static final String NETWORK_SERVICE_VERSION = "1.0";

    public Helpers()
    {
    }

    public static NetworkService createNetworkService( String postfix )
    {
        return new NetworkService( createNsd( postfix ) );
    }

    public static Nsd createNsd( String postfix )
    {

        Nsd nsd = new Nsd();
        nsd.setName( NETWORK_SERVICE_NAME + postfix );
        nsd.setVendor( "3" );
        nsd.setVersion( NETWORK_SERVICE_VERSION );
        nsd.setVnfds( Arrays.asList( "32" ) );
        nsd.setVnffgd( new Vnffgd( Arrays.asList(  ) ) );

        nsd.setLifecycleEvents(
            new LifecycleEvents( Arrays.asList( new Start( "32", "stop", "rel_le0", "le0", "flavor0" ),
                new Start("32", "stop", "rel_le0", "le1", "flavor1" ) ),
                Arrays.asList(), Arrays.asList(  )
                 ) );
        nsd.setMonitoringParameters( Arrays.asList( new MonitoringParameter( "Availability", "availability", "%" ),
            new MonitoringParameter( "CPU Usage", "cpu_usage", "%" ) ) );
        nsd.setVld( createVld() );
        nsd.setSla( createSlaArray() );

        nsd.setAutoScalePolicy( new AutoScalePolicy(  ) );

        nsd.setProvider( "T-NOVA" );
        nsd.setDescription( "Managed Firewall Version ( Test )" );
        nsd.setProviderId( "3" );
        nsd.setDescriptorVersion( "1" );


        return nsd;
    }

    private static AutoScalePolicy createAutoScalePolicy()
    {

        return new AutoScalePolicy();
    }

    private static List<Sla> createSlaArray()
    {
        List<Sla> slas = Arrays.asList(
            new Sla(
                "sla0",
                Arrays.asList( new AssuranceParameter( "MIN(VNF:623.availability)", "availability", "availability",
                    new Penalty( "discount", "%", "P1D", 10L ), "%", "LT(99)",
                    Arrays.asList( new Violation( 2, 360 ) ) ) ),
                new Billing( "PAYG", new Price( 20, 5, "EUR" ) ),
                Arrays.asList( new ConstituentVnfSla( 1, "Active", "gold", "1" ) ),
                "Basic") );

        return slas;
    }

    private static Vld createVld()
    {
        Vld vld = new Vld(
            0,
            Arrays.asList(
                new VirtualLink(
                    "vld0",
                    "management",
                    "Unlimited",
                    "Unlimited",
                    new Qos( "", "", "" ),
                    Arrays.asList( "VNF#32:ext_management" ),
                    "E-LAN",
                    true,
                    true,
                    "sla0"),
                new VirtualLink(
                    "vld1",
                    "management",
                    "Unlimited",
                    "Unlimited",
                    new Qos( "", "", "" ),
                    Arrays.asList( "VNF#32:ext_data_in", "VNF#32:ext_data_out" ),
                    "E-Line",
                    true,
                    false,
                    "sla0"),
                new VirtualLink(
                    "vld2",
                    "management",
                    "Unlimited",
                    "Unlimited",
                    new Qos( "", "", "" ),
                    Arrays.asList( "VNF#32:ext_data_in", "VNF#32:ext_data_out" ),
                    "E-Line",
                    true,
                    false,
                    "sla0")
                ));

        return vld;
    }

    private static Vnffgd createVnffgd()
    {
        return new Vnffgd( );
    }

    private static List<Vnffg> createVnffg()
    {
        List<Vnffg> vnffgs = new ArrayList<>();
        Vnffg vnffg = new Vnffg( "vnffg0", 2, 2, Arrays.asList( "vld1", "vld2", "vld3" ),
            createNetworkForwardingPaths() );
        vnffgs.add( vnffg );

        return vnffgs;
    }

    private static List<NetworkForwardingPath> createNetworkForwardingPaths()
    {
        List<NetworkForwardingPath> paths = new ArrayList<>();
        NetworkForwardingPath path = new NetworkForwardingPath( "nfp1", Arrays.asList( "vld1", "vld2" ),
            Arrays.asList( "data0", "data1" ), Arrays.asList( new ConstituentVnf( "103", "gold" ) ) );

        paths.add( path );

        return paths;
    }
}
