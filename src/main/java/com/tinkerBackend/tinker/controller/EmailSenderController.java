package com.tinkerBackend.tinker.controller;

import com.tinkerBackend.tinker.model.Admin;
import com.tinkerBackend.tinker.model.EmailResponse;
import com.tinkerBackend.tinker.model.User;
import com.tinkerBackend.tinker.model.VerificationToken;
import com.tinkerBackend.tinker.service.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailSenderController {

    private final EmailSenderService emailSenderService;


    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send/reset-password")
    public ResponseEntity<EmailResponse> sendResetPassword(@RequestBody VerificationToken token) {
        System.out.println(token.getVerificationCode());
        EmailResponse emailResponse = this.emailSenderService.sendResetPassword(token);
        return new ResponseEntity<>(emailResponse, HttpStatus.OK);
    }

    @PostMapping("/send/order-confirmation")
    public ResponseEntity<EmailResponse> sendOrderConfirmation(@RequestBody VerificationToken token) {
        EmailResponse emailResponse = this.emailSenderService.sendOrderConfirmation(token);
        return new ResponseEntity<>(emailResponse, HttpStatus.OK);
    }

    @PostMapping("/send/verify-user")
    public ResponseEntity<EmailResponse> sendVerifyUser(@RequestBody VerificationToken token) {
        System.out.println(token.getVerificationCode());
        EmailResponse emailResponse = this.emailSenderService.sendVerifyUser(token);
        return new ResponseEntity<>(emailResponse, HttpStatus.OK);
    }

    @PostMapping("/send/confirm-user")
    public ResponseEntity<EmailResponse> sendConfirmUser(@RequestBody User user) {
        EmailResponse emailResponse = this.emailSenderService.sendConfirmationUser(user);
        return new ResponseEntity<>(emailResponse, HttpStatus.OK);
    }

    @PostMapping("/send/confirm-admin")
    public ResponseEntity<EmailResponse> sendConfirmAdmin(@RequestBody Admin admin) {
        EmailResponse emailResponse = this.emailSenderService.sendConfirmationAdmin(admin);
        return new ResponseEntity<>(emailResponse, HttpStatus.OK);
    }

    @PostMapping("/send/admin")
    public ResponseEntity<EmailResponse> sendAdminInfo(@RequestBody Admin admin) {
        EmailResponse emailResponse = this.emailSenderService.sendAdminInfo(admin);
        return new ResponseEntity<>(emailResponse, HttpStatus.OK);
    }


}