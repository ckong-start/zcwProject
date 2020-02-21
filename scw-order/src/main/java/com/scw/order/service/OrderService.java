package com.scw.order.service;

import com.scw.order.bean.TOrder;

public interface OrderService {
	
	void createOrder(TOrder order);
	
	void updateOrderStatus(String ordernum, String status);

	
}
