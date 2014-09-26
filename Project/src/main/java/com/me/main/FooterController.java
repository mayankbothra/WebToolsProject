package com.me.main;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.DAO.AddressDAO;
import com.me.DAO.DepotDAO;
import com.me.DAO.ManufacturerDAO;
import com.me.DAO.OrderDetailsDAO;
import com.me.DAO.OrderItemDAO;
import com.me.DAO.PersonDAO;
import com.me.DAO.ProductDAO;
import com.me.DAO.StockItemDAO;
import com.me.DAO.UserAccountDAO;
import com.me.pojo.Manufacturer;

@Controller
@RequestMapping("/")
public class FooterController {
	
	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private DepotDAO depotDAO;
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

	
	@RequestMapping(value = "/developer.htm", method = RequestMethod.GET)
	public ModelAndView developer(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		
		return new ModelAndView("developer");
	}
	
	@RequestMapping(value = "/technologies.htm", method = RequestMethod.GET)
	public ModelAndView technologies(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArrayList<Manufacturer> manufacturerList = manufacturerDAO.getListOfManufacturer();
		request.setAttribute("manufacturerList", manufacturerList);
		
		return new ModelAndView("technologies");
	}
}
