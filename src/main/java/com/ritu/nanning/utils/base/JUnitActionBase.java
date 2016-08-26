package com.ritu.nanning.utils.base;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.junit.BeforeClass;  
import org.springframework.mock.web.MockServletContext;  
import org.springframework.web.context.WebApplicationContext;  
import org.springframework.web.context.support.XmlWebApplicationContext;  
import org.springframework.web.servlet.HandlerAdapter;  
import org.springframework.web.servlet.HandlerExecutionChain;  
import org.springframework.web.servlet.HandlerMapping;  
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;  
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;  
/**  
* 说明： JUnit测试action时使用的基类  
*/   
public class JUnitActionBase {  
    private static HandlerMapping handlerMapping;  
    private static HandlerAdapter handlerAdapter;  
    /** 
     * 读取spring3 MVC配置文件 
     */  
    @BeforeClass  
 public static void setUp() {  
        if (handlerMapping == null) {  
            String[] configs = { "file:src/main/webapp/WEB-INF/spring-mvc.xml" };  
            System.out.println("++++++++：1:" + 1 + ";  这是输出的");
            XmlWebApplicationContext context = new XmlWebApplicationContext();  
            System.out.println("++++++++：2:" + 2 + ";  这是输出的");
            context.setConfigLocations(configs);  
            System.out.println("++++++++：3:" + 3 + ";  这是输出的");
            MockServletContext msc = new MockServletContext();  
            System.out.println("++++++++：3.1:" + 3.1 + ";  这是输出的");
            context.setServletContext(msc);    
            System.out.println("++++++++：3.2:" + 3.2 + ";  这是输出的");
            context.refresh();  
           System.out.println("++++++++：4:" + 4 + ";  这是输出的");
            msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);  
            handlerMapping = (HandlerMapping) context.getBean("UserManagementController"); 
            System.out.println("++++++++：5:" + 5 + ";  这是输出的");
//            System.out.println(context.getBeanNamesForType(AnnotationMethodHandlerAdapter.class));
            
            handlerAdapter = (HandlerAdapter) context.getBean("UserManagementController");     
        }  
    }  
  
    /** 
     * 执行request对象请求的action 
     *  
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
    public ModelAndView excuteAction(HttpServletRequest request, HttpServletResponse response)  
 throws Exception {  
        HandlerExecutionChain chain = handlerMapping.getHandler(request);  
        final ModelAndView model = handlerAdapter.handle(request, response,  
                chain.getHandler());  
        return model;  
    }  
}