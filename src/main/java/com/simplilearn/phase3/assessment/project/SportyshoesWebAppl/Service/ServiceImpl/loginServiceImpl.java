package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.loginCredentials;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository.userloginRepository;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.loginService;


@Service
public class loginServiceImpl implements loginService
{
	@Autowired
	private userloginRepository loginRepo;
	
	@Override
	public String validateCredentials(String username,String password)
	{
		String isValid;
		/*
		 * boolean isValid = loginRepo.validateLogin(username, password); return
		 * isValid;
		 */	
	
	loginCredentials cred = loginRepo.findUserByCredentials(username, password);
	if(cred==null)
	{
		isValid="false";
	}
	else
	{
		if(cred.getRole().equalsIgnoreCase("admin"))
		{
			isValid="admin";
		}
		else
		{
			isValid="user";
		}
	}
	return isValid;
	}
	
	@Override
	public int updateCredentials(String username,String newpassword)
	{
		int updateValue = loginRepo.updateUserPassword(newpassword, username);
		return updateValue;
	}

}
