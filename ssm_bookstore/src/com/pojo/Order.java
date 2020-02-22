package com.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String orderId;

    private Date createTime;

    private BigDecimal totalPrice;

    private Integer status;

    private Integer userId;

    public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String orderId, Date createTime, BigDecimal totalPrice, Integer status, Integer userId) {
		super();
		this.orderId = orderId;
		this.createTime = createTime;
		this.totalPrice = totalPrice;
		this.status = status;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", createTime=" + createTime + ", totalPrice=" + totalPrice + ", status="
				+ status + ", userId=" + userId + "]";
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}