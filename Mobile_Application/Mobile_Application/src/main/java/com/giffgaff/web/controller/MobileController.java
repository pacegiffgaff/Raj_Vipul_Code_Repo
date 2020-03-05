package com.giffgaff.web.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.giffgaff.web.dao.MobileRepository;
import com.giffgaff.web.dao.OrderRepository;
import com.giffgaff.web.entity.Mobile;
import com.giffgaff.web.entity.Order;

@Controller
public class MobileController {
    
    
    @Autowired
    private MobileRepository mobileRepo ;

    @Autowired
    private OrderRepository orderRepo ;


	/**
	 * @param model
	 * @param brand
	 * @return
	 */
    @RequestMapping(value ="/list-mobiles", method = RequestMethod.GET)
    public String showMobileList(ModelMap model, @RequestParam(defaultValue = "") String brand ){
    	
    	if(!brand.equals("")) {
    		model.put("mobiles",mobileRepo.findByBrand(brand));
    		return "list-mobiles";
    	}
    	
    	model.put("mobiles",mobileRepo.findAll());
        return "list-mobiles";
    }

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add-mobile", method = RequestMethod.GET)
	public String showAddMobilePage(ModelMap model) {
		
		model.addAttribute("mobile", new Mobile("A3000","Samsung",100000L));
		return "mobile";
	}


	/**
	 * @param model
	 * @param order
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public String addToCart(ModelMap model, Order order, @RequestParam int id) {
		order.setUser(getLoggedInUserName(model));
		
		Mobile mobile = mobileRepo.findById(id).get();
		
		order.setMobile(mobile);
		orderRepo.save(order);
		model.addAttribute("mobile", new Mobile("A3000","Samsung",100000L));
		return "order_success";
	}


	/**
	 * @param model
	 * @param mobile
	 * @param result
	 * @return
	 */
    @RequestMapping(value = "/add-mobile", method = RequestMethod.POST)
	public String addMobile(ModelMap model, @Valid Mobile mobile, BindingResult result) {
		
		if(result.hasErrors()){
			return "mobile";
		}
		
		mobileRepo.save(mobile);
		
		return "redirect:/list-mobiles";
	}

	/**
	 * @param id
	 * @return
	 */
    @RequestMapping(value="/delete-mobile", method = RequestMethod.GET)
	public String deleteMobile(@RequestParam int id){
    	mobileRepo.deleteById(id);
		return "redirect:/list-mobiles";
	}


	/**
	 * @param id
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/update-mobile", method = RequestMethod.GET)
	public String showUpdateMobilePage(@RequestParam int id, ModelMap model) {
    	Mobile mobile = mobileRepo.findById(id).get();
		model.put("mobile", mobile);
		return "mobile";
	}

	/**
	 * @param model
	 * @param mobile
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update-mobile", method = RequestMethod.POST)
	public String updateMobile(ModelMap model, @Valid Mobile mobile, BindingResult result) {
		if (result.hasErrors()) {
			return "mobile";
		}
		
		mobileRepo.save(mobile);
		return "redirect:/list-mobiles";
	}

	/**
	 * Refactoring session name
	 */
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
}
