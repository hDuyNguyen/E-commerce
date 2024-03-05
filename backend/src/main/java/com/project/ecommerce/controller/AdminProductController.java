package com.project.ecommerce.controller;

import com.project.ecommerce.exeption.OrderException;
import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.request.CreateProductRequest;
import com.project.ecommerce.response.ApiResponse;
import com.project.ecommerce.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req) {

        Product product = productService.createProduct(req);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {
        productService.deleteProduct(productId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Product deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = productService.findALlProduct();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product req ,@PathVariable Long productId) throws ProductException {

        Product product = productService.updateProduct(productId, req);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] requests) {

        for (CreateProductRequest product:requests) {
            productService.createProduct(product);
        }

        ApiResponse response = new ApiResponse();
        response.setMessage("Product create successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
