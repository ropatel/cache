package com.rohinp.mentoring.cache;

public class MemoryManagerConfig {
	
	private int maxMemory_ = 0;
	private int blockSize_ = 0;
	
	// This value was arbitrarily chosen.  This value should likely be 
	// computed based upon the host specifications.
	private static final int DEFAULT_MAX_SIZE = 6000;
	
	// This value was chosen <need an explanation here>
	private static final int BLOCK_SIZE = 4;
	
	public MemoryManagerConfig()
	{
		this(DEFAULT_MAX_SIZE,BLOCK_SIZE);
	}
	
	public MemoryManagerConfig(final int maxMemory,final int blockSize) throws IllegalArgumentException
	{
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
