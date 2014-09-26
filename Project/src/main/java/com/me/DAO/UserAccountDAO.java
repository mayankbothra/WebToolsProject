package com.me.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me.pojo.UserAccount;

@Repository
public class UserAccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Transactional
	public void persist(UserAccount userAccount){
		Session session = sessionFactory.openSession();
		session.save(userAccount);
		session.close();
	}
	
	@Transactional
	public UserAccount findUserAccountById(int id){
		Session session = sessionFactory.openSession();
		UserAccount userAccount = (UserAccount)session.get(UserAccount.class, id);
		session.close();
		return userAccount;
	}
	
	public void update(UserAccount userAccount){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.update(userAccount);
		trx.commit();
		session.close();
	}
	
	public void delete(UserAccount userAccount){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.delete(userAccount);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public UserAccount authenticateUser(String username, String password) {
		Session session = sessionFactory.openSession();
		Query query = (Query)session.createQuery("from UserAccount where username = :username");
		query.setParameter("username", username);
		
		List u = query.list();
		UserAccount ua = null;
		
		if(u.size()==0){
			session.close();
			return null;
		}
		else{	
			for (Object o : u) {
				ua = (UserAccount) o;
			}
			if(ua.getPassword().equals(password)){
				session.close();
					return ua;
				}
			else{
				session.close();
				return null;
				}
		}
	}
	
//	@Transactional
//	public void updateStatus(UserAccount userAccount, String status){
//		Session session = sessionFactory.openSession();
//		int id = userAccount.getId();
//		Query query = session.createQuery("update UserAccount set status =:status" + " where id=:id");
//		query.setParameter("id", id);
//		query.setParameter("status", status);
//		query.executeUpdate();
//	}
	
	@Transactional
	public boolean isUserExists(String user){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from UserAccount where username =:user");
		query.setParameter("user", user);
		List u = query.list();
		
		if(u.size() == 0){
			return false;
		}
		else {
			return true;
		}
	}
	
}
