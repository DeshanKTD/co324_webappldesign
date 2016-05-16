//package monitor;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

public class Reading {
	/**
	 * The size required to store a reading.
	 */
	static final int SIZE = Long.SIZE * 2 
			+ Integer.SIZE*2 + Float.SIZE;
	/**
	 * ByteBuffer is a handy type for storing binary data.
	 */
	final ByteBuffer buf;
	
	/**
	 * Constructs a Reading from the given values. 
	 */
	public Reading(long id, long time, int pulse, float temp) {
		buf = ByteBuffer.allocate(SIZE);		
		buf.putLong(id);
		buf.putLong(time);
		buf.putInt(pulse);
		buf.putFloat(temp);
		buf.putInt(SIZE);		
	}
	
	/**
	 * Constructs a Reading from the given stream. 
	 */	
	public Reading(DataInputStream sin) throws IOException {
		byte[] a = new byte[SIZE];
		sin.readFully(a);
		buf = ByteBuffer.wrap(a);
	}
	
	/**
	 * @return the underlying data array.
	 */
	byte[] data() {
		return buf.array();
	}

	public long getID(){
		return this.buf.getLong();
	}
	
	/**
	 * Reading data rendered as a string. 
	 */
	@Override
	public String toString() {
		return "ID:" + buf.getLong()
			+ ", time:" + new Date(buf.getLong())
			+ ", pulse:" + buf.getInt()
			+ ", temp:" + buf.getFloat()
			+ ", SIZE:" + buf.getInt();
	}
	
	/**
	 * A quick unit test for the class. 
	 */
	public static void main(String[] args) throws IOException {
		Reading p1 = new Reading(12345l, 
				System.currentTimeMillis(),
				60,	37.5f);
		
		DataInputStream din = new DataInputStream( 
				new ByteArrayInputStream(p1.data()));
		
		Reading p2 = new Reading(din);
		
		assert Arrays.equals(p1.data(), p2.data());
	}
}


// The length field is not needed, because message is fixed length one