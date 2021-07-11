package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.category;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.color;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.product;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.size;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.stock;



@Repository
@Transactional
public class productRepository 
{
	@Autowired
	EntityManager em;
	
	public List<category> getAllCategories()
	{
		return em.createQuery("Select c from category c", category.class).getResultList();
	}
	
	public List<color> getAllColors()
	{
		return em.createQuery("Select c from color c", color.class).getResultList();
	}
	
	public List<size> getAllSizes()
	{
		return em.createQuery("Select s from size s", size.class).getResultList();
	}
	
	public category getCategoryById(int categoryId) 
	{ 
		TypedQuery<category> query =em.createQuery("Select c from category c where c.categoryId = :catId",category.class); 
		query.setParameter("catId", categoryId); 
		category prodcat = query.getSingleResult(); 
		return prodcat; 
		
	}
	
	public int getStockId(Long productId)
	{
		TypedQuery<stock> query =em.createQuery("Select s from stock s where s.productstock.productId = :prodId",stock.class); 
		query.setParameter("prodId", productId); 
		stock prodstock = query.getSingleResult(); 
		return prodstock.getStockId(); 
		
	}
	
	public product getProductById(String productId)
	{
		long prodId = Long.parseLong(productId);
		return em.find(product.class, prodId);
	}
	
	public size getSizeById(int psizeId) 
	{ 
		TypedQuery<size> query =em.createQuery("Select s from size s where s.sizeId = :sId",size.class); 
		query.setParameter("sId", psizeId); 
		size prodsize = query.getSingleResult(); 
		return prodsize; 
		
	}
	
	public void saveProduct(String productName,String customerType,String pcategory,String proddescription,String pcolor,List<String> psize,String pImgname,BigDecimal Productprice,String ptotalStock)
	{
		product prod = new product();
		prod.setProductname(productName);
		prod.setCustType(customerType);
		int pcatval = Integer.parseInt(pcategory);
		category pcat = em.find(category.class, pcatval);
		
		prod.setPcategory(pcat);
		prod.setDescription(proddescription);
		
		int pclr = Integer.parseInt(pcolor);
		color pcol = em.find(color.class, pclr);
		prod.setPcolor(pcol);
		prod.setImageName(pImgname);
		prod.setPrice(Productprice);
		prod.setStatus("Instock");
		em.persist(prod);
		for(String ps : psize)
		{
			int sizeId = Integer.parseInt(ps);
			size prodsize = em.find(size.class, sizeId);
			prod.addSize(prodsize);
			size s = new size();
			s.addProduct(prod);
			em.persist(prod);
		}
		
		int pstk = Integer.parseInt(ptotalStock);
		stock pstock = new stock(pstk,pstk);
		pstock.setProductstock(prod);
		em.persist(pstock);
	}
	
	public void updateProduct(String productId,String productName,String customerType,String pcategory,String proddescription,String pcolor,List<String> psize,String pImgname,BigDecimal Productprice,String ptotalStock,String AvailStock)
	{
		long prodId = Long.parseLong(productId);
		product prod = em.find(product.class, prodId);
		prod.setProductname(productName);
		prod.setCustType(customerType);
		
		int pcatval = Integer.parseInt(pcategory);
		category pcat = em.find(category.class, pcatval);
		prod.setPcategory(pcat);
		
		prod.setDescription(proddescription);
		int pclr = Integer.parseInt(pcolor);
		color pcol = em.find(color.class, pclr);
		prod.setPcolor(pcol);
		prod.setImageName(pImgname);
		prod.setPrice(Productprice);
		prod.setStatus("Instock");
		em.merge(prod);
		
		int stockId = getStockId(prodId);
		stock stk = em.find(stock.class, stockId);
		int Totalstk = Integer.parseInt(ptotalStock);
		stk.setTotalStock(Totalstk);
	
		int Availstk = Integer.parseInt(AvailStock);
		stk.setAvailStock(Availstk);
		em.merge(stk);
		
		 if(psize.isEmpty())
		 {
			 em.merge(prod);
		 }
		 else
		 {
			 prod.getPsizes().forEach(size ->
			  { 
				  size.getProducts().remove(prod);
			  });
			 em.merge(prod);
			 prod.getPsizes().clear();
			 for(String ps : psize) 
			 { 
				 if(ps.isBlank()==false)
				 {
					 int sizeId = Integer.parseInt(ps); 
					 size prodsize =em.find(size.class, sizeId); 
					 prod.addSize(prodsize); 
					 size s = new size();
					 s.addProduct(prod); 
					 em.merge(prod); 
				 }
					 
			 }	 
		 }	 
	}
	
	public void deleteProduct(String productId)
	{
		long prodId = Long.parseLong(productId);
		product prod = em.find(product.class, prodId);
		prod.getPsizes().forEach(size ->
		{ 
			size.getProducts().remove(prod);
		});
		 prod.getPsizes().clear();
		prod.setStatus("removed");
		em.merge(prod);
	}
	
	public void deleteProductBySize(String productId,int prodsize)
	{
		long prodId = Long.parseLong(productId);
		size psize = em.find(size.class, prodsize);
		product prod = em.find(product.class, prodId);
		prod.getPsizes().remove(psize);
		//prod.setStatus("removed");
		em.merge(prod);
		em.flush();
		if(prod.getPsizes().isEmpty())
		{
			prod.setStatus("removed");
			em.merge(prod);
		}
	}
	
}
