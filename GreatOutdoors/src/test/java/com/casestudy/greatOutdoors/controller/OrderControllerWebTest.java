package com.casestudy.greatOutdoors.controller;

import com.casestudy.greatOutdoors.dao.ProductRepository;
import com.casestudy.greatOutdoors.entity.Product;
import com.casestudy.greatOutdoors.service.TestData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderControllerWebTest {

    private TestData testData = new TestData();

    @MockBean
    ProductRepository mockProductRepository;

    @Autowired
    OrderController orderController;

    @Test
    void testShowAvailableProducts() {
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
        assertEquals(prodReqListSize, returnProducts.size());
    }
}