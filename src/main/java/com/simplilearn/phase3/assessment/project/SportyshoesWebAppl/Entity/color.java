package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="color")
public class color 
{
	@Id
	@GeneratedValue
	@Column(name = "colorid")
	private int colorId;
	
	@Column(name = "colorname")
	private String colorname;
	
	@OneToMany(mappedBy="pcolor")
	private List<product> products;

	public color()
	{
		
	}

	public color(String colorname) {
		super();
		this.colorname = colorname;
	}

	public String getColorname() {
		return colorname;
	}

	public void setColorname(String colorname) {
		this.colorname = colorname;
	}

	public int getColorId() {
		return colorId;
	}

	@Override
	public String toString() {
		return "color [colorId=" + colorId + ", colorname=" + colorname + "]";
	}
	
}
