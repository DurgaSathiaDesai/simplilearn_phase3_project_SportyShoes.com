package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class orders 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "orderid")
	private long orderId;
	
	@Column(name = "orderdate")
	private LocalDate orderDate;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private userInfo user;
	
	@Column(name = "shippingaddr")
	private String shippingAddr;
	
	@Column(name = "totalqty")
	private int totalQty;
	
	@Column(name = "totalamount")
	private double amount;
	
	@Column(name = "orderstatus")
	private String orderStatus;

	
	  @OneToMany(mappedBy = "primaryKey.order", cascade = CascadeType.ALL) private
	  List<productOrders> productorders = new ArrayList<productOrders>();
	 
	
		/*
		 * @ManyToMany
		 * 
		 * @JoinTable(name="product_orders", joinColumns=@JoinColumn(name="orderid"),
		 * inverseJoinColumns=@JoinColumn(name="productid") ) private
		 * List<orderedProducts> orderproducts = new ArrayList<>();
		 */
	 
	
	public orders()
	{
		
	}
	
	public orders(LocalDate orderDate, userInfo user, String shippingAddr, int totalQty, double amount,
			String orderStatus) {
		super();
		this.orderDate = orderDate;
		this.user = user;
		this.shippingAddr = shippingAddr;
		this.totalQty = totalQty;
		this.amount = amount;
		this.orderStatus = orderStatus;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public userInfo getUser() {
		return user;
	}

	public void setUser(userInfo user) {
		this.user = user;
	}

	public String getShippingAddr() {
		return shippingAddr;
	}

	public void setShippingAddr(String shippingAddr) {
		this.shippingAddr = shippingAddr;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public long getOrderId() {
		return orderId;
	}
	
	
	 
	
	  public List<productOrders> getProductOrders() { return productorders; }
	  
	  public void setProductOrders(List<productOrders> productOrders) {
	  this.productorders = productOrders; }
	 

	@Override
	public String toString() {
		return "orders [orderId=" + orderId + ", orderDate=" + orderDate + ", user=" + user + ", shippingAddr="
				+ shippingAddr + ", totalQty=" + totalQty + ", amount=" + amount + ", orderStatus=" + orderStatus + "]";
	}

	
	
}
