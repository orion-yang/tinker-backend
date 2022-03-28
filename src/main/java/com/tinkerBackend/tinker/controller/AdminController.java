package com.tinkerBackend.tinker.controller;

import com.tinkerBackend.tinker.model.Admin;
import com.tinkerBackend.tinker.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin newAdmin = adminService.addAdmin(admin);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/search/{userName}")
    public ResponseEntity<Admin> findByUserName (@PathVariable("userName") String userName) {
        Admin admin = adminService.findByUserName(userName);

        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        Admin updateAdmin = adminService.updateAdmin(admin);
        return new ResponseEntity<>(updateAdmin, HttpStatus.OK);
    }

    @PutMapping("/update/password")
    public ResponseEntity<Admin> updatePassword(@RequestBody Admin admin) {
        Admin updateAdmin = adminService.updatePassword(admin);
        return new ResponseEntity<>(updateAdmin, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody Admin admin) {
        Admin authenticatedAdmin = adminService.login(admin);
        return new ResponseEntity<>(authenticatedAdmin, HttpStatus.OK);
    }

    @PostMapping("/verify-password")
    public ResponseEntity<Admin> verifyPassword(@RequestBody Admin admin) {
        Admin tempAdminPassword = adminService.verifyPassword(admin);
        return new ResponseEntity<>(tempAdminPassword, HttpStatus.OK);
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<Admin> findByEmail(@PathVariable("email") String email) {
        Admin admin = adminService.findByEmail(email);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
}
