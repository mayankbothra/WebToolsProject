package com.me.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.me.pojo.UserAccount;

@Controller
@RequestMapping("/")
public class SiteController {
	
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

	
	@RequestMapping(value = "/mApprovalHome.htm", method = RequestMethod.GET)
	public ModelAndView mApprovalHome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("mApprovalHome");
	}
	
	@RequestMapping(value = "/newManufacturerRequest.htm", method = RequestMethod.POST)
	public ModelAndView newManu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap<Manufacturer, Person> mList = manufacturerDAO.getNewManufacturerRequestsList();
		
		request.setAttribute("mList", mList);
		
		return new ModelAndView("newManufacturerRequest");
	}
	
	@RequestMapping(value = "/viewAllManufacturers.htm", method = RequestMethod.POST)
	public ModelAndView viewAll(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap<Manufacturer, Person> mList = manufacturerDAO.getManufacturerMap();
		
		request.setAttribute("mList", mList);
		return new ModelAndView("viewAllManufacturers");
	}
	
	@RequestMapping(value = "/processRequest.htm", method = RequestMethod.POST)
	public ModelAndView processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int manuId = (Integer.parseInt(request.getParameter("select")));
		String status = request.getParameter("status");
		
		Manufacturer manufacturer = manufacturerDAO.findManufacturerById(manuId);
		UserAccount userAccount = null;
		Person person = null;
		
		ArrayList<Person> pList = personDAO.getPersonListByManufacturer(manufacturer);
		for(Person p: pList){
			UserAccount ua = p.getUserAccount();
				if(ua.getRole().equalsIgnoreCase("MA")){
					person = p;
					userAccount = ua;
					break;
				}
		}
		userAccount.setStatus(status);
		userAccountDAO.update(userAccount);	
		
		String toEmail = person.getEmail();
		String subject = "";
		String message = "";
		
		if(status.equals("active")) {
		subject = "Account Activated";
		message = "Hello " + person.getfName() + " " + person.getlName() + ",\n\nCongratulations. Your account has been successfully verified and activated. You can now login to market your products.";
		}
		else{
			subject = "Account Rejected";
			message = "Hello " + person.getfName() + " " + person.getlName() + ",\n\nWe are sorry. Due to some verification criteria, your account request has been rejected. Contact administrator on phone.";
		}
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(toEmail);
		email.setSubject(subject);
		email.setText(message);
		
		mailSender.send(email);
		
		return new ModelAndView("manufacturerRequestProcessed");
	}
	
	@RequestMapping(value = "/viewManufacturerDetails.htm", method = RequestMethod.POST)
	public ModelAndView viewManufacturerDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int manuId = (Integer.parseInt(request.getParameter("select")));
		
		Manufacturer manufacturer = manufacturerDAO.findManufacturerById(manuId);
		Person person = null;
		
		ArrayList<Person> pList = personDAO.getPersonListByManufacturer(manufacturer);
		for(Person p: pList){
			UserAccount ua = p.getUserAccount();
				if(ua.getRole().equalsIgnoreCase("MA")){
					person = p;
					break;
				}
		}
		
		request.setAttribute("person", person);
		return new ModelAndView("viewManufacturerDetails", "manufacturer", manufacturer);
	}
	
	
	@RequestMapping(value = "/newOrderRequest.htm", method = RequestMethod.POST)
	public ModelAndView newOrderRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap<OrderDetails, Person> orderList = orderDetailsDAO.getNewOrderRequestsList();
		
		request.setAttribute("orderList", orderList);
		
		return new ModelAndView("newOrderRequest");
	}
	
	@RequestMapping(value = "/viewOrderDetails.htm", method = RequestMethod.POST)
	public ModelAndView viewOrderDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int oId = Integer.parseInt(request.getParameter("select"));
		OrderDetails order = orderDetailsDAO.findOrderDetailsById(oId);
		
		ArrayList<OrderItem> orderItemList = orderItemDAO.getOrderItemByCustomerOrder(order);
		
		request.setAttribute("oId", oId);
		
		return new ModelAndView("viewOrderDetails", "orderItemList", orderItemList);
	}
	
	
	@RequestMapping(value = "/processOrder.htm", method = RequestMethod.POST)
	public ModelAndView processOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		int oId = Integer.parseInt(request.getParameter("oId"));
		
		OrderDetails order = orderDetailsDAO.findOrderDetailsById(oId);
		

		ArrayList<OrderItem> orderItemList = orderItemDAO.getOrderItemByCustomerOrder(order);
		order.setStatus("Order Processed");
		orderDetailsDAO.update(order);
		
		for(OrderItem oi : orderItemList){
            
            
            Manufacturer manufacturer = oi.getProduct().getManufacturer();
            
            OrderDetails orderDetails = new OrderDetails();
            ArrayList<OrderItem> temp = new ArrayList<OrderItem>();
            orderDetails.setManufacturer(manufacturer);
            orderDetails.setOrderItemList(temp);
            orderDetails.setPerson(oi.getPerson());
            orderDetails.setStatus("Order Processed");
            orderDetails.setTotalCost(oi.getTotal());
            oi.setItemStatus("Order Processed");
            oi.setOrderDetails(orderDetails);
            
            List<OrderDetails> orderList = orderDetailsDAO.getOrdersByManufacturer(manufacturer);
            
            if(orderList != null){
                    orderList.add(orderDetails);
            }
            else{
                    ArrayList<OrderDetails> tempList = new ArrayList<OrderDetails>();
                    tempList.add(orderDetails);
                    orderList = tempList;
            }
            
            manufacturer.setOrderDetailsList(orderList);
            manufacturerDAO.update(manufacturer);
            orderItemDAO.update(oi);
             
		}
		
		return new ModelAndView("orderProcessed");
	}
	
}
