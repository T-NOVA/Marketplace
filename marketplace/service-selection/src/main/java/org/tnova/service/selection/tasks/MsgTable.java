package org.tnova.service.selection.tasks;

public interface MsgTable<K, V>
{
    void add( K key, V value );

    V get( K key );

    V remove( K key );

    void removeAll();

    boolean containsKey( K key );
}
