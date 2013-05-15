package com.rohinp.mentoring.cache;

import java.util.ArrayDeque;

public class FreeMemoryManager {
		
	private ArrayDeque <Integer> freeMemoryBlocks_;
	private final int maxMemory_;
	private final int blockSize_;
	
	
	public FreeMemoryManager(final MemoryManagerConfig config)
	{
		maxMemory_ = config.getMaxMemory();
		blockSize_ = config.getBlockSize();
		
		int totalBlocks = getTotalBlocks();		
		int blockLocation;
		
		freeMemoryBlocks_ = new ArrayDeque <Integer> ();
		
		// Load free memory stack
		for (int i = 0; i <= totalBlocks-1; i++) {
			blockLocation = computeBlockLocation(i);
			freeMemoryBlocks_.push(blockLocation);
		}		
	}
	
	public void push(int blockLocation) throws OutOfMemoryError {
		if (getFreeMemory() == maxMemory_) {
			throw new OutOfMemoryError("Operation failed - memory not available");
		} else {
			freeMemoryBlocks_.push(blockLocation);			
		}
	}
	
	public int pop() throws OutOfMemoryError {
		if (getFreeMemory() == 0) {
			throw new OutOfMemoryError("Operation failed - memory not available");
		} else {
			return freeMemoryBlocks_.pop();			
		}		
	}	
	
	public int getFreeMemory()
	{
		return freeMemoryBlocks_.size() * blockSize_;
	}
	
	public boolean isCapacityAvailable(byte[] value)
	{
		int freeMemory = getFreeMemory();
		
		if (value.length <= freeMemory) {
			return true;
		} 
		return false;		
	}
	
	
	private int getTotalBlocks()
	{
		return maxMemory_ / blockSize_;
	}
	
	private Integer computeBlockLocation(int blockIndex)
	{
		return blockIndex * blockSize_;
	}

}
