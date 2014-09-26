package com.me.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.me.pojo.Address;
import com.me.pojo.Depot;
import com.me.pojo.Manufacturer;
import com.me.pojo.OrderDetails;
import com.me.pojo.OrderItem;
import com.me.pojo.Person;
import com.me.pojo.Product;
import com.me.pojo.UserAccount;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/main.htm", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		session.invalidate();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		
		return new ModelAndView("home");
	}

	
	@RequestMapping(value = "/loginPageLink.htm", method = RequestMethod.GET)
	protected ModelAndView loginPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		
		Cookie[] cookies = request.getCookies();
		boolean flag = false;
		
		if(cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("username")) {
				flag = true;
				break;
			}
		}
		}

		if (cookies == null || flag == false) {
			
			return new ModelAndView("loginPage", "useraccount", new UserAccount());
		}

		else {
			String username = "";
			String password = "";

			for (int i = 0; i < cookies.length; i++) {
				Cookie c1 = cookies[i];
				if (c1.getName().equals("username")) {
					username = c1.getValue();
				} else if (c1.getName().equals("password")) {
					password = c1.getValue();
				}
			}
			
			UserAccount userAccount = new UserAccount();
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			userAccount.setRePassword(password);
			
			return cookieLogin(userAccount, request, response);

		}
			
	}
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	protected ModelAndView login(@ModelAttribute("useraccount") @Valid UserAccount userAccount,BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		if(result.hasErrors()){
			ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
			request.setAttribute("manufacturerList", manufacturerList);
			return new ModelAndView("loginPage");
		}
		else {
		String username = userAccount.getUsername();
		String password = userAccount.getRePassword();
		
		UserAccount ua = userAccountDAO.authenticateUser(username, password);
		
		if(ua != null){
		
			if (request.getParameter("rememberme") != null
					&& request.getParameter("rememberme").equals("remember")) {

				Cookie cookie1 = new Cookie("username", username);
				cookie1.setMaxAge(120);
				Cookie cookie2 = new Cookie("password", password);
				cookie2.setMaxAge(120);
				response.addCookie(cookie1);
				response.addCookie(cookie2);
			}
			
			Person p = ua.getPerson();
			HttpSession session = request.getSession();
			
			if(ua.getStatus().equalsIgnoreCase("active")){
				if(ua.getRole().equals("MAM")){
					
					session.setAttribute("person", p);
					return new ModelAndView("mApprovalHome");
				}
				else if(ua.getRole().equals("MA")){
					Manufacturer man = p.getManufacturer();
					session.setAttribute("manufacturer", man);
					session.setAttribute("person", p);
					return new ModelAndView("mAdminHome");
				}
				else if(ua.getRole().equals("PM")){
					
					Manufacturer man = p.getManufacturer();
					session.setAttribute("manufacturer", man);
					session.setAttribute("person", p);
					
					return new ModelAndView("productManagerHome");
				}
				else if(ua.getRole().equals("SM")){
					
					Manufacturer man = p.getManufacturer();
					session.setAttribute("manufacturer", man);
					session.setAttribute("person", p);
					return new ModelAndView("stockManagerHome");
				}
				else if(ua.getRole().equals("DM")){
					Manufacturer man = p.getManufacturer();
					Depot depot = depotDAO.getDepotByPerson(p);
					session.setAttribute("manufacturer", man);
					session.setAttribute("person", p);
					session.setAttribute("depot", depot);
					return new ModelAndView("depotManagerHome");
				}
				
				else if(ua.getRole().equals("OPM")){
					session.setAttribute("person", p);
					return new ModelAndView("oProcessingHome");
				}
				else{
					ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
					session.setAttribute("manufacturerList", manufacturerList);
					session.setAttribute("person", p);
					return new ModelAndView("customerHome");
				}
			}
			else {
				ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
				request.setAttribute("manufacturerList", manufacturerList);
				return new ModelAndView("loginPage2");
			}
		}
		else{
			ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
			request.setAttribute("manufacturerList", manufacturerList);
			return new ModelAndView("loginPage1");
		}
	}
	}
	
	protected ModelAndView cookieLogin(@ModelAttribute("useraccount") @Valid UserAccount userAccount, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		String username = userAccount.getUsername();
		String password = userAccount.getRePassword();
		
		UserAccount ua = userAccountDAO.authenticateUser(username, password);
		
		if(ua !=null){
			Person p = ua.getPerson();
			HttpSession session = request.getSession();
			
			if(ua.getStatus().equalsIgnoreCase("active")){
				if(ua.getRole().equals("MAM")){
					
					session.setAttribute("person", p);
					return new ModelAndView("mApprovalHome");
				}
				else if(ua.getRole().equals("MA")){
					Manufacturer man = p.getManufacturer();
					session.setAttribute("manufacturer", man);
					session.setAttribute("person", p);
					return new ModelAndView("mAdminHome");
				}
				else if(ua.getRole().equals("PM")){
					
					Manufacturer man = p.getManufacturer();
					session.setAttribute("manufacturer", man);
					session.setAttribute("person", p);
					
					return new ModelAndView("productManagerHome");
				}
				else if(ua.getRole().equals("SM")){
					
					Manufacturer man = p.getManufacturer();
					session.setAttribute("manufacturer", man);
					session.setAttribute("person", p);
					return new ModelAndView("stockManagerHome");
				}
				else if(ua.getRole().equals("DM")){
					Manufacturer man = p.getManufacturer();
					Depot depot = depotDAO.getDepotByPerson(p);
					session.setAttribute("manufacturer", man);
					session.setAttribute("person", p);
					session.setAttribute("depot", depot);
					return new ModelAndView("depotManagerHome");
				}
				
				else if(ua.getRole().equals("OPM")){
					session.setAttribute("person", p);
					return new ModelAndView("oProcessingHome");
				}
				else{
					session.setAttribute("person", p);
					return new ModelAndView("customerHome");
				}
			}
			else {
				ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
				request.setAttribute("manufacturerList", manufacturerList);
				return new ModelAndView("loginPage2");
			}
		}
		else{
			ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
			request.setAttribute("manufacturerList", manufacturerList);
			return new ModelAndView("loginPage1");
		}
	}
	
	
	@RequestMapping(value = "/myHome.htm", method = RequestMethod.GET)
	public ModelAndView myHome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		HttpSession session = request.getSession();
		Person p = (Person)session.getAttribute("person");
		UserAccount ua = p.getUserAccount();
		
		if(ua !=null){
			
			if(ua.getStatus().equalsIgnoreCase("active")){
				if(ua.getRole().equals("MAM")){
					
					return new ModelAndView("mApprovalHome");
				}
				else if(ua.getRole().equals("MA")){
					
					return new ModelAndView("mAdminHome");
				}
				else if(ua.getRole().equals("PM")){
					
					return new ModelAndView("productManagerHome");
				}
				else if(ua.getRole().equals("SM")){

					return new ModelAndView("stockManagerHome");
				}
				else if(ua.getRole().equals("DM")){

					return new ModelAndView("depotManagerHome");
				}
				
				else if(ua.getRole().equals("OPM")){

					return new ModelAndView("oProcessingHome");
				}
				else{
					
					return new ModelAndView("customerHome");
				}
			}
			else {
				ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
				request.setAttribute("manufacturerList", manufacturerList);
				return new ModelAndView("loginPage2");
			}
		}
		else{
			ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
			request.setAttribute("manufacturerList", manufacturerList);
			return new ModelAndView("loginPage1");
		}
	}
	
	@RequestMapping(value = "/viewOrderPdf.htm", method = RequestMethod.GET)
	public ModelAndView billPdf(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String output = ServletRequestUtils.getStringParameter(request, "output");
		
		HttpSession session = request.getSession();
        Person person = (Person)session.getAttribute("person");
        
        ArrayList<OrderDetails> orderList = orderDetailsDAO.getOrdersByPerson(person);
        Map<String,String> billData = new HashMap<String,String>();

        if(orderList != null){
        	for(OrderDetails o: orderList){
        		if(o.getManufacturer() != null){
        			ArrayList<OrderItem> oilist = orderItemDAO.getOrderItemByCustomerOrder(o);
        		
        			for(OrderItem oi : oilist){
        				billData.put(oi.getProduct().getProductName(), oi.getItemStatus());
        			}
        		}
        	}
        }

			if(output ==null || "".equals(output)){
				//return normal view
				return new ModelAndView("PdfOrderSummary","billData",billData);
				
			}else if("PDF".equals(output.toUpperCase())){
				//return excel view
				return new ModelAndView("PdfOrderSummary","billData",billData);
				
			}else{
				//return normal view
				return new ModelAndView("PdfOrderSummary","billData",billData);
				
			}
	}
	
