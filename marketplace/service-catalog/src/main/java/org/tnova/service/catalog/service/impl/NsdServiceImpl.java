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

package org.tnova.service.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.tnova.service.catalog.domain.nsd.NetworkService;
import org.tnova.service.catalog.domain.nsd.Nsd;
import org.tnova.service.catalog.exceptions.*;
import org.tnova.service.catalog.repository.NetworkServiceRepository;
import org.tnova.service.catalog.service.NsdService;
import org.tnova.service.catalog.service.OrchestratorService;
import org.tnova.service.catalog.service.SlaService;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class NsdServiceImpl implements NsdService
{
    private static final Logger logger = LoggerFactory.getLogger( NsdServiceImpl.class );

    private NetworkServiceRepository networkServiceRepository;

    @Autowired
    private OrchestratorService orchestratorService;

    @Autowired
    SlaService slaService;

    @Autowired
    public NsdServiceImpl( NetworkServiceRepository networkServiceRepository )
    {
        this.networkServiceRepository = networkServiceRepository;
    }


    @Override
    public NetworkService getServiceByName( String name )
    {
        logger.info( "Retrieve network service by name {}", name );

        Nsd nsd = networkServiceRepository.findByName( name )
            .orElseThrow( () -> new NetworkServiceNotFoundException( name ) );

        return new NetworkService( nsd );
    }

    @Override
    public NetworkService getServiceById( String id )
    {
        logger.info( "Find a network service by Id=" + id );

        Nsd nsd = networkServiceRepository.findOne( id );

        if( nsd == null )
        {
            throw new NetworkServiceNotFoundException( id );
        }

        return new NetworkService( nsd );
    }

    @Override
    public List<NetworkService> getServices()
    {
        logger.info( "Retrieving list of services " );
        List<Nsd> nsds = networkServiceRepository.findAll();

        if( nsds == null || nsds.isEmpty() )
            throw new NetworkServiceEmptyListException(
                String.format( "No network services are available in catalog" ) );

        logger.info( "{} network services found in repository", nsds.size() );
        List<NetworkService> networkServices = new ArrayList<>();
        for( Nsd nsd : nsds )
        {
            NetworkService networkService = new NetworkService( nsd );
            networkServices.add( networkService );
        }

        return networkServices;

    }

    @Override
    public NetworkService save( NetworkService networkService )
    {
        logger.info( "Creating a network service {}", networkService );
        boolean operationSuccess = true;
        boolean success = false;

        if( networkServiceRepository.findByName( networkService.getNsd().getName() ).isPresent() )
        {
            throw new NetworkServiceAlreadyExistsException(
                String.format( "There is already a network service with name=%s", networkService.getNsd().getName() ) );
        }

        Nsd nsd = networkServiceRepository.save( networkService.getNsd() );

        if ( nsd.getId() != null )
        {
            logger.info( "Publishing network service"
                + " to orchestrator.... !!! " );
            operationSuccess = orchestratorService.createNsdToOrchestrator( new NetworkService( nsd ) );

            if ( !operationSuccess )
            {
                logger.error( "An Error occured during publishing network service with id = {} to orchestraror.",
                     nsd.getId() );
                throw new NetworkServiceOrchestratorException( "An error occurred during publishing the NSD to orchestrator. " );

            }
            else
            {
                try {
                    success = slaService.populateSlaTemplate( nsd );

                } catch( Exception ex )
                {
                    logger.error( "An exception occured during sla template publishing !!!!!" );
                    logger.error( ex.getMessage() );
                }
                if ( !success )
                {
                    logger.error( "An error occured during publishing the generated sla template "
                            + "to sla module for network service with id = ", nsd.getId() );
                    operationSuccess = false;
                }
            }
        }

        if ( !operationSuccess )
        {
            delete( nsd.getId() );
            return null;
        }


        return new NetworkService( nsd );
    }

    @Override
    public void delete( String id )
    {
        logger.info( "Delete a network service with Id=" + id );

         Nsd nsd = networkServiceRepository.findOne( id );

        if( nsd == null )
        {
            throw new NetworkServiceNotFoundException( id );
        }

        boolean result = orchestratorService.deleteNsdToOrchestrator( new NetworkService( nsd ) );

        if ( result )
        {
            logger.info( "Deleting service from the Business service catalog" );
            networkServiceRepository.delete( nsd.getId() );
        }
        else
        {
            throw new NetworkServiceOrchestratorException( "An error occured during deletion of Network Service from Orchestrator. "
                + "Check the logs. Network Service will not be deleted from BSc." );
        }
    }

    @Override
    public void deleteAllServices()
    {
        logger.info( "Delete all network services from catalog " );

        networkServiceRepository.deleteAll();
    }

    @Override
    public NetworkService update( String id, NetworkService networkService )
    {
        logger.debug( "Updating Network Service in BSc Catalog" );
        Nsd nsd = networkServiceRepository.save( networkService.getNsd() );

        if ( nsd != null )
        {
            logger.info( "Successful update of NetworkService with id={}", nsd.getId() );
            logger.info( "Updating Network service to Orchestrator" );
            boolean success = orchestratorService.modifyNsdToOrchestrator( networkService );

            if ( success )
            {
                return new NetworkService( nsd );
            }
            else
                return null;
        }
        else
        {
            logger.error( "An error occured during the update of network service with id={}", id );

            return null;
        }
    }

    @Override
    public List<NetworkService> getServicesByVendor( String vendor )
    {
        logger.info( "Retrieve all services based on vendor=", vendor );

        List<Nsd> nsds = networkServiceRepository.findByVendor( vendor );

        if( nsds.isEmpty() )
            throw new NetworkServiceEmptyListException(
                String.format( "No network services are available in catalog" ) );

        logger.info( "{} network service found for vendor name = {}", nsds.size(), vendor );


        List<NetworkService> networkServices = new ArrayList<>();
        for( Nsd nsd : nsds )
        {
            NetworkService networkService = new NetworkService( nsd );
            networkServices.add( networkService );
        }

        return networkServices;
    }

    @Override
    public List<NetworkService> getServiceBySetupCost( String price )
    {
//        logger.info( "Retrieve all network services based on setup_cost=", price );
//
//        List<NetworkService> networkServices = networkServiceRepository.findByServiceDeploymentFlavoursBillingPriceSetupCost(
//            price );
//        if ( networkServices.isEmpty() )
//        {
//            throw new NetworkServiceEmptyListException(
//            String.format( "No network services are available in the catalog") );
//        }

        return null;
    }

    @Override
    public List<NetworkService> getByBillingPriceSetupCostLessThan( String maxPrice )
    {
        logger.info( "Retrieve all services where price is greater than {}", maxPrice );
       /* List<NetworkService> networkServices = networkServiceRepository.findByServiceDeploymentFlavoursBillingPriceSetupCostLessThan(
      *//*/*      maxPrice );

        if ( networkServices.isEmpty() )
        {
            throw new NetworkServiceEmptyListException( "No network services are available in the catalog" );
        }*/

        return null;
    }

    @Override
    public List<NetworkService> getByBillingPriceSetupCostBetween( String from, String to )
    {
        logger.info( "Retrieve all services where price is from {} to {}", from, to );
        /*List<NetworkService> networkServices = networkServiceRepository.findByServiceDeploymentFlavoursBillingPriceSetupCostBetween(
            from, to );

        if ( networkServices.isEmpty() )
        {
            throw new NetworkServiceEmptyListException( "No network services are available in the catalog" );
        }*/

        return null;
    }
}
