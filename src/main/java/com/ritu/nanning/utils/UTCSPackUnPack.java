package com.ritu.nanning.utils;

import java.nio.ByteBuffer;
//import org.apache.mina.core.buffer.ByteBuffer;
/**
 * 863通讯协议的解包和打包
 * 
 * @author XL
 *
 */
public class UTCSPackUnPack  implements UTCSNetProtocol {
	/**
	 * 打包
	 * @param inBuf
	 * @return
	 * @throws Exception
	 */
	public static ByteBuffer PackTable(byte[] inBuf) throws Exception
	{
		int i;
		byte chVerify=0;

//		if(inBuf.length<MIN_PKGSIZE || inBuf.length>MAX_PKGSIZE){
//			System.err.println("不可以");
//			return null;
//		}else{
//			System.out.println("可以放");
//		}
		
		ByteBuffer buffer=ByteBuffer.allocate(MAX_PACKEDPKGSIZE);
		// Head END_CHAR
		buffer.put(END_CHAR);
		
		// Data Elements
		for (i=0; i<inBuf.length; i++) {
			switch (inBuf[i]) {
				case END_CHAR:
					buffer.put(ESC_CHAR);
					chVerify ^= ESC_CHAR;
					buffer.put(ESC_END);
					chVerify ^= ESC_END;
					break;
				case ESC_CHAR:
					buffer.put(ESC_CHAR);
					chVerify ^= ESC_CHAR;
					buffer.put(ESC_ESC);
					chVerify ^= ESC_ESC;
					break;
				default:
					buffer.put(inBuf[i]);
					chVerify ^= inBuf[i];
					break;
			}
		}

		// Verify char
		switch (chVerify) {
			case END_CHAR:
				buffer.put(ESC_CHAR);
				buffer.put(ESC_END);
				break;
			case ESC_CHAR:
				buffer.put(ESC_CHAR);
				buffer.put(ESC_ESC);
				break;
			default:
				buffer.put(chVerify);
				break;
		}

		// Tail END_CHAR
		buffer.put(END_CHAR);
		
		return buffer;
	}
	
	public static ByteBuffer PackTable(ByteBuffer inBf) throws Exception
	{
		int i;
		byte chVerify=0;
		byte oneOutByte;
		System.out.println("33:"+inBf);
		inBf.flip();
		System.out.println("33:"+inBf);
		if(inBf.limit()<MIN_PKGSIZE || inBf.limit()>MAX_PKGSIZE)
			return null;
		
		ByteBuffer buffer=ByteBuffer.allocate(MAX_PACKEDPKGSIZE);
//		ByteBuffer buffer=ByteBuffer.allocate(MAX_PACKEDPKGSIZE, false);
		// Head END_CHAR
		buffer.put(END_CHAR);
		
		// Data Elements
		for (i=0; i<inBf.limit(); i++) {
			oneOutByte=inBf.get(i);
			switch (oneOutByte) {
				case END_CHAR:
					buffer.put(ESC_CHAR);
					chVerify ^= ESC_CHAR;
					buffer.put(ESC_END);
					chVerify ^= ESC_END;
					break;
				case ESC_CHAR:
					buffer.put(ESC_CHAR);
					chVerify ^= ESC_CHAR;
					buffer.put(ESC_ESC);
					chVerify ^= ESC_ESC;
					break;
				default:
					buffer.put(oneOutByte);
					chVerify ^= oneOutByte;
					break;
			}
		}

		// Verify char
		switch (chVerify) {
			case END_CHAR:
				buffer.put(ESC_CHAR);
				buffer.put(ESC_END);
				break;
			case ESC_CHAR:
				buffer.put(ESC_CHAR);
				buffer.put(ESC_ESC);
				break;
			default:
				buffer.put(chVerify);
				break;
		}

		// Tail END_CHAR
		buffer.put(END_CHAR);
		
		return buffer;
	}
	/**
	 * 解包
	 * @param inBuf
	 * @return
	 * @throws Exception
	 */
	public static byte[] UnpackTable(byte[] inBuf) throws Exception
	{
		int Head, Tail;
		byte LeftByte, RightByte;
		byte VerifyByte = 0;


		Head = 1;
		Tail = inBuf.length-2;
		ByteBuffer buffer=ByteBuffer.allocate(MAX_PKGSIZE);
		
		// Get verify char
		VerifyByte = inBuf[--Tail];
		switch (VerifyByte) {
			case ESC_CHAR:
				RightByte = inBuf[Tail+1];
				switch (RightByte) {
					case ESC_END:
						VerifyByte = END_CHAR;
						break;
					case ESC_ESC:
						VerifyByte = ESC_CHAR;
						break;
					default:
						throw new Exception("UnPack Error!");
				}
				break;
			default:
				VerifyByte = inBuf[++Tail];
				break;
		}

		// Unpack
		// Head is point to first non-ENDCHAR byte;
		// Tail is point to VerifyChar or ESCESC char.
		LeftByte = inBuf[Head];
		while (Head != Tail) {
			VerifyByte ^= LeftByte;
			if (LeftByte==ESC_CHAR) {
				RightByte = inBuf[++Head];
				if (Head==Tail)
					throw new Exception("UnPack Error!");
				VerifyByte ^= RightByte;
				switch (RightByte) {
				case ESC_END:
					buffer.put(END_CHAR);
					break;
				case ESC_ESC:
					buffer.put(ESC_CHAR);
					break;
				default:
					throw new Exception("UnPack Error!");
				}
			}
			else {
				// When User get packed table from original buffer,
				//	CAN drop away table that END_CHAR is not in HEAD or TAIL!
				// So, here not consider Byte is END_CHAR.
				buffer.put(LeftByte);
			}
			LeftByte = inBuf[++Head];
		}

		if (VerifyByte!=0)		// Verify byte should be 0
			throw new Exception("UnPack Error,VerifyByte Error!");

		buffer.flip();
		byte[] retData = new byte[buffer.limit()];
		buffer.get(retData);
		buffer=null;
		return retData;
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args){
		StringBuffer sbMsg = new StringBuffer();
		byte[] data ={(byte)210,0,7,32,0};
		byte[] ret=null;
		System.out.println(data.toString());
		try{
			ByteBuffer bf=PackTable(data);
//			for (int i = 0; i < bf.limit(); i++) {
//				o(i,ByteUtil.toInt(bf.get(i)));
//			}
			
			bf.flip();
//			o(bf.position(),bf.capacity());
			ret=new byte[bf.limit()];
			bf.get(ret);
			bf=null;
		}catch(Exception e){
			e.printStackTrace();
		}
//		System.out.println("00000:"+ret);
		for (int i = 0; i < ret.length; i++) {
			sbMsg.append(ByteUtil.toInt(ret[i])).append("-");
		}
		System.out.println(sbMsg);
		
		byte[] ret2=null;
		try{
			ret2=UnpackTable(ret);
		}catch(Exception e){
			e.printStackTrace();
		}
		sbMsg.setLength(0);
		for (int i = 0; i < ret2.length; i++) {
			sbMsg.append(ByteUtil.toInt(ret2[i])).append("-");
		}
		System.out.println(sbMsg);
	}
}
