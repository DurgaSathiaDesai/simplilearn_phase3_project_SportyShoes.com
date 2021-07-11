package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="size")
public class size 
{
	@Id
	@GeneratedValue
	@Column(name = "sizeid")
	private int sizeId;
	
	@Column(name = "sizevalue")
	private String sizevalue;
	
	@ManyToMany(mappedBy="psizes")
	private List<product> products = new ArrayList<>();

	public size()
	{
		
	}

	public size(String sizevalue) {
		super();
		this.sizevalue = sizevalue;
	}

	public String getSizevalue() {
		return sizevalue;
	}

	public void setSizevalue(String sizevalue) {
		this.sizevalue = sizevalue;
	}

	public int getSizeId() {
		return sizeId;
	}

	
	public List<product> getProducts() 
	{
		return products;
	}

	public void addProduct(product prod) 
	{
		this.products.add(prod);
	}
	
	public void removeProduct(product prod) 
	{
		this.products.remove(prod);
	}

	@Override
	public String toString() {
		return String.format("%s",sizevalue);
	}

}
