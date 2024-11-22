package com.example.demospring.productorderrestapi.service;

import com.example.demospring.productorderrestapi.model.Email;
import com.example.demospring.productorderrestapi.model.request.EmailRequest;

import java.util.List;

public interface EmailService {
    List<Email> getAllEmail();

    Email getEmailById(Long id);

    Email createEmail(EmailRequest emailRequest);

    Email updateEmailById(Long id, EmailRequest emailRequest);

    void deleteEmailById(Long id);
}