//	@RequestMapping(value = "/viewBillExcel.htm", method = RequestMethod.GET)
//	public ModelAndView billExcel(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		
//		String output = ServletRequestUtils.getStringParameter(request, "output");
//			
//			//dummy data
//			Map<String,String> billData = new HashMap<String,String>();
//			billData.put("1/20/2010", "$100,000");
//			billData.put("1/21/2010", "$200,000");
//			billData.put("1/22/2010", "$300,000");
//			billData.put("1/23/2010", "$400,000");
//			billData.put("1/24/2010", "$500,000");
//			
//			if(output ==null || "".equals(output)){
//				//return normal view
//				return new ModelAndView("ExcelBillSummary","billData",billData);
//				
//			}else if("EXCEL".equals(output.toUpperCase())){
//				//return excel view
//				return new ModelAndView("ExcelBillSummary","billData",billData);
//			}else{
//				//return normal view
//				return new ModelAndView("ExcelBillSummary","billData",billData);
//				
//			}
//	}
	
	
	
	@RequestMapping(value = "/search.htm", method = RequestMethod.GET)
	public String loginLink(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String q = request.getParameter("q");
		ArrayList<Product> plist = productDAO.getAllProduct();
		String result = "";
		
		for(Product p : plist){
			
			if(p.getProductName().contains(q)){
				result = result + p.getProductName() + "<br />";
			}
			
		}
		
		
		return result;
	}
	
	
	
	
}
