package org.tnova.service.selection.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;

import org.tnova.service.selection.domain.instantiate.NetworkServiceInstantiateReply;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

public class NetworkServiceActivationTask extends TimerTask
{
    private static final Logger logger = LoggerFactory.getLogger( NetworkServiceActivationTask.class );

    private int requestId;
    private AtomicLong concurrentRequests;
    private DeferredResult<NetworkServiceInstantiateReply> deferredResult;


    public NetworkServiceActivationTask(
        int requestId,
        AtomicLong concurrentRequests,
        DeferredResult<NetworkServiceInstantiateReply> deferredResult )
    {
        this.requestId = requestId;
        this.concurrentRequests = concurrentRequests;
        this.deferredResult = deferredResult;
    }

    @Override
    public void run()
    {
        long requests = concurrentRequests.getAndDecrement();

        if ( deferredResult.isSetOrExpired() )
        {
            logger.warn( "{}: Processing of non-blocking request #{} already expired", requests, requestId );
        }
        else
        {
            logger.info( "Retrieving result" );
            NetworkServiceInstantiateReply reply = ServiceSelectionMsgTable.getInstance().get( requestId );
            if( reply != null )
            {
                logger.info( "Retrieving reply from orchestrator with ns id {}", requestId );
            }

            boolean deferredStatus = deferredResult.setResult( reply );
            logger.debug("{}: Processing of non-blocking request #{} done, deferredStatus = {}",
                requests, requestId, deferredStatus);
        }
    }
}
