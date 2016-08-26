package com.ritu.nanning.utils;

public class ByteUtil {
	/**
	 * 得到byte中指定位（0-7）的值为
	 * @param data
	 * 
	 * @param pos
	 * 		[0-7]
	 * @return
	 * 		1 true
	 * 		0 false
	 */
	public static boolean getByteBit(byte data,int pos) throws Exception{
		if(pos<0 || pos >7){
			throw new Exception("pos out of range");
		}
		byte nChk=1;
		nChk<<=pos;
		if((data&nChk)==0)
			return false;
		return true;
	}
	
	/**
	 * 设置byte中指定位bit（0-7）的值为1
	 * 
	 * @param data
	 * @param pos
	 * @throws Exception
	 */
	public static byte setByteBit(byte data,int pos) throws Exception{
		if(pos<0 || pos >7){
			throw new Exception("pos out of range");
		}
		byte val=1;
		val<<=pos;
		return toByte(val | data);
	}
	/**
	 * int型转成byte型
	 * 
	 * @param value
	 * @return
	 */
	public static byte toByte(int value){
		return (byte)value;
	}
	/**
	 * byte型转成int型
	 * 
	 * @param b
	 * @return
	 */
	public static int toInt(byte b) {
		return (int)(b & 0xFF);
	}
	/**
	 * 得到byte的低4位
	 * @param b　
	 * @return
	 */
	public static int low4BitToInt(byte b){
		byte tmp=(byte)(b & 0x0f);
		return toInt(tmp);
	}
	/**
	 * 得到byte的高4位
	 * @param b
	 * @return
	 */
	public static int hige4BitToInt(byte b){
		byte tmp=(byte)(b>>4);
		return low4BitToInt(tmp);
	}	
	/**
	 * 4 byte Array to int
	 *  
	 * @param byteValue
	 * @return
	 */ 
	public static int byteArray4ToInt(byte[] byteValue) {
		return byteArray4ToInt(byteValue,0);
	}

	/**
	 * Convert an int to a byte array
	 *		低位在前，高位在后
	 * @param value int
	 * @return byte[]
	 */
	public static byte[] shortToByteArray(short value) {
		int len=2;
		byte[] b = new byte[len];

		// 使用2个byte表示short
		for (int i = 0; i < len; i++) {
			int offset = i * 8;
			b[i] = (byte) ((value >> offset) & 0xFF);
		}
		return b;
	}
	public static byte[] intToByteArray(int value) {
		byte[] b = new byte[4];

		// 使用4个byte表示int
		for (int i = 0; i < 4; i++) {
			int offset = i * 8;
			b[i] = (byte) ((value >> offset) & 0xFF);
		}
		return b;
	}

	/**
	 * Convert the byte array to an int starting from the given offset.
	 * 		低位在前，高位在后
	 * @param b The byte array
	 * @param offset The array offset,如果byte数组长度就是4，则该值为0
	 * @return The integer
	 */
	public static int byteArray4ToInt(byte[] b, int offset) {
		if (b.length < 4+offset)
			return 0;
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int shift =  i * 8;
			value += (b[i + offset] & 0x000000FF) << shift;
		}
		return value;
	}
	/**
	 * 2 byte Array to int
	 *  
	 * @param byteValue
	 * @return
	 */ 
	public static int byteArray2ToInt(byte[] byteValue) {
		return byteArray2ToInt(byteValue,0);
	}

	public static int byteArray2ToInt(byte[] b, int offset) {
		if (b.length < 2+offset)
			return 0;
		int value = 0;
		for (int i = 0; i < 2; i++) {
			int shift =  i * 8;
			value += (b[i + offset] & 0x000000FF) << shift;
		}
		return value;
	}
	/**
	 * 二进制字符串转byte（例如：00000011 转成 byte类型的3）
	 * 
	 * @param value
	 * @return
	 */
	public static byte BinStringToByte(String value){
		byte b = 0;
		if(value!=null && value.length()==8){
			for(int i=0;i<8;i++){
				int nBase=1;
				if(value.charAt(i)=='1'){
					nBase<<=(7-i);
					b+=nBase;
//					System.out.print(i);
//					System.out.println(value.charAt(i));
				}
			}
		}
		return b; 
	}	
	public static void main(String[] args){
		//byte b=(new Byte("192")).byteValue();
//		System.out.println(hige4BitToInt((byte)0xF5));
//		System.out.println(low4BitToInt((byte)0xa5));
//		int value=1025;
//		
//		//Convert int to byte[4]
//		byte buf[]=intToByteArray(value);
//		StringBuffer sbMsg = new StringBuffer();
//		for (int i = 0; i < 4; i++) {
//			sbMsg.append(ByteUtil.toInt(buf[i])).append("-");
//		}
//		System.out.println(sbMsg);
//		//Convert tbyte[4]  to int
//		System.out.println(byteArrayToInt(buf,0));
		System.out.println(toByte(255));
		System.out.println(toInt((byte)-1));
		byte data=0;
		try{
			data=setByteBit(data,0);
		}catch(Exception e){}
		System.out.println(data);
	}
}
