package com.rohinp.mentoring.cache;

import org.junit.Before;
import org.junit.Test;

public class ByteArrayFreeMemoryManagerTest {
	
	private ByteArrayFreeMemoryManager freeMemMgr_;
	
	private static final int MAX_MEMORY_STORGAGE_SIZE = MemoryManagerConfig.DEFAULT_MAX_MEMORY_STORAGE_SIZE;
	private static final int BLOCK_SIZE = MemoryManagerConfig.DEFAULT_BLOCK_SIZE;
	
	@Before
	public void setUp()
	{
		MemoryManagerConfig config = new MemoryManagerConfig();
		freeMemMgr_ = new ByteArrayFreeMemoryManager(config);		
	}

	@Test
	public void testPush() {
		int goodMemoryLocation = MAX_MEMORY_STORGAGE_SIZE - BLOCK_SIZE;
		freeMemMgr_.pop();
		// Mark: This should fail, right? (pushing the same memory location twice)
		freeMemMgr_.push(goodMemoryLocation);
	}	
	
	@Test (expected=OutOfMemoryError.class)
	public void testPushWithFullMemory() {
		// Mark: This should be broken up into two tests (1: bad memory location. 2: full memory).  Otherwise, you
		//       don't really know which error condition you're reaching.
		int badMemoryLocation = MAX_MEMORY_STORGAGE_SIZE + BLOCK_SIZE;
		
		freeMemMgr_.push(badMemoryLocation);
	}

	@Test
	public void testPop() {
		int topMemLocation = MAX_MEMORY_STORGAGE_SIZE - BLOCK_SIZE;
		int memSizePostPop = MAX_MEMORY_STORGAGE_SIZE - BLOCK_SIZE;

		org.junit.Assert.assertEquals(topMemLocation, freeMemMgr_.pop());
		org.junit.Assert.assertEquals(memSizePostPop, freeMemMgr_.getFreeMemory());
	}
	
	@Test (expected=OutOfMemoryError.class)
	public void testPopWithNoMemory() {
		// Mark: Should we be asserting that totalSize() is right as well?
		int totalMemLocations = MAX_MEMORY_STORGAGE_SIZE / BLOCK_SIZE;

		// Mark: just for clarity, should the pop that fails be outside the loop?  How does your test
		//       prove the exception is happening only on the last pop and not any other ones?
		for (int i=0;i <= totalMemLocations; i++) {
			freeMemMgr_.pop();
		}

	}

	@Test
	public void testGetFreeMemory() {
		org.junit.Assert.assertEquals(MAX_MEMORY_STORGAGE_SIZE, freeMemMgr_.getFreeMemory());
	}

	@Test
	public void testIsCapacityAvailable() {
		byte [] value = {127,127,127,0};		
		org.junit.Assert.assertEquals(true, freeMemMgr_.isCapacityAvailable(value.length));
	}

}
