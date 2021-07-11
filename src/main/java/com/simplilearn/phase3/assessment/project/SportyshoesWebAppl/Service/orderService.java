package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.orders;


public interface orderService 
{
	Page<orders> getAllOrders(int pageNum);
	Page<orders> getAllOrdersByDateAndCategory(LocalDate orderDate,String categoryId,int pageNum);
}
