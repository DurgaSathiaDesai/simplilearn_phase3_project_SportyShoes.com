package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.category;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.color;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.product;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.size;



public interface productService 
{
	Page<product> getAllProducts(String custType,int pageNum);
	Page<product> getAllProductsByStatus(String custType,int pageNum);
	product getProductById(String productId);
	List<category> getAllCategories();
	List<color> getAllColors();
	List<size> getAllSizes();
	void saveProduct(String productName,String customerType,String pcategory,String proddescription,String pcolor,List<String> psize,String pImgname,String Productprice,String ptotalStock);
	
	void updateProduct(String productId,String productName,String customerType,String pcategory,String proddescription,String pcolor,List<String> psize,String pImgname,String Productprice,String ptotalStock,String pAvailStock);
	
	void deleteProduct(String productId,String prodsize);
	category getCategoryById(int categoryId); 
	size getSizeById(String sizeId); 
}
