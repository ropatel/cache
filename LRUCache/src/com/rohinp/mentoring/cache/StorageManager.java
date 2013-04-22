package com.rohinp.mentoring.cache;

public interface StorageManager <K,V>
{
	public int getCapacity();	
	public boolean isCapacityAvailable(V value);
	public void save(K key, V value);
	public V read(K key);
	public void delete(K key);			
}
