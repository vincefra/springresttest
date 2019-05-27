package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;
    
    private User user;

    @Autowired
    public LoginServiceImpl(final UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public User getLoginUser(User user) {

        List<User> users = getAllusers();
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())
                    && u.getPassword().equals(user.getPassword())) {
                setUser(u);
                return u;
            }
        }
        return new User();
    }

    @Override
    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(User user) {
        
        List<User> users = getAllusers();
        // if-satsen ska tas bort när vi skapat databasen
        if(users.size() > 0){
        for (User u : users) {
            if (!(user.getUsername().equalsIgnoreCase(u.getUsername()))) {
                user.setRoles();
                userRepository.save(user);
                return user;
                }
            }
        }
        // Detta ska vi ta bort när vi har skapat databasen
        else {
            user.setRoles();
            userRepository.save(user);
            return user;
        }
        
        return new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
