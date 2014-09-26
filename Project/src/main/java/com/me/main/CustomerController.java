package com.me.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.DAO.AddressDAO;
import com.me.DAO.DepotDAO;
import com.me.DAO.DepotOrderDetailsDAO;
import com.me.DAO.ManufacturerDAO;
import com.me.DAO.OrderDetailsDAO;
import com.me.DAO.OrderItemDAO;
import com.me.DAO.PersonDAO;
import com.me.DAO.ProductDAO;
import com.me.DAO.StockItemDAO;
import com.me.DAO.UserAccountDAO;
import com.me.pojo.Manufacturer;
import com.me.pojo.OrderDetails;
import com.me.pojo.OrderItem;
import com.me.pojo.Person;
import com.me.pojo.Product;
import com.me.pojo.UserAccount;

@Controller
@RequestMapping("/")
public class CustomerController {
	
	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private DepotDAO depotDAO;
	@Autowired
	private DepotOrderDetailsDAO depotOrderDetailsDAO;
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	@Autowired
	private OrderDetailsDAO orderDetailsDAO;
	@Autowired
	private OrderItemDAO orderItemDAO;
	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private StockItemDAO stockItemDAO;
	@Autowired
	private UserAccountDAO userAccountDAO;
	@Autowired
    private JavaMailSender mailSender;

	
	@RequestMapping(value = "/shopByManufacturer.htm", method = RequestMethod.GET)
	public ModelAndView mApprovalHome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		
		int id = Integer.parseInt(request.getParameter("mid"));
		Manufacturer manufacturer = manufacturerDAO.findManufacturerById(id);
		ArrayList<Product> productList = productDAO.getProductListByManufacturer(manufacturer);
		request.setAttribute("manufacturer", manufacturer);
		
		HttpSession session = request.getSession();
		Person p = (Person)session.getAttribute("person");
		
