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
        //System.out.println("Context:"+context.getBean(OrderRepository.class));
        List<Order> orderHistoryList = mockOrderRepository.findByCustomerName(customerName);
        System.out.println("***** orderHistoryList: " + orderHistoryList.size());
        for (int i = 0; i < orderHistoryList.size(); i++) {
            Order order = orderHistoryList.get(i);
            Assert.assertEquals(customerName+i, order.getCustomerName());
            Assert.assertEquals(Integer.valueOf(i), order.getId());
        }
    }
    @Test
    public void testNewOrder() {
        int i =202;
        Order order = testData.getOrder(i);
        when(mockOrderRepository.save(order)).thenReturn(order);
        Order  saveOrder = (Order)mockOrderRepository.save(order);
        System.out.println("***** Order Id: "+saveOrder.getId());
        Assert.assertEquals(Integer.valueOf(i),order.getId());

    }

    @Test
    public void testCreateOrderStatus() {
        int i =303;
        Order order = testData.getOrder(i);
        OrderStatus orderStatus = testData.getOrderStatus(i);
        when(mockOrderRepository.save(orderStatus.getOrder())).thenReturn(order);
        Order  saveOrder = (Order)mockOrderRepository.save(orderStatus.getOrder());
        System.out.println("***** Created Order Status: "+saveOrder.getStatus());
        Assert.assertEquals("Initiated303",saveOrder.getStatus());

    }

    @Test
    public void testPlaceOrder() {
        int i =111;
        Order order = testData.getOrder(i);
        when(mockOrderRepository.save(order)).thenReturn(order);
        Order  saveOrder = (Order)mockOrderRepository.save(order);
        System.out.println("***** PlaceOrder Id: "+saveOrder.getId());
        Assert.assertEquals(Integer.valueOf(i),order.getId());
        Assert.assertEquals("Customer"+i, order.getCustomerName());
    }

    @Test
    public void testGetOrder() {
        int i =123;
        Order order = testData.getOrder(i);
        when(mockOrderRepository.findById(i)).thenReturn(Optional.ofNullable(order));
        Optional<Order> saveOrder = mockOrderRepository.findById(i);
        System.out.println("*****  Get Order Id: "+saveOrder.get().getId());
        Assert.assertEquals(Integer.valueOf(i),saveOrder.get().getId());
        Assert.assertEquals("Customer"+i, saveOrder.get().getCustomerName());
    }

    @Test
    public void testProcessRefund() {
        int i =123;
        String status = "Refund";
        Order order = testData.getOrderByStatus(i, status);
        when(mockOrderRepository.findById(i)).thenReturn(Optional.ofNullable(order));
        Optional<Order> saveOrder = mockOrderRepository.findById(i);
        System.out.println("*****  Get Order Status: "+saveOrder.get().getStatus());
        Assert.assertEquals(status+Integer.valueOf(i),saveOrder.get().getStatus());
        Assert.assertEquals("Customer"+i, saveOrder.get().getCustomerName());
    }
}
