package com.rohinp.mentoring.cache;

public class MemoryManagerConfig {
	
	// Mark: These should be final.
	private final int maxMemory_;
	private final int blockSize_;
	
	// This value was arbitrarily chosen.  This value should likely be 
	// computed based upon the host specifications.
	private static final int DEFAULT_MAX_MEMORY_STORAGE_SIZE = 6000;
	
	// This value was chosen <need an explanation here>
	private static final int BLOCK_SIZE = 4;
	
	public MemoryManagerConfig()
	{
		this(DEFAULT_MAX_MEMORY_STORAGE_SIZE,BLOCK_SIZE);
	}
	
	public MemoryManagerConfig(final int maxMemory,final int blockSize) throws IllegalArgumentException
	{
		// Mark: Probably worth checking out Apache common's Validate class.
		if (maxMemory % blockSize > 0) {
			throw new IllegalArgumentException("Maximum memory size must be a multiple the block size.");
		} else {
			maxMemory_ = maxMemory;
			blockSize_ = blockSize;
		}
	}
	
	public int getMaxMemory()
	{
		return maxMemory_;
	}
	
	public int getBlockSize()
	{
		return blockSize_;
	}

}
