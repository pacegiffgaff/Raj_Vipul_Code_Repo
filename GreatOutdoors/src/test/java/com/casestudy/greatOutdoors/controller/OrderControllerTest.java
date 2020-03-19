package com.casestudy.greatOutdoors.controller;

import com.casestudy.greatOutdoors.dao.*;
import com.casestudy.greatOutdoors.entity.Order;
import com.casestudy.greatOutdoors.entity.Product;
import com.casestudy.greatOutdoors.service.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;
import org.springframework.ui.ModelMapExtensionsKt;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    private TestData testData = new TestData();

    RedirectAttributes flashAttributes=Mockito.mock(RedirectAttributes.class);

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
    OrderController orderController;

    @Test
    public void testShowAvailableProducts() throws Exception {

        List<Product> productList = new ArrayList<Product>();
        int prodReqListSize = 5;
        for (int i = 0; i < prodReqListSize; i++) {
            Product product = testData.getProduct(i);
            productList.add(product);
        }
        when(mockProductRepository.findAll()).thenReturn(productList);
        ModelMap modelMap = new ModelMap();
        orderController.showAvailableProducts(modelMap);
        List<Product> returnProducts =(List<Product>)modelMap.get("products");
        System.out.println("******** size:"+returnProducts.size());
        assertEquals(prodReqListSize, returnProducts.size());
    }
     @Test
    public void testInitiateOrder() throws Exception {
        String code = "12";
        Product product = testData.getProduct(Integer.valueOf(code));
        Order order = testData.getOrder(12);
        when(mockProductRepository.findById(code)).thenReturn(java.util.Optional.ofNullable(product));
        when(mockOrderRepository.save(order)).thenReturn(order);
        ModelMap modelMap = new ModelMap();
        String returnAttribute = orderController.initiateOrder(code, order,modelMap);
        Order returnOrder = (Order) modelMap.get("order");
        assertEquals(code, returnOrder.getProduct().getCode());
        assertEquals("customer_shipping_details", returnAttribute);
    }

    @Test
    public void testPlaceOrder() throws Exception {
        String code = "22";
       // Product product = testData.getProduct(Integer.valueOf(code));
        Order order = testData.getOrder(22);
        when(mockProductRepository.findById(code)).thenReturn(java.util.Optional.ofNullable(order.getProduct()));
        when(mockOrderRepository.save(order)).thenReturn(order);
        ModelMap modelMap = new ModelMap();
        String returnAttribute = orderController.placeOrder(modelMap, order, code);
        when(mockProductRepository.save(order.getProduct())).thenReturn(order.getProduct());
        Order returnOrder = (Order) modelMap.get("completedOrder");
        assertEquals(code, returnOrder.getProduct().getCode());
        assertEquals("success", returnAttribute);
    }

    @Test
    public void testOrderStatus() throws Exception {
        String customerName = "test";
        int noOfOrders = 5;
        List<Order> orderList = testData.getOrders(noOfOrders);

        when(mockOrderRepository.findByCustomerName(customerName)).thenReturn(orderList);
        ModelMap modelMap = new ModelMap();
        String returnAttribute = orderController.orderStatus(modelMap);
        List<Order> orderHistoryList = (List<Order>) modelMap.get("orderHistory");
        assertEquals(noOfOrders, orderHistoryList.size());
        assertEquals("order_history", returnAttribute);
    }


    @Test
    public void testRefundOrder() throws Exception {
        int orderId = 55;
        Order order = testData.getOrder(orderId);
        when(mockOrderRepository.findById(orderId)).thenReturn(java.util.Optional.ofNullable(order));
        when(mockOrderRepository.save(order)).thenReturn(order);

        ModelMap modelMap = new ModelMap();
        String returnAttribute = orderController.refundOrder(modelMap, String.valueOf(orderId), flashAttributes);
         assertEquals(orderId, order.getId());
         assertEquals("redirect:/order_status", returnAttribute);
    }
}
