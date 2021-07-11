package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity;

import java.math.BigDecimal;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="productorders")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.order",
        joinColumns = @JoinColumn(name = "orderid")),
    @AssociationOverride(name = "primaryKey.prod",
        joinColumns = @JoinColumn(name = "productid")),
    @AssociationOverride(name = "primaryKey.sizevalue",
    	joinColumns = @JoinColumn(name = "sizevalue"))})
public class productOrders 
{
	// composite-id key
	@EmbeddedId
    private productorderId primaryKey = new productorderId();
	
	/*
	 * @Column(name = "sizevalue") private String sizevalue;
	 */
	@Column(name = "productname")
	private String productname;
	
	@Column(name = "customertype")
	private String custType;
	
	@Column(name = "categoryname")
	private String categoryname;
	
	@Column(name = "color")
	private String colorname;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "qty")
	private int quantity;

	public productorderId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(productorderId primaryKey) {
		this.primaryKey = primaryKey;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
    @Transient
    public orders getOrder() {
        return getPrimaryKey().getOrder();
    }
 
    public void setOrder(orders order) {
        getPrimaryKey().setOrder(order);
    }
    
    @Transient
    public product getProduct() {
        return getPrimaryKey().getProd();
    }
    public void setProduct(product prod) {
        getPrimaryKey().setProd(prod);
    }

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getColorname() {
		return colorname;
	}

	public void setColorname(String colorname) {
		this.colorname = colorname;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

    
	/*
	 * public String getSizevalue() { return sizevalue; }
	 * 
	 * public void setSizevalue(String sizevalue) { this.sizevalue = sizevalue; }
	 */
 
}

