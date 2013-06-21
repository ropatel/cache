package com.rohinp.mentoring.cache;

public class ByteArrayManagerComputer {
	
	private final MemoryManagerConfig config_;
	
	
	/**
	 * Constructor 
	 * Initializes a ByteArrayManagerComputer using
	 * a MemoryManagerConfig object.
	 * 
	 * @param config  MemoryManagerConfig
	 */	
	public ByteArrayManagerComputer(MemoryManagerConfig config) {
		config_ = config;
	}
	
	/**
	 * Returns total number of memory blocks for the
	 * provided MemoryManagerConfig
	 *
	 * @return int Total number of memory blocks for memory store. 
	 */		
	public int getTotalBlocks()
	{
		return config_.getMaxMemory() / config_.getBlockSize();
	}

	/**
	 * Returns the number of memory blocks
	 * required to store a byte array of the
	 * provided size
	 * 
	 * @param int size Size of byte array to be stored.
	 * @return int Total number of memory blocks to store the byte array. 
	 */			
	public int getStorageBlockSize(int size)
	{
		return (int) Math.ceil(size / (double) config_.getBlockSize());
	}
	
	

}
