package com.rohinp.mentoring.cache;

import java.util.Iterator;

public class ByteArray implements Iterator<Byte>{
	
	private final byte[] value_;
	private int index_;
	private final int size_;
	
	public ByteArray(byte[] value) {
		value_ = value;
		index_ = 0;
		size_ = value.length;
	}

	@Override
	public boolean hasNext() {
		return index_ <= (size_ - 1);
	}

	@Override
	public Byte next() {
		return value_[index_++];
		// TODO Should throw an exception if you attempt to call out of bounds.
	}

	@Override
	public void remove() {
		//TODO Throw an unsupported method.
	}

}
