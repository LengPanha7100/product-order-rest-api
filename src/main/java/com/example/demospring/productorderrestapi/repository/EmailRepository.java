package com.example.demospring.productorderrestapi.repository;

import com.example.demospring.productorderrestapi.model.Email;
import com.example.demospring.productorderrestapi.model.request.EmailRequest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmailRepository {

    @Select("""
    SELECT *FROM email;
    """)
    List<Email> getAllEmail();

    @Select("""
    INSERT INTO email(email)
    VALUES (#{email})
    returning *;
    """)
    Email createEmail(EmailRequest emailRequest);

    @Select("""
    SELECT *FROM email WHERE id = #{id};
    """)
    Email getEmailById(Long id);

    @Select("""
    UPDATE email
    SET email = #{email.email}
    WHERE id = #{id}
    returning * ;
    """)
    Email updateEmailById(Long id, @Param("email") EmailRequest emailRequest);

    @Delete("""
    DELETE FROM email WHERE  id = #{id} ;
    """)
    void deleteEmail(Long id);

    @Select("""
    SELECT *FROM email WHERE email = #{email};
    """)
    Email getEmailByName(String email);
}
