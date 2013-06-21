package com.rohinp.mentoring.cache;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ByteArrayManagerComputerTest {
	
	private ByteArrayManagerComputer computer_;
	private int maxMemory_;
	private int blockSize_;

	@Before
	public void setUp() throws Exception {
		
		// Config with default settings
		MemoryManagerConfig config = new MemoryManagerConfig();
		
		maxMemory_ = config.getMaxMemory();
		blockSize_ = config.getBlockSize();
		
		computer_ = new ByteArrayManagerComputer(config);
	}

	@Test
	public void testGetTotalBlocks() {
		org.junit.Assert.assertEquals("failure - expected total blocks of:" + (maxMemory_ / blockSize_), maxMemory_ / blockSize_, computer_.getTotalBlocks());
	}

	@Test
	public void testGetStorageBlockSize() {
		fail("Not yet implemented");
	}

}
