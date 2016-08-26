package com.ritu.nanning.web.modules;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.org.rapid_framework.generator.GeneratorMain;

import com.ritu.nanning.entity.Demo;
import com.ritu.nanning.entity.DemoVo;
import com.ritu.nanning.service.modules.DemoService;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.nanning.utils.fileUtilsMy;
import com.ritu.nanning.utils.o;
import com.ritu.nanning.utils.base.BaseControl;
import com.ritu.nanning.utils.base.BaseEntityVo;
import com.ritu.nanning.utils.base.BaseService;
import com.ritu.nanning.utils.email.TestMail;
import com.ritu.nanning.utils.speechProduction.voice.voiceCreateMain;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.OutputUtil;

/**
 * @function demo控制层
 * @author cheng.G.Y 
 * @date 2014-09-25
 * @latitude 1.0
 */
@Controller
@RequestMapping(value = "/demomanagement")
public class DemoManagementController extends BaseControl<Demo,Long>{
	
	private DemoService demoService;//业务层对象
	//模糊查询的时候用的页面属性Vo类，和真正的类差不多，
	//多了一个页面，还有导出Excel时候的字段长度
	//
	private DemoVo demoVo = new DemoVo(); 

	@Autowired
	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	//用于在公共方法中：获取Service
	@Override
	protected BaseService getEntityService() {
		return demoService;
	}
	
	//用于在公共方法中：获取该类的Vo页面类
	@Override
	protected BaseEntityVo getBaseEntityVo() {
		return demoVo;
	}
	
	@ResponseBody
	@RequestMapping(value = "voice", produces = "text/html;charset=UTF-8")
	public String voice(@RequestParam(value = "w", required = false) String w) throws Exception {
		GeneratorMain.create();
		return JsonBuilder.toJson(true, voiceCreateMain.createSQL(w));
	}
	
	@RequestMapping(value = "1", produces = "text/html;charset=UTF-8")
	public String d() throws Exception {
//		final Robot rb = new Robot();
//		  rb.delay(2000); //模拟回车
//		  rb.keyPress(KeyEvent.VK_ENTER);
		return "/modules/voice";
	}
	
	@RequestMapping(value = "f", produces = "text/html;charset=UTF-8")
	public String f() throws Exception {
//		final Robot rb = new Robot();
//		  rb.delay(2000); //模拟回车
//		  rb.keyPress(KeyEvent.VK_ENTER);
		return "/modules/final";
	}
	
	/**
	 * POST方法要返回的页面位置
	 * @param model
	 * @return 页面位置
	 */
	@RequestMapping()
	public String html(Model model) {
		return "/modules/DemoManagement";
	}
       

	@ResponseBody
	@RequestMapping(value = "img", produces = "text/html;charset=UTF-8")
	public String img(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 String strImg = request.getParameter("imgId"); 
		  strImg = strImg.substring(strImg.indexOf(",")+1,strImg.length());
		 System.out.println("开始了");
		 String rt = createImgAndSendEmai(strImg);
		return JsonBuilder.toJson(true, rt);
	}
	
	private String createImgAndSendEmai(String strImg) throws Exception{
		fileUtilsMy.createFile("D:\\ff");
		String url = "D:\\ff\\"+new Date().getTime()+".jpg";
		o.o(url);
		  Test64Bit.GenerateImage(strImg, url);
		  TestMail.sendEmail(url);
		  return "1";
	}
	
}
