package com.casestudy.greatOutdoors.service;

import com.casestudy.greatOutdoors.dao.OrderRepository;
import com.casestudy.greatOutdoors.dao.OrderStatusRepository;
import com.casestudy.greatOutdoors.entity.Order;
import com.casestudy.greatOutdoors.entity.OrderStatus;
import com.casestudy.greatOutdoors.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    @Autowired
    OrderRepository orderrepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;


    /**
     * it will fetch the orders by customer Name
     * @param customerName
     * @return list of orders
     */
    public List<Order> findOrderHistory(String customerName) {
        logger.info("*** OrderService - findOrderHistory ***");
        List<Order> orderHistoryList =orderrepository.findByCustomerName(customerName);
        return orderHistoryList;
    }


    /**
     * It will create new order
     * @param order
     * @return order
     */
    public Order newOrder(Order order) {
        logger.info("*** OrderService - newOrder ***");
        Order createdOrder = orderrepository.save(order);
        this.createOrderStatus(createdOrder);
        return createdOrder;
    }


    /**
     * It will set the order status and save the order
     * @param order
     */
    public void createOrderStatus(Order order){
        logger.info("*** OrderService - createOrderStatus ***");
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatus(order.getStatus());
        orderStatus.setOrder(order);
        orderStatus.setUpdatedAt(order.getOrderDate());

        orderStatusRepository.save(orderStatus);
    }

    /**
     * It will place the order
     * @param order
     * @return order
     */
    public Order placeOrder(Order order) {
        logger.info("*** OrderService - placeOrder ***");
        Order orderPlaced = orderrepository.save(order);
        this.createOrderStatus(orderPlaced);
        return orderPlaced;

    }


    /**
     * It will get the order by order id
     * @param orderId
     * @return
     */
    public Order getOrder(String orderId) {
        logger.info("*** OrderService - getOrder ***");
        Order order = orderrepository.findById(Integer.parseInt(orderId)).get();
        return order;
    }

    /**
     * It will process the refund
     * @param order
     * @return status
     */
    public boolean processRefund(Order order) {
        logger.info("*** OrderService - processRefund ***");
        if(order.getStatus().equals("completed")) {
            order.setStatus("refund initiated");
            order.setOrderDate(new Date());
            orderrepository.save(order);
            this.createOrderStatus(order);
            return true;
        }
        return false;
    }

    public void generateLastDayReport(Date date) {

        //orderrepository.findAllByOrderDate(date2);

        //System.out.println(day);
        //orderrepository.findAllByOrderDate(date);
    }


    /**
     * It will fetch all the orders
     * @return
     */
    public List<Order> getAllOrders() {
        logger.info("*** OrderService - getAllOrders ***");
        List<Order> orders = orderrepository.findAll();
        return orders;
    }

    /**
     * It will get the top selling product
     * @param status
     * @return product map
     */
    public Map<Product, Integer> getTopSellingProduct(String status) {
        logger.info("*** OrderService - getTopSellingProduct ***");
       List<Object[]> results = orderrepository.findByProduct(status);
       Map<Product,Integer> productMap = new HashMap<Product, Integer>();
        for (Object[] result : results) {
            Product product = (Product) result[0];
            int count = ((Number) result[1]).intValue();
            productMap.put(product,count);
            return productMap;
        }
       return null;
    }

    /**
     * It will get the order details by status
     * @param status
     * @return list of orders
     */
    public List<Order> getOrdersByStatus(String status) {
        logger.info("*** OrderService - getOrdersByStatus ***");
        List<Order> completedOrderList =orderrepository.findByStatus(status);
        return completedOrderList;
    }


    /**
     * It will get the order details by order date
     * @param startDate
     * @param endDate
     * @param status
     * @return list of orders
     */
    public List<Order> getOrdersByDate(Date startDate, Date endDate, String status) {
        logger.info("*** OrderService - getOrdersByDate ***");
        List<Order> orderListByDate = orderrepository.findByOrderDate(startDate,endDate,status);
        return orderListByDate;
    }
}
