package com.casestudy.greatOutdoors.controller;


import com.casestudy.greatOutdoors.entity.Order;
import com.casestudy.greatOutdoors.entity.Product;
import com.casestudy.greatOutdoors.service.OrderService;
import com.casestudy.greatOutdoors.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class OrderController {



    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;



    @RequestMapping(value ="/customer/product", method = RequestMethod.GET)
    public String showAvailableProducts(ModelMap model){
        List<Product> products = productService.getAllProducts();
        model.put("products",products);
        return "customer_product_list";
    }

    @RequestMapping(value ="/customer/buy_now", method = RequestMethod.POST)
    public String initiateOrder(@RequestParam("pcode") String code,Order order, ModelMap model) {
        String username = "BookMan";
        Product product = productService.getProduct(code);
        order.setProduct(product);
        order.setStatus("initiated");
        order.setCustomerName(username);
        order.setOrderDate(new Date());
        order.setBill(order.getQuantity()*product.getPrice());
        Order orderInserted = orderService.newOrder(order);
        model.addAttribute("order",orderInserted);
        return "customer_shipping_details";
    }

    @RequestMapping(value ="/customer/place_order", method = RequestMethod.POST)
    public String placeOrder(ModelMap model, Order order, @RequestParam("code") String code) {
        Product product = productService.getProduct(code);
        order.setProduct(product);
        order.setStatus("completed");
        order.setOrderDate(new Date());
        Order completedOrder = orderService.placeOrder(order);
        if(!completedOrder.equals(null)){
            productService.updateInventory(product,order.getQuantity());
        }
        model.addAttribute("completedOrder", completedOrder);
        return "success";
    }

    @RequestMapping(value ="/order_status", method = RequestMethod.GET)
    public String orderStatus(ModelMap model){
       String customerName = "BookMan";
        List<Order> orderHistoryList= orderService.findOrderHistory(customerName);
        model.put("orderHistory",orderHistoryList);
        return "order_history";
    }

    @RequestMapping(value ="/refund_order", method = RequestMethod.GET)
    public String refundOrder(ModelMap model, @RequestParam("id") String orderId, RedirectAttributes redirectAttributes){
        Order order = orderService.getOrder(orderId);
        boolean isRefunded = orderService.processRefund(order);
        if(isRefunded) {
            redirectAttributes.addFlashAttribute("message", "Refund initiated successfully");
            return "redirect:/order_status";
        }

        redirectAttributes.addFlashAttribute("message", "Order cannot be refunded !!");
        return "redirect:/order_status";
    }
}
