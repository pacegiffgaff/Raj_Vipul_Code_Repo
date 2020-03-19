package com.casestudy.greatOutdoors.service;

import com.casestudy.greatOutdoors.dao.*;
import com.casestudy.greatOutdoors.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

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

    @Test
    public void ProductServiceRepoTest() {
        //fail("Not yet implemented");
        System.out.println(" ***** ProdServiceRepoTest *****");
    }

    @Test
    public void testGetAllProducts() {

        List<Product> productList = testData.getProductList(10);
        when(mockProductRepository.findAll()).thenReturn(productList);
        List<Product> returnProductList = mockProductRepository.findAll();
        System.out.println("++++++++ testGetAllProducts returnProductList size"+returnProductList.size());
        assertEquals(10, returnProductList.size());
    }

    @Test
    public void testGetProductDetails(){
        String code = "7";
        Product productDetails = testData.getProduct(Integer.valueOf(code));
        when(mockProductRepository.findById(productDetails.getCode())).thenReturn(Optional.ofNullable(productDetails));

        Optional<Product> returnProductDetails = mockProductRepository.findById(productDetails.getCode());
        System.out.println("++++++++ testGetProductDetails returnProductDetails Code "+returnProductDetails.get().getCode());
        assertEquals("7", returnProductDetails.get().getCode());
    }

    @Test
    public void testGetProductByCode(){
        String code = "101";
        Product product = testData.getProduct(Integer.valueOf(code));
        when(mockProductRepository.findById(code)).thenReturn(Optional.ofNullable(product));

        Optional<Product> returnProduct= mockProductRepository.findById(code);
        System.out.println("++++++++ testGetProductByCode returnProduct Code "+returnProduct.get().getCode());
        assertEquals("101", returnProduct.get().getCode());
    }

}
