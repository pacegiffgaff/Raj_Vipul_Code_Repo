package com.casestudy.greatOutdoors.controller;


import com.casestudy.greatOutdoors.GreatOutdoorsApplication;
import com.casestudy.greatOutdoors.dao.ProductRepository;
import com.casestudy.greatOutdoors.entity.Product;
import com.casestudy.greatOutdoors.form.ProductForm;
import com.casestudy.greatOutdoors.service.ProductService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    private static final Logger logger = LogManager.getLogger(AdminController.class);
    //static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    ProductService productService;

    /**
     * @param model
     * @return product list
     */
    @RequestMapping(value ="/admin/product", method = RequestMethod.GET)
    public String showAvailableProducts(ModelMap model){
        logger.info("*** AdminController showAvailableProducts ***");
        List<Product> products = productService.getAllProducts();
        model.put("products",products);
        return "admin_product_list";
    }

    /**
     * @param model
     * @return product form data
     */
    @RequestMapping(value = "/admin/new_product", method = RequestMethod.GET)
    public String showAddNewProductPage(ModelMap model) {
        logger.info("*** AdminController showAddNewProductPage ***");
        model.addAttribute("productForm", new ProductForm("000", "Default"));
        return "admin_add_product";
    }

    /**
     * @param model
     * @param productForm
     * @param product
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/admin/new_product", method = RequestMethod.POST)
    public String addNewProduct(ModelMap model, ProductForm productForm, Product product) throws IOException {
        logger.info("*** AdminController addNewProduct ***");
       // Product product = new Product();
        product.setCode(productForm.getCode());
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        byte[] image = productForm.getFileData().getBytes();
        product.setImage(image);
        product.setCreateDate(new Date());
        product.setQuantity(productForm.getQuantity());
        productService.saveProduct(product);
        return "redirect:/admin/product";
    }

    /**
     * @param request
     * @param response
     * @param model
     * @param code
     * @throws IOException
     */
    @RequestMapping(value = { "/get_image" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam("code") String code) throws IOException {
        logger.info("*** AdminController productImage ***");
        Product product = null;
        if (code != null) {
            product = productService.getProduct(code);
        }
        if (product != null && product.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(product.getImage());
        }
        response.getOutputStream().close();
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value ="/admin/report", method = RequestMethod.GET)
    public String showReportsPage(ModelMap model){
        logger.info("*** AdminController showReportsPage ***");
        return "report_links";
    }



}


