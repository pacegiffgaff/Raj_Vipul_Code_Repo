package com.casestudy.greatOutdoors.controller;

import com.casestudy.greatOutdoors.dao.OrderRepository;
import com.casestudy.greatOutdoors.entity.Order;
import com.casestudy.greatOutdoors.entity.Product;
import com.casestudy.greatOutdoors.service.OrderService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;

@Controller
public class GenerateReportController {

    private static final Logger logger = LogManager.getLogger(GenerateReportController.class);
    //static Logger logger = Logger.getLogger(GenerateReportController.class);



    @Autowired
    OrderService orderService;

    /**
     * @param model
     * @return
     */
    @RequestMapping(value ="/admin/order_report", method = RequestMethod.GET)
    public String orderCompletedReport(ModelMap model){
        logger.info("*** GenerateReportController - orderCompletedReport ***");
        String status = "completed";
        List<Order> orderList = orderService.getOrdersByStatus(status);
        model.addAttribute("orderCompletedList",orderList);
        return "admin_order_report";
    }

    /**
     * @param model
     * @param days
     * @return
     */
    @RequestMapping(value ="/admin/filterByDate", method = RequestMethod.GET)
    public String orderFilterDropDown(ModelMap model, @RequestParam Integer days){
        logger.info("*** GenerateReportController - orderFilterDropDown ***");
        //Date endDate = new Date();
        Calendar cal = Calendar.getInstance();
        Date endDate = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, -days);
        Date startDate = cal.getTime();
        String status = "completed";
        List<Order> orderListByDate = orderService.getOrdersByDate(startDate,endDate,status);
        model.addAttribute("orderCompletedList",orderListByDate);
        return "admin_order_report";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value ="/admin/refund_report", method = RequestMethod.GET)
    public String orderRefundReport(ModelMap model){
        logger.info("*** GenerateReportController - orderRefundReport ***");
        String status = "refund initiated";
        List<Order> orderList = orderService.getOrdersByStatus(status);
        model.addAttribute("orderRefundList",orderList);
        return "admin_refund_report";
    }


    /**
     * @param model
     * @return
     */
    @RequestMapping(value ="/admin/hot_product", method = RequestMethod.GET)
    public String getMostSellingProduct(ModelMap model){
        logger.info("*** GenerateReportController - getMostSellingProduct ***");
        String status = "completed";
        Map<Product,Integer> productMap = orderService.getTopSellingProduct(status);
        int count = 0;
        Set<Product> products = productMap.keySet();
        for(Product product:products){
           count = productMap.get(product);
        }
        model.addAttribute("productSet",products);
        model.addAttribute("count",count);
        return "top_product";
    }
}
