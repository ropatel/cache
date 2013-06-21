package com.rohinp.mentoring.cache;

import java.util.Iterator;

import org.apache.commons.lang3.Validate;

public class ByteArray implements Iterator<Byte>{
	
	private final byte[] value_;
	private int index_;
	
	public ByteArray(byte[] value) {
		value_ = value;
		index_ = 0;
	}

	@Override
	public boolean hasNext() {
		return index_ <= (value_.length - 1);
	}

	@Override
	public Byte next() throws IndexOutOfBoundsException {
		Validate.isTrue(index_ < value_.length,"Exceeded number of bytes in array.");
		return value_[index_++];
	}

	@Override
	public void remove() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

}
