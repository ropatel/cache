package com.rohinp.mentoring.cache;

import org.apache.commons.lang3.Validate;

public class MemoryManagerConfig {
	
	private final int maxMemory_;
	private final int blockSize_;
	
	// This value was arbitrarily chosen.  This value should likely be 
	// computed based upon the host specifications.
	public static final int DEFAULT_MAX_MEMORY_STORAGE_SIZE = 6000;
	
	// This value was chosen as a preliminary block size for storing byte arrays.
	// The value should be adjusted based upon the usage metrics.
	public static final int DEFAULT_BLOCK_SIZE = 4;
	
	/**
	 * Default Constructor 
	 * Initializes a MemoryManagerConfig object using
	 * a default maximum storage memory size and memory block size.
	 */		
	public MemoryManagerConfig()
	{
		this(DEFAULT_MAX_MEMORY_STORAGE_SIZE,DEFAULT_BLOCK_SIZE);
	}

	
	/**
	 * Constructor 
	 * Initializes a MemoryManagerConfig object using
	 * the provided maximum storage memory size and memory block size.
	 * 
	 * @param int maxMemory  Maximum storage memory size
	 * @param int blockSize Memory block size 
	 */		
	public MemoryManagerConfig(final int maxMemory,final int blockSize) throws IllegalArgumentException
	{
		Validate.isTrue(maxMemory > 0,"Maximum memory size must be greater than 0.");
		Validate.isTrue(blockSize > 0,"Memory block size must be greater than 0.");		
		Validate.isTrue((maxMemory % blockSize == 0),"Maximum memory size must be a multiple of the block size.");

		maxMemory_ = maxMemory;
		blockSize_ = blockSize;
	}

	/**
	 * Returns the maximum storage memory
	 * as defined in the MemoryManagerConfig object.
	 * 
	 * @return int Maximum storage memory.
	 */		
	public int getMaxMemory()
	{
		return maxMemory_;
	}

	/**
	 * Returns the storage memory block size.
	 * 
	 * @return int Memory block size
	 */			
	public int getBlockSize()
	{
		return blockSize_;
	}	

}
