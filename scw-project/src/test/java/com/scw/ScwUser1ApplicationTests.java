package com.scw;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.scw.project.bean.TReturn;
import com.scw.project.bean.TTag;
import com.scw.project.mapper.TProjectMapper;
import com.scw.project.mapper.TTagMapper;
import com.scw.project.vo.ProjectRedisStorageVo;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ScwUser1ApplicationTests {

	@Test
	public void contextLoads() throws IOException {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
		//管理员创建的用户，并分配的accessKey并设置了操作OSS权限的账号
		String accessKeyId = "LTAI4FqVZbpgDBu5D3bcVDM8";
		String accessKeySecret = "BusMTZZvkvqrQqkHz2H2WyZrzIZcIs";

		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// 上传文件流。
		InputStream inputStream = new FileInputStream(new File("C:\\Users\\CKong\\Pictures\\Saved Pictures\\ACG.GY_30.jpg"));
		ossClient.putObject("scw-sh-191010", "scw-project/test.gif", inputStream);

		// 关闭OSSClient。
		ossClient.shutdown();
	}
	
	@Autowired
	TProjectMapper projectMapper;
	@Autowired
	TTagMapper tagMapper;
	@Test
	public void test() {
		long count = projectMapper.countByExample(null);
		log.info("自动装配的查询工程个数：{}", count);
		List<TTag> list = tagMapper.selectByExample(null);
		log.info("自动装配的查询标签有：{}", list);
	}

	@Test
	public void test1() {
		ProjectRedisStorageVo vo = new ProjectRedisStorageVo();
		TReturn rtn = new TReturn();
		//List<TReturn> list = new ArrayList<>();
		//vo.getProjectReturns().add(rtn);
		System.out.println(vo.getProjectReturns());
	}
}
