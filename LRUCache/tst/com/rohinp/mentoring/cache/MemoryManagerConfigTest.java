package com.rohinp.mentoring.cache;

import org.junit.Before;
import org.junit.Test;

public class MemoryManagerConfigTest {
	
	private MemoryManagerConfig defaultConfig_;
	private MemoryManagerConfig customConfig_;
	
	// Mark: Could the defautl constants be public and then you woulnd'thave to hardcode them twice (less operational work)
	private static final int DEFAULT_MAX_MEMORY = 6000;
	private static final int DEFAULT_BLOCK_SIZE = 4;
	private static final int CUSTOM_MAX_MEMORY = 9000;
	private static final int CUSTOM_BLOCK_SIZE = 10;
	
	@Before
	public void setUp()
	{		
		defaultConfig_ = new MemoryManagerConfig();
		customConfig_ = new MemoryManagerConfig(CUSTOM_MAX_MEMORY,CUSTOM_BLOCK_SIZE);
	}

	
	@Test (expected=IllegalArgumentException.class)
	public void testIllegalArgumentException() {
		@SuppressWarnings("unused")
		// Mark: Magic number.  Either document or make it a named constant
		MemoryManagerConfig customConfig = new MemoryManagerConfig(CUSTOM_MAX_MEMORY,7);
	}
	
	
	@Test
	public void testGetMaxMemoryDefault() {
		org.junit.Assert.assertEquals("failure - expected max memory of:" + DEFAULT_MAX_MEMORY,DEFAULT_MAX_MEMORY, defaultConfig_.getMaxMemory());
	}

	@Test
	public void testGetBlockSizeDefault() {		
		org.junit.Assert.assertEquals("failure - expected block size of:" + DEFAULT_BLOCK_SIZE,DEFAULT_BLOCK_SIZE, defaultConfig_.getBlockSize());		
	}
	
	@Test
	public void testGetMaxMemoryCustom() {
		org.junit.Assert.assertEquals("failure - expected max memory of:" + CUSTOM_MAX_MEMORY,CUSTOM_MAX_MEMORY, customConfig_.getMaxMemory());
	}

	@Test
	public void testGetBlockSizeCustom() {
		org.junit.Assert.assertEquals("failure - expected block size of:" + CUSTOM_BLOCK_SIZE,CUSTOM_BLOCK_SIZE, customConfig_.getBlockSize());		
	}

}
