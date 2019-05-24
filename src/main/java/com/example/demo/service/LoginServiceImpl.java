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

    @Autowired
    public LoginServiceImpl(final UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public User getLoginUser(User user) {
        userRepository.save(new User("a", "test1", "test"));
        userRepository.save(new User("b", "test2", "test"));
        userRepository.save(new User("c", "test3", "test"));
        userRepository.save(new User("d", "test4", "test"));
        productRepository.save(new Product("string1", "namn", "brand", "description", 100.0, 100.0));
        productRepository.save(new Product("string2", "namn", "brand", "description", 100.0, 100.0));
        productRepository.save(new Product("string3", "namn", "brand", "description", 100.0, 100.0));
        productRepository.save(new Product("string4", "namn", "brand", "description", 100.0, 100.0));
        productRepository.save(new Product("string5", "namn", "brand", "description", 100.0, 100.0));
        List<User> users = getAllusers();
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())
                    && u.getPassword().equals(user.getPassword())) {
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
        else{
            user.setRoles();
            userRepository.save(user);
            return user;
        }
        return new User();

    }
}
