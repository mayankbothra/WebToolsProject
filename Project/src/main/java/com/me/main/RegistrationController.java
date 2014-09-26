package com.me.main;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.me.forms.CustomerForm;
import com.me.forms.ManufacturerForm;
import com.me.pojo.Address;
import com.me.pojo.Depot;
import com.me.pojo.DepotOrderDetails;
import com.me.pojo.Manufacturer;
import com.me.pojo.OrderDetails;
import com.me.pojo.Person;
import com.me.pojo.Product;
import com.me.pojo.UserAccount;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class RegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
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
	
	
	
	@RequestMapping(value = "/manuReg1.htm", method = RequestMethod.GET)
	protected ModelAndView manuPage1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		
		return new ModelAndView("manuReg1");
	}
	
	@RequestMapping(value = "/customerReg.htm", method = RequestMethod.GET)
	protected ModelAndView custReg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {			
		
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		return new ModelAndView("customerReg", "customerForm", new CustomerForm());
	}
	
	@RequestMapping(value = "/customerRegSuccess.htm", method = RequestMethod.POST)
public ModelAndView customerRegSuccess(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception{

		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		
		String username = (String)result.getFieldValue("userAccount.username");
		String password = (String)result.getFieldValue("userAccount.password");
		String rePassword = (String)result.getFieldValue("userAccount.rePassword");
		String state = (String)result.getFieldValue("address.state");
		
		if(result.hasErrors()){
			return new ModelAndView("customerReg", "customerForm", customerForm) ;
		}
		else if(state.equalsIgnoreCase("default"))
		{
			FieldError fieldError = new FieldError("address.state", "address.state", "Select a state");
			result.addError(fieldError);
			return new ModelAndView("customerReg", "customerForm", customerForm);
		}
		else if(userAccountDAO.isUserExists(username))
		{
			FieldError fieldError = new FieldError("userAccount.username", "userAccount.username", "Username already exists");
			result.addError(fieldError);
			return new ModelAndView("customerReg", "customerForm", customerForm);
		}
		else if(!password.equals(rePassword))
		{
			FieldError fieldError = new FieldError("userAccount.rePassword", "userAccount.rePassword", "Password dont match");
			result.addError(fieldError);
			return new ModelAndView("customerReg", "customerForm", customerForm);
		}

		else{
						
			Person person = customerForm.getPerson();
			Address address = customerForm.getAddress();
			UserAccount userAccount = customerForm.getUserAccount();
			
			userAccount.setPerson(person);
			userAccount.setRole("Customer");
			userAccount.setRoleString("Customer");
			userAccount.setStatus("active");
			
			address.setPerson(person);

			person.setAddress(address);
			person.setManufacturer(null);
			ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
			person.setOrderList(orderDetailsList);
			person.setUserAccount(userAccount);
	
			personDAO.persist(person);
		
		return new ModelAndView("customerRegSuccess");
	}
	}
	
	@RequestMapping(value = "/manuReg2.htm", method = RequestMethod.POST)
	protected ModelAndView manuPage2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		
		return new ModelAndView("manuReg2", "manufacturerForm", new ManufacturerForm());
	}
	
	
	@RequestMapping(value = "/manuReg3", method = RequestMethod.POST)
	public ModelAndView manuPage3(@ModelAttribute("manufacturerForm") @Valid ManufacturerForm manufacturerForm, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		
		String username = (String)result.getFieldValue("userAccount.username");
		String password = (String)result.getFieldValue("userAccount.password");
		String rePassword = (String)result.getFieldValue("userAccount.rePassword");
		String state = (String)result.getFieldValue("address.state");
		
		if(result.hasErrors()){
			return new ModelAndView("manuReg2", "manufacturerForm", manufacturerForm) ;
		}
		else if(state.equalsIgnoreCase("default"))
		{
			FieldError fieldError = new FieldError("address.state", "address.state", "Select a state");
			result.addError(fieldError);
			return new ModelAndView("manuReg2", "manufacturerForm", manufacturerForm);
		}
		else if(userAccountDAO.isUserExists(username))
		{
			FieldError fieldError = new FieldError("userAccount.username", "userAccount.username", "Username already exists");
			result.addError(fieldError);
			return new ModelAndView("manuReg2", "manufacturerForm", manufacturerForm);
		}
		else if(!password.equals(rePassword))
		{
			FieldError fieldError = new FieldError("userAccount.rePassword", "userAccount.rePassword", "Password dont match");
			result.addError(fieldError);
			return new ModelAndView("manuReg2", "manufacturerForm", manufacturerForm);
		}

		else{
						
			Manufacturer manufacturer = manufacturerForm.getManufacturer();
			Person person = manufacturerForm.getPerson();
			Address address = manufacturerForm.getAddress();
			UserAccount userAccount = manufacturerForm.getUserAccount();
			
			userAccount.setPerson(person);
			userAccount.setRole("MA");
			userAccount.setRoleString("Manufacturer Admin");
			userAccount.setStatus("disabled");
			
			address.setPerson(person);

			person.setAddress(address);
			person.setManufacturer(manufacturer);
			person.setOrderList(null);
			person.setUserAccount(userAccount);
			
			ArrayList<Person> personList = new ArrayList<Person>();
			personList.add(person);
			ArrayList<Depot> depotList = new ArrayList<Depot>();
			ArrayList<Product> productList = new ArrayList<Product>();
			ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
			ArrayList<DepotOrderDetails> depotOrderDetailsList = new ArrayList<DepotOrderDetails>();
			
			manufacturer.setDepotList(depotList);
			manufacturer.setPersonList(personList);
			manufacturer.setProductList(productList);
			manufacturer.setOrderDetailsList(orderDetailsList);
			manufacturer.setDepotOrderDetails(depotOrderDetailsList);
			
			manufacturerDAO.persist(manufacturer);
						
			return new ModelAndView("manuRegSuccess");
		}
		
	}		
}
