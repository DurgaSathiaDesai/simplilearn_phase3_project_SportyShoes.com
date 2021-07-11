package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.category;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.color;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.product;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.size;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository.productRepository;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository.productsRepository;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.productService;



@Service
public class productServiceImpl implements productService 
{
	@Autowired
	private productRepository productRepo;
	
	@Autowired
	private productsRepository productsRepo;

	@Override
	public Page<product> getAllProducts(String custType,int pageNum) 
	{
		Page<product> products = null;
		
		int pageSize = 3;
		
		 Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		 
		if(custType.equalsIgnoreCase("all"))
		{
			products = productsRepo.findAll(pageable);
		}
		else
		{
			products = productsRepo.getAllProductsByCustomerType(custType,pageable);
		}
		return products;
	}

	@Override
	public Page<product> getAllProductsByStatus(String custType,int pageNum) 
	{
		Page<product> products = null;
		int pageSize = 3;
		String status = "removed";
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		if(custType.equalsIgnoreCase("all"))
		{
			products = productsRepo.getAllProductsByStatus(status, pageable);
		}
		else
		{
			products = productsRepo.getAllProductsByCustomerTypeAndStatus(custType,status, pageable);
		}
		return products;
	}
	
	@Override
	public List<category> getAllCategories() 
	{
		return productRepo.getAllCategories();
	}

	@Override
	public List<color> getAllColors() 
	{
		return productRepo.getAllColors();
	}

	@Override
	public List<size> getAllSizes() 
	{
		return productRepo.getAllSizes();
	}

	@Override
	public void saveProduct(String productName,String customerType,String pcategory,String proddescription,String pcolor,List<String> psize,String pImgname,String Productprice,String ptotalStock) 
	{
		BigDecimal price = new BigDecimal(Productprice);
		productRepo.saveProduct(productName,customerType,pcategory,proddescription,pcolor,psize,pImgname,price,ptotalStock);
		
	}

	@Override
	public product getProductById(String productId) 
	{
		product prod = productRepo.getProductById(productId);
		return prod;
	}

	@Override
	public void updateProduct(String productId, String productName, String customerType, String pcategory,
			String proddescription, String pcolor, List<String> psize, String pImgname, String Productprice,
			String ptotalStock, String pAvailStock) 
	{
		BigDecimal price = new BigDecimal(Productprice);
		productRepo.updateProduct(productId,productName,customerType,pcategory,proddescription,pcolor,psize,pImgname,price,ptotalStock,pAvailStock);
	}

	@Override
	public void deleteProduct(String productId,String prodsize) 
	{
		if(prodsize.equalsIgnoreCase("all"))
		{
			productRepo.deleteProduct(productId);
		}
		else
		{
			int psize = Integer.parseInt(prodsize);
			productRepo.deleteProductBySize(productId,psize);
		}
	}

	@Override
	public category getCategoryById(int categoryId)
	{
		return productRepo.getCategoryById(categoryId);
	}

	@Override
	public size getSizeById(String sizeId) 
	{
		int psizeId = Integer.parseInt(sizeId);
		return productRepo.getSizeById(psizeId);
	}

}
