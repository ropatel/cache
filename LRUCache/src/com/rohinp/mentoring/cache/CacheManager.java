package com.rohinp.mentoring.cache;

public interface CacheManager <K>
{
	
	/**
	 * Executes a get against the cache
	 * The cache re-orders the entry based on the 
	 * item that was fetched.
	 *
	 * @param key  Key for fetched entry
	 * @throws Missing from cache
	 */
	public void get(K key);
	
	/**
	 * Puts an entry into the cache
	 *
	 * @param key  Key for entry stored
	 * @throws Missing from cache
	 */		
	public void put(K key);
	
	/**
	 * Evicts the LRU entry from the cache
	 *
	 * @throws Missing from cache
	 */			
	public void evict ();

	/**
	 * delete the LRU entry from the cache
	 *
	 * @throws Missing from cache
	 */			
	public void delete ();	
	
}
