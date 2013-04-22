package com.rohinp.mentoring.cache;

import java.util.LinkedList;

public class LRUCache<K> implements CacheManager<String> {
	
	private LinkedList <String> cacheIndex;
	
	public LRUCache() {
		cacheIndex = new LinkedList <String>();
	}

	@Override
	public void get(String key) {
		int targetIndex = cacheIndex.indexOf(key);
		
		if ( targetIndex < 0) {
			//TODO - Throw exception - item missing from cache
		} else {
			cacheIndex.remove(targetIndex);
			cacheIndex.push(key);
		}
	}


	@Override
	public void put(String key) {				
		cacheIndex.push(key);
	}

	@Override
	public void evict(String key) {
		cacheIndex.removeLast();
	}	

}
