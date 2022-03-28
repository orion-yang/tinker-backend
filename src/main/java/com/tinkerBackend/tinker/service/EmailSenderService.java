package com.tinkerBackend.tinker.service;

import com.tinkerBackend.tinker.exception.UserNotFoundException;
import com.tinkerBackend.tinker.model.Admin;
import com.tinkerBackend.tinker.model.EmailResponse;
import com.tinkerBackend.tinker.model.User;
import com.tinkerBackend.tinker.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    private String emailFrom = "yangos0219@gmail.com";
    private EmailResponse emailResponse;
    private User user;
    private Admin admin;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    public EmailResponse sendResetPassword(VerificationToken token) {
        emailResponse = new EmailResponse();
        if(isExistingEmail(token.getEmail())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(token.getEmail());
            message.setSubject("Reset Password");
            message.setText("Enter the following verification code to reset your password: "
                    + token.getVerificationCode());

            javaMailSender.send(message);
            System.out.print("Mail Sent!");

            emailResponse.setMessage("Success");
        } else {
            System.out.print("Email does not exist");

            emailResponse.setMessage("Failed");
        }
        return emailResponse;
    }


    public EmailResponse sendOrderConfirmation(VerificationToken token) {
        emailResponse = new EmailResponse();
        if(isExistingEmail(token.getEmail())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(token.getEmail());
            message.setSubject("Confirmation Order");
            message.setText("Your order has been confirmed!");

            javaMailSender.send(message);
            System.out.print("Mail Sent!");

            emailResponse.setMessage("Success");
        } else {
            System.out.print("Email does not exist");

            emailResponse.setMessage("Failed");
        }
        return emailResponse;
    }

    public EmailResponse sendVerifyUser(VerificationToken token) {
        emailResponse = new EmailResponse();
        if(isExistingEmail(token.getEmail())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(token.getEmail());
            message.setSubject("Verify your Account");
            message.setText("Enter the following verification code to verify your new account: "
                    + token.getVerificationCode());

            javaMailSender.send(message);
            System.out.print("Mail Sent!");

            emailResponse.setMessage("Success");
        } else {
            System.out.print("Email does not exist");

            emailResponse.setMessage("Failed");
        }
        return emailResponse;
    }

    public EmailResponse sendConfirmationUser(User user) {
        emailResponse = new EmailResponse();
        if(isExistingEmail(user.getEmail())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(user.getEmail());
            message.setSubject("Your Profile Has Been Updated");
            message.setText("Hi, " + user.getFirstName() + "!" + "\n\n\nThis is to notify you that your Profile has been recently updated. " +
                    "If this was you, please ignore this message. Otherwise, login or use our Forgot Password feature to secure your account.");

            javaMailSender.send(message);
            System.out.print("Mail Sent!");

            emailResponse.setMessage("Success");
        } else {
            System.out.print("Email does not exist");

            emailResponse.setMessage("Failed");
        }
        return emailResponse;
    }

    public EmailResponse sendConfirmationAdmin(Admin admin) {
        emailResponse = new EmailResponse();
        if(isExistingEmail(admin.getEmail())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(admin.getEmail());
            message.setSubject("Your Profile Has Been Updated");
            message.setText("Hi, " + admin.getFirstName() + "!" + "\n\n\nThis is to notify you that your Profile has been recently updated. " +
                    "If this was you, please ignore this message. Otherwise, login or use our Forgot Password feature to secure your account.");

            javaMailSender.send(message);
            System.out.print("Mail Sent!");

            emailResponse.setMessage("Success");
        } else {
            System.out.print("Email does not exist");

            emailResponse.setMessage("Failed");
        }
        return emailResponse;
    }

    public EmailResponse sendAdminInfo(Admin admin) {
        emailResponse = new EmailResponse();
        if(isExistingEmail(admin.getEmail())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(admin.getEmail());
            message.setSubject("New Admin Account");
            message.setText("Hi, " + admin.getFirstName() + "!" + "\n\nAn Admin account has been assigned to you. " +
                    "Please use the following username and password to login: " +
                    "\n\n\n Username: " + admin.getUserName() + "\n Password: " + admin.getPassword()
                    + "\n\nWelcome to the team!");

            javaMailSender.send(message);
            System.out.print("Mail Sent!");

            emailResponse.setMessage("Success");
        } else {
            System.out.print("Email does not exist");

            emailResponse.setMessage("Failed");
        }
        return emailResponse;
    }

    public boolean isExistingEmail(String email) {
        try {
            User user = this.userService.findByEmail(email);
            this.user = user;
            return true;
        } catch (UserNotFoundException e) {

        }
        try {
            Admin admin = this.adminService.findByEmail(email);
            this.admin = admin;
            return true;
        } catch (UserNotFoundException e) {

        }
        return false;
    }
}
