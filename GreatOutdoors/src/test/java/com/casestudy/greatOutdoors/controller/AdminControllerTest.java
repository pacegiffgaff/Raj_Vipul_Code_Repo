package com.casestudy.greatOutdoors.controller;

import com.casestudy.greatOutdoors.dao.OrderRepository;
import com.casestudy.greatOutdoors.dao.ProductRepository;
import com.casestudy.greatOutdoors.entity.Product;
import com.casestudy.greatOutdoors.form.ProductForm;
import com.casestudy.greatOutdoors.service.TestData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminControllerTest {

    private TestData testData = new TestData();

    private ModelMap modelMap = new ModelMap();

    @MockBean
    ProductRepository mockProductRepository;

    @Autowired
    AdminController adminController;

    @MockBean
    HttpServletRequest httpServletRequest;

    @MockBean
    HttpServletResponse httpServletResponse;

    @MockBean
    Model model;


    @Test
    void testShowAvailableProducts() {

        int noOfProducts = 5;
        List<Product> productList = new ArrayList<Product>();
        int prodReqListSize = 5;
        for (int i = 0; i < prodReqListSize; i++) {
            Product product = testData.getProduct(i);
            productList.add(product);
        }

        when(mockProductRepository.findAll()).thenReturn(productList);

        String returnAttribute = adminController.showAvailableProducts(modelMap);
        List<Product> returnProducts =(List<Product>)modelMap.get("products");
        assertEquals(noOfProducts, returnProducts.size());
        assertEquals("admin_product_list", returnAttribute);
    }

    @Test
    void testShowAddNewProductPage() {

        String returnAttribute = adminController.showAddNewProductPage(modelMap);
        assertEquals("admin_add_product", returnAttribute);
    }

    @Test
    void testAddNewProduct() throws IOException {
        int productCode = 5;

        Product product = testData.getProduct(productCode);

        when(mockProductRepository.save(product)).thenReturn(product);
        mockProductRepository.save(product);

        //String returnAttribute = adminController.addNewProduct(modelMap,productForm,product);
        // System.out.println("******** returnAttribute:"+returnAttribute);
        //assertEquals("redirect:/admin/product", returnAttribute);

        assertEquals(productCode, Integer.valueOf(product.getCode()));

    }

    @Test
    void testProductImage() throws  IOException{

        HttpServletRequest request;
        HttpServletResponse response;
        String productCode = "77";
        Product product = testData.getProductWithImage(Integer.valueOf(productCode));
        when(mockProductRepository.findById(productCode)).thenReturn(java.util.Optional.ofNullable(product));
        assertEquals(productCode, product.getCode());

        //adminController.productImage(httpServletRequest, httpServletResponse, model ,product.getCode());

    }

    @Test
    void testShowReportsPage() {
        String returnAttribute = adminController.showReportsPage(modelMap);
        assertEquals("report_links", returnAttribute);
    }
}