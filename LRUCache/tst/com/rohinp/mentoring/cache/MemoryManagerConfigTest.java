package com.rohinp.mentoring.cache;

import org.junit.Before;
import org.junit.Test;

public class MemoryManagerConfigTest {
	
	private MemoryManagerConfig defaultConfig_;
	private MemoryManagerConfig customConfig_;
	
	private static final int DEFAULT_MAX_MEMORY_STORAGE_SIZE = MemoryManagerConfig.DEFAULT_MAX_MEMORY_STORAGE_SIZE;
	private static final int DEFAULT_BLOCK_SIZE = MemoryManagerConfig.DEFAULT_BLOCK_SIZE;
	
	private static final int CUSTOM_MAX_MEMORY_STORAGE_SIZE = 9000;
	private static final int CUSTOM_BLOCK_SIZE = 10;
	
	private static final int BAD_CUSTOM_BLOCK_SIZE = 7; // Bad because it's not an even divisor of the CUSTOM_MAX_MEMORY_STORAGE_SIZE
	
	@Before
	public void setUp()
	{		
		defaultConfig_ = new MemoryManagerConfig();
		customConfig_ = new MemoryManagerConfig(CUSTOM_MAX_MEMORY_STORAGE_SIZE,CUSTOM_BLOCK_SIZE);
	}

	
	@Test (expected=IllegalArgumentException.class)
	public void testIllegalArgumentException() {
		@SuppressWarnings("unused")
		MemoryManagerConfig customConfig = new MemoryManagerConfig(CUSTOM_MAX_MEMORY_STORAGE_SIZE,BAD_CUSTOM_BLOCK_SIZE);
	}
	
	
	@Test
	public void testGetMaxMemoryDefault() {
		org.junit.Assert.assertEquals("failure - expected max memory of:" + DEFAULT_MAX_MEMORY_STORAGE_SIZE,DEFAULT_MAX_MEMORY_STORAGE_SIZE, defaultConfig_.getMaxMemory());
	}

	
	@Test
	public void testGetBlockSizeDefault() {		
		org.junit.Assert.assertEquals("failure - expected block size of:" + DEFAULT_BLOCK_SIZE,DEFAULT_BLOCK_SIZE, defaultConfig_.getBlockSize());		
	}
	
	
	@Test
	public void testGetMaxMemoryCustom() {
		org.junit.Assert.assertEquals("failure - expected max memory of:" + CUSTOM_MAX_MEMORY_STORAGE_SIZE,CUSTOM_MAX_MEMORY_STORAGE_SIZE, customConfig_.getMaxMemory());
	}

	
	@Test
	public void testGetBlockSizeCustom() {
		org.junit.Assert.assertEquals("failure - expected block size of:" + CUSTOM_BLOCK_SIZE,CUSTOM_BLOCK_SIZE, customConfig_.getBlockSize());		
	}

}
