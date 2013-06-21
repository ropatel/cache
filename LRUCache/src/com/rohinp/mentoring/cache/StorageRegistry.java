package com.rohinp.mentoring.cache;

import java.util.HashMap;
import java.util.Map;

public class StorageRegistry {
	
	private final String registryKey_;
	private final Map <String,MemoryLocation> registry_;
	
	
	/**
	 * Constructor 
	 * Initializes a StorageRegistry object using
	 * the provided key.
	 * 
	 * @param String key This is the key used to map to the stored byte array. 
	 */		
	public StorageRegistry(String key)
	{
		registryKey_ = key;
		
		// Create initial memory index
		registry_ = new HashMap <String, MemoryLocation>();		
	}
	
	/**
	 * Saves a MemoryLocation object in the
	 * storage registry object.  Effectively associating 
	 * the MemoryLocation with the key.
	 * 
	 * @param MemoryLocation location
	 */		
	public void save(MemoryLocation location)
	{
		registry_.put(registryKey_, location);
	}
	
	/**
	 * Returns a MemoryLocation object using the
	 * provided key.
	 * 
	 * @return int Maximum storage memory.
	 */		
	public MemoryLocation fetch(String key)
	{
		return registry_.get(key);
	}

	/**
	 * Deletes the MemoryLocation object in the
	 * storage registry object.  Effectively disassociating
	 * the MemoryLocation with the key.
	 * @return int Maximum storage memory.
	 */	
	public void delete(String key)
	{
		registry_.remove(key);
	}
	
}
