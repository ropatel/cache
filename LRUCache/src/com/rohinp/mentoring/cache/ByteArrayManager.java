package com.rohinp.mentoring.cache;

import java.util.HashMap;
import java.util.Map;

public class ByteArrayManager <K,V> implements StorageManager <String,byte[]>
{
	private byte[] storageMemory_;
	private ByteArrayFreeMemoryManager freeMemMgr_;
	
	// Arguably a bad name.  key->location?
	private final Map <String,MemoryLocation> lookupTable_;
	
	private static final int DEFAULT_MAX_SIZE = 6000;
	private static final int BLOCK_SIZE = 4;

	
	
	/**
	 * Constructor
	 * Initializes the memory, free memory stack 
	 * and byte array hash map.
	 *
	 * @throws Missing from cache
	 */	
	public ByteArrayManager()
	{		
		this(DEFAULT_MAX_SIZE);
	}

	public ByteArrayManager(final int size)
	{
		initMemoryStorage(size);

		// Create initial memory index
		lookupTable_ = new HashMap <String, MemoryLocation>();		
		
		MemoryManagerConfig config = new MemoryManagerConfig();
		
		freeMemMgr_ = new ByteArrayFreeMemoryManager(config);
	}	
	
	private final void initMemoryStorage(final int size)
	{
		// Create initial memory space
		storageMemory_ = new byte[size];				
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
		return freeMemMgr_.getFreeMemory();
	}

	
	
	/**
	 * {@inheritDoc}
	 */	
	public boolean isCapacityAvailable(final byte[] value)
	{
		return freeMemMgr_.isCapacityAvailable(value.length);
	}

	
	/**
	 * {@inheritDoc}
	 */	
	public void save(final String key, final byte[] value)
	{
		// Number of memory blocks required to store the value
		int memoryBlocks = (int) Math.ceil(value.length / (double) BLOCK_SIZE);
		// Track memory balance
		int storageBalance = value.length;
		// Track index in memory storage
		int memoryIndex;
		// Track index in byte array
		int valueIndex = 0;
		
		// iterator, encapsulation???
		
		// Create memory location object to track the memory locations.
		MemoryLocation memoryLocations = new MemoryLocation(BLOCK_SIZE);
	
		// Store value to memory 
		for (int i = 0; i < memoryBlocks; i++) {
			
			// Get next available memory block
			memoryIndex = freeMemMgr_.pop();
			
			memoryLocations.put(memoryIndex);
			
			if (storageBalance >= BLOCK_SIZE) {
				for (int j=0; j<BLOCK_SIZE; j++) {
					storageMemory_[memoryIndex] = value[valueIndex];
					memoryIndex++;
					valueIndex++;
				}
			} else {
				memoryLocations.setTerminator(BLOCK_SIZE - storageBalance);
				for (int k = 0; k < storageBalance; k++) {
					storageMemory_[memoryIndex] = value[valueIndex];
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
	public byte[] read(final String key)
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
					outputByteArray[outputIndex] = storageMemory_[memoryIndex + j];
					outputIndex++;
				}
			}
			memoryIndex = memoryLocations.get();
			for (int i = 0; i < terminatorIndex; i++) {
				outputByteArray[outputIndex] = storageMemory_[memoryIndex + i];
			}			
				outputIndex++;
		} else {
			for (int i = 0; i < memoryLocations.getBlockCount(); i++) {
				memoryIndex = memoryLocations.get();
				for (int j = 0; j < BLOCK_SIZE; j++) {
					memoryByte = storageMemory_[memoryIndex + j];
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
	public void delete(final String key)
	{
		int memoryIndex;
		
		MemoryLocation memoryLocations = lookupTable_.get(key);
		
		for (int i = 0; i < memoryLocations.getBlockCount(); i++) {
			memoryIndex = memoryLocations.get();
			for (int j = 0; j < BLOCK_SIZE; j++) {
				storageMemory_[memoryIndex + j] = 0;
			}
			freeMemMgr_.push(memoryIndex);
		}
		lookupTable_.remove(key);
	}	
	
	
}