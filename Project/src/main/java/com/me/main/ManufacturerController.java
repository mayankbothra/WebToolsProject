package com.me.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.me.DAO.AddressDAO;
import com.me.DAO.DepotDAO;
import com.me.DAO.DepotOrderDetailsDAO;
import com.me.DAO.DepotOrderItemDAO;
import com.me.DAO.ManufacturerDAO;
import com.me.DAO.OrderDetailsDAO;
import com.me.DAO.OrderItemDAO;
import com.me.DAO.PersonDAO;
import com.me.DAO.ProductDAO;
import com.me.DAO.StockItemDAO;
import com.me.DAO.UserAccountDAO;
import com.me.forms.DepotForm;
import com.me.forms.EmployeeForm;
import com.me.pojo.Address;
import com.me.pojo.Depot;
import com.me.pojo.DepotOrderDetails;
import com.me.pojo.DepotOrderItem;
import com.me.pojo.Manufacturer;
import com.me.pojo.OrderDetails;
import com.me.pojo.OrderItem;
import com.me.pojo.Person;
import com.me.pojo.Product;
import com.me.pojo.StockItem;
import com.me.pojo.UserAccount;

@Controller
@RequestMapping("/")
public class ManufacturerController {

	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private DepotDAO depotDAO;
	@Autowired
	private DepotOrderDetailsDAO depotOrderDetailsDAO;
	@Autowired
	private DepotOrderItemDAO depotOrderItemDAO;
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

	@RequestMapping(value = "/mAdminHome.htm", method = RequestMethod.GET)
	public ModelAndView mAdminHome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession(false);
		if(session == null){
			response.sendRedirect("login.htm");
		}
		
