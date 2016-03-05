package org.tnova.service.selection.service;

import org.springframework.http.HttpStatus;
import org.tnova.service.selection.domain.AccountingRequest;
import org.tnova.service.selection.domain.NetworkServiceActivationReply;
import org.tnova.service.selection.domain.NetworkServiceActivationRequest;
import org.tnova.service.selection.domain.instantiate.NetworkServiceInstantiateReply;
import org.tnova.service.selection.domain.nsd.NetworkService;

import java.util.List;

public interface ServiceSelectionService
{
    NetworkServiceActivationReply activateNetworkService( NetworkServiceActivationRequest request );

    String activateNetworkServiceAsync( NetworkServiceActivationRequest request, int id ) throws Exception;

    boolean publishingToAccounting( NetworkService networkService, NetworkServiceActivationReply reply,
        NetworkServiceActivationRequest request );

    boolean publishingToAccountingAsync( NetworkService networkService,
        NetworkServiceInstantiateReply reply, NetworkServiceActivationRequest request );

    HttpStatus stopNetworkService( String id );

    HttpStatus terminateNetworkService( String id );

    String getNetworkInstanceStatus( String id );

    List<NetworkServiceInstantiateReply> getAllNsInstances();

    List<AccountingRequest> getAccountingRequests();

    List<AccountingRequest> getAccountingRequestByNetworkInstance(String instanceId );

}
