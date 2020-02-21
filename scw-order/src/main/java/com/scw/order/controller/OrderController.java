package com.scw.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scw.common.bean.AppResponse;
import com.scw.order.bean.TOrder;
import com.scw.order.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="订单模块")
@RestController
public class OrderController {

	@ApiOperation(value="取消订单")
	@PostMapping("/order/cancle")
	public String cancleOrder() {
		
		return null;
	}
	
	@Autowired
	OrderService orderService;
	
	//创建订单的方法    复杂类型数据在远程调用传递时，作为方法参数 必须使用@RequestBody标注
	@ApiOperation(value="创建订单")
	@PostMapping("/order/create")
	public AppResponse<Object> createOrder(@RequestBody TOrder order) {
		try {
			orderService.createOrder(order);
		} catch (Exception e) {
			return AppResponse.fail("30000", "订单重复提交");
		}
		return AppResponse.ok("订单创建成功");
	}
	
	//更新订单的方法
	@ApiOperation(value="修改订单的状态")
	@GetMapping("/order/updateOrderStatus")
	public AppResponse<Object> updateOrderStatus(@RequestParam("ordernum") String ordernum,@RequestParam("status")String status){
		orderService.updateOrderStatus(ordernum, status);
		return AppResponse.ok("更新成功");
	}
	
	@ApiOperation(value="立即付款")
	@PostMapping("/order/pay")
	public String doPay() {
		
		return null;
	}
}
