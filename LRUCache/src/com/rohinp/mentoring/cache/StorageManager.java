package com.rohinp.mentoring.cache;

public interface StorageManager <K,V>
{
	
	/**
	 * Returns current storage capacity.
	 */		
	public int getCapacity();
	
	/**
	 * Returns whether there is enough capacity
	 * for storing the provided entry.
	 *
	 * @param key  Key for fetched entry
	 * @return boolean - available storage capacity 
	 * @throws Missing from cache
	 */		
	public boolean isCapacityAvailable(V value);
	
	/**
	 * Saves an entry in storage.
	 *
	 * @param key  Key for entry to be stored
	 * @param value Value for entry to be stored 
	 * @throws Missing from cache
	 */			
	public void save(K key, V value);
	
	/**
	 * Returns an entry when provided the corresponding
	 * key.
	 *
	 * @param key  Key for entry
	 * @return entry to be retrieved 
	 * @throws Missing from cache
	 */			
	public V read(K key);
	
	/**
	 * Deletes an entry from storage when provided the
	 * corresponding key.
	 *
	 * @param key  Key for entry 
	 * @throws Missing from cache
	 */			
	public void delete(K key);			
}
