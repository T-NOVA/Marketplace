package org.tnova.service.selection.service;

import org.tnova.service.selection.domain.nsd.NetworkService;

public interface NetworkCatalogService
{
    NetworkService getNetworkServiceByNsdId( String id );
}
