package com.rohinp.mentoring.cache;


public class ByteArrayManager <K,V> implements StorageManager <String,byte[]>
{
	private byte[] storageMemory_;
	private ByteArrayFreeMemoryManager freeMemMgr_;
	
	private StorageRegistry registry_;
	
	private final MemoryManagerConfig config_;
	
	private final ByteArrayManagerComputer computer_;	
	
	
	/**
	 * Constructor
	 * Initializes the memory, free memory stack 
	 * and byte array hash map.
	 *
	 * @throws Missing from cache
	 */	
	public ByteArrayManager()
	{	
		
		config_ = new MemoryManagerConfig();
		
		initMemoryStorage(config_.getMaxMemory());
		
		computer_ = new ByteArrayManagerComputer(config_);
		
		freeMemMgr_ = new ByteArrayFreeMemoryManager(config_);
	}	
	
	private final void initMemoryStorage(final int size)
	{
		// Create initial memory space
		storageMemory_ = new byte[size];				
	}	
	
	/**
	 * Returns the default maximum storage capacity
	 * 
	 * @return int Default maximum storage capacity.
	 */	
	public int getDefaultMaxCapacity()
	{
		return config_.getMaxMemory();
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
		// Create a storage registry to track the memory locations
		registry_ = new StorageRegistry(key);
		// Number of memory blocks required to store the value
		int memoryBlocks = computer_.getStorageBlockSize(value.length);
		// Track memory balance
		int storageBalance = value.length;
		// Track index in memory storage
		int memoryIndex;
		// Track index in byte array
		int valueIndex = 0;
		
		// iterator, encapsulation???
		
		// Create memory location object to track the memory locations.
		MemoryLocation memoryLocations = new MemoryLocation(config_);
	
		// Store value to memory 
		for (int i = 0; i < memoryBlocks; i++) {
			
			// Get next available memory block
			memoryIndex = freeMemMgr_.pop();
			
			memoryLocations.put(memoryIndex);
			
			if (storageBalance >= config_.getBlockSize()) {
				for (int j=0; j<config_.getBlockSize(); j++) {
					storageMemory_[memoryIndex] = value[valueIndex];
					memoryIndex++;
					valueIndex++;
				}
			} else {
				memoryLocations.setTerminator(config_.getBlockSize() - storageBalance);
				for (int k = 0; k < storageBalance; k++) {
					storageMemory_[memoryIndex] = value[valueIndex];
					memoryIndex++;
					valueIndex++;
				}				
			}
			storageBalance -= config_.getBlockSize();
		}
		registry_.save(memoryLocations);
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
		
		MemoryLocation memoryLocations = registry_.fetch(key);
		int terminatorIndex = memoryLocations.getTerminator();
		dataSize = memoryLocations.getSize();		
		byte[] outputByteArray = new byte[dataSize];
		
		if (terminatorIndex > 0) {
			for (int i = 0; i < (memoryLocations.getBlockCount()-1); i++) {
				memoryIndex = memoryLocations.get();
				for (int j = 0; j < config_.getBlockSize(); i++) {
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
				for (int j = 0; j < config_.getBlockSize(); j++) {
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
		
		MemoryLocation memoryLocations = registry_.fetch(key);
		
		for (int i = 0; i < memoryLocations.getBlockCount(); i++) {
			memoryIndex = memoryLocations.get();
			for (int j = 0; j < config_.getBlockSize(); j++) {
				storageMemory_[memoryIndex + j] = 0;
			}
			freeMemMgr_.push(memoryIndex);
		}
		registry_.delete(key);
	}	
	
	
}