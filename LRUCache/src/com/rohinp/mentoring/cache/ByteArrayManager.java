package com.rohinp.mentoring.cache;

import java.util.ArrayDeque;
import java.util.HashMap;

public class ByteArrayManager <K,V> implements StorageManager <String,byte[]>
{
	private byte[] memory_;
	private ArrayDeque <Integer> freeMemory_;
	private HashMap <String,MemoryLocation> lookupTable_;
	
	private static final int DEFAULT_MAX_SIZE = 6000;
	private static final int BLOCK_SIZE = 4;

	
	
	/**
	 * Constructor
	 * Initializes the memory, free memory stack 
	 * and byte array hashmap.
	 *
	 * @throws Missing from cache
	 */	
	public ByteArrayManager()
	{		
		// Create initial memory space
		memory_ = new byte[DEFAULT_MAX_SIZE];
		
		// Create free memory stack
		freeMemory_ = new ArrayDeque <Integer> (DEFAULT_MAX_SIZE / BLOCK_SIZE);
		
		// Create initial memory index
		lookupTable_ = new HashMap <String, MemoryLocation>();
		
		// Load free memory stack
		for (int i = 0; i <= (DEFAULT_MAX_SIZE-BLOCK_SIZE); i += BLOCK_SIZE) {
			freeMemory_.push(i);
		}
	}


	
	/**
	 * Returns the default maximum storage capacity
	 */	
	public int getDefaultMaxCapacity()
	{
		return DEFAULT_MAX_SIZE;
	}

	
	
	/**
	 * {@inheritDoc}
	 */	
	public int getCapacity()
	{
		return (freeMemory_.size() * BLOCK_SIZE);
	}

	
	
	/**
	 * {@inheritDoc}
	 */	
	public boolean isCapacityAvailable(byte[] value)
	{
		if (value.length <= freeMemory_.size() * BLOCK_SIZE) {
			return true;
		} 
		
		return false;
	}
	

	
	/**
	 * {@inheritDoc}
	 */	
	public void save(String key, byte[] value)
	{
		// Number of memory blocks required to store the value
		int memoryBlocks = (int) Math.ceil(value.length / (double) BLOCK_SIZE);
		// Track memory balance
		int storageBalance = value.length;
		// Track index in memory storage
		int memoryIndex;
		// Track index in byte array
		int valueIndex = 0;
		
		// Create memory location object to track the memory locations.
		MemoryLocation memoryLocations = new MemoryLocation(BLOCK_SIZE);
	
		// Store value to memory 
		for (int i = 0; i < memoryBlocks; i++) {
			
			// Get next available memory block
			memoryIndex = freeMemory_.pop();
			
			memoryLocations.put(memoryIndex);
			
			if (storageBalance >= BLOCK_SIZE) {
				for (int j=0; j<BLOCK_SIZE; j++) {
					memory_[memoryIndex] = value[valueIndex];
					memoryIndex++;
					valueIndex++;
				}
			} else {
				memoryLocations.setTerminator(BLOCK_SIZE - storageBalance);
				for (int k = 0; k < storageBalance; k++) {
					memory_[memoryIndex] = value[valueIndex];
					memoryIndex++;
					valueIndex++;
				}				
			}
			storageBalance -= BLOCK_SIZE;
		}
		lookupTable_.put(key, memoryLocations);
	}


	
	/**
	 * {@inheritDoc}
	 */	
	public byte[] read(String key)
	{
		int memoryIndex;
		int dataSize;
		int outputIndex = 0;
		byte memoryByte;
		
		MemoryLocation memoryLocations = lookupTable_.get(key);
		int terminatorIndex = memoryLocations.getTerminator();
		dataSize = memoryLocations.getSize();		
		byte[] outputByteArray = new byte[dataSize];
		
		if (terminatorIndex > 0) {
			for (int i = 0; i < (memoryLocations.getBlockCount()-1); i++) {
				memoryIndex = memoryLocations.get();
				for (int j = 0; j < BLOCK_SIZE; i++) {
					outputByteArray[outputIndex] = memory_[memoryIndex + j];
					outputIndex++;
				}
			}
			memoryIndex = memoryLocations.get();
			for (int i = 0; i < terminatorIndex; i++) {
				outputByteArray[outputIndex] = memory_[memoryIndex + i];
			}			
				outputIndex++;
		} else {
			for (int i = 0; i < memoryLocations.getBlockCount(); i++) {
				memoryIndex = memoryLocations.get();
				for (int j = 0; j < BLOCK_SIZE; j++) {
					memoryByte = memory_[memoryIndex + j];
					outputByteArray[outputIndex] = memoryByte;
					outputIndex++;
				}				
			}			
		}
		return outputByteArray;
	}


	
	/**
	 * {@inheritDoc}
	 */		
	public void delete(String key)
	{
		int memoryIndex;
		
		MemoryLocation memoryLocations = lookupTable_.get(key);
		
		for (int i = 0; i < memoryLocations.getBlockCount(); i++) {
			memoryIndex = memoryLocations.get();
			for (int j = 0; j < BLOCK_SIZE; j++) {
				memory_[memoryIndex + j] = 0;
			}
			freeMemory_.push(memoryIndex);
		}
		lookupTable_.remove(key);
	}	
	
	
}
