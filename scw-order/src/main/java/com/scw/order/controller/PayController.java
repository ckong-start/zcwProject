package com.scw.order.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="支付模块")
@RestController
public class PayController {

	@ApiOperation(value="支付宝支付")
	@PostMapping("/order/pay/alipay")
	public String payByAlipay() {
		
		return null;
	}
	
	@ApiOperation(value="微信支付")
	@PostMapping("/order/pay/weixin")
	public String payByWX() {
		
		return null;
	}
}
