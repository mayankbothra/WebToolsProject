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
import com.me.pojo.Product;
import com.me.pojo.StockItem;

@Repository
public class StockItemDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Transactional
	public void persist(StockItem stockItem){
		Session session = sessionFactory.openSession();
		session.save(stockItem);
		session.close();
	}
	
	@Transactional
	public void saveOrUpdate(StockItem stockItem){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.saveOrUpdate(stockItem);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public StockItem findStockItemById(int id){
		Session session = sessionFactory.openSession();
		StockItem stockItem = (StockItem)session.get(StockItem.class, id);
		session.close();
		return stockItem;
	}
	
	@Transactional
	public void update(StockItem stockItem){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.update(stockItem);
		trx.commit();
		session.close();
	}
	
	@Transactional
	public void delete(StockItem stockItem){
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		session.delete(stockItem);
		trx.commit();
		session.close();
	}
	
	
	@Transactional
	public StockItem getStockItemListByDepotAndProduct(Depot depot, Product product){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from StockItem where depot =:depot AND product =:product");
		query.setParameter("depot", depot);
		query.setParameter("product", product);
		
		List si = query.list();
		if(si.size()==0){
			session.close();
			return null;
		}
		
		StockItem stockItem = null;
		
		for(Object o : si){
			stockItem = (StockItem)o;
			break;
		}
		session.close();
		return stockItem;	
	}
	
	public ArrayList<StockItem> returnStockItemListOfDepot(Depot depot){                
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from StockItem where depot =:depot");
        query.setParameter("depot", depot);
        
        List sil = query.list();                
        if(sil.size() == 0){
                session.close();
                return null;
        }
        else{
                ArrayList<StockItem> templist = new ArrayList<StockItem>();
                for(Object o : sil){
                        StockItem stockItem = (StockItem)o;
                        templist.add(stockItem);
                }        
                session.close();
                return templist;                        
        }
}
	
	public ArrayList<StockItem> returnStockItemListOfDepotLessThanThreshold(Depot depot){                
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from StockItem where depot =:depot AND quantity <= 10");
        query.setParameter("depot", depot);
        
        List sil = query.list();                
        if(sil.size() == 0){
                session.close();
                return null;
        }
        else{
                ArrayList<StockItem> templist = new ArrayList<StockItem>();
                for(Object o : sil){
                        StockItem stockItem = (StockItem)o;
                        templist.add(stockItem);
                }        
                session.close();
                return templist;                        
        }
}
	
}
