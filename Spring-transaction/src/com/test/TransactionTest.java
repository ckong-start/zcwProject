package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.TransactionService;
@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionTest {
	@Autowired
	private TransactionService transactionService;
	@Test
	public void test1() throws Exception {
		transactionService.multiUpdate();
	}
	
}
