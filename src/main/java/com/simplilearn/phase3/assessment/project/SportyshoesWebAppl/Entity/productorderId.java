package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class productorderId implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private orders order;
	@ManyToOne
    private product prod;
	 
	//@Column(name = "sizevalue") 
	private String sizevalue;
	
	public orders getOrder() {
		return order;
	}
	public void setOrder(orders order) {
		this.order = order;
	}
	public product getProd() {
		return prod;
	}
	public void setProd(product prod) {
		this.prod = prod;
	}
	public String getSizevalue() {
		return sizevalue;
	}
	public void setSizevalue(String sizevalue) {
		this.sizevalue = sizevalue;
	}
	 
}
