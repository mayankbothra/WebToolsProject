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
import com.me.pojo.OrderDetails;
import com.me.pojo.OrderItem;
import com.me.pojo.Person;

@Repository
public class OrderItemDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Transactional
	public void persist(OrderItem orderItem){
		Session session = sessionFactory.openSession();
		session.save(orderItem);
		session.close();
	}
	
	@Transactional
	public OrderItem findOrderItemById(int id){
		Session session = sessionFactory.openSession();
		OrderItem orderItem = (OrderItem)session.get(OrderItem.class, id);
		session.close();
		return orderItem;
	}
	
	public void update(OrderItem orderItem){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.saveOrUpdate(orderItem);
		trx.commit();
		session.close();
	}
	
	public void delete(OrderItem orderItem){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.delete(orderItem);
		trx.commit();
		session.close();
	}
	
	
	@Transactional
	public ArrayList<OrderItem> getOrderItemByCustomerOrder(OrderDetails order){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from OrderItem where orderDetails =:order");
		query.setParameter("order",order);
		
		List pl = query.list();
		if(pl.size()==0){
			session.close();
			return null;
		}
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		for(Object o : pl){
			OrderItem oi = (OrderItem)o;
			orderItemList.add(oi);
		}
		session.close();
		return orderItemList;	
	}
	
	
	
	@Transactional
	public ArrayList<OrderItem> getCartItemByPerson(Person person){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from OrderItem where person =:person AND itemStatus =:itemStatus");
		query.setParameter("person", person);
		query.setString("itemStatus", "Cart");
		
		List pl = query.list();
		if(pl.size()==0){
			session.close();
			return null;
		}
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		for(Object o : pl){
			OrderItem oi = (OrderItem)o;
			orderItemList.add(oi);
		}
		session.close();
		return orderItemList;	
	}

}
