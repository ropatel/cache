package com.rohinp.mentoring.cache;

import java.util.LinkedList;

public class LRUCache<K> implements CacheManager<String>
{
	
	private LinkedList <String> cacheIndex;
	
	public LRUCache()
	{
		cacheIndex = new LinkedList <String>();
	}


	/**
	 * Executes a get against the cache
	 * The cache re-orders the entry based on the 
	 * item that was fetched.
	 *
	 * @param key  Key for fetched entry
	 * @throws Missing from cache
	 */	
	public void get(String key)
	{
		int targetIndex = cacheIndex.indexOf(key);
		
		if (targetIndex < 0) {
			//TODO - Throw exception - item missing from cache
		} else {
			cacheIndex.remove(targetIndex);
			cacheIndex.push(key);
		}
	}


	/**
	 * Puts an entry into the cache
	 *
	 * @param key  Key for entry stored
	 * @throws Missing from cache
	 */	
	public void put(String key)
	{				
		cacheIndex.push(key);
	}


	/**
	 * Evicts the LRU entry from the cache
	 *
	 * @throws Missing from cache
	 */		
	public void evict()
	{
		cacheIndex.removeLast();
	}
	
	

}
