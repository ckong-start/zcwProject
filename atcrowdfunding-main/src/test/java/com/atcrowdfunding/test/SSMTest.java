package com.atcrowdfunding.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atcrowdfunding.bean.TAdmin;
import com.atcrowdfunding.mapper.TAdminMapper;

@ContextConfiguration(locations = { "classpath:spring/spring-beans.xml", "classpath:spring/spring-mybatis.xml",
		"classpath:spring/spring-tx.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SSMTest {
	@Autowired
	TAdminMapper adminMapper;

	@Test
	public void test() {
		Logger logger = LoggerFactory.getLogger(getClass());
		long startTime = System.currentTimeMillis();
		logger.debug("开始执行方法：查询的管理员id={}，当前时间为：{}", 1, startTime);
		TAdmin admin = adminMapper.selectByPrimaryKey(1);
		logger.info("方法执行的结果为：{}，耗时为：{}", admin, System.currentTimeMillis() - startTime);
		logger.warn("warn...");
		logger.error("error...");
		
	}
}
