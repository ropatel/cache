package com.rohinp.mentoring.cache;

public class ByteArrayManagerComputer {
	
	private final MemoryManagerConfig config_;
	
	public ByteArrayManagerComputer(MemoryManagerConfig config) {
		config_ = config;
	}
	
	public int getTotalBlocks()
	{
		return config_.getMaxMemory() / config_.getBlockSize();
	}
	
	public int getStorageBlockSize(int size)
	{
		return (int) Math.ceil(size / (double) config_.getBlockSize());
	}
	
	

}
