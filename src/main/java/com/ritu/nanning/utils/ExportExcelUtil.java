package com.ritu.nanning.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 * 打印时
 * 
 * */
public class ExportExcelUtil {

	/**
	 * 导出Excel
	 * @param response
	 * @param listObject 包含数据的List
	 * @param objVo  该类型的Vo类
	 * @param title 文档名称
	 * @param encodeStr 包含没一列标题的JSON数据  如：{"id":"编号","name":名称}
	 */
	public static void exportExcel(HttpServletResponse response, List listObject, Object objVo, String title ,String encodeStr) {
		JSONObject  jasonObject1 = JSONObject.fromObject(encodeStr); 
		
		Workbook wb = new HSSFWorkbook();        
		// 创建第一个sheet（页），并命名        
		Sheet sheet = wb.createSheet("报表xls");     
		Iterator keys2=jasonObject1.keys();
		//通过反射，获取设置在Vo类里的各个属性字段Excel中的长度
		if (keys2 != null) {
			while(keys2.hasNext()){ 
				for(int a=0;a<jasonObject1.size();a++){
					String key = (String) keys2.next();
					sheet.setColumnWidth((short) a,  (FieldValueByName.getExcelWidthByName(key, objVo)));   
				}
				break;
			}
		}
		
		//设置字体样式
		Row row = sheet.createRow((short) 0);       
		// 创建两种单元格格式       
		CellStyle cs = wb.createCellStyle();       
		CellStyle cs2 = wb.createCellStyle();
		ExportExcelUtil.setStyle(wb, cs, cs2);
		//写表头
		Cell cell = null;
		ExportExcelUtil.writeHeader(cell, jasonObject1, row, cs);
		HashMap hm = new HashMap();//记录方法的HashMap，减少根据属性名称读取方法的次数
		for (short i = 0; i < listObject.size(); i++) {  
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的        
			// 创建一行，在页sheet上           
			o.o(listObject.get(i));
			row  = sheet.createRow((short) i + 1);         
			keys2 = jasonObject1.keys();
			//内容赋值
			if (keys2 != null) {
				while(keys2.hasNext()){ 
					for(int a=0;a<jasonObject1.size();a++){
						String key = (String) keys2.next(); 
						cell = row.createCell(a); 
						cell.setCellValue((String)FieldValueByName.getFieldValueByName(key, listObject.get(i), hm));
						cell.setCellStyle(cs2);  
					}
				} 
			}
		
		} 
		//Excel生成
		readExcel(response, wb,title);
	}
	
	/**
	 *读Excel  
	 */
	public static void readExcel(HttpServletResponse response,Workbook wb,String a2){
		ServletOutputStream out = null;
		BufferedInputStream bis = null;    
		BufferedOutputStream bos = null; 
		InputStream is = null;
		try {  
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);    
			byte[] content = os.toByteArray();
			is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面   
			response.reset();       
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			SimpleDateFormat dateformat1=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			StringBuffer String2 = new StringBuffer();
			String a1=dateformat1.format(new Date());
			String2.append(a2);
			String2.append(a1);
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((String2.toString() + ".xls").getBytes(), "ISO8859-1"));
			
			out = response.getOutputStream();
			
			bis = new BufferedInputStream(is);        
			bos = new BufferedOutputStream(out);  
			
			byte[] buff = new byte[2048];
			int bytesRead;
			
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) { 
				bos.write(buff, 0, bytesRead);           
				} 
			} catch (IOException e) {   
				e.printStackTrace(); 
			} finally {       
				close(bis,bos);
			}  
	}
	
	
	/**
	 * 关流
	 */
	public static void close(BufferedInputStream bis,BufferedOutputStream bos){
		if (bis != null)
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		if (bos != null)
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	
	/**
	 *根据 属性名获取属性值 
	 */
	 	public static Object getFieldValueByName(String fieldName, Object o) {  
	       try {    
	    	   DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	           String getter = "get" + firstLetter + fieldName.substring(1);    
	           Method method = o.getClass().getMethod(getter, new Class[] {}); 
	           Type returnType = method.getGenericReturnType();// 返回类型
	           Object value ="";
	           if (new Date().getClass() == returnType){
	        	   value = method.invoke(o, new Object[] {});
	        	   value = df.format(value);
	           }else{
	        	   value = method.invoke(o, new Object[] {})+"";
	           }
	           return value;    
	       } catch (Exception e) {    
	           e.getMessage() ;    
	           return "";    
	       }
	       
	   }

	 	
	  /**	
	   * 设置样式 字体
	   * */ 
		public static void setStyle(Workbook wb,CellStyle cs,CellStyle cs2) {
			// TODO Auto-generated method stub
			Font f = wb.createFont();       
			Font f2 = wb.createFont();        
			// 创建第一种字体样式     
			f.setFontHeightInPoints((short) 10);       
			f.setColor(IndexedColors.BLACK.getIndex());      
			f.setBoldweight(Font.BOLDWEIGHT_BOLD);       
			// 创建第二种字体样式        
			f2.setFontHeightInPoints((short) 10);       
			f2.setColor(IndexedColors.BLACK.getIndex());      
			//f2.setBoldweight(Font.BOLDWEIGHT_BOLD);        
			// 设置第一种单元格的样式        
			cs.setFont(f);  
			cs.setBorderLeft(CellStyle.BORDER_THIN);       
			cs.setBorderRight(CellStyle.BORDER_THIN);       
			cs.setBorderTop(CellStyle.BORDER_THIN);       
			cs.setBorderBottom(CellStyle.BORDER_THIN);        
			// cs.setDataFormat(df.getFormat("#,##0.0"));        
			// 设置第二种单元格的样式       
			cs2.setFont(f2);       
			cs2.setBorderLeft(CellStyle.BORDER_THIN);      
			cs2.setBorderRight(CellStyle.BORDER_THIN);     
			cs2.setBorderTop(CellStyle.BORDER_THIN);       
			cs2.setBorderBottom(CellStyle.BORDER_THIN);    
			// cs2.setDataFormat(df.getFormat("text"));
		}
		
	
	/**
	 * 写表头
	 * */	
		public static void writeHeader(Cell cell,JSONObject jasonObject1,Row row,CellStyle cs){
			Iterator keys=jasonObject1.keys();  
			while(keys.hasNext()){  
		        for(int i=0;i<jasonObject1.size();i++){ 
		        	String key=(String) keys.next(); 
		        	cell = row.createCell(i);
		        	cell.setCellValue(jasonObject1.get(key).toString());
		        	cell.setCellStyle(cs);  
		        } 
			}
		}
	  
}
