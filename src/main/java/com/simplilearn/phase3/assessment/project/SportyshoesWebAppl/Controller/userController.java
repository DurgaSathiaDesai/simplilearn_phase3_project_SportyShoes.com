package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.userInfo;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.userService;


@Controller
public class userController 
{
	@Autowired
	private userService userservice;
	
	@RequestMapping("/users/{pageNum}")
	public String displayUsers(Model model,@RequestParam(value="roleType", required=false,defaultValue = "all")String role,@PathVariable(name = "pageNum") int pageNum)
	{
		//model.addAttribute("url", "userInfoDisplay");
		//String role = "user";
		if(role.isEmpty())
		{
			role = "all";
		}
		Page<userInfo> userpages = userservice.getAllUsers(role,pageNum);
		
		List<userInfo> users = userpages.getContent();
		
		if(users.isEmpty())
		{
			String result = null;
			model.addAttribute("userlist", users);
			if (role.equalsIgnoreCase("admin"))
				result = "No admins have signed up !!";
			if (role.equalsIgnoreCase("user"))
				result = "No users have signed up !!";
			if (role.equalsIgnoreCase("all"))
				result = "No users/admins with have signed up !!";
			model.addAttribute("result",result);
		}
		else
		{
			model.addAttribute("userlist", users);
		}
		
		model.addAttribute("role",role);
		model.addAttribute("page",0);
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", userpages.getTotalPages());
		return "userInfoDisplay";
	}
	
	@RequestMapping("/usersbyname/{pageNum}")
	public String displayUsersbyName(Model model,@RequestParam(value="firstname")String firstname,@RequestParam(value="lastname")String lastname,@RequestParam(value="roleType")String role,@PathVariable(name = "pageNum") int pageNum)
	{
		//model.addAttribute("url", "userInfoDisplay");
		//List<userInfo> users = userservice.getAllUsers();
		List<userInfo> users = null;
		Page<userInfo> userpages = null;
		
		String name;
		if(lastname.isEmpty())
		{
			userpages = userservice.getUserByFirstName(firstname,role,pageNum);
			users = userpages.getContent();
			name = "firstname " + firstname;
		}
		else
		{
			if(firstname.isEmpty())
			{
				userpages = userservice.getUserByLastName(lastname,role,pageNum);
				users = userpages.getContent();
				name = "lastname " + lastname;
			}
			else
			{
				userpages = userservice.getUserByName(firstname, lastname,role,pageNum);
				users = userpages.getContent();
				name = "name " + firstname + " " + lastname;
			}
		}
		
		if(users.isEmpty())
		{
			String result = null;
			model.addAttribute("userlist", users);
			if (role.equalsIgnoreCase("admin"))
				result = "No admins with " + name + " have signed up !!";
			if (role.equalsIgnoreCase("user"))
				result = "No users with " + name + " have signed up !!";
			if (role.equalsIgnoreCase("all"))
				result = "No users/admins with " + name + " have signed up !!";
			model.addAttribute("result",result);
		}
		else
		{
			model.addAttribute("userlist", users);
		}
		model.addAttribute("userlist", users);
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", userpages.getTotalPages());
		model.addAttribute("role",role);
		model.addAttribute("page",1);
		return "userInfoDisplay";
	}
	
	
}
