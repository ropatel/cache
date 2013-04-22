package com.rohinp.mentoring.cache;

public interface CacheManager <K> {
	
	public void get(K key);
	public void put(K key);
	public void evict (K key);

}
