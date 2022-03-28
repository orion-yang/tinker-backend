package com.tinkerBackend.tinker.controller;

import com.tinkerBackend.tinker.model.User;
import com.tinkerBackend.tinker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PutMapping("/update/password")
    public ResponseEntity<User> updatePassword(@RequestBody User user) {
        User updateUser = userService.updatePassword(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/{userName}")
    public ResponseEntity<User> findByUserName(@PathVariable("userName") String userName) {
        User user = userService.findByUserName(userName);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserByFirstName(@RequestBody String firstName) {
        userService.deleteUserByFirstName(firstName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User authenticatedUser = userService.login(user);
        return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
    }

    @PostMapping("/verify-password")
    public ResponseEntity<User> verifyPassword(@RequestBody User user) {
        User tempUserPassword = userService.verifyPassword(user);
        return new ResponseEntity<>(tempUserPassword, HttpStatus.OK);
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
        User user = userService.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
