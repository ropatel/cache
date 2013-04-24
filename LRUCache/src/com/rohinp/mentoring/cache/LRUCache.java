package com.rohinp.mentoring.cache;

import java.util.LinkedList;

public class LRUCache<K> implements CacheManager<String>
{
	
	private LinkedList <String> cacheIndex_;

	
	
	/**
	 * Constructor - initializes the cache index. 
	 *
	 * @throws Missing from cache
	 */		
	public LRUCache()
	{
		cacheIndex_ = new LinkedList <String>();
	}


	/**
	 * {@inheritDoc}
	 */	
	public void get(String key)
	{
		int targetIndex = cacheIndex_.indexOf(key);
		
		if (targetIndex < 0) {
			//TODO - Throw exception - item missing from cache
		} else {
			cacheIndex_.remove(targetIndex);
			cacheIndex_.push(key);
		}
	}


	/**
	 * {@inheritDoc}
	 */	
	public void put(String key)
	{				
		cacheIndex_.push(key);
	}


	/**
	 * {@inheritDoc}
	 */		
	public void evict()
	{
		cacheIndex_.removeLast();
	}
	
	

}
