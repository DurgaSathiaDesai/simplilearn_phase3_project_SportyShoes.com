package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class category 
{
	@Id
	@GeneratedValue
	@Column(name = "categoryid")
	private int categoryId;
	
	@Column(name = "categoryname")
	private String categoryname;

	@OneToMany(mappedBy="pcategory")
	private List<product> products;
	
	public category()
	{
		
	}
	public category(String categoryname)
	{
		super();
		this.categoryname = categoryname;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public int getCategoryId() {
		return categoryId;
	}
	@Override
	public String toString() {
		return "category [categoryId=" + categoryId + ", categoryname=" + categoryname + "]";
	}
	
}
