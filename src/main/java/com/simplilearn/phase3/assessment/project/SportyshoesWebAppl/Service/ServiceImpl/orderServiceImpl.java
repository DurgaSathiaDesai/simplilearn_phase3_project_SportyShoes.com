package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.ServiceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.category;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.orders;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository.ordersRepository;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository.productRepository;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.orderService;


@Service
public class orderServiceImpl implements orderService
{

	@Autowired
	private ordersRepository ordersRepo;
	
	@Autowired
	private productRepository productRepo;

	@Override
	public Page<orders> getAllOrdersByDateAndCategory(LocalDate orderDate, String categoryId,int pageNum) {
		
		int catId = Integer.parseInt(categoryId);
		
		//Page<product> products = null;
		
		int pageSize = 3;
		
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		 //List<Long> prodIdList = productRepo.getProdIdsByCategory(catId);
		//List<Long> prodIdList = ordersRepo.getProdIdsByCategory(catId);
		category cat = productRepo.getCategoryById(catId);
		String catname = cat.getCategoryname();
		return ordersRepo.findOrderByDateAndCategory(orderDate, catname,pageable);
	}

	@Override
	public Page<orders> getAllOrders(int pageNum) 
	{
		//Page<product> products = null;
		
		int pageSize = 3;
		
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		return ordersRepo.findAll(pageable);
	}

}
