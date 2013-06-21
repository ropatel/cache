package com.rohinp.mentoring.cache;

import java.util.ArrayList;

public class LRUDriver {
	
	private final LRUCache lru = new LRUCache();
	private final ByteArrayManager memoryManager = new ByteArrayManager();
	
	public LRUDriver() {
		
	}

	
	/**
	 * Puts the provided byte array into
	 * the cache using the provided key. 
	 * 
	 * @param String key Key for the byte array to be stored.
	 * @param byte[] value The byte array to be stored.
	 */
	public boolean put(String key ,byte[] value) {
		return false;
	}

	/**
	 * Returns the byte array corresponding to
	 * the provided key. 
	 * 
	 * @param String key Key for the byte array to be returned.
	 * @return byte[] value The byte array to be returned.
	 */	
	public byte[] get(String key) {
		return null;
	}

	/**
	 * Deletes the byte array from the cache using
	 * the provided key. 
	 * 
	 * @param String key Key for the byte array to be deleted.
	 */	
	public void delete(String key) {
		
	}
	
	/**
	 * Returns a list of keys which map to byte
	 * arrays stored in the cache.
	 * 
	 * @return ArrayList<String> List of keys which map to byte arrays in the cache.
	 */
	public ArrayList<String> list() {
		return null;
	}

}
