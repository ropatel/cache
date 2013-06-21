package com.rohinp.mentoring.cache;

import java.util.LinkedList;

public class MemoryLocation
{
	LinkedList <Integer> memoryIndex;
	private int terminatorIndex;
	private int blockSize;
	
	
	/**
	 * Constructor 
	 * Initializes a MemoryLocation object using
	 * the provided MemoryManagerConfig object.
	 * 
	 * @param config  MemoryManagerConfig
	 */		
	MemoryLocation(MemoryManagerConfig config)
	{
		memoryIndex = new LinkedList<Integer>();
		terminatorIndex = 0;
		this.blockSize = config.getBlockSize();
	}
	
	
	/**
	 * Puts the index of the memory block into
	 * the memory index. 
	 * 
	 * @param int value Memory block index.
	 */	
	public void put(int value)
	{
		memoryIndex.push(value);
	}
	
	
	/**
	 * Gets the next memory block index from
	 * the memory index. 
	 * 
	 * @return int Memory block index.
	 */		
	public int get()
	{
		return memoryIndex.getLast();
	}
	
	
	/**
	 * Gets the number of memory blocks
	 * stored in the memory index. 
	 * 
	 * @return int Memory block count.
	 */			
	public int getBlockCount()
	{
		return memoryIndex.size();
	}
	
	
	/**
	 * Returns a boolean indicating whether 
	 * there are additional memory blocks available
	 * in the memory index. 
	 * 
	 * @return boolean Memory blocks present?
	 */			
	public boolean memoryIndexExists()
	{
		return !memoryIndex.isEmpty();
	}
	
	
	/**
	 * Sets a memory slot index for a memory block 
	 * which is not completely used. 
	 * 
	 * @param int index This is an index within a partially filled memory block.
	 */		
	public void setTerminator(int index)
	{
		terminatorIndex = index;
	}

	
	/**
	 * Returns a memory slot index for a memory block 
	 * which is not completely used. 
	 * 
	 * @return int This is an index within a partially filled memory block.
	 */	
	public int getTerminator()
	{
		return terminatorIndex;
	}
	
	
	/**
	 * Returns the size of the byte array that is 
	 * being tracked by the MemoryLocation object. 
	 * 
	 * @return int This is size of the byte array being tracked by the MemoryLocation object.
	 */	
	public int getSize()
	{
		if (terminatorIndex > 0) {
			return ((memoryIndex.size()-1) * blockSize) + terminatorIndex;
		}
		return (memoryIndex.size() * blockSize);
	}
	
	
}
