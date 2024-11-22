package com.example.demospring.productorderrestapi.controller;

import com.example.demospring.productorderrestapi.model.Email;
import com.example.demospring.productorderrestapi.model.request.EmailRequest;
import com.example.demospring.productorderrestapi.model.response.APIResponse;
import com.example.demospring.productorderrestapi.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Email>>> getAllEmail() {
        List<Email> emails = emailService.getAllEmail();
        APIResponse<List<Email>> apiResponse = APIResponse.<List<Email>>builder()
                .message("Get all email successfully!")
                .status(HttpStatus.OK)
                .payload(emails)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Email>> getEmailById(@PathVariable Long id) {
        Email emails = emailService.getEmailById(id);
        APIResponse<Email> apiResponse = APIResponse.<Email>builder()
                .message("Get email by id successfully!")
                .status(HttpStatus.OK)
                .payload(emails)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Email>> createEmail(@RequestBody EmailRequest emailRequest) {
        Email emails = emailService.createEmail(emailRequest);
        APIResponse<Email> apiResponse = APIResponse.<Email>builder()
                .message("Created email successfully!")
                .status(HttpStatus.CREATED)
                .payload(emails)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Email>> updateEmailById(@PathVariable Long id,@RequestBody EmailRequest emailRequest) {
        Email emails = emailService.updateEmailById(id,emailRequest);
        APIResponse<Email> apiResponse = APIResponse.<Email>builder()
                .message("Updated email by id successfully!")
                .status(HttpStatus.OK)
                .payload(emails)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Email>> deleteEmailById(@PathVariable Long id) {
        emailService.deleteEmailById(id);
        APIResponse<Email> apiResponse = APIResponse.<Email>builder()
                .message("Updated email by id successfully!")
                .status(HttpStatus.OK)
                .payload(null)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }



}
