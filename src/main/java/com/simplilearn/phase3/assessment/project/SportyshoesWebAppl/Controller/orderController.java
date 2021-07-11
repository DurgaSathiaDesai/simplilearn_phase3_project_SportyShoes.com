package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.category;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity.orders;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.orderService;
import com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Service.productService;

@Controller
public class orderController 
{
	@Autowired
	private orderService orderservice;
	
	@Autowired
	private productService productservice;
	
	@RequestMapping("/purchaseReports/{pageNum}")
	public String displayReports(Model model,@PathVariable(name = "pageNum") int pageNum)
	{
		String resultmsg = null;
		
		List<category> categories = productservice.getAllCategories();
		Page<orders> orderPage = orderservice.getAllOrders(pageNum);
		
		List<orders> orderlist = orderPage.getContent();
		if(orderlist.isEmpty())
		{
			resultmsg = "No purchases done !!";
			model.addAttribute("result", resultmsg);
		}
		
		model.addAttribute("categorylist", categories);
		model.addAttribute("orderlist", orderlist);
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", orderPage.getTotalPages());
		//model.addAttribute("comma", " , ");
		model.addAttribute("category", 0);
		model.addAttribute("page",0);
		model.addAttribute("reportFor", "all");
		return "purchaseReports";
	}
	
	@RequestMapping("/purchaseReportsByfilter/{pageNum}")
	public String displayReportsByfilter(Model model,@RequestParam(value="orderdate")String orderdate,@RequestParam(value="category")String category,@PathVariable(name = "pageNum") int pageNum)
	{
		String resultmsg = null;
		String categoryname = null;
		
		LocalDate date = LocalDate.parse(orderdate);
		int catId = Integer.parseInt(category);
		
		List<category> categories = productservice.getAllCategories();
		model.addAttribute("categorylist", categories);
		
		for(category cat : categories)
		{
			if(cat.getCategoryId() == catId)
			{
				categoryname = cat.getCategoryname();
				break;
			}
		}
		Page<orders> orderPage = orderservice.getAllOrdersByDateAndCategory(date, category,pageNum);
		List<orders> orderlist = orderPage.getContent();
		if(orderlist.isEmpty())
		{
			resultmsg = "No purchases for category " + categoryname + " for Date : " + orderdate;
			model.addAttribute("result", resultmsg);
		}
		model.addAttribute("orderlist", orderlist);
		model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", orderPage.getTotalPages());
		//model.addAttribute("comma", " , ");
		model.addAttribute("categoryname", categoryname);
		model.addAttribute("page",1);
		model.addAttribute("reportFor", "filter");
		return "purchaseReports";
	}
}
