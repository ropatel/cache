package com.rohinp.mentoring.cache;

import java.util.HashMap;
import java.util.Map;

public class StorageRegistry {
	
	private final String registryKey_;
	private final Map <String,MemoryLocation> registry_;
	
	public StorageRegistry(String key)
	{
		registryKey_ = key;
		
		// Create initial memory index
		registry_ = new HashMap <String, MemoryLocation>();		
	}

	
	
}
