package com.rohit.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohit.models.User;
import com.rohit.repositories.UserRepository;
import com.rohit.response_wrapper.MyResponseWrapper;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyResponseWrapper myResponseWrapper;

    public ResponseEntity<?> addUser(User user) {
        User savedUser = userRepository.save(user);
        myResponseWrapper.setMessage("User added successfully");
        myResponseWrapper.setData(savedUser);
        return new ResponseEntity<>(myResponseWrapper, HttpStatus.CREATED);
    }

    
    public ResponseEntity<?> getAllUsers() {
        myResponseWrapper.setMessage("User list fetched successfully");
        myResponseWrapper.setData(userRepository.findAll());
        return new ResponseEntity<>(myResponseWrapper, HttpStatus.OK);
    }

    public ResponseEntity<?> getUserById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            myResponseWrapper.setMessage("User found");
            myResponseWrapper.setData(userOpt.get());
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.OK);
        } else {
            myResponseWrapper.setMessage("User with id " + id + " does not exist");
            myResponseWrapper.setData(null);
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            myResponseWrapper.setMessage("User deleted successfully");
            myResponseWrapper.setData(null);
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.OK);
        } else {
            myResponseWrapper.setMessage("User with id " + id + " does not exist");
            myResponseWrapper.setData(null);
            return new ResponseEntity<>(myResponseWrapper, HttpStatus.NOT_FOUND);
        }
    }
}
