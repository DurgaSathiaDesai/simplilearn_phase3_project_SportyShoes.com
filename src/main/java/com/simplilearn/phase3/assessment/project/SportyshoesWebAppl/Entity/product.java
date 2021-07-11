package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class product 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "productid")
	private long productId;
	
	@Column(name = "productname")
	private String productname;

	@Column(name = "pdescription")
	private String description;
	
	@Column(name = "customertype")
	private String custType;
	
	@Column(name = "imagename")
	private String imageName;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "pstatus")
	private String status;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="categoryid")
	private category pcategory;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="colorid")
	private color pcolor;
	
	@ManyToMany
	@JoinTable(name="product_sizes",
	  joinColumns=@JoinColumn(name="productid"),
	  inverseJoinColumns=@JoinColumn(name="sizeid")
	  )
	private List<size> psizes = new ArrayList<>();
	
	@OneToOne(mappedBy="productstock")
	//@JoinColumn(name="stockid")
	private stock pstock;
	
	
	  @OneToMany(mappedBy = "primaryKey.prod", cascade = CascadeType.ALL) private
	  List<productOrders> productOrders = new ArrayList<productOrders>();
	 
	
	public product()
	{
		
	}

	public product(String productname, String description, String custType, String imageName, BigDecimal price,
			String status, category pcategory, color pcolor, stock pstock) {
		super();
		this.productname = productname;
		this.description = description;
		this.custType = custType;
		this.imageName = imageName;
		this.price = price;
		this.status = status;
		this.pcategory = pcategory;
		this.pcolor = pcolor;
		this.pstock = pstock;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public category getPcategory() {
		return pcategory;
	}

	public void setPcategory(category pcategory) {
		this.pcategory = pcategory;
	}

	public color getPcolor() {
		return pcolor;
	}

	public void setPcolor(color pcolor) {
		this.pcolor = pcolor;
	}

	public stock getPstock() {
		return pstock;
	}

	public void setPstock(stock pstock) {
		this.pstock = pstock;
	}

	public long getProductId() {
		return productId;
	}

	public List<size> getPsizes() {
		return psizes;
	}
	
	public void addSize(size s)
	{
		this.psizes.add(s);
	}
	
	public void removeSize(size s)
	{
		this.psizes.remove(s);
	}
	
	public void removeSizes(List<size> prodsizes)
	{
		this.psizes.removeAll(prodsizes);
	}

	
	
	  public List<productOrders> getProductOrders() { return productOrders; }
	  
	  public void setProductOrders(List<productOrders> productOrders) {
	  this.productOrders = productOrders; }
	 

	@Override
	public String toString() {
		return "product [productId=" + productId + ", productname=" + productname + ", description=" + description
				+ ", custType=" + custType + ", imageName=" + imageName + ", price=" + price + ", status=" + status
				+ ", pcategory=" + pcategory + ", pcolor=" + pcolor + ", psizes=" + psizes + ", pstock=" + pstock + "]";
	}
	
}
