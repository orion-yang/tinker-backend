package com.tinkerBackend.tinker.service;

import com.tinkerBackend.tinker.exception.UserNotFoundException;
import com.tinkerBackend.tinker.exception.WrongPasswordException;
import com.tinkerBackend.tinker.model.Admin;
import com.tinkerBackend.tinker.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class AdminService {

    private final AdminRepo adminRepo;
    private final PasswordSecurity passwordSecurity;

    @Autowired
    public AdminService(AdminRepo adminRepo, PasswordSecurity passwordSecurity) {
        this.adminRepo = adminRepo;
        this.passwordSecurity = passwordSecurity;
    }

    public Admin addAdmin(Admin admin) {
        String encodedPassword = this.passwordSecurity.encodePassword(admin.getPassword());
        admin.setPassword(encodedPassword);
        return adminRepo.save(admin);
    }


    public Admin findByUserName(String userName) {
        return adminRepo.findByUserName(userName).orElseThrow(() -> new UserNotFoundException("User by username " + userName + " was not found"));
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    public Admin login(Admin admin) throws WrongPasswordException {
        Admin foundAdmin = findByUserName(admin.getUserName());
        String decodedPassword = this.passwordSecurity.decodePassword(foundAdmin.getPassword());
        if(admin.getPassword().equals(decodedPassword)) {
            return foundAdmin;
        } else {
            throw new WrongPasswordException("Incorrect Password");
        }
    }

    public Admin verifyPassword(Admin admin) {
        Admin foundAdmin = findByUserName(admin.getUserName());
        String decodedPassword = this.passwordSecurity.decodePassword(foundAdmin.getPassword());
        if(admin.getPassword().equals(decodedPassword)) {
            return foundAdmin;
        } else {
            throw new WrongPasswordException("Incorrect Password");
        }
    }

    public Admin findByEmail(String email) {
        return this.adminRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Invalid admin email"));
    }

    public Admin updatePassword(Admin admin) {
        String encodedPassword = this.passwordSecurity.encodePassword(admin.getPassword());
        admin.setPassword(encodedPassword);

        return adminRepo.save(admin);
    }


}
