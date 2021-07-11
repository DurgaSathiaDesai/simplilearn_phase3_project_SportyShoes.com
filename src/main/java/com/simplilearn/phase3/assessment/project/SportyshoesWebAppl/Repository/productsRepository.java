package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.product;

public interface productsRepository extends JpaRepository<product, Long> 
{
	@Query("Select p from product p where p.custType = :custType")
	Page<product> getAllProductsByCustomerType(String custType,Pageable pageable);
	
	@Query("Select p from product p where p.status <> :pstatus")
	Page<product> getAllProductsByStatus(String pstatus,Pageable pageable);
	
	@Query("Select p from product p where p.custType = :ctype and p.status <> :pstatus")
	Page<product> getAllProductsByCustomerTypeAndStatus(String ctype,String pstatus,Pageable pageable);
}
