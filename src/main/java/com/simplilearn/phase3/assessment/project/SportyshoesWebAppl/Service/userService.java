package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service;


import org.springframework.data.domain.Page;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.userInfo;

public interface userService 
{
	Page<userInfo> getAllUsers(String role,int pageNum);
	Page<userInfo> getUserByName(String firstname,String lastname,String role,int pageNum);
	Page<userInfo> getUserByFirstName(String firstname,String role,int pageNum);
	Page<userInfo> getUserByLastName(String lastname,String role,int pageNum);
}
