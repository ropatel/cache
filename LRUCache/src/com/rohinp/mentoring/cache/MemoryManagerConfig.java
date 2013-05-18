package com.rohinp.mentoring.cache;

import org.apache.commons.lang3.Validate;

public class MemoryManagerConfig {
	
	private final int maxMemory_;
	private final int blockSize_;
	
	// This value was arbitrarily chosen.  This value should likely be 
	// computed based upon the host specifications.
	public static final int DEFAULT_MAX_MEMORY_STORAGE_SIZE = 6000;
	
	// This value was chosen <need an explanation here>
	public static final int DEFAULT_BLOCK_SIZE = 4;
	
	public MemoryManagerConfig()
	{
		this(DEFAULT_MAX_MEMORY_STORAGE_SIZE,DEFAULT_BLOCK_SIZE);
	}
	
	public MemoryManagerConfig(final int maxMemory,final int blockSize) throws IllegalArgumentException
	{
		Validate.isTrue(maxMemory > 0,"Maximum memory size must be greater than 0.");
		Validate.isTrue(blockSize > 0,"Memory block size must be greater than 0.");		
		Validate.isTrue((maxMemory % blockSize == 0),"Maximum memory size must be a multiple of the block size.");

		maxMemory_ = maxMemory;
		blockSize_ = blockSize;
	}
	
	public int getMaxMemory()
	{
		return maxMemory_;
	}
	
	public int getBlockSize()
	{
		return blockSize_;
	}

	public int getTotalBlocks()
	{
		return maxMemory_ / blockSize_;
	}	

}
