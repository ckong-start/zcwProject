package com.scw.webui.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.scw.common.bean.AppResponse;
import com.scw.webui.bean.TOrder;

@FeignClient(value="SCW-ORDER")
public interface OrderServiceFeignClient {

	@PostMapping("/order/create")
	public AppResponse<Object> createOrder(@RequestBody TOrder order);
	
	//更新订单的方法
	@GetMapping("/order/updateOrderStatus")
	public AppResponse<Object> updateOrderStatus(@RequestParam("ordernum") String ordernum,@RequestParam("status")String status);
}
