package exer.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import exer.pojo.Employee;

@Repository
public class EmployeeDao extends JdbcDaoSupport{
	/*@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int saveEmployee(Employee employee) {
		String sql = "insert into employee (`name`, `salary`) values(?,?)";
		return jdbcTemplate.update(sql, employee.getName(), employee.getSalary());
	}*/
	
	@Autowired
	public void setDataSource2(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public int saveEmployee(Employee employee) {
		String sql = "insert into employee (`name`, `salary`) values(?,?)";
		return getJdbcTemplate().update(sql, employee.getName(), employee.getSalary());
	}
}
