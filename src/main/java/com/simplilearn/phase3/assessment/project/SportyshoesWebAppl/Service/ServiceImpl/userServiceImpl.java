package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.userInfo;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository.userRepository;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.userService;


@Service
public class userServiceImpl implements userService
{
	@Autowired
	private userRepository userRepo;
		
	@Override
	public Page<userInfo> getAllUsers(String role,int pageNum)
	{
		Page<userInfo> usersInfo = null;
		int pageSize = 4;
		
		 Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		 
		if(role.equalsIgnoreCase("all"))
		{
			usersInfo = userRepo.findAll(pageable);
		}
		else
		{
			usersInfo = userRepo.findUserByRole(role,pageable);
		}
			
		return usersInfo;
	}
	
	@Override
	public Page<userInfo> getUserByName(String firstname,String lastname,String role,int pageNum)
	{
		Page<userInfo> usersInfo = null;
		int pageSize = 4;
		
		 Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		//List<userInfo> usersInfo = null;
		if(role.equalsIgnoreCase("all"))
		{
			usersInfo = userRepo.findUserByName(firstname, lastname,pageable);
		}
		else
		{
			usersInfo = userRepo.findUserByNameAndRole(firstname, lastname,role,pageable);
		}
		return usersInfo;
	}
	
	@Override
	public Page<userInfo> getUserByFirstName(String firstname,String role,int pageNum)
	{
		//List<userInfo> usersInfo = null;
		
		Page<userInfo> usersInfo = null;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		 
		if(role.equalsIgnoreCase("all"))
		{
			usersInfo = userRepo.findUserByFirstName(firstname,pageable);
		}
		else
		{
			usersInfo = userRepo.findUserByFirstNameAndRole(firstname,role,pageable);
		}
		return usersInfo;
	}
	
	@Override
	public Page<userInfo> getUserByLastName(String lastname,String role,int pageNum)
	{
		//List<userInfo> usersInfo = null;
		
		Page<userInfo> usersInfo = null;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		 
		if(role.equalsIgnoreCase("all"))
		{
			usersInfo = userRepo.findUserByLastName(lastname,pageable);
		}
		else
		{
			usersInfo = userRepo.findUserByLastNameAndRole(lastname,role,pageable);
		}
		 
		 return usersInfo;
	}
}
