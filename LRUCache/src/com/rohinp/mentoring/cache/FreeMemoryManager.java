package com.rohinp.mentoring.cache;

import java.util.ArrayDeque;

public class FreeMemoryManager {
	// Mark: Just need documentatin to what the integers refer to (more specifically, is the fact taht it's an integer relying on the fact
	//       that you're using a bytearray memory?  Could this work for something that's not a bytearray manager?
	//       Also, should it be a long? and final?
	private ArrayDeque <Integer> freeMemoryBlocks_;
	
	// Mark: Should you just store a config object?  Why copy the values out?
	private final int maxMemory_;
	private final int blockSize_;
	
	
	public FreeMemoryManager(final MemoryManagerConfig config)
	{
		maxMemory_ = config.getMaxMemory();
		blockSize_ = config.getBlockSize();
		
		// Should this calculation be in the config object?  You're kind of breaking an encapsulation barrier
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
		// Mark: not quite the right check?  You need to make sure that the same location isn't there more than once.
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
	
	// Mark: Why not just take the size as a parameter?
	public boolean isCapacityAvailable(byte[] value)
	{
		int freeMemory = getFreeMemory();
		
		// mark: This is better written as simply: return (value.length <= freeeMemory);
		if (value.length <= freeMemory) {
			return true;
		} 
		return false;		
	}
	
	
	private int getTotalBlocks()
	{
		// divide by 0?
		return maxMemory_ / blockSize_;
	}
	
	// Mark: You're kind of playing fast and lose with the difference between int and Integer.  Why use hte object here but not other places?
	private Integer computeBlockLocation(int blockIndex)
	{
		return blockIndex * blockSize_;
	}

}
