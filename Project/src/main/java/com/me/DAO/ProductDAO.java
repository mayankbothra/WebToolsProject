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
import com.me.pojo.Product;

@Repository
public class ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Transactional
	public void persist(Product product){
		Session session = sessionFactory.openSession();
		session.save(product);
		session.close();
	}
	
	@Transactional
	public Product findProductById(int id){
		Session session = sessionFactory.openSession();
		Product product = (Product)session.get(Product.class, id);
		session.close();
		return product;
	}
	
	@Transactional
	public void update(Product product){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.update(product);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public void delete(Product product){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.delete(product);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public ArrayList<Product> getProductListByManufacturer(Manufacturer manufacturer){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Product where manufacturer =:manufacturer");
		query.setParameter("manufacturer", manufacturer);
		
		List pl = query.list();
		if(pl.size()==0){
			session.close();
			return null;
		}
		ArrayList<Product> productList = new ArrayList<Product>();
		
		for(Object o : pl){
			Product product = (Product)o;
			productList.add(product);
		}
		session.close();
		return productList;	
	}
	
	@Transactional
	public ArrayList<Product> getProductListByProductType(String type){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Product where productType =:type");
		query.setParameter("type", type);
		
		List pl = query.list();
		if(pl.size()==0){
			session.close();
			return null;
		}
		ArrayList<Product> productList = new ArrayList<Product>();
		
		for(Object o : pl){
			Product product = (Product)o;
			productList.add(product);
		}
		session.close();
		return productList;	
	}
	
	
	@Transactional
	public ArrayList<Product> getAllProduct(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Product");
		
		List pl = query.list();
		if(pl.size()==0){
			session.close();
			return null;
		}
		ArrayList<Product> productList = new ArrayList<Product>();
		
		for(Object o : pl){
			Product product = (Product)o;
			productList.add(product);
		}
		session.close();
		return productList;	
	}

}
