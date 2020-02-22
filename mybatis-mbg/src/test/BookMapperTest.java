package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import mapper.BookMapper;
import pojo.Book;
import pojo.BookExample;
import pojo.BookExample.Criteria;

public class BookMapperTest {
	static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
	}

	@Test
	public void testCountByExample() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			BookMapper mapper = session.getMapper(BookMapper.class);
			BookExample bookExample = new BookExample();
			// 创建一个查询条件
			Criteria criteria = bookExample.createCriteria();
			// 添加条件,价格在10到50之间
			criteria.andPriceBetween(new BigDecimal(10), new BigDecimal(50));
			// 添加or条件
			Criteria criteria2 = bookExample.createCriteria();
			criteria2.andSalesBetween(50, 100);
			bookExample.or(criteria2);
			System.out.println(mapper.countByExample(bookExample));
		} finally {
			session.close();
		}
	}

	@Test
	public void testDeleteByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertSelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByExampleSelective() {

		SqlSession session = sqlSessionFactory.openSession();
		try {
			BookMapper bookMapper = session.getMapper(BookMapper.class);

			Book book = new Book(3, "国哥的书", "国哥", null, null, null);

			BookExample bookExample = new BookExample();

			bookExample.createCriteria().andIdEqualTo(3);

			/**
			 * 第一个参数是更新的值 <br/>
			 * 第二个参数是更新的条件
			 */
			bookMapper.updateByExampleSelective(book, bookExample);
			// 如果是以下方法，null值也更新
			//bookMapper.updateByExample(book, bookExample);
			session.commit();
		} finally {
			session.close();
		}
	}

	@Test
	public void testUpdateByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKey() {
		fail("Not yet implemented");
	}

}
