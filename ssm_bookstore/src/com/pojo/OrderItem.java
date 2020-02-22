package com.pojo;

import java.math.BigDecimal;

public class OrderItem {
    private Integer id;

    private String name;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private Integer count;

    private String orderId;

    public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(Integer id, String name, BigDecimal price, BigDecimal totalPrice, Integer count, String orderId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.totalPrice = totalPrice;
		this.count = count;
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", name=" + name + ", price=" + price + ", totalPrice=" + totalPrice + ", count="
				+ count + ", orderId=" + orderId + "]";
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }
}