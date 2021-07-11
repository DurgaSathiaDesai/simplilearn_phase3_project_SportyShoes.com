package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service;

public interface loginService 
{
	String validateCredentials(String username,String password);
	int updateCredentials(String username,String newpassword);
}
