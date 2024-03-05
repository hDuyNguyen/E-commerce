package com.project.ecommerce.serivce.imp;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.model.Category;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.request.CreateProductRequest;
import com.project.ecommerce.serivce.ProductService;
import com.project.ecommerce.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserService userService;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Product createProduct(CreateProductRequest req) {

        Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());

        if (topLevel == null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel=categoryRepository.save(topLevelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());

        if (secondLevel == null) {
            Category topSecondCategory = new Category();
            topSecondCategory.setName(req.getSecondLevelCategory());
            topSecondCategory.setLevel(2);

            secondLevel=categoryRepository.save(topSecondCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(), secondLevel.getName());

        if (thirdLevel == null) {
            Category topThirdCategory = new Category();
            topThirdCategory.setName(req.getSecondLevelCategory());
            topThirdCategory.setLevel(3);

            thirdLevel=categoryRepository.save(topThirdCategory);
        }

        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setDescription(req.getDescription());
        product.setPrice(req.getPrice());
        product.setDiscounted_price(req.getDiscountedPrice());
        product.setDiscounted_percent(req.getDiscountPercent());
        product.setQuantity(req.getQuantity());
        product.setBrand(req.getBrand());
        product.setColor(req.getColor());
        product.setSizes(req.getSize());
        product.setImageUrl(req.getImageUrl());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {

        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);

        return "Delete success";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product = findProductById(productId);

        if (req.getQuantity() != 0 ) {
            product.setQuantity(req.getQuantity());
        }

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {

        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ProductException("Product not found with id - " + id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {



        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice,
                                       Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProduct(category, minPrice, maxPrice, minDiscount, sort);

        if (!colors.isEmpty()) {
            products = products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }

        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream().filter(p->p.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {
                products = products.stream().filter(p->p.getQuantity() < 1).collect(Collectors.toList());
            }
        }

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);
        Page<Product> filteredProduct = new PageImpl<>(pageContent, pageable, products.size());

        return filteredProduct;
    }

    @Override
    public List<Product> findALlProduct() {
        List<Product> products = productRepository.findAll();
        return products;
    }
}
