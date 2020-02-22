package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pojo.Book;

@Repository
public class BookDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int saveBook(Book book) {
		String sql = "insert into t_book (`name`,`author`,`price`,`sales`,`stock`) values(?,?,?,?,?)";
		return jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(),
				book.getStock());
	}

	public int updateBook(Book book) {
		String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=? where id=?";
		return jdbcTemplate.update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(),
				book.getStock(), book.getId());
	}

	public int deleteBook(Integer id) {
		String sql = "delete from t_book where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	public Book queryBookById(Integer id) {
		String sql = "select `name`,`author`,`price`,`sales`,`stock`,`id` from t_book where id = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), id);
	}

	public List<Book> queryBooks() {
		String sql = "select `name`,`author`,`price`,`sales`,`stock`,`id` from t_book";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
	}
}
