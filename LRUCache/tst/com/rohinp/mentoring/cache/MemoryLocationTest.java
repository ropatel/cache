package com.rohinp.mentoring.cache;

import org.junit.Before;
import org.junit.Test;

public class MemoryLocationTest {
	
	private MemoryLocation memLocs;
	private int startIndex;

	@Before
	public void setUp() {
		memLocs = new MemoryLocation(4);
		startIndex = 30;
	}
	
	@Test
	public void testPut() {
		
		memLocs.put(startIndex);
		org.junit.Assert.assertEquals(1, memLocs.getBlockCount());
		org.junit.Assert.assertEquals(30, memLocs.get());		

	}

	@Test
	public void testMemoryIndexExists() {
		org.junit.Assert.assertFalse(memLocs.memoryIndexExists());
		memLocs.put(startIndex);
		org.junit.Assert.assertTrue(memLocs.memoryIndexExists());
	}

	@Test
	public void testGetTerminator() {
		memLocs.put(startIndex);
		memLocs.setTerminator(2);
		org.junit.Assert.assertEquals(2, memLocs.getTerminator());
	}
	
	@Test
	public void testGetSize() {
		memLocs.put(startIndex);
		memLocs.put(startIndex + 1);
		org.junit.Assert.assertEquals(8, memLocs.getSize());
		memLocs.setTerminator(2);
		org.junit.Assert.assertEquals(6, memLocs.getSize());
	}

}
