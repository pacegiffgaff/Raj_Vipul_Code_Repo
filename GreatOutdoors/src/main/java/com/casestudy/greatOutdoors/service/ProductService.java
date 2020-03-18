package com.casestudy.greatOutdoors.service;


import com.casestudy.greatOutdoors.dao.ProductRepository;
import com.casestudy.greatOutdoors.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;



    public Product getProductDetails(Product product) {
       Product productDetails = productRepository.findById(product.getCode()).get();
       return productDetails;
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product getProduct(String code) {
        Product product = productRepository.findById(code).get();
        return product;
    }


    public void updateInventory(Product product, int quantity) {
        int inventory_quantity = product.getQuantity();
        int ordered_quantity = quantity;
        int updated_quantity = inventory_quantity - ordered_quantity;
        product.setQuantity(updated_quantity);
        productRepository.save(product);
    }


    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}


