package org.tnova.service.selection.tasks;

import org.tnova.service.selection.domain.AccountingRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AccountingRequestMsgTable implements MsgTable<String, List<AccountingRequest>>
{

    private static AccountingRequestMsgTable instance = null;
    private static ConcurrentMap<String, List<AccountingRequest>> accountingReqMap = null;

    private AccountingRequestMsgTable()
    {
        accountingReqMap = new ConcurrentHashMap<>();
    }

    public static synchronized AccountingRequestMsgTable getInstance()
    {
        if( instance == null )
        {
            instance = new AccountingRequestMsgTable();
        }

        return instance;
    }

    @Override
    public void add( String key, List<AccountingRequest> requests )
    {
        accountingReqMap.putIfAbsent( key, requests );
    }

    @Override
    public List<AccountingRequest> get( String key )
    {
        return accountingReqMap.get( key );
    }

    @Override
    public List<AccountingRequest> remove( String key )
    {
        return null;
    }

    @Override
    public void removeAll()
    {

    }

    @Override
    public boolean containsKey( String key )
    {
        return false;
    }
    
    public List<AccountingRequest> getAllAccountingRequests()
    {
        List<AccountingRequest> requests = new ArrayList<>();

        for( List<AccountingRequest> accountingRequests : accountingReqMap.values() )
        {
            requests.addAll( accountingRequests );
        }

        return requests;
    }
}
