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
import com.me.pojo.DepotOrderDetails;
import com.me.pojo.Manufacturer;
import com.me.pojo.OrderDetails;
import com.me.pojo.Product;

@Repository
public class DepotOrderDetailsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void persist(DepotOrderDetails depotOrderDetails){
		Session session = sessionFactory.openSession();
		session.save(depotOrderDetails);
		session.close();
	}
	
	@Transactional
	public DepotOrderDetails findDepotOrderDetailsById(int id){
		Session session = sessionFactory.openSession();
		DepotOrderDetails depotOrderDetails = (DepotOrderDetails)session.get(DepotOrderDetails.class, id);
		session.close();
		return depotOrderDetails;
	}
	
	public void update(DepotOrderDetails depotOrderDetails){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.update(depotOrderDetails);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public void delete(DepotOrderDetails depotOrderDetails){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.delete(depotOrderDetails);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public ArrayList<DepotOrderDetails> getDepotOrderDetailsByManufacturer(Manufacturer manufacturer){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from DepotOrderDetails where manufacturer =:manufacturer AND status ='placed'");
		query.setParameter("manufacturer", manufacturer);
		
		List pl = query.list();
		if(pl.size()==0){
			session.close();
			return null;
		}
		ArrayList<DepotOrderDetails> depotOrderList = new ArrayList<DepotOrderDetails>();
		
		for(Object o : pl){
			DepotOrderDetails dep = (DepotOrderDetails)o;
			depotOrderList.add(dep);
		}
		session.close();
		return depotOrderList;	
	}
	
	@Transactional
	public ArrayList<DepotOrderDetails> getDepotOrderDetailsByDepot(Depot depot){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from DepotOrderDetails where depot =:depot");
		query.setParameter("depot", depot);
		
		List pl = query.list();
		if(pl.size()==0){
			session.close();
			return null;
		}
		ArrayList<DepotOrderDetails> depotOrderList = new ArrayList<DepotOrderDetails>();
		
		for(Object o : pl){
			DepotOrderDetails dep = (DepotOrderDetails)o;
			depotOrderList.add(dep);
		}
		session.close();
		return depotOrderList;	
	}
		
}

