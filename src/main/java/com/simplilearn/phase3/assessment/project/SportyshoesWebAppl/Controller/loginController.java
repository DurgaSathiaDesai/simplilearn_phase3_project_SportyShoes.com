package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.loginService;


@Controller
public class loginController 
{
	@Autowired
	private loginService loginservice;
	
	@RequestMapping(value= {
			"/",
			"/home"
	})
	public String home()
	{
		return "login";
	}
	
	@RequestMapping("/processlogin")
	public String login(Model model,@RequestParam(value="username")String username,@RequestParam(value="password")String password)
	{
		//model.addAttribute("user", username);
		String url;
		String isValid = loginservice.validateCredentials(username, password);
		if(isValid.equalsIgnoreCase("false"))
		{
			model.addAttribute("error", "Invalid Username or Password");
			url = "login";
		}
		else
		{
			if(isValid.equalsIgnoreCase("admin"))
			{
				url = "adminDashboard";
			}
			else
			{
				model.addAttribute("error", "Only Admins can login");
				url = "login";
			}
		}
		return url;
	}
	
	@RequestMapping("/changepassword")
	public String changePassword()
	{
		return "changepassword";
	}
	
	@RequestMapping("/changePwd")
	public String changePwd(Model model,@RequestParam(value="username")String username,@RequestParam(value="oldpassword")String oldpassword,
			@RequestParam(value="newpassword")String newpassword,@RequestParam(value="cpassword")String cpassword)
	{
		String url = null;
		String isValid = loginservice.validateCredentials(username, oldpassword);
		if(isValid.equalsIgnoreCase("false")||isValid.equalsIgnoreCase("user"))
		{
			model.addAttribute("error", "Invalid Username or Password");
			url = "changepassword";
		}
		else
		{
			int updateValue = loginservice.updateCredentials(username, newpassword);
			if(updateValue != 0)
			{
				model.addAttribute("message", "Password updated successfully!Please login using new credentials!");
				url = "login";
			}
			else
			{
				model.addAttribute("error", "Error in updating password!Please try again!");
				url = "changepassword";
			}
		}
		return url;
	}
	
	@RequestMapping(value = {
			"/adminDashboard",
			"*/adminDashboard"
			})
	public String adminDashboard(Model model,@RequestParam(value="url",required=false,defaultValue="users/1")String ifurl)
	{
		//String srcUrl;
		//model.addAttribute("url", "userInfoDisplay");
		
		/*
		 * if (ifurl.equalsIgnoreCase("products")) srcUrl = "products"; else srcUrl =
		 * "users";
		 */
		 
            model.addAttribute("ifurl", ifurl);
		return "adminDashboard";
	}
	
}
