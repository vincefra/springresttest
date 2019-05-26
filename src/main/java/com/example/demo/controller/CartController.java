package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addToCart")
final class CartController {

    private final ProductService productService;

    @Autowired
    public CartController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    ResponseEntity<Product> postResult(@RequestBody Product product) {
        System.out.println(product.getName());
        return ResponseEntity.ok(productService.addToCart(product));
    }

}
