package com.rohinp.mentoring.cache;

import org.junit.Before;
import org.junit.Test;


public class ByteArrayManagerTest {
	
	private ByteArrayManager <String,byte[]>  byteManager;
	
	@Before
	public void setUp() {		
		this.byteManager = new ByteArrayManager <String,byte[]> ();
	}

	@Test
	public void testGetCapacity() {

		int capacity = byteManager.getCapacity();
		int expected = 6000;
		org.junit.Assert.assertEquals("failure - incorrect capacity",expected, capacity);
	}

	@Test
	public void testIsCapacityAvailable() {
		
		byte[] testEntry = {0,1,2,3};
		org.junit.Assert.assertTrue("failure - should return true",byteManager.isCapacityAvailable(testEntry));
	}

	@Test
	public void testSave() {
		
		String key = "foo";
		byte[] sizeFourEntry = {0,1,2,3};
		byteManager.save(key, sizeFourEntry);
		
		int expected = 5996;
		int capacity = byteManager.getCapacity();
		
		org.junit.Assert.assertEquals("failure - incorrect post-save capacity", expected,capacity);
		
		key = "bar";
		byte[] sizeEightEntry = {0,1,2,3,4,5,6,7};
		byteManager.save(key, sizeEightEntry);
		
		expected = 5988;
		capacity = byteManager.getCapacity();
		
		org.junit.Assert.assertEquals("failure - incorrect post-save capacity", expected,capacity);		
		
		key = "baz";
		byte[] sizeTenEntry = {0,1,2,3,4,5,6,7,8,9};
		byteManager.save(key, sizeTenEntry);
		
		expected = 5976;
		capacity = byteManager.getCapacity();
		
		org.junit.Assert.assertEquals("failure - incorrect post-save capacity", expected,capacity);
		
	}

	@Test
	public void testRead() {
		
		org.junit.Assert.assertEquals("failure - incorrect post-save capacity", 6000,byteManager.getCapacity());
		
		String key = "foo";
		byte[] sizeFourEntry = {0,1,2,3};
		byteManager.save(key, sizeFourEntry);
		
		org.junit.Assert.assertEquals("failure - incorrect post-save capacity", 5996,byteManager.getCapacity());
		
		byte[] output = byteManager.read(key);
		
		org.junit.Assert.assertArrayEquals("failure - incorrect post-save capacity", sizeFourEntry,output);
		
	}

	@Test
	public void testDelete() {

		org.junit.Assert.assertEquals("failure - incorrect post-save capacity", 6000,byteManager.getCapacity());
		
		String key = "foo";
		byte[] sizeFourEntry = {0,1,2,3};
		byteManager.save(key, sizeFourEntry);
		
		org.junit.Assert.assertEquals("failure - incorrect post-save capacity", 5996,byteManager.getCapacity());
		
		byteManager.delete(key);
		
		org.junit.Assert.assertEquals("failure - incorrect post-save capacity", 6000,byteManager.getCapacity());
		
	}

}
