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

package org.tnova.service.catalog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tnova.service.catalog.domain.nsd.NetworkService;
import org.tnova.service.catalog.exceptions.*;
import org.tnova.service.catalog.service.NsdService;
import org.tnova.service.catalog.util.Helpers;

@RestController
@RequestMapping( "/service/catalog" )
public class NetworkServiceRestController
{
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger( NetworkServiceRestController.class );

    private NsdService nsdService;

    @Autowired
    public NetworkServiceRestController( NsdService nsdService )
    {
        this.nsdService = nsdService;
    }

    @RequestMapping( value = "{id}", method = RequestMethod.GET )
    public NetworkService getNetworkServiceById(
        @PathVariable( "id" )
        String id )
    {
        LOG.info( "Request to retrieve a network service based on id={}", id );

        return nsdService.getServiceById( id );
    }

    @RequestMapping( method = RequestMethod.GET )
    public List<NetworkService> getServices()
    {
        LOG.info( "Retrieve all services" );

        List<NetworkService> networkServices = nsdService.getServices();
        LOG.info( "Found {} network services", networkServices.size() );

        return networkServices;
    }

    @RequestMapping( value = "/name={name}", method = RequestMethod.GET )
    public NetworkService getNetworkServiceByName( @PathVariable( "name" ) String name )
    {
        LOG.info( "Request to retrieve a network service based on name = {}", name );

        return nsdService.getServiceByName( name );
    }

    @RequestMapping( value = "/vendor={vendor}", method = RequestMethod.GET )
    public List<NetworkService> getNetworkServicesByVendor( @PathVariable( "vendor" ) String vendor )
    {
        LOG.info( "Request to retrieve a network service based on vendor = {}", vendor );

        return nsdService.getServicesByVendor( vendor );
    }


    @RequestMapping( value = "/price={price}", method = RequestMethod.GET )
    public List<NetworkService> getServicesByPrice(
        @PathVariable( "price" )
        String price )
    {
        LOG.info( "Request to retrieve network services based on setup_cost=", price );
        List<NetworkService> networkServices = nsdService.getServiceBySetupCost( price );

        if( networkServices != null && !networkServices.isEmpty() )
        {
            return networkServices;
        }

        return null;
    }

    @RequestMapping( value = "/price/max={maxPrice}", method = RequestMethod.GET )
    public List<NetworkService> getServicesByMaxPrice( @PathVariable( "maxPrice" ) String maxPrice )
    {
        List<NetworkService> networkServices = nsdService.getByBillingPriceSetupCostLessThan( maxPrice );

        if ( networkServices != null && !networkServices.isEmpty() )
        {
            return networkServices;
        }

        return null;
    }

    @RequestMapping( value = "/price/from={from}&to={to}", method = RequestMethod.GET )
    public List<NetworkService> getServicesByPriceRange(
        @PathVariable( "from" ) String from,
        @PathVariable( "to" ) String to )
    {
        List<NetworkService> networkServices = nsdService.getByBillingPriceSetupCostBetween( from, to );

        return networkServices;
    }


    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    public NetworkService createNetworkService( @RequestBody final NetworkService networkService )
    {
        LOG.info( "Receiving request to create the {} !", networkService  );

        return nsdService.save( networkService );
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    public NetworkService updateNetworkService( @PathVariable("id") String id,
        @RequestBody final NetworkService networkService )
    {
        LOG.info( "Receiving request to update the network service with id {} !", id );

        return nsdService.update( id, networkService );
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseStatus( HttpStatus.OK )
    public void deleteNetworkService( @PathVariable( "id") String id )
    {
        LOG.info( "Deleting network service with id={}", id );

        nsdService.delete( id );
    }

    /* helper methods -- for populates services for testing reasons */
    @RequestMapping( value = "/populate", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public void populateNetworkServices()
    {
        for( int i = 0; i < 1; i++ )
        {
            nsdService.save( Helpers.createNetworkService( Integer.toString( i ) ) );
        }
    }

    @RequestMapping( value = "/populate", method = RequestMethod.DELETE )
    @ResponseStatus( HttpStatus.OK )
    public void deleteAllNetworkServices()
    {
        nsdService.deleteAllServices();
    }

    @ExceptionHandler
    @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
    public String handNetworkServiceOrchestratorException( NetworkServiceOrchestratorException ex )
    {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public String handleNetworkServiceBadRequestException( NetworkServiceBadRequestException ex )
    {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus( HttpStatus.NOT_FOUND)
    public String handleNetworkServiceNotFoundException( NetworkServiceNotFoundException ex )
    {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus( HttpStatus.CONFLICT )
    public String handleNetworkServiceAlreadyExistsException( NetworkServiceAlreadyExistsException ex )
    {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public String handleNetworkServiceEmptyListException( NetworkServiceEmptyListException ex )
    {
        return ex.getMessage();
    }

}
