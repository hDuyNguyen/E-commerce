package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateProductRequest;
import com.project.mdyshop.dto.request.UpdateProductRequest;
import com.project.mdyshop.dto.request.UpdateProductStatusRequest;
import com.project.mdyshop.exception.ProductException;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;

import javax.sound.sampled.Port;
import java.util.List;

public interface ProductService {

    Product createProduct(CreateProductRequest request, Shop shop);

    Product updateProduct(UpdateProductRequest request, Long productId) throws ProductException;

    Product deleteProduct(Long productId) throws ProductException;

    Product findProductById(Long productId) throws ProductException;

    List<Product> getAllProduct();

    List<Product> getAllProductFromShop(Long shopId) throws ProductException;

    List<Product> getAllAvailableProduct();

   List<Product> findProductByCategory(Long categoryId);

    List<Product> findProductByNameAndStatus(Long shopId, String name, String status);

    List<Product> findProductFromShop(Long shopId) throws ProductException;

    List<Product> filterProducts(Long categoryId, Double ratingValue);

    List<Product> filterProducts1(Long categoryId);

    void confirmProduct(Long productId) throws ProductException;

    void deniedProduct(Long productId) throws ProductException;

    List<Product> findProductByCategoryShop(Long categoryShopId);

    List<Product> userFindProduct(Long shopId);

    List<Product> findProductByNameAndShopId(String name, Long shopId);

//    List<Product> findProductByTag(String tag);

    Product updateProductStatus (UpdateProductStatusRequest request, Long productId);

    List<Product> getAllProductByName(String name);

    List<Product> getAllProductByCategory(Long categoryId);

}
