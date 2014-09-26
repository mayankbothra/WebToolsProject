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
import com.me.pojo.OrderDetails;
import com.me.pojo.Person;
import com.me.pojo.UserAccount;

@Repository
public class OrderDetailsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void persist(OrderDetails orderDetails){
		Session session = sessionFactory.openSession();
		session.save(orderDetails);
		session.close();
	}
	
	@Transactional
	public OrderDetails findOrderDetailsById(int id){
		Session session = sessionFactory.openSession();
		OrderDetails orderDetails = (OrderDetails)session.get(OrderDetails.class, id);
		session.close();
		return orderDetails;
	}
	
	public void update(OrderDetails orderDetails){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.update(orderDetails);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public void delete(OrderDetails orderDetails){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.delete(orderDetails);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public HashMap<OrderDetails, Person> getNewOrderRequestsList(){
		
		Session session = sessionFactory.openSession();
		Query query = (Query)session.createQuery("from OrderDetails where status =:status");
		query.setString("status", "Order Placed");
		List or = query.list();
		
		if(or.size()==0){
			session.close();
			return null;
		}
		
		ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
		
		for(Object o : or) {
			OrderDetails ord = (OrderDetails)o;
			orderList.add(ord);
		}
			
		HashMap<OrderDetails,Person> newList = new HashMap<OrderDetails, Person>();
		
		for(OrderDetails order : orderList){
			Person person = order.getPerson();
			newList.put(order, person);
		}
		session.close();
		return newList;		
	}
	
	@Transactional
	public ArrayList<OrderDetails> getOrdersByPerson(Person person){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from OrderDetails where person =:person");
		query.setParameter("person", person);
		List p = query.list();
		
		if(p.size() == 0){
			session.close();
			return null;
		}
		
		ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
		
		for(Object o : p){
			OrderDetails ord = (OrderDetails)o;
			orderList.add(ord);
		}
		session.close();
		return orderList;
		
	}
	
	
	@Transactional
	public ArrayList<OrderDetails> getOrdersByManufacturer(Manufacturer manufacturer){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from OrderDetails where manufacturer =:manufacturer");
		query.setParameter("manufacturer", manufacturer);
		List p = query.list();
		
		if(p.size() == 0){
			session.close();
			return null;
		}
		
		ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
		
		for(Object o : p){
			OrderDetails ord = (OrderDetails)o;
			orderList.add(ord);
		}
		session.close();
		return orderList;
		
	}
	
	
	@Transactional
	public ArrayList<OrderDetails> getOrdersByManufacturerAndStatus(Manufacturer manufacturer){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from OrderDetails where manufacturer =:manufacturer AND status =:status");
		query.setParameter("manufacturer", manufacturer);
		query.setString("status", "Order Processed");
		
		List p = query.list();
		
		if(p.size() == 0){
			session.close();
			return null;
		}
		
		ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
		
		for(Object o : p){
			OrderDetails ord = (OrderDetails)o;
			orderList.add(ord);
		}
		session.close();
		return orderList;
		
	}
	

}
