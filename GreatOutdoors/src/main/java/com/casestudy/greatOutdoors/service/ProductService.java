package com.casestudy.greatOutdoors.service;


import com.casestudy.greatOutdoors.dao.ProductRepository;
import com.casestudy.greatOutdoors.entity.Order;
import com.casestudy.greatOutdoors.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepository;


    /**
     * It will get the product details by product id
     * @param product
     * @return product details
     */
    public Product getProductDetails(Product product) {
        logger.info("*** ProductService - getProductDetails ***");
       Product productDetails = productRepository.findById(product.getCode()).get();
       return productDetails;
    }

    /**
     * It will fetch all the products
     * @return  list of products
     */
    public List<Product> getAllProducts() {
        logger.info("*** ProductService - getAllProducts ***");
        List<Product> products = productRepository.findAll();
        return products;
    }

    /**
     * It will get the product by id
     * @param code
     * @return product details
     */
    public Product getProduct(String code) {
        logger.info("*** ProductService - getProduct ***");
        Product product = productRepository.findById(code).get();
        return product;
    }


    /**
     * It will update the inventory by using product and quantity
     * @param product
     * @param quantity
     */
    public void updateInventory(Product product, int quantity) {
        logger.info("*** ProductService - updateInventory ***");
        int inventory_quantity = product.getQuantity();
        int ordered_quantity = quantity;
        int updated_quantity = inventory_quantity - ordered_quantity;
        product.setQuantity(updated_quantity);
        productRepository.save(product);
    }


    /**
     * It will take the product and save the product
     * @param product
     */
    public void saveProduct(Product product) {
        logger.info("*** ProductService - saveProduct ***");
        productRepository.save(product);
    }
}


