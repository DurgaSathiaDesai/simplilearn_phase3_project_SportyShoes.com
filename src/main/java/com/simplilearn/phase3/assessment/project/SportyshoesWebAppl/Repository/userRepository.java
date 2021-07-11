package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.userInfo;

public interface userRepository extends JpaRepository<userInfo, Long> 
{
	@Query("SELECT u FROM userInfo u WHERE u.firstname = ?1 and u.lastname = ?2")
	Page<userInfo> findUserByName(String firstname,String lastname,Pageable pageable);
	
	@Query("SELECT u FROM userInfo u WHERE u.firstname = ?1 and u.lastname = ?2 and u.userCredentials.role = ?3")
	Page<userInfo> findUserByNameAndRole(String firstname,String lastname,String role,Pageable pageable);
	
	@Query("SELECT u FROM userInfo u WHERE u.firstname = ?1 and u.userCredentials.role = ?2")
	Page<userInfo> findUserByFirstNameAndRole(String firstname,String role,Pageable pageable);
	
	@Query("SELECT u FROM userInfo u WHERE u.firstname = ?1")
	Page<userInfo> findUserByFirstName(String firstname,Pageable pageable);
	
	@Query("SELECT u FROM userInfo u WHERE u.lastname = ?1 and u.userCredentials.role = ?2")
	Page<userInfo> findUserByLastNameAndRole(String lastname,String role,Pageable pageable);
	
	@Query("SELECT u FROM userInfo u WHERE u.lastname = ?1")
	Page<userInfo> findUserByLastName(String lastname,Pageable pageable);
	
	@Query("SELECT u FROM userInfo u WHERE u.userCredentials.role = ?1") 
	Page<userInfo> findUserByRole(String role,Pageable pageable);
}
