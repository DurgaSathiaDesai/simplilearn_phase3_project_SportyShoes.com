package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.orders;


public interface ordersRepository extends JpaRepository<orders, Long> 
{
	@Transactional
	
	@Query("select o from orders o join productOrders po on o.orderId = po.primaryKey.order.orderId where o.orderDate = :orddate and po.categoryname = :catname")
	Page<orders> findOrderByDateAndCategory(LocalDate orddate,String catname,Pageable pageable);
}
