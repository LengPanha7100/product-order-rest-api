package com.example.demospring.productorderrestapi.service.serviceImp;

import com.example.demospring.productorderrestapi.exception.NotFoundException;
import com.example.demospring.productorderrestapi.model.Email;
import com.example.demospring.productorderrestapi.model.request.EmailRequest;
import com.example.demospring.productorderrestapi.repository.EmailRepository;
import com.example.demospring.productorderrestapi.service.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImp implements EmailService {
    private final EmailRepository emailRepository;

    public EmailServiceImp(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public List<Email> getAllEmail() {
        return emailRepository.getAllEmail();
    }

    @Override
    public Email getEmailById(Long id) {
        Email email = emailRepository.getEmailById(id);
        if(email == null){
            throw new NotFoundException("Update email by id "+ id + " not found!");
        }
        return email ;
    }

    @Override
    public Email createEmail(EmailRequest emailRequest) {
        return emailRepository.createEmail(emailRequest);
    }

    @Override
    public Email updateEmailById(Long id, EmailRequest emailRequest) {
        getEmailById(id);
        return emailRepository.updateEmailById(id,emailRequest);
    }

    @Override
    public void deleteEmailById(Long id) {
        getEmailById(id);
        emailRepository.deleteEmail(id);
    }
}
