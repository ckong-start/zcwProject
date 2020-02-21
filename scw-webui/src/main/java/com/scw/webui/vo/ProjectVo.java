package com.scw.webui.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProjectVo implements Serializable{

	private Integer id;
	private String headerImgUrl;
	private String name;
	private String remark;
}
