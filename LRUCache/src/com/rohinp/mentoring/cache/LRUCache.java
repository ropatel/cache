package com.rohinp.mentoring.cache;

import java.util.LinkedList;

public class LRUCache<K> implements CacheManager<String>
{
	
	private LinkedList <String> cacheIndex;

	
	
	/**
	 * Constructor - initializes the cache index. 
	 *
	 * @throws Missing from cache
	 */		
	public LRUCache()
	{
		cacheIndex = new LinkedList <String>();
	}


	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */	
	public void put(String key)
	{				
		cacheIndex.push(key);
	}


	/**
	 * {@inheritDoc}
	 */		
	public void evict()
	{
		cacheIndex.removeLast();
	}
	
	

}
