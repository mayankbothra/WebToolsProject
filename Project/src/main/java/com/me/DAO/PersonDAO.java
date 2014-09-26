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

import com.me.pojo.Manufacturer;
import com.me.pojo.Person;
import com.me.pojo.UserAccount;

@Repository
public class PersonDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Transactional
	public void persist(Person person){
		Session session = sessionFactory.openSession();
		session.save(person);
		session.close();
	}
	
	@Transactional
	public Person findPersonById(int id){
		Session session = sessionFactory.openSession();
		Person person = (Person)session.get(Person.class, id);
		session.close();
		return person;
	}
	
	public void update(Person person){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.update(person);
		trx.commit();
		session.close();
	}
	
	public void delete(Person person){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.delete(person);
		trx.commit();
		session.close();	
	}
	
	@Transactional
	public ArrayList<Person> getPersonListByManufacturer(Manufacturer manufacturer){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Person where manufacturer =:manufacturer");
		query.setParameter("manufacturer", manufacturer);
		
		List pl = query.list();
		if(pl.size()==0){
			session.close();
			return null;
		}
		ArrayList<Person> personList = new ArrayList<Person>();
		
		for(Object o : pl){
			Person person = (Person)o;
			personList.add(person);
		}
		session.close();
		return personList;	
	}


}
