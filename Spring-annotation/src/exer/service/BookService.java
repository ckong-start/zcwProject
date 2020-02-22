package exer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import exer.dao.BookDao;

@Service
public class BookService {
	/**
	 * 会自动的到SpringIOC容器中查找BookService，并注入自动注入赋值操作<br/>
	 * 	1 @Autowired默认是按类型查找并注入<br/>
	 *  2 @Autowired如果按类型找到多个。接着按属性名做为id继续查找并注入<br/>
	 *  3 使用注解@Qualifier来指定id值 查找并注入（它会忽略默认的属性名）
	 *  4 通过设置required属性值为false允许找不到，值为null。
	 */
	//@Qualifier("bookDao")
	@Autowired(required=false)
	private BookDao bookDao;

	@Override
	public String toString() {
		return "BookService [bookDao=" + bookDao + "]";
	}
	
	
}
