package com.me.DAO;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.me.pojo.Product;
import com.me.pojo.UserAccount;

@Repository
public class ManufacturerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void persist(Manufacturer manufacturer){
		Session session = sessionFactory.openSession();
		session.save(manufacturer);
		session.close();
	}
	
	@Transactional
	public Manufacturer findManufacturerById(int id){
		Session session = sessionFactory.openSession();
		Manufacturer manufacturer = (Manufacturer)session.get(Manufacturer.class, id);
		session.close();
		return manufacturer;
	}
	
	public void update(Manufacturer manufacturer){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.update(manufacturer);
		trx.commit();
		session.close();
		
	}
	
	public void delete(Manufacturer manufacturer){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.delete(manufacturer);
		trx.commit();
		session.close();
	}
	
	
	@Transactional
	public HashMap<Manufacturer, Person> getManufacturerMap(){
		
		Session session = sessionFactory.openSession();
		Query query = (Query)session.createQuery("from Manufacturer");
		
		List m = query.list();
		
		if(m.size()==0){
			session.close();
			return null;
		}
		
		ArrayList<Manufacturer> manuList = new ArrayList<Manufacturer>();
		
		for(Object o : m) {
			Manufacturer man = (Manufacturer)o;
			manuList.add(man);
		}
		
		HashMap<Manufacturer,Person> newList = new HashMap<Manufacturer, Person>();
		
		for(Manufacturer m1 : manuList){
			List<Person> pList = m1.getPersonList();
			for(Person p: pList){
				UserAccount ua = p.getUserAccount();
				if(ua.getRole().equalsIgnoreCase("MA")){
					newList.put(m1, p);
					break;
				}
			}
		}
		session.close();
		return newList;
	}
	
	@Transactional
	public HashMap<Manufacturer, Person> getNewManufacturerRequestsList(){
		
		Session session = sessionFactory.openSession();
		Query query = (Query)session.createQuery("from Manufacturer");
		
		List m = query.list();
		
		if(m.size()==0){
			session.close();
			return null;
		}
		
		ArrayList<Manufacturer> manuList = new ArrayList<Manufacturer>();
		
		for(Object o : m) {
			Manufacturer man = (Manufacturer)o;
			manuList.add(man);
		}
			
		HashMap<Manufacturer,Person> newList = new HashMap<Manufacturer, Person>();
		
		for(Manufacturer m1 : manuList){
			List<Person> pList = m1.getPersonList();
			for(Person p: pList){
				UserAccount ua = p.getUserAccount();
				if(ua.getRole().equalsIgnoreCase("MA") && (ua.getStatus().equalsIgnoreCase("disabled"))){
					newList.put(m1, p);
					break;
				}
			}
		}
		session.close();
		return newList;		
	}
	
	@Transactional
	public ArrayList<Manufacturer> getListOfManufacturer(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Manufacturer");
		
		List pl = query.list();
		if(pl.size()==0){
			session.close();
			return null;
		}
		ArrayList<Manufacturer> manufacturerList = new ArrayList<Manufacturer>();
		
		for(Object o : pl){
			Manufacturer manufacturer = (Manufacturer)o;
			manufacturerList.add(manufacturer);
		}
		session.close();
		return manufacturerList;	
	}
	

}
