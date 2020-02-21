package com.scw.webui.vo;

import lombok.Data;

@Data
public class OrderCreateInfoVo {
	//address-收货地址、invoice-是否需要发票：0-不需要、1-需要、invoictitle-发票抬头、remark-备注、rtncount-回报数量；
	private String address;
	private String invoice;
	private String invoictitle;
	private String remark;
	private Integer rtncount;
}
