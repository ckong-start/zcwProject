package test;

import static org.junit.Assert.fail;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import exer1.mapper.KeyMapper;

public class KeyMapperTest {
	static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
	}
	
	@Test
	public void testQueryKeyByIdForTwoStep() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			KeyMapper mapper = session.getMapper(KeyMapper.class);
			System.out.println(mapper.queryKeyByIdForTwoStep(1).getName());
			System.out.println(mapper.queryKeyByIdForTwoStep(2).getLock().getName());
		} finally {
			session.close();
		}
	}

	@Test
	public void testQueryKeyById() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			KeyMapper mapper = session.getMapper(KeyMapper.class);
			System.out.println(mapper.queryKeyById(2));
		} finally {
			session.close();
		}
	}

}
