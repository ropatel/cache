package com.rohinp.mentoring.cache;

import java.util.ArrayDeque;

public class ByteArrayFreeMemoryManager {
	// Mark: Just need documentation to what the integers refer to (more specifically, is the fact that it's an integer relying on the fact
	//       that you're using a byte array memory?  Could this work for something that's not a byte array manager?
	//       Also, should it be a long? and final?
	// Stack - contains the indices within the array
	private final ArrayDeque <Integer> freeMemoryBlocks_;
	private final MemoryManagerConfig config_;
	private final ByteArrayManagerComputer computer_;
	
	
	public ByteArrayFreeMemoryManager(final MemoryManagerConfig config)
	{
		config_ = config;
		
		computer_ = new ByteArrayManagerComputer(config_);
		
		int totalBlocks =  computer_.getTotalBlocks();		
		int blockLocation;
		
		freeMemoryBlocks_ = new ArrayDeque <Integer> ();
		
		// Load free memory stack
		for (int i = 0; i <= totalBlocks - 1; i++) {
			blockLocation = computeBlockLocation(i);
			freeMemoryBlocks_.push(blockLocation);
		}		
	}
	
	public void push(int blockLocation) throws OutOfMemoryError
	{		
		if (isMemoryUnavailble(blockLocation)) {
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
		return freeMemoryBlocks_.size() * config_.getBlockSize();
	}
	
	public boolean isCapacityAvailable(int size)
	{
		int freeMemory = getFreeMemory();
		return (size <= freeMemory);
	}
	
	private boolean isMemoryUnavailble(int blockLocation)
	{
		return (doesMemoryLocationExist(blockLocation) || isMemoryFull());
	}
	
	private boolean doesMemoryLocationExist(int blockLocation)
	{
		return freeMemoryBlocks_.contains(blockLocation);
	}
	
	private boolean isMemoryFull()
	{
		return getFreeMemory() == config_.getMaxMemory();
	}

	private int computeBlockLocation(int blockIndex)
	{
		return blockIndex * config_.getBlockSize();
	}

}
