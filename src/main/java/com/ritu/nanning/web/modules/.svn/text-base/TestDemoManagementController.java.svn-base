package com.ritu.nanning.web.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ritu.nanning.entity.Role;
import com.ritu.nanning.entity.User;
import com.ritu.nanning.service.account.AccountService;
import com.ritu.nanning.service.account.ResourceService;
import com.ritu.nanning.service.account.RoleService;
import com.ritu.nanning.service.modules.DepartmentService;
import com.ritu.nanning.service.modules.UserService;
import com.ritu.nanning.utils.o;
import com.ritu.nanning.utils.JsonBuilder;
import com.ritu.android.rgps.wg;

/**
 * @function 测试例子控制层
 * @author
 * @date 2014-03-21
 * @latitude 1.0
 */
@Controller
@RequestMapping(value = "/testdemomanagement")
public class TestDemoManagementController
{
	 @Autowired
	    private AccountService accountService;
	  @Autowired
	    private UserService userService;
	  
	  @Autowired
	    private RoleService roleService;
	  
	  @Autowired
	    private ResourceService resourceService;
	  
	  @Autowired
	    private DepartmentService departmentService;
	/**
	 * 默认访问 /testdemomanagement 的时候的跳转页面 Get方法要返回的页面位置
	 * 
	 * @param model
	 * @return 页面位置
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String html(Model model)
	{
		return "/modules/tt";
	}

	/**
	 * 查看用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testname(Model model) {
		model.addAttribute("userList", userService.findAll());
		return "/account/user/list";
	}

	/**
	 * 新增用户
	 * 
	 * @return result
	 */
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("user", new User());
        model.addAttribute("op", "新增");
        return "/account/user/edit";
    }
    
    /**
	 * 添加用户信息
	 * 
	 * @return result
	 */
    @RequestMapping(value = "addUser", produces = "text/html;charset=UTF-8")
    public String addUser(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "job", required = false) String job,
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
			@RequestParam(value = "available", required = false) int available,
			@RequestParam(value = "department", required = false) Long department,
			@RequestParam(value = "role", required = false) Long role,Model model) {
    	User user = new User();
    	user.setLoginName(username);
    	user.setPlainPassword(password);
    	user.setRole(roleService.findById(role));
    	user.setDepartment(departmentService.findById(department));
    	user.setJob(job);
    	user.setTel(phoneNumber);
    	user.setAvailable(available);
    	accountService.registerUser(user);
    	model.addAttribute("userList", userService.findAll());
    	return "/account/user/list";
    }
    
    /**
	 * 修改用户请求
	 * 
	 * @return result
	 */
    @RequestMapping(value = "/editUserReq", method = RequestMethod.GET)
    public String showEditForm(@RequestParam(value = "id", required = false) Long userID,Model model) {
        setCommonData(model);
        User user = userService.findById(userID);
        model.addAttribute("user", user);
        model.addAttribute("op", "修改");
        return "/account/user/edit";
    }
    
    /**
	 * 修改用户信息
	 * 
	 * @return result
	 */
    @RequestMapping(value = "editUser", produces = "text/html;charset=UTF-8")
    public String editUser(@RequestParam(value = "id", required = false) Long userID,
    		@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "job", required = false) String job,
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
			@RequestParam(value = "available", required = false) int available,
			@RequestParam(value = "department", required = false) Long department,
			@RequestParam(value = "role", required = false) Long role,Model model) {
    	User user = userService.findById(userID);
    	user.setLoginName(username);
    	user.setPlainPassword(password);
    	user.setRole(roleService.findById(role));
    	user.setDepartment(departmentService.findById(department));
    	user.setJob(job);
    	user.setTel(phoneNumber);
    	user.setAvailable(available);
    	userService.update(user);
    	model.addAttribute("userList", userService.findAll());
    	return "/account/user/list";
    }
    
    /**
	 * 删除用户信息
	 * 
	 * @return result
	 */
    @RequestMapping(value = "deleteUser", produces = "text/html;charset=UTF-8")
    public String deleteUser(@RequestParam(value = "id", required = false) Long[] userID,Model model) {
    	for(int i=0;i<userID.length;i++){
    		if(userID[i]==1){
    			break;
    		}
    		userService.delete(userID[i]);
    	}
    	model.addAttribute("userList", userService.findAll());
    	return "/account/user/list";
    }
    
    
    //查看角色信息
    @RequestMapping(value = "/testRole", method = RequestMethod.GET)
	public String testRole(Model model) {
    	 model.addAttribute("roleList", roleService.findAll());
		return "/account/role/list";
	}
    
    //新增角色请求
    @RequestMapping(value = "/testaddrole", method = RequestMethod.GET)
    public String testaddrole(Model model) {
    	 setCommonData1(model);
         model.addAttribute("role", new Role());
         model.addAttribute("op", "新增");
        return "/account/role/edit";
    }
    
    //提交角色信息
    @RequestMapping(value = "/testaddrole", method = RequestMethod.POST)
    public String addRole(Role role, RedirectAttributes redirectAttributes) {
    	role.setRemark("管理员");
    	role.setDescription("管理");
    	role.setResources("21,31");
    	role.setRole("xx");
    	accountService.addRole(role);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/home";
    }
    
    
	@RequestMapping(value = "/testel", method = RequestMethod.GET)
	public String testel() {
		return "/modules/testOpenid";
	}

	@RequestMapping(value = "/nanning", method = RequestMethod.GET)
	public String nanning() {
		System.out.println("33333");
		return "/modules/indexNanning";
	}
	
	private void setCommonData(Model model) {
        /*model.addAttribute("organizationList", organizationService.findAll());*/
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("departmentList", departmentService.findAll());
    }
	private void setCommonData1(Model model) {
		model.addAttribute("resourceList", accountService.findAllRecourse());
    }
	public static void main(String[] args) {
	}
}
