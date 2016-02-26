package org.tnova.service.selection.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tnova.service.selection.domain.instantiate.NetworkServiceInstantiateReply;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceSelectionMsgTable implements MsgTable<Integer, NetworkServiceInstantiateReply>
{

    private final static Logger logger = LoggerFactory.getLogger( ServiceSelectionMsgTable.class );
    private static ServiceSelectionMsgTable instance = null;
    private static ConcurrentHashMap<Integer, NetworkServiceInstantiateReply> orcheReplyMsgMap = null;
    private Thread acknoledgeThread= null;

    private ServiceSelectionMsgTable()
    {
        logger.info( "Create an instance of Selection Request Info Table" );

        orcheReplyMsgMap = new ConcurrentHashMap<>();
    }

    public static synchronized ServiceSelectionMsgTable getInstance()
    {
        if( instance == null )
        {
            instance = new ServiceSelectionMsgTable();
        }

        return instance;
    }

    @Override
    public void add( Integer key, NetworkServiceInstantiateReply message )
    {
        orcheReplyMsgMap.put( key, message );
    }

    @Override
    public NetworkServiceInstantiateReply get( Integer key )
    {
        NetworkServiceInstantiateReply message = null;
        if( key != null )
        {
            message = orcheReplyMsgMap.get( key );

            return message;
        }

        return message;
    }

    @Override
    public NetworkServiceInstantiateReply remove( Integer key )
    {
        return orcheReplyMsgMap.remove( key );
    }


    @Override
    public boolean containsKey( Integer msgId )
    {
        return orcheReplyMsgMap.containsKey( msgId );
    }

    @Override
    public void removeAll()
    {
        //do nothing
    }

    public Set<Integer> getKeySet()
    {
        return orcheReplyMsgMap.keySet();
    }

}
