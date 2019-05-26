/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;


import com.example.demo.domain.Cart;
import com.example.demo.domain.CartProduct;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.repository.CartProductRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    
    @Inject
    private LoginServiceImpl loginServiceImpl;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductByProductId(long productId) {
        List<Product> products = productRepository.findAll();
        for(Product p : products){
            if(p.getId() == productId){
                return p;
            }
        }
        return null;
    }

    @Override
    public Product addToCart(Product product) {
        User user = loginServiceImpl.getUser();
        Cart cart = new Cart();
        cart.setUser(user);
        CartProduct cp = new CartProduct(product,cart,1);
        cartRepository.save(cart);
        cartProductRepository.save(cp);
        return product;
    }

}
