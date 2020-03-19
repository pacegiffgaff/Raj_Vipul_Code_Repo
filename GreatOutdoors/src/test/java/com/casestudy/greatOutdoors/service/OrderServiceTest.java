package com.casestudy.greatOutdoors.service;

import com.casestudy.greatOutdoors.dao.*;
import com.casestudy.greatOutdoors.entity.Order;
import com.casestudy.greatOutdoors.entity.OrderStatus;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    private  TestData testData = new TestData();

    @MockBean
    ProductRepository mockProductRepository;

    @MockBean
    OrderRepository mockOrderRepository;

    @MockBean
    OrderStatusRepository mockOrderStatusRepository;

    @MockBean
    AccountRepository mockAccountRepository;

    @MockBean
    CustomerRepository mockCustomerRepository;

    @Autowired
    ApplicationContext context;

    @Before
    public void setUp(){
        //TestData testData = new TestData();
        //Mockito.when(orderRepositoryMock.findByCustomerName("test")).thenReturn(testData.getOrders(3));
    }

    @Test
    public void findOrderHistoryTest() {
        String customerName = "Customer";
        Mockito.when(mockOrderRepository.findByCustomerName(customerName)).thenReturn(testData.getOrders(3));
        List<Order> orderHistoryList = mockOrderRepository.findByCustomerName(customerName);
        for (int i = 0; i < orderHistoryList.size(); i++) {
            Order order = orderHistoryList.get(i);
            Assert.assertEquals(customerName+i, order.getCustomerName());
            Assert.assertEquals(Integer.valueOf(i), order.getId());
        }
    }
    @Test
    public void testNewOrder() {
        int noOfOrders =202;
        Order order = testData.getOrder(noOfOrders);
        when(mockOrderRepository.save(order)).thenReturn(order);
        Order  saveOrder = (Order)mockOrderRepository.save(order);
        Assert.assertEquals(Integer.valueOf(noOfOrders),order.getId());

    }

    @Test
    public void testCreateOrderStatus() {
        int noOfOrders =303;
        Order order = testData.getOrder(noOfOrders);
        OrderStatus orderStatus = testData.getOrderStatus(noOfOrders);
        when(mockOrderRepository.save(orderStatus.getOrder())).thenReturn(order);
        Order  saveOrder = (Order)mockOrderRepository.save(orderStatus.getOrder());
        Assert.assertEquals("Initiated303",saveOrder.getStatus());

    }

    @Test
    public void testPlaceOrder() {
        int noOfOrders =111;
        Order order = testData.getOrder(noOfOrders);
        when(mockOrderRepository.save(order)).thenReturn(order);
        Order  saveOrder = (Order)mockOrderRepository.save(order);
        Assert.assertEquals(Integer.valueOf(noOfOrders),order.getId());
        Assert.assertEquals("Customer"+noOfOrders, order.getCustomerName());
    }

    @Test
    public void testGetOrder() {
        int noOfOrders =123;
        Order order = testData.getOrder(noOfOrders);
        when(mockOrderRepository.findById(noOfOrders)).thenReturn(Optional.ofNullable(order));
        Optional<Order> saveOrder = mockOrderRepository.findById(noOfOrders);
        Assert.assertEquals(Integer.valueOf(noOfOrders),saveOrder.get().getId());
        Assert.assertEquals("Customer"+noOfOrders, saveOrder.get().getCustomerName());
    }

    @Test
    public void testProcessRefund() {
        int noOfOrders =123;
        String status = "Refund";
        Order order = testData.getOrderByStatus(noOfOrders, status);
        when(mockOrderRepository.findById(noOfOrders)).thenReturn(Optional.ofNullable(order));
        Optional<Order> saveOrder = mockOrderRepository.findById(noOfOrders);
        Assert.assertEquals(status+Integer.valueOf(noOfOrders),saveOrder.get().getStatus());

    }

    @Test
    public void testGetOrdersByDate() {
        int noOfOrders =222;
        String status = "Completed";
        List<Order> orderListByDate = testData.getOrders(noOfOrders);
        Order order = testData.getOrderByStatus(noOfOrders, status);
        Date startDate = testData.getStartDate();
        Date endDate = testData.getEndDate();
        when(mockOrderRepository.findByOrderDate(startDate,endDate,status )).thenReturn(orderListByDate);
        List<Order> orderList = mockOrderRepository.findByOrderDate(startDate,endDate,status );
        Assert.assertEquals(noOfOrders, orderList.size());
    }
}
