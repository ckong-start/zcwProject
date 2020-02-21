package exer1.service;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import exer1.dao.BookDao;
import exer1.dao.UserDao;
@Service
public class TransactionService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void multiUpdate(){
		bookDao.updateBook();
		
		int i = 12 / 0;
		
		userDao.updateUser();
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED, timeout=3)
	public void multiUpdate1() throws FileNotFoundException {
		bookDao.updateBook();
		int i = 12;
		if (i == 12) {
			
			throw new FileNotFoundException();
		}
		
		userDao.updateUser();
	}
	
/*	@Transactional(noRollbackFor=ArithmeticException.class)
	public void multiUpdate() {
		bookDao.updateBook();
		
		int i = 10 / 0;
		
		userDao.updateUser();
	}
	@Transactional(noRollbackForClassName="java.lang.ArithmeticException")
	public void multiUpdate1() {
		bookDao.updateBook();
		
		int i = 10 / 0;
		
		userDao.updateUser();
	}*/
}
