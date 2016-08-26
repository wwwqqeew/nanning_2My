package com.ritu.nanning.service.modules;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class testMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		addOne();
//		testURLGet() ;
		
		int all = 10000000;
		Long b = 0l;
		int d = 0;
		int k = 0;
		HashMap<Long, String> hs = new HashMap<Long, String>();
		for (int i = 0; i < all; i++) {
//			b = (long) UUID.randomUUID().hashCode();
			b = Math.abs(UUID.randomUUID().getMostSignificantBits());
			
			k = (""+b).length() > k ? (""+b).length(): k;
			
			if (hs.get(b) != null) {
				System.out.println("第"+i+"个:"+hs.get(b));
				d ++;
			}else{
				hs.put(b, ""+b);
			}
		}
//		k = (int) (b%10);
		System.out.println(k);
//		;
		System.out.println(Math.abs(UUID.randomUUID().getMostSignificantBits()));
		System.out.println("完成了:"+d);
		
//		 String  date = new Date().toLocaleString();
//		 System.out.println(new Date().getTime());
	}
	
	  /**
	   * 根据日期生成长整型id
	   * @param args
	   */
	private static long num=0; 
	  public static synchronized long getLongId(){
//	    String date=DateUtil.getDate2FormatString(new Date(), "yyyyMMddHHmmssS");
		  String  date = new Date().toLocaleString();
	    System.out.println("原始id="+date);
	    if(num>=99) num=0l;
	    ++num;
	    if(num<10) {
	      date=date+00+num;
	    }else{
	      date+=num;
	    }
	    return Long.valueOf(date);
	  }

	public static void addOne() throws Exception {
		FileWriter fw=null;
		String path=testMain.class.getResource("/port.properties").getPath(); 
		System.out.println("pp:"+path);
		File file=new File(path);
		//true 参数制定从文件的末尾开始写数据
		fw=new FileWriter(file,true);
		fw.close();
		}
	
	private  static void testURLGet() throws URISyntaxException, IOException {
		// TODO Auto-generated method stub
		URL url = testMain.class.getResource("/port.properties");
		System.out.println("222:"+url.toURI());
        File file = new File(url.toURI());
        FileWriter writer = new FileWriter(file, true);
	}
}
