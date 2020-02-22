package test;

import static org.junit.Assert.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import exer2.mapper.KeyMapper;

public class KeyMapperTest {
	static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

	}

	@Test
	public void testQueryKeyById() {
		SqlSession session = sqlSessionFactory.openSession();
		KeyMapper mapper = session.getMapper(KeyMapper.class);
		System.out.println(mapper.queryKeyById(2));
	}

}
