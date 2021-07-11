package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="stock")
public class stock 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "stockid")
	private int stockId;
	
	@Column(name = "totalstock")
	private int totalStock;

	@Column(name = "Availablestock")
	private int availStock;
	
	@OneToOne(optional = false)
	@JoinColumn(name="productid")
	private product productstock;
	
	public stock()
	{
		
	}

	public stock(int totalStock, int availStock) {
		super();
		this.totalStock = totalStock;
		this.availStock = availStock;
		//this.productstock = productstock;
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public int getAvailStock() {
		return availStock;
	}

	public void setAvailStock(int availStock) {
		this.availStock = availStock;
	}

	public int getStockId() {
		return stockId;
	}

	public product getProductstock() {
		return productstock;
	}

	public void setProductstock(product productstock) {
		this.productstock = productstock;
	}

	@Override
	public String toString() {
		return "stock [stockId=" + stockId + ", totalStock=" + totalStock + ", availStock=" + availStock
				+ ", productstock=" + productstock + "]";
	}

}
