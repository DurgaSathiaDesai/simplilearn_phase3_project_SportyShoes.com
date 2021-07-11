package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.category;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.color;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.product;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.size;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.productService;


@Controller
public class productController 
{
	
	@Autowired
	private productService productservice;
	
	@RequestMapping(value= {
			"/products/{pageNum}",
			"*/products/{pageNum}"
	})
	public String displayProducts(Model model,@RequestParam(value="custType", required=false,defaultValue = "all")String custtype,@PathVariable(name = "pageNum") int pageNum)
	{
		String result = null;
		Page<product> productpage = productservice.getAllProducts(custtype,pageNum);
		List<product> products = productpage.getContent();
		if(products.isEmpty())
		{
			result = "No products available for " + custtype;
			model.addAttribute("result",result);
		}
		
		model.addAttribute("productlist", products);
		model.addAttribute("custtype",custtype);
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", productpage.getTotalPages());
		return "productDisplay";
	}
	
	@RequestMapping(value= {
			"/addProduct",
			"*/addProduct"
	})
	public String addProducts(Model model)
	{
		List<category> categories = productservice.getAllCategories();
		model.addAttribute("categorylist", categories);
		List<color> colors = productservice.getAllColors();
		model.addAttribute("colorlist", colors);
		List<size> sizes = productservice.getAllSizes();
		model.addAttribute("sizelist", sizes);
		return "addProduct";
	}
	
	@RequestMapping(value= {
			"/saveProduct",
			"*/saveProduct"
	})
	public String saveProduct(Model model,@RequestParam(value="pname")String productName,@RequestParam(value="custType")String customerType,
										  @RequestParam(value="category")String pcategory,@RequestParam(value="proddesc")String proddescription,
										  @RequestParam(value="color")String pcolor,@RequestParam(value="size")List<String> psize,
										  @RequestParam(value="Imgname")String pImgname,@RequestParam(value="Prodprice")String Productprice,
										  @RequestParam(value="totalStock")String ptotalStock)
	{
		productservice.saveProduct(productName,customerType,pcategory,proddescription,pcolor,psize,pImgname,Productprice,ptotalStock);
		List<category> categories = productservice.getAllCategories();
		model.addAttribute("categorylist", categories);
		List<color> colors = productservice.getAllColors();
		model.addAttribute("colorlist", colors);
		List<size> sizes = productservice.getAllSizes();
		model.addAttribute("sizelist", sizes);
		model.addAttribute("message", "Product added successfully!");
		return "addProduct";
	}
	
	@RequestMapping(value= {
			"/updateProduct/{pageNum}",
			"*/updateProduct/{pageNum}"
	})
	public String updateProducts(Model model,@RequestParam(value="custType", required=false,defaultValue = "all")String custtype,@PathVariable(name = "pageNum") int pageNum)
	{
		String result = null;
		Page<product> productpage = productservice.getAllProductsByStatus(custtype,pageNum);
		List<product> products = productpage.getContent();
		if(products.isEmpty())
		{
			result = "No products available for " + custtype;
			model.addAttribute("result",result);
		}
		
		model.addAttribute("productlist", products);
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", productpage.getTotalPages());
		model.addAttribute("custtype",custtype);
		return "updateProduct";
	}
	
	@RequestMapping(value= {
			"/deleteProduct/{pageNum}",
			"*/deleteProduct/{pageNum}"
	})
	public String deleteProducts(Model model,@RequestParam(value="custType", required=false,defaultValue = "all")String custtype,@PathVariable(name = "pageNum") int pageNum)
	{
		String result = null;
		Page<product> productpage = productservice.getAllProductsByStatus(custtype,pageNum);
		List<product> products = productpage.getContent();
		if(products.isEmpty())
		{
			result = "No products available for " + custtype;
			model.addAttribute("result",result);
		}
		
		model.addAttribute("productlist", products);
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", productpage.getTotalPages());
		model.addAttribute("custtype",custtype);
		
		return "deleteProduct";
	}
	
	@RequestMapping(value= {
			"/processdelete/{pageNum}",
			"*/processdelete/{pageNum}"
	})
	public String processDelete(Model model,@RequestParam(value="productId")String productId,@RequestParam(value="size")String prodsize,@PathVariable(name = "pageNum") int pageNum)
	{
		String result = null;
		productservice.deleteProduct(productId,prodsize);
		Page<product> productpage = productservice.getAllProductsByStatus("all",pageNum);
		List<product> products = productpage.getContent();
		
		if(products.isEmpty())
		{
			result = "No products available!!";
			
		}
		if(prodsize.equalsIgnoreCase("all"))
		{
			result = "Product with Id " + productId + " is deleted successfully";
		}
		else
		{
			size s = productservice.getSizeById(prodsize);
			result = "Size " + s.getSizevalue() + " of product with Id " + productId + " is removed successfully";
		}
		model.addAttribute("productlist", products);
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", productpage.getTotalPages());
		model.addAttribute("custtype","all");
		model.addAttribute("result",result);
		return "deleteProduct";
	}
	
	@RequestMapping(value= {
			"/processupdate",
			"*/processupdate"
	})
	public String processUpdates(Model model,@RequestParam(value="productId")String productId)
	{
		//logger.info("{}",productId);
		String sizeId = "";
		product prod =  productservice.getProductById(productId);
		List<size> psizes = prod.getPsizes();
		//logger.info("{}",sizeId);
		for(int i=0;i<psizes.size();i++)
		{
			sizeId = sizeId + psizes.get(i).getSizeId() + ",";
		}
		//logger.info("{}",sizeId);
		model.addAttribute("product", prod);
		model.addAttribute("sizes", sizeId);
		List<category> categories = productservice.getAllCategories();
		model.addAttribute("categorylist", categories);
		List<color> colors = productservice.getAllColors();
		model.addAttribute("colorlist", colors);
		List<size> sizes = productservice.getAllSizes();
		model.addAttribute("sizelist", sizes);
		//logger.info("{}",prod);
		return "productUpdation";
	}
	
	@RequestMapping(value= {
			"/doProductUpdate/{pageNum}",
			"*/doProductUpdate/{pageNum}"
	})
	public String productUpdation(Model model,@RequestParam(value="productId")String productId,@RequestParam(value="pname")String productName,@RequestParam(value="custType")String customerType,
										  @RequestParam(value="category")String pcategory,@RequestParam(value="proddesc")String proddescription,
										  @RequestParam(value="color")String pcolor,@RequestParam(value="prodsize",required=false)List<String> psize,
										  @RequestParam(value="Imgname")String pImgname,@RequestParam(value="Prodprice")String Productprice,
										  @RequestParam(value="totalStock")String ptotalStock,@RequestParam(value="AvblStock")String pAvailStock,@PathVariable(name = "pageNum")int pageNum)
	{
		String result = null;
		
		productservice.updateProduct(productId,productName,customerType,pcategory,proddescription,pcolor,psize,pImgname,Productprice,ptotalStock,pAvailStock);
		
		Page<product> productpage = productservice.getAllProductsByStatus("all",pageNum);
		List<product> products = productpage.getContent();
		
		if(products.isEmpty())
		{
			result = "No products available!!";
		}
		result = "Product with Id " + productId + " is updated successfully";
		model.addAttribute("productlist", products);
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", productpage.getTotalPages());
		model.addAttribute("custtype","all");
		model.addAttribute("result",result);
		return "updateProduct";
		
	}
}
