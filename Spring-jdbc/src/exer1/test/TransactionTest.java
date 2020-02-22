package exer1.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exer1.service.TransactionService;
@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionTest {
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;

	
	@Test
	public void test() throws Exception {
		transactionService.multiUpdate();
	}
	
	
	@Test
	public void test1() throws Exception {
		System.out.println(jdbcTemplate);
		System.out.println(dataSource.getConnection());
	}
}
