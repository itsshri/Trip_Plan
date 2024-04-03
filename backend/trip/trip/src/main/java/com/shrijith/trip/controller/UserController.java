package com.shrijith.trip.controller;

import com.shrijith.trip.exception.UserNotFoundException;
import com.shrijith.trip.model.User;
import com.shrijith.trip.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

     @GetMapping("/user/{id}")
     User getUserById(@PathVariable Long id) {
         return userRepository.findById(id)
                 .orElseThrow(() -> new UserNotFoundException(id));
     }

     @PutMapping("/user/{id}")
     User updateUser(@RequestBody User newUser, @PathVariable Long id) {
         return userRepository.findById(id)
                 .map(user -> {
                     user.setUsername(newUser.getUsername());
                     user.setPassword(newUser.getPassword()); // Update password
                     user.setName(newUser.getName());
                     user.setEmail(newUser.getEmail());
                     user.setPhno(newUser.getPhno()); // Update phone number
                     user.setCity(newUser.getCity()); // Update city
                     user.setSex(newUser.getSex()); // Update sex
                     return userRepository.save(user);
                 }).orElseThrow(() -> new UserNotFoundException(id));
     }

     @DeleteMapping("/user/{id}")
     String deleteUser(@PathVariable Long id){
         if(!userRepository.existsById(id)){
             throw new UserNotFoundException(id);
         }
         userRepository.deleteById(id);
         return  "User with id "+id+" has been deleted success.";
     }
}
