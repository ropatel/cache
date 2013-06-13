package com.rohinp.mentoring.cache;

import java.util.LinkedList;

public class MemoryLocation
{
	LinkedList <Integer> memoryIndex;
	private int terminatorIndex;
	private int blockSize;
	
	MemoryLocation(MemoryManagerConfig config)
	{
		memoryIndex = new LinkedList<Integer>();
		terminatorIndex = 0;
		this.blockSize = config.getBlockSize();
	}
	
	
	
	public void put(int value)
	{
		memoryIndex.push(value);
	}
	
	
	
	public int get()
	{
		return memoryIndex.getLast();
	}
	
	
	
	public int getBlockCount()
	{
		return memoryIndex.size();
	}
	
	
	
	public boolean memoryIndexExists()
	{
		return !memoryIndex.isEmpty();
	}
	
	
	
	public void setTerminator(int index)
	{
		terminatorIndex = index;
	}

	
	
	public int getTerminator()
	{
		return terminatorIndex;
	}
	
	
	
	public int getSize()
	{
		if (terminatorIndex > 0) {
			return ((memoryIndex.size()-1) * blockSize) + terminatorIndex;
		}
		return (memoryIndex.size() * blockSize);
	}
	
	
}
