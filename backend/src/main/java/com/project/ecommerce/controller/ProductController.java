package com.project.ecommerce.controller;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("/")
    public ResponseEntity<Page<Product>> findProductCategoryHandler(@RequestParam String category, @RequestParam List<String> color,
                                                                    @RequestParam List<String> size, @RequestParam Integer minPrice,
                                                                    @RequestParam Integer maxPrice, @RequestParam Integer minDiscount,
                                                                    @RequestParam String sort, @RequestParam String stock, @RequestParam Integer pageNumber,
                                                                    @RequestParam Integer pageSize) {
        Page<Product> res = productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount,
                sort, stock, pageNumber, pageSize);

        System.out.println("product completed");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {
        Product product = productService.findProductById(productId);

        return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductHandler(@RequestParam String q) {

        List<Product> list = new ArrayList<>();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
