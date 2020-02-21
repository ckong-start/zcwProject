package com.scw.project.vo;

import java.io.Serializable;
import java.util.List;

import com.scw.project.bean.TReturn;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProjectDetailsVo implements Serializable{
	private String name;

	private String remark;

	private Long money;// 对拷失败

	private Integer day;

	private Integer memberid;
	// 后台生成的默认数据
	// 创建项目的日期：后台保存项目时的日期就是创建日期
	private String createdate;
	// 0-未审核 1-审核中 2-审核通过 3-审核失败
	private String status;

	// 审核通过后才会有的数据
	private Integer id;
	// 项目审核通过后 发布的日期
	private String deploydate;
	// 用户购买的总金额
	private Long supportmoney;
	// 支持者数量
	private Integer supporter;
	// 完成度
	private Integer completion;
	// 关注者
	private Integer follower;
	//===============项目发起人数据
	private String selfintroduction;

    private String detailselfintroduction;

    private String telphone;

    private String hotline;

    //================回报信息
    private List<TReturn> rtns;
    //=============项目头图
    private String headerImgurl;
    //============项目详情图
    private List<String> detailsImgurls;
    
}
