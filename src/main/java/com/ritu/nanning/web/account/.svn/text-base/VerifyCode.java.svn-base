package com.ritu.nanning.web.account;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 验证码生成
 * @author 陈国元
 * @Date 2014-03-17 10:34:15
 */
@Controller
@RequestMapping(value = "/verifycode")
public class VerifyCode extends HttpServlet{

	 private int width = 60;//图片宽度
	    private int height = 26;//图片高度
	    @SuppressWarnings("unchecked")
		@RequestMapping(method = RequestMethod.GET)
		public String html(HttpServletRequest req, HttpServletResponse resp) throws
	    ServletException, java.io.IOException {
			
			 BufferedImage buffImg = 
			        	new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			        Graphics2D g = buffImg.createGraphics();
			        
			        g.setColor(Color.WHITE);
			        g.fillRect(0, 0, width, height);//填充矩形内部区域

			        Font font = new Font("Times New Roman", Font.PLAIN, 18);
			        g.setFont(font);

//			        g.setColor(Color.BLACK);
//			        g.drawRect(0, 0, width - 1, height - 1);//绘制矩形的高亮边框

			        g.setColor(Color.GRAY);
			        
			        Random random = new Random();//创建随机数生成器
			        //创建验证码的背景随机线
			        for (int i = 0; i < 50; i++) {
			            int x = random.nextInt(width);
			            int y = random.nextInt(height);
			            int xl = random.nextInt(12);
			            int yl = random.nextInt(12);
			            g.drawLine(x, y, x + xl, y + yl);//绘制直线
			        }

			        StringBuffer randomCode = new StringBuffer();//创建缓冲字符流
			        int red = 0, green = 0, blue = 0;//设置画笔随机色的默认值
			        //循环创建随机数，这里指定创建4个随机数
			        for (int i = 0; i < 4; i++) {
			        	//获取随机数并转换为String
			            String strRand = String.valueOf(random.nextInt(10));
			            
			            //设置画笔颜色为随机色
			            red = random.nextInt(110);
			            green = random.nextInt(50);
			            blue = random.nextInt(50);
			            g.setColor(new Color(red, green, blue));
			            
			            //或者设置画笔颜色为指定颜色
//			            g.setColor(Color.getColor("9BB47F"));
			            g.drawString(strRand, 13 * i + 6, 16);
			            
			            randomCode.append(strRand);//为缓冲字符流追加数据
			        }
			        //把验证码字串放进session
			        
			        HttpSession session = req.getSession();
			        session.setAttribute("verifyCode", randomCode.toString());
			        
			        //设置响应头部信息
			        resp.setHeader("Pragma", "no-cache");
			        resp.setHeader("Cache-Control", "no-cache");
			        resp.setDateHeader("Expires", 0);
			        //设置响应内容为图像，格式为jpg
			        resp.setContentType("image/jpeg");

			        ServletOutputStream sos = resp.getOutputStream();
			        ImageIO.write(buffImg, "jpeg", sos);
			        sos.close();
			
			return "account/VerifyCode";
		}
}
