package com.casestudy.greatOutdoors.service;

import com.casestudy.greatOutdoors.dao.OrderRepository;
import com.casestudy.greatOutdoors.dao.OrderStatusRepository;
import com.casestudy.greatOutdoors.entity.Order;
import com.casestudy.greatOutdoors.entity.OrderStatus;
import com.casestudy.greatOutdoors.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderrepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;



    public List<Order> findOrderHistory(String customerName) {
        List<Order> orderHistoryList =orderrepository.findByCustomerName(customerName);
        return orderHistoryList;
    }


    public Order newOrder(Order order) {
        Order createdOrder = orderrepository.save(order);
        this.createOrderStatus(createdOrder);
        return createdOrder;
    }


    public void createOrderStatus(Order order){

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatus(order.getStatus());
        orderStatus.setOrder(order);
        orderStatus.setUpdatedAt(order.getOrderDate());

        orderStatusRepository.save(orderStatus);
    }

    public Order placeOrder(Order order) {
        Order orderPlaced = orderrepository.save(order);
        this.createOrderStatus(orderPlaced);
        return orderPlaced;

    }


    public Order getOrder(String orderId) {
        Order order = orderrepository.findById(Integer.parseInt(orderId)).get();
        return order;
    }

    public boolean processRefund(Order order) {
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


    public List<Order> getAllOrders() {
        List<Order> orders = orderrepository.findAll();
        return orders;
    }

    public Map<Product, Integer> getTopSellingProduct(String status) {
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

    public List<Order> getOrdersByStatus(String status) {
        List<Order> completedOrderList =orderrepository.findByStatus(status);
        return completedOrderList;
    }


    public List<Order> getOrdersByDate(Date startDate, Date endDate, String status) {
        List<Order> orderListByDate = orderrepository.findByOrderDate(startDate,endDate,status);
        return orderListByDate;
    }
}
