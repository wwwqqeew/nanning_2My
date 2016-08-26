package com.ritu.nanning.utils;

import java.io.File;


public class fileUtilsMy {

	public static void main(String[] args) {
		o.o(creatFile("d:\\ff", false));
	}
	/**
	 * 
	 * @param destFileName
	 */
	public static boolean createFile(String destFileName) {
		File file =new File(destFileName);    
		//如果文件夹不存在则创建    
		if(!file .exists()  && !file .isDirectory())      
		{       
			o.o("创建了文件夹："+destFileName);  
		    file .mkdir();    
		}else{  
			return false;
		}
		return true;  
	}
	
	/**
	 * 创建文件夹
	 * @param destFileName
	 * @param overlay
	 * @return
	 */
	public static boolean creatFile(String destFileName, boolean overlay){
		 // 判断目标文件是否存在  
        File destFile = new File(destFileName);  
        if (destFile.exists()) {  
            // 如果目标文件存在并允许覆盖  
            if (overlay) {  
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件  
                new File(destFileName).delete();  
            }  
        } else {  
            // 如果目标文件所在目录不存在，则创建目录  
            if (!destFile.getParentFile().exists()) {  
                // 目标文件所在目录不存在  
                if (!destFile.getParentFile().mkdirs()) {  
                    // 复制文件失败：创建目标文件所在目录失败  
                    return false;  
                }  
            }else{
            	o.o("创建了文件夹："+destFileName);
            	destFile .mkdir();
            }
        }
		return true;  
	}
}
