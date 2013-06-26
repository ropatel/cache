package com.rohinp.mentoring.cache;

import java.util.ArrayList;

public interface CacheDriver <K,V> {	
	
	/**
	 * Puts the provided value into
	 * the cache using the provided key. 
	 * 
	 * @param <K> key Key for the <V> value to be stored.
	 * @param <V> value The <V> value to be stored.
	 */
	public boolean put(K key ,V value);

	/**
	 * Returns the value corresponding to
	 * the provided key. 
	 * 
	 * @param <K> key Key for the <V> value to be returned.
	 * @return <V> value The <V> value to be returned.
	 */	
	public byte[] get(K key);

	/**
	 * Deletes the value from the cache using
	 * the provided key. 
	 * 
	 * @param <K> key Key for the <V> value to be deleted.
	 */	
	public void delete(K key);
	
	/**
	 * Returns a list of keys which map to values
	 * stored in the cache.
	 * 
	 * @return ArrayList<K> List of keys which map to <V> values in the cache.
	 */
	public ArrayList<K> list();

}
