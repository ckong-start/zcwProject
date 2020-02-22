package test;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import exer.mapper.UserMapper;
import pojo.User;

public class UserMapperTest {
	static SqlSessionFactory sqlSessionFactory;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
	}
	
	@Test
	public void testQueryUsersLikeName() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.queryUsersLikeName("mi").forEach(System.out::println);
			
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testQueryUsersByMap() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			Map<String, Object> map = new HashMap<>();
			map.put("name", "admin");
			map.put("sex", 0);
			mapper.queryUsersByMap(map).forEach(System.out::println);
			
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testQueryUsersByNameOrSex() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.queryUsersByNameOrSex("zsxd", 1).forEach(System.out::println);
			
		} finally {
			session.close();
		}
	}

	@Test
	public void testQueryUserById() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			System.out.println(mapper.queryUserById(1));
			
		} finally {
			session.close();
		}
	}

}
