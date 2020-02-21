package exer.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import exer.dao.EmployeeDao;
import exer.pojo.Employee;


@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void test1() throws Exception {
		System.out.println(dataSource.getConnection());
		System.out.println(jdbcTemplate);
	}

	@Test
	public void test2() throws Exception {
		// 实验2：将id=5的记录的salary字段更新为1300.00
		String sql = "update employee set salary = ? where id = ?";
		int update = jdbcTemplate.update(sql, new BigDecimal(1300), 5);
		System.out.println(update);

	}

	@Test
	public void test3() throws Exception {
		// 实验3：批量插入
		String sql = "insert into employee (`name`, `salary`) values (?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[] {"一条",new BigDecimal(359)});
		batchArgs.add(new Object[] {"二条",new BigDecimal(3590)});
		batchArgs.add(new Object[] {"三条",new BigDecimal(35900)});
		int[] batchUpdate = jdbcTemplate.batchUpdate(sql, batchArgs);
		for (int i : batchUpdate) {
			System.out.println(i);
		}
		//int update = jdbcTemplate.update(sql, "国哥",new BigDecimal(10000000));
		//System.out.println(update);
	}
	
	@Test
	public void test4() throws Exception {
		// 实验4：查询id=5的数据库记录，封装为一个Java对象返回
		String sql = "select `id`, `name`, `salary` from employee where id = ?";
		
		Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Employee>(Employee.class), 5);
		System.out.println(employee);
	}
	
	@Test
	public void test5() throws Exception {
		// 实验5：查询salary>4000的数据库记录，封装为List集合返回(集合中每个都是JavaBean)
		String sql = "select * from employee where salary > ?";
		List<Employee> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class), new BigDecimal(4000));
		list.forEach(System.out::println);
	}
	
	@Test
	public void test6() throws Exception {
		// 实验6：查询最大salary
		String sql = "select max(salary) from employee";
		BigDecimal max = jdbcTemplate.queryForObject(sql, BigDecimal.class);
		System.out.println(max);
	}
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Test
	public void test7() throws Exception {
		//实验7：使用带有具名参数的SQL语句插入一条员工记录，并以Map形式传入参数值
		String sql = "insert into employee (`name`, `salary`) values (:name, :salary)";
		Map<String, Object> paramSource = new HashMap<>();
		paramSource.put("name","具名参数插一条");
		paramSource.put("salary", new BigDecimal(1234));
		int i = namedParameterJdbcTemplate.update(sql, paramSource);
		System.out.println(i);
	}
	
	@Test
	public void test8() throws Exception {
		// 实验8：重复实验7，以SqlParameterSource形式传入参数值
		String sql = "insert into employee (`name`, `salary`) values (:name, :salary)";
		Employee employee = new Employee(null, "具名参数二", new BigDecimal(1234211));
		int i = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(employee));
		System.out.println(i);
	}
	
	@Autowired
	private EmployeeDao employeeDao;
	@Test
	public void test9() throws Exception {
		//实验9：创建Dao，自动装配JdbcTemplate对象
		int i = employeeDao.saveEmployee(new Employee(null, "dao插入", new BigDecimal(2000)));
		System.out.println(i);
	}
	
	@Test
	public void test10() throws Exception {
		//实验10：通过继承JdbcDaoSupport创建JdbcTemplate的Dao
		int i = employeeDao.saveEmployee(new Employee(null, "继承后dao插入", new BigDecimal(6700)));
		System.out.println(i);
	}
}
