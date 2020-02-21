package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookDao;
import com.dao.UserDao;

@Service
public class TransactionService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	
	public void multiUpdate() {
		userDao.updateUser();
		
		bookDao.updateBook();
	}
}
