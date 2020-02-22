package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

/*import mapper.UserMapper;
import exer.mapper.UserMapper;*/
import exer1.mapper.UserMapper;
import pojo.User;

public class UserMapperTest {
	static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
	}

	/*@Test
	public void testQueryUserForMap() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			Map<Integer, User> map = mapper.queryUserForMap();
			Set<Entry<Integer,User>> entrySet = map.entrySet();
			for (Entry<Integer, User> entry : entrySet) {
				System.out.println(entry);
			}
		} finally {
			session.close();
		}
	}
	@Test
	public void testQueryUserById() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = mapper.queryUserById(3);
			System.out.println(user);
		} finally {
			session.close();
		}
	}

	@Test
	public void testSaveUser() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = new User(null, "tghyu", 1);
			mapper.saveUser(user);
			System.out.println(user);
			session.commit();
		} finally {
			session.close();
		}
	}

	@Test
	public void testUpdateUserById() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.updateUserById(new User(4, "ghjkl", 0));
			session.commit();
		} finally {
			session.close();
		}
	}

	@Test
	public void testDeleteUserById() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.deleteUserById(10);
			session.commit();
		} finally {
			session.close();
		}
	}

	@Test
	public void testQueryUsers() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.queryUsers().forEach(System.out::println);
		} finally {
			session.close();
		}
	}*/
	
	/*@Test
	public void testQueryUserById2() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = mapper.queryUserById(4);
			System.out.println(user);
		} finally {
			session.close();
		}
	}
	@Test
	public void testQueryUserByNameOrSex() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			List<User> list = mapper.queryUserByNameOrSex("s", 1);
			list.forEach(System.out::println);
			
		} finally {
			session.close();
		}
	}
	@Test
	public void testQueryUserByMap() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("name", "s");
			paramMap.put("sex", 1);
			
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.queryUserByMap(paramMap).forEach(System.out::println);
			
		} finally {
			session.close();
		}
	}
	@Test
	public void testUpdateUser() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = new User(9, "zsxd", 0);
			mapper.updateUser(user);
			session.commit();
		} finally {
			session.close();
		}
	}
	@Test
	public void testQueryUserLikeName() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.queryUserLikeName("d").forEach(System.out::println);
		} finally {
			session.close();
		}
	}*/
	@Test
	public void testQueryUsersResultMap() throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.queryUsers().forEach(System.out::println);
		} finally {
			session.close();
		}
	}
}
