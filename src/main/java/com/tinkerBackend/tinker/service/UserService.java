package com.tinkerBackend.tinker.service;


import com.tinkerBackend.tinker.exception.UserNotFoundException;
import com.tinkerBackend.tinker.exception.WrongPasswordException;
import com.tinkerBackend.tinker.model.User;
import com.tinkerBackend.tinker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;
    private final PasswordSecurity passwordSecurity;

    @Autowired
    public UserService(UserRepo userRepo, PasswordSecurity passwordSecurity) {

        this.userRepo = userRepo;
        this.passwordSecurity = passwordSecurity;
    }

    public User addUser(User user) {
        String encodedPassword = this.passwordSecurity.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);

        User newUser = userRepo.save(user);
        return newUser;
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public User updatePassword(User user) {
        String encodedPassword = this.passwordSecurity.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public User findByUserName(String userName) {
        return userRepo.findByUserName(userName).orElseThrow(() -> new UserNotFoundException("User by username " + userName + " was not found"));
    }

    public void deleteUserByFirstName(String firstName) {
        userRepo.deleteUserByFirstName(firstName);
    }

    public User login(User user) throws WrongPasswordException {
        User foundUser = findByUserName(user.getUserName());
        String decodedPassword = this.passwordSecurity.decodePassword(foundUser.getPassword());
        if(user.getPassword().equals(decodedPassword)) {
            return foundUser;
        } else {
            throw new WrongPasswordException("Incorrect Password");
        }
    }

    public User verifyPassword(User user) {
        User foundUser = findByUserName(user.getUserName());
        String decodedPassword = this.passwordSecurity.decodePassword(foundUser.getPassword());
        if(user.getPassword().equals(decodedPassword)) {
            return foundUser;
        } else {
            throw new WrongPasswordException("Incorrect Password");
        }
    }

    public User findByEmail(String email) {
        return this.userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Invalid user email"));
    }

}
