package com.me.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me.pojo.Address;

@Repository
public class AddressDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void persist(Address address){
		Session session = sessionFactory.openSession();
		session.save(address);
		session.close();
	}
	
	@Transactional
	public Address findAddressById(int id){
		Session session = sessionFactory.openSession();
		Address address = (Address)session.get(Address.class, id);
		session.close();
		return address;
	}
	
	public void update(Address address){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.update(address);
		trx.commit();
		session.close();
	}
	

	public void delete(Address address){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.delete(address);
		trx.commit();
		session.close();
	}
}
