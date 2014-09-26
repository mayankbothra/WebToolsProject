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

import com.me.pojo.Depot;
import com.me.pojo.Manufacturer;
import com.me.pojo.Person;

@Repository
public class DepotDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void persist(Depot depot){
		Session session = sessionFactory.openSession();
		session.save(depot);
		session.close();
	}
	
	@Transactional
	public Depot findDepotById(int id){
		Session session = sessionFactory.openSession();
		Depot depot = (Depot)session.get(Depot.class, id);
		session.close();
		return depot;
	}
	
	public void update(Depot depot){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.update(depot);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public void delete(Depot depot){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.delete(depot);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public ArrayList<Depot> getDepotListByManufacturer(Manufacturer manufacturer){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Depot where manufacturer =:manufacturer");
		query.setParameter("manufacturer", manufacturer);
		
		List dl = query.list();
		if(dl.size()==0){
			session.close();
			return null;
		}
		ArrayList<Depot> depotList = new ArrayList<Depot>();
		
		for(Object o : dl){
			Depot depot= (Depot)o;
			depotList.add(depot);
		}
		session.close();
		return depotList;	
	}
	
	@Transactional
	public Depot getDepotByPerson(Person person){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Depot where person =:person");
		query.setParameter("person", person);
		
		List dl = query.list();
		if(dl.size()==0){
			session.close();
			return null;
		}
		Depot depot = null;
		
		for(Object o : dl){
			depot= (Depot)o;
			break;
		}
		session.close();
		return depot;	
	}
}
