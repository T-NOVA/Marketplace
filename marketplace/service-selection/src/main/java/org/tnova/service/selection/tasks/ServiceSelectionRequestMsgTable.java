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

package org.tnova.service.selection.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tnova.service.selection.domain.NetworkServiceActivationRequest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ServiceSelectionRequestMsgTable implements MsgTable<Integer, NetworkServiceActivationRequest>
{
    private final static Logger logger = LoggerFactory.getLogger( ServiceSelectionRequestMsgTable.class );

    private static ServiceSelectionRequestMsgTable instance = null;
    private static ConcurrentMap<Integer, NetworkServiceActivationRequest> requestMsgMap = null;

    private ServiceSelectionRequestMsgTable()
    {
        requestMsgMap = new ConcurrentHashMap<>();
    }

    public static synchronized ServiceSelectionRequestMsgTable getInstance()
    {
        if( instance == null )
        {
            instance = new ServiceSelectionRequestMsgTable();
        }

        return instance;
    }

    @Override
    public void add( Integer key, NetworkServiceActivationRequest value )
    {
        requestMsgMap.put( key, value );
    }

    @Override
    public NetworkServiceActivationRequest get( Integer key )
    {
        NetworkServiceActivationRequest request;

        request = requestMsgMap.get( key );

        return request;
    }

    @Override
    public NetworkServiceActivationRequest remove( Integer key )
    {
        return requestMsgMap.remove( key );
    }

    @Override
    public void removeAll()
    {
        //        requestMsgMap.containsKey(  )

    }

    @Override
    public boolean containsKey( Integer key )
    {
        return requestMsgMap.containsKey( key );
    }
}
