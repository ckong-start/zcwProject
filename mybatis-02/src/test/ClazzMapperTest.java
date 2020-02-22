package test;

import static org.junit.Assert.fail;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import exer2.mapper.ClazzMapper;

public class ClazzMapperTest {
	static SqlSessionFactory sqlSessionFactory;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
		
	}
	
	@Test
	public void testQueryClazzByIdForTwoStep() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ClazzMapper mapper = session.getMapper(ClazzMapper.class);
			System.out.println(mapper.queryClazzByIdForTwoStep(2).getName());
		} finally {
			session.close();
		}
	}

	@Test
	public void testQueryClazzByIdForSimple() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ClazzMapper mapper = session.getMapper(ClazzMapper.class);
			System.out.println(mapper.queryClazzByIdForSimple(2));
			//mapper.queryClazzByIdForSimple(2).getStus().forEach(System.out::println);
		} finally {
			session.close();
		}
	}

}
