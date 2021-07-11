package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.loginCredentials;


@Repository
public interface userloginRepository extends CrudRepository<loginCredentials, Long>
{
	/*
	 * @Query("SELECT u FROM loginCredentials u WHERE u.username = ?1 and u.password = ?2 and u.role = 'admin'"
	 * )
	 */
	
	@Query("SELECT u FROM loginCredentials u WHERE u.username = ?1 and u.password = ?2") 
	loginCredentials findUserByCredentials(String username,String password);
	 
	
	@Modifying
	@Transactional
	@Query("update loginCredentials u set u.password = :password where u.username = :name")
	int updateUserPassword(String password, String name);
}