		return new ModelAndView("mAdminHome");
	}

	@RequestMapping(value = "/mAdminHome.htm", method = RequestMethod.POST)
	public ModelAndView mAdminHomePost(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		if(session == null){
			return new ModelAndView("login");
		}
		
		return new ModelAndView("mAdminHome");
	}

	@RequestMapping(value = "/productManagerHome.htm", method = RequestMethod.GET)
	public ModelAndView productManagerHome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		if(session == null){
			response.sendRedirect("login.htm");
		}
		return new ModelAndView("productManagerHome");
	}

	@RequestMapping(value = "/stockManagerHome.htm", method = RequestMethod.GET)
	public ModelAndView stockManagerHome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("stockManagerHome");
	}

	@RequestMapping(value = "/depotManagerHome.htm", method = RequestMethod.GET)
	public ModelAndView depotManagerHome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("depotManagerHome");
	}

	@RequestMapping(value = "/addDepot.htm", method = RequestMethod.POST)
	public ModelAndView adddepot(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("addDepot", "depotForm", new DepotForm());
	}

	@RequestMapping(value = "/depotAdded.htm", method = RequestMethod.POST)
	public ModelAndView depotadded(
			@ModelAttribute("depotForm") @Valid DepotForm depotForm,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String username = (String) result.getFieldValue("userAccount.username");
		String password = (String) result.getFieldValue("userAccount.password");
		String rePassword = (String) result
				.getFieldValue("userAccount.rePassword");
		String state = (String) result.getFieldValue("address.state");

		if (result.hasErrors()) {
			return new ModelAndView("addDepot");
		} else if (userAccountDAO.isUserExists(username)) {
			FieldError fieldError = new FieldError("userAccount.username",
					"userAccount.username", "Username already exists");
			result.addError(fieldError);
			return new ModelAndView("addDepot", "depotForm", depotForm);
		} else if (!password.equals(rePassword)) {
			FieldError fieldError = new FieldError("userAccount.rePassword",
					"userAccount.rePassword", "Password dont match");
			result.addError(fieldError);
			return new ModelAndView("addDepot", "depotForm", depotForm);
		} else if (state.equalsIgnoreCase("default")) {
			FieldError fieldError = new FieldError("address.state",
					"address.state", "Select a state");
			result.addError(fieldError);
			return new ModelAndView("addDepot", "depotForm", depotForm);
		}

		else {

			HttpSession session = request.getSession();
			Depot depot = new Depot();
			Person person = depotForm.getPerson();
			Address address = depotForm.getAddress();
			UserAccount userAccount = depotForm.getUserAccount();
			Manufacturer man = (Manufacturer) session
					.getAttribute("manufacturer");
			Manufacturer manufacturer = manufacturerDAO
					.findManufacturerById(man.getId());
			List<StockItem> stockItemList = new ArrayList<StockItem>();

			userAccount.setPerson(person);
			userAccount.setRole("DM");
			userAccount.setRoleString("Depot Manager");
			userAccount.setStatus("active");

			address.setPerson(person);

			ArrayList<DepotOrderDetails> depotOrderDetails = new ArrayList<DepotOrderDetails>();
			
			depot.setDepotOrderDetails(depotOrderDetails);
			depot.setLocation(address.getState());
			depot.setManufacturer(manufacturer);
			depot.setStockItemList(stockItemList);
			depot.setPerson(person);
			person.setAddress(address);
			person.setManufacturer(manufacturer);
			person.setOrderList(null);
			person.setUserAccount(userAccount);

			List<Person> personList = personDAO
					.getPersonListByManufacturer(manufacturer);
			personList.add(person);
			List<Depot> depotList = depotDAO
					.getDepotListByManufacturer(manufacturer);

			if (depotList == null) {
				ArrayList<Depot> tempList = new ArrayList<Depot>();
				tempList.add(depot);
				depotList = tempList;
			} else {
				depotList.add(depot);
			}

			manufacturer.setDepotList(depotList);
			manufacturer.setPersonList(personList);

			manufacturerDAO.update(manufacturer);

			return new ModelAndView("depotAdded");
		}
	}

	@RequestMapping(value = "/addEmployees.htm", method = RequestMethod.POST)
	public ModelAndView addemployees(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("addEmployee", "employeeForm",
				new EmployeeForm());
	}

	@RequestMapping(value = "/employeeAdded.htm", method = RequestMethod.POST)
	public ModelAndView employeeadded(
			@ModelAttribute("employeeForm") @Valid EmployeeForm employeeForm,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String username = (String) result.getFieldValue("userAccount.username");
		String password = (String) result.getFieldValue("userAccount.password");
		String rePassword = (String) result
				.getFieldValue("userAccount.rePassword");
		String role = (String) result.getFieldValue("userAccount.role");
		String state = (String) result.getFieldValue("address.state");

		if (result.hasErrors()) {
			return new ModelAndView("addEmployee", "employeeForm", employeeForm);
		} else if (state.equalsIgnoreCase("default")) {
			FieldError fieldError = new FieldError("address.state",
					"address.state", "Select a state");
			result.addError(fieldError);
			return new ModelAndView("addEmployee", "employeeForm", employeeForm);
		} else if (userAccountDAO.isUserExists(username)) {
			FieldError fieldError = new FieldError("userAccount.username",
					"userAccount.username", "Username already exists");
			result.addError(fieldError);
			return new ModelAndView("addEmployee", "employeeForm", employeeForm);
		} else if (!password.equals(rePassword)) {
			FieldError fieldError = new FieldError("userAccount.rePassword",
					"userAccount.rePassword", "Password dont match");
			result.addError(fieldError);
			return new ModelAndView("addEmployee", "employeeForm", employeeForm);
		} else if (role.equalsIgnoreCase("default")) {
			FieldError fieldError = new FieldError("userAccount.role",
					"userAccount.role", "Select a role");
			result.addError(fieldError);
			return new ModelAndView("addEmployee", "employeeForm", employeeForm);
		}

		else {

			HttpSession session = request.getSession();
			Person person = employeeForm.getPerson();
			Address address = employeeForm.getAddress();
			UserAccount userAccount = employeeForm.getUserAccount();
			Manufacturer man = (Manufacturer) session
					.getAttribute("manufacturer");
			Manufacturer manufacturer = manufacturerDAO
					.findManufacturerById(man.getId());

			userAccount.setPerson(person);
			if (userAccount.getRole().equals("PM")) {
				userAccount.setRoleString("Product Manager");
			} else {
				userAccount.setRoleString("Stock Manager");
			}

			userAccount.setStatus("active");

			address.setPerson(person);

			person.setAddress(address);
			person.setManufacturer(manufacturer);
			person.setOrderList(null);
			person.setUserAccount(userAccount);

			List<Person> personList = personDAO
					.getPersonListByManufacturer(manufacturer);
			personList.add(person);

			manufacturer.setPersonList(personList);

			manufacturerDAO.update(manufacturer);

			return new ModelAndView("employeeAdded");
		}
	}

	@RequestMapping(value = "/addProduct.htm", method = RequestMethod.POST)
	public ModelAndView addProduct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("addProduct", "product", new Product());
	}

	@RequestMapping(value = "/productAdded.htm", method = RequestMethod.POST)
	public ModelAndView productadded(@ModelAttribute("product") @Valid Product product, BindingResult result,
			@RequestParam CommonsMultipartFile fileUpload, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String saveDirectory = "/Users/mayank/Documents/Project_Images/";

		String type = (String) result.getFieldValue("productType");

		if (result.hasErrors()) {
			return new ModelAndView("addProduct", "product", product);
		} else if (type.equalsIgnoreCase("default")) {
			FieldError fieldError = new FieldError("productType",
					"productType", "Select type of product");
			result.addError(fieldError);
			return new ModelAndView("addProduct", "product", product);
		} else {
			
			String fileName = "";
			
			if (fileUpload != null) {

				System.out.println("Saving file: " + fileUpload.getOriginalFilename());

				if (!fileUpload.getOriginalFilename().equals("")) {
					fileName = saveDirectory + fileUpload.getOriginalFilename();
					fileUpload.transferTo(new File(fileName));
				}
			}
			System.out.println(fileName);

			HttpSession session = request.getSession();
			Manufacturer man = (Manufacturer) session
					.getAttribute("manufacturer");
			Manufacturer manufacturer = manufacturerDAO
					.findManufacturerById(man.getId());

			product.setManufacturer(manufacturer);
			product.setOrderItemList(null);
			product.setStockItemList(null);
			product.setFileName(fileName);

			List<Product> productList = productDAO
					.getProductListByManufacturer(manufacturer);

			if (productList == null) {
				ArrayList<Product> tempList = new ArrayList<Product>();
				tempList.add(product);
				productList = tempList;
			} else {
				productList.add(product);
			}

			manufacturer.setProductList(productList);
			manufacturerDAO.update(manufacturer);

			return new ModelAndView("productAdded");
		}
	}

	@RequestMapping(value = "/viewProduct.htm", method = RequestMethod.POST)
	public ModelAndView viewProduct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Manufacturer manufacturer = (Manufacturer) session
				.getAttribute("manufacturer");
		ArrayList<Product> productList = productDAO
				.getProductListByManufacturer(manufacturer);

		return new ModelAndView("viewProduct", "productList", productList);
	}

	@RequestMapping(value = "/deleteProduct.htm", method = RequestMethod.POST)
	public ModelAndView deleteProduct(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int productId = (Integer.parseInt(request.getParameter("select")));
		Product product = productDAO.findProductById(productId);

		productDAO.delete(product);

		return new ModelAndView("productDeleted");
	}

	@RequestMapping(value = "/addStock.htm", method = RequestMethod.POST)
	public ModelAndView addStock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Manufacturer manufacturer = (Manufacturer) session
				.getAttribute("manufacturer");

		ArrayList<Depot> depotList = depotDAO
				.getDepotListByManufacturer(manufacturer);
		ArrayList<Product> productList = productDAO
				.getProductListByManufacturer(manufacturer);

		request.setAttribute("depotList", depotList);
		request.setAttribute("productList", productList);

		return new ModelAndView("addStock");
	}

	@RequestMapping(value = "/stockAdded.htm", method = RequestMethod.POST)
	public ModelAndView stockadded(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// HttpSession session = request.getSession();
		// Manufacturer manufacturer =
		// (Manufacturer)session.getAttribute("manufacturer");
		int productId = Integer.parseInt(request.getParameter("product"));
		int depotId = Integer.parseInt(request.getParameter("depot"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		Product product = productDAO.findProductById(productId);
		Depot depot = depotDAO.findDepotById(depotId);

		StockItem stockItem = stockItemDAO.getStockItemListByDepotAndProduct(
				depot, product);

		if (stockItem == null) {
			StockItem tempItem = new StockItem();
			tempItem.setDepot(depot);
			tempItem.setProduct(product);
			tempItem.setQuantity(quantity);
			stockItem = tempItem;
		} else {
			stockItem.setQuantity(quantity + stockItem.getQuantity());
		}

		stockItemDAO.saveOrUpdate(stockItem);

		return new ModelAndView("stockAdded");
	}

	@RequestMapping(value = "/viewAllProducts.htm", method = RequestMethod.POST)
	public ModelAndView viewAllProducts(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Manufacturer manufacturer = (Manufacturer) session
				.getAttribute("manufacturer");
		ArrayList<Product> productList = productDAO
				.getProductListByManufacturer(manufacturer);

		return new ModelAndView("viewAllProducts", "productList", productList);
	}

	@RequestMapping(value = "/viewDepotStock.htm", method = RequestMethod.POST)
	public ModelAndView viewDepotStock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer");
		Depot depot = (Depot) session.getAttribute("depot");
		ArrayList<StockItem> stockItemList = stockItemDAO
				.returnStockItemListOfDepot(depot);
		ArrayList<StockItem> shortStockList = stockItemDAO
				.returnStockItemListOfDepotLessThanThreshold(depot);

		request.setAttribute("shortList", shortStockList);
		if(shortStockList != null)
		request.setAttribute("size", shortStockList.size());
		
		return new ModelAndView("viewDepotStock", "stockItemList",stockItemList);
	}
	
	
	@RequestMapping(value = "/quickOrderDepot.htm", method = RequestMethod.POST)
	public ModelAndView quickOrderDepot(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer");
		Depot depot = (Depot) session.getAttribute("depot");
		ArrayList<StockItem> shortStockList = stockItemDAO.returnStockItemListOfDepotLessThanThreshold(depot);
		
		DepotOrderDetails depotOrderDetails = new DepotOrderDetails();
		depotOrderDetails.setDepot(depot);
		depotOrderDetails.setManufacturer(manufacturer);
		ArrayList<DepotOrderItem> orderItemList = new ArrayList<DepotOrderItem>();
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		for(StockItem si : shortStockList){
			DepotOrderItem oi = new DepotOrderItem();
			oi.setDepotOrderDetails(depotOrderDetails);
			oi.setProduct(si.getProduct());
			oi.setQuantity(quantity);
			orderItemList.add(oi);
		}
		
		depotOrderDetails.setDepotOrderItemList(orderItemList);
		depotOrderDetails.setStatus("placed");
		
		List<DepotOrderDetails> depotOrderDetailsList = depotOrderDetailsDAO.getDepotOrderDetailsByDepot(depot);
		
		if(depotOrderDetailsList == null) {
			List<DepotOrderDetails> temp = new ArrayList<DepotOrderDetails>();
			temp.add(depotOrderDetails);
			depotOrderDetailsList = temp;
		}
		else {
			depotOrderDetailsList.add(depotOrderDetails);
		}
		
		depot.setDepotOrderDetails(depotOrderDetailsList);
		depotDAO.update(depot);
		
		return new ModelAndView("quickOrderPlaced");
	}
	
	
	@RequestMapping(value = "/viewDepotOrder.htm", method = RequestMethod.POST)
	public ModelAndView viewDepotOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer");
		ArrayList<DepotOrderDetails> depotOrderDetailsList = depotOrderDetailsDAO.getDepotOrderDetailsByManufacturer(manufacturer);
		HashMap<DepotOrderDetails, ArrayList<DepotOrderItem>> map = new HashMap<DepotOrderDetails, ArrayList<DepotOrderItem>>();
		
		
		if(depotOrderDetailsList != null) {		
			for(DepotOrderDetails d : depotOrderDetailsList){	
					ArrayList<DepotOrderItem> orderItemList = depotOrderItemDAO.getOrderItemByOrder(d);
					map.put(d, orderItemList);
			}
		}
		
		if(depotOrderDetailsList == null)
		request.setAttribute("size", 0);
		
//		if(depotOrderDetailsList != null){
//		for(DepotOrderDetails d : depotOrderDetailsList){
//			Depot depot = d.getDepot();
//			depotList.add(depot);
//		}
//		}
//		
//		request.setAttribute("depotList", depotList);
		return new ModelAndView("viewDepotOrder", "orderList", map);
	}
	
	
	@RequestMapping(value = "/processDepotOrder.htm", method = RequestMethod.POST)
	public ModelAndView processDepotOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int orderId = Integer.parseInt(request.getParameter("orderId"));
		DepotOrderDetails depotOrderDetails = depotOrderDetailsDAO.findDepotOrderDetailsById(orderId);
		ArrayList<DepotOrderItem> orderItemList = depotOrderItemDAO.getOrderItemByOrder(depotOrderDetails);
		int depotId = depotOrderDetails.getDepot().getId();
		Depot depot = depotDAO.findDepotById(depotId);
		
		for(DepotOrderItem oi : orderItemList) {
		
		int productId = oi.getProduct().getId();
		int quantity = oi.getQuantity();
		Product product = productDAO.findProductById(productId);		
		StockItem stockItem = stockItemDAO.getStockItemListByDepotAndProduct(depot, product);
		stockItem.setQuantity(quantity + stockItem.getQuantity());
		stockItemDAO.saveOrUpdate(stockItem);
		}
		
		depotOrderDetails.setStatus("processed");
		depotOrderDetailsDAO.update(depotOrderDetails);
		return new ModelAndView("depotOrderProcessed");
	}
	
	@RequestMapping(value = "/viewTotalStock.htm", method = RequestMethod.POST)
	public ModelAndView viewStock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer");
		ArrayList<Depot> depotList = depotDAO.getDepotListByManufacturer(manufacturer);
		HashMap<Depot, ArrayList<StockItem>> map = new HashMap<Depot, ArrayList<StockItem>>();
		
		if(depotList != null) {
		for(Depot d : depotList){
			ArrayList<StockItem> stockItemList = stockItemDAO.returnStockItemListOfDepot(d);
			map.put(d, stockItemList);
		}
		}
		
		if(depotList == null)
		request.setAttribute("size", 0);
		
		return new ModelAndView("viewTotalStock", "stockList", map);
	}
	
	
	@RequestMapping(value = "/processCustomerOrder.htm", method = RequestMethod.POST)
	public ModelAndView processCustOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer");
		Person depotManager = (Person)session.getAttribute("person");
		String location = depotManager.getAddress().getCity();
		
		ArrayList<OrderDetails> orderList = orderDetailsDAO.getOrdersByManufacturerAndStatus(manufacturer);
		HashMap<OrderDetails, ArrayList<OrderItem>> map = new HashMap<OrderDetails, ArrayList<OrderItem>>();
		
		for(OrderDetails order : orderList) {
			
			Person customer = order.getPerson();
			if(customer.getAddress().getCity().equals(location)){
				ArrayList<OrderItem> oiList = orderItemDAO.getOrderItemByCustomerOrder(order);
				map.put(order, oiList);
			}
		}
		
		return new ModelAndView("viewCustomerOrders", "orderList", map);
	}
	
	
	@RequestMapping(value = "/shipOrder.htm", method = RequestMethod.POST)
	public ModelAndView checkAvail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("select"));
		HttpSession session = request.getSession();
		Person depotManager = (Person)session.getAttribute("person");
		
		OrderDetails order = orderDetailsDAO.findOrderDetailsById(id);
		ArrayList<OrderItem> oiList = orderItemDAO.getOrderItemByCustomerOrder(order);
		Product product = null;
		int quantity = 0;
		
		
		for(OrderItem oi : oiList){
			product = oi.getProduct();
			quantity = oi.getQuantity();
			break;	
		}
		
		Depot depot = depotDAO.getDepotByPerson(depotManager);
		StockItem stock = stockItemDAO.getStockItemListByDepotAndProduct(depot, product);
		
		if(stock.getQuantity() >= quantity){
			stock.setQuantity(stock.getQuantity() - quantity);
			order.setStatus("Shipped");
			ArrayList<OrderItem> oiiList = orderItemDAO.getOrderItemByCustomerOrder(order);
			for(OrderItem oi : oiiList){
				oi.setItemStatus("Shipped");
				orderItemDAO.update(oi);
			}
			
			stockItemDAO.update(stock);
			orderDetailsDAO.update(order);
			
			return new ModelAndView("shipped");
		}
		else{
			return new ModelAndView("lessStock");
		}
		
	}
	
	
	
}
