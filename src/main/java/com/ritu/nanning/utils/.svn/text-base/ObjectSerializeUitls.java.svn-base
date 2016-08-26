package com.ritu.nanning.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class ObjectSerializeUitls {

	public static byte[] toByte(Object obj) {
		byte[] bs = new byte[0];
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream(); //Byte新输出流
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			bs = bos.toByteArray();
		} catch (Exception e) {
			System.out.println(ObjectSerializeUitls.class.getSimpleName() + ".toByte : " + e.getCause());
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (Exception e) {
				System.out.println(ObjectSerializeUitls.class.getSimpleName() + " : " + e.getCause());
			}

		}
		return bs;
	}

	/**
	 * 根据l来设置缓冲区容量
	 * @param l
	 * @return
	 */
	public static byte[] fromLong(long l) {
		ByteBuffer buffer = ByteBuffer.allocate(8); //分配一个新的字节缓冲区。 
		buffer.putLong(l); //将 8 个包含给定 long 值的字节按照当前的字节顺序写入到此缓冲区的当前位置，然后将该位置增加 8。
		return buffer.array();
	}

	/**
	 * 根据i来设置缓冲区容量
	 * @param i
	 * @return
	 */
	public static byte[] fromInt(int i) {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(i);
		return buffer.array();
	}
	
	public static byte[] fromByte(byte i) {
		ByteBuffer buffer = ByteBuffer.allocate(1);
		buffer.put(i);
		return buffer.array();
	}
	
	public static byte[] fromBytes(byte[] i) {
		ByteBuffer buffer = ByteBuffer.allocate(i.length);
		buffer.put(i);
		return buffer.array();
	}

	public static Object toObject(byte[] bs) {
		Object o = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bs);
			ois = new ObjectInputStream(bis);
			o = ois.readObject();
		} catch (Exception e) {
			System.out.println(ObjectSerializeUitls.class.getSimpleName() + ".toObject : " + e.getCause());
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (Exception e) {
				System.out.println(ObjectSerializeUitls.class.getSimpleName() + " : " + e.getCause());
			}
		}
		return o;
	}

	public static Long toLong(byte[] bs, int sta, int length) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.put(bs, sta, length);
		buffer.flip();
		return buffer.getLong();
	}

	public static Integer toInt(byte[] bs, int sta, int length) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.put(bs, sta, length);
		buffer.flip();
		return buffer.getInt();
	}
	
	public static byte toByte(byte[] bs, int sta, int length) {
		ByteBuffer buffer = ByteBuffer.allocate(1);
		buffer.put(bs, sta, length);
		buffer.flip();
		return buffer.get();
	}

}