		if(p!= null){
			ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
			session.setAttribute("manufacturerList", manufacturerList);
			return new ModelAndView("shopByManufacturer2", "pList", productList);
		}
		else{
			ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
			request.setAttribute("manufacturerList", manufacturerList);
			return new ModelAndView("shopByManufacturer1", "pList", productList);
		}
	}
	
	
	@RequestMapping(value = "/shopByProductType.htm", method = RequestMethod.GET)
	public ModelAndView shopByProductType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		
		String type = request.getParameter("type");
		ArrayList<Product> productList = productDAO.getProductListByProductType(type);
		request.setAttribute("type", type);
		
		HttpSession session = request.getSession();
		Person p = (Person)session.getAttribute("person");
		
		if(p!= null){
			ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
			session.setAttribute("manufacturerList", manufacturerList);
			return new ModelAndView("shopByProductType2", "pList", productList);
		}
		else{
			ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
			request.setAttribute("manufacturerList", manufacturerList);
			return new ModelAndView("shopByProductType1", "pList", productList);
		}
	}
	
	
	@RequestMapping(value = "/addToCart.htm", method = RequestMethod.POST)
	public ModelAndView addToCart(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Person person = (Person)session.getAttribute("person");
		
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		session.setAttribute("manufacturerList", manufacturerList);
		
		if(person != null){                                
            int pid = Integer.parseInt(request.getParameter("productId"));
            Product product = productDAO.findProductById(pid);        
            
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int total = quantity * product.getPrice();
            
            List<OrderItem> orderItemList = orderItemDAO.getCartItemByPerson(person);
            OrderItem orderItem = null;
            
            if(orderItemList != null) {
            	for(OrderItem oi: orderItemList){
                    if(oi.getProduct().getProductName().equals(product.getProductName())){
                            orderItem = oi;
                            break;
                    }
            	}
                    
            	if(orderItem != null){
                        orderItem.setQuantity(quantity + orderItem.getQuantity());
                        orderItem.setTotal(total + orderItem.getTotal());
                        orderItemDAO.update(orderItem);
            	}
                else{
                                OrderItem o = new OrderItem();
                                o.setPerson(person);
                                o.setProduct(product);
                                o.setQuantity(quantity);
                                o.setItemStatus("Cart");
                                o.setTotal(total);
                                o.setOrderDetails(null);
                            
                                orderItemDAO.persist(o);
                }
            }
            else{
            	ArrayList<OrderItem> temp = new ArrayList<OrderItem>();
            	OrderItem o = new OrderItem();
                o.setPerson(person);
                o.setProduct(product);
                o.setQuantity(quantity);
                o.setItemStatus("Cart");
                o.setTotal(total);
                o.setOrderDetails(null);
                temp.add(o);
                orderItemDAO.persist(o);
            }
            
			return new ModelAndView("addToCartSuccess");
		}
		else{
			
			return new ModelAndView("loginPage3");
		}
	}
	
	
	@RequestMapping(value = "/myCart.htm", method = RequestMethod.GET)
	public ModelAndView myCart(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Person person = (Person)session.getAttribute("person");
		
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		session.setAttribute("manufacturerList", manufacturerList);
		
		ArrayList<OrderItem> orderItemList = orderItemDAO.getCartItemByPerson(person);
		
		return new ModelAndView("myCart", "orderItemList", orderItemList);
		
	}
	
	
	@RequestMapping(value = "/deleteFromCart.htm", method = RequestMethod.POST)
	public ModelAndView removeFromCart(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session  = request.getSession();
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		session.setAttribute("manufacturerList", manufacturerList);
		
		int oid = Integer.parseInt(request.getParameter("productId"));
        OrderItem oi = orderItemDAO.findOrderItemById(oid);
        
        orderItemDAO.delete(oi);
            
        return new ModelAndView("addToCartSuccess");
	}
	
	@RequestMapping(value = "/checkout.htm", method = RequestMethod.POST)
	public ModelAndView checkout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Person person = (Person)session.getAttribute("person");
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		session.setAttribute("manufacturerList", manufacturerList);
		
		ArrayList<OrderItem> orderItemList = orderItemDAO.getCartItemByPerson(person);
		
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setManufacturer(null);
		for(OrderItem oi : orderItemList){
			oi.setItemStatus("Order Placed");
//			oi.setOrderDetails(orderDetails);
		}
				
		orderDetails.setOrderItemList(orderItemList);
		orderDetails.setPerson(person);
		orderDetails.setStatus("Order Placed");
		int total = 0;
		
		for(OrderItem o : orderItemList){
			total = total + o.getTotal();
			o.setOrderDetails(orderDetails);
		}
		orderDetails.setTotalCost(total);
		
//		orderDetailsDAO.persist(orderDetails);
		
		
		
		List<OrderDetails> orderDetailsList = orderDetailsDAO.getOrdersByPerson(person);
		
		if(orderDetailsList != null){
			orderDetailsList.add(orderDetails);
		}
		else {
			List<OrderDetails> temp = new ArrayList<OrderDetails>();
			temp.add(orderDetails);
			orderDetailsList = temp;
		}
		
		person.setOrderList(orderDetailsList);
		
		personDAO.update(person);
		
        return new ModelAndView("orderPlaced");
	}
	
	
	@RequestMapping(value="myOrders.htm", method=RequestMethod.GET)
    protected ModelAndView myOrders(HttpServletRequest request,
                    HttpServletResponse response) throws Exception {                        
            
            HttpSession session = request.getSession();
            Person person = (Person)session.getAttribute("person");
            
            ArrayList<OrderDetails> orderList = orderDetailsDAO.getOrdersByPerson(person);

            HashMap<OrderDetails, List<OrderItem>> orderDetails = new HashMap<OrderDetails, List<OrderItem>>();

            if(orderList != null){
            	for(OrderDetails o: orderList){
            		ArrayList<OrderItem> oilist = orderItemDAO.getOrderItemByCustomerOrder(o);
            		orderDetails.put(o, oilist);
        }
}
 
            request.setAttribute("orderDetails", orderDetails);
             return new ModelAndView("myOrders");
            
    }
	
}
